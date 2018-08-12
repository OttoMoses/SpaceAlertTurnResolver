package com.example.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.Section;
import com.example.otto.spacealertresolver.Threats.ThreatInternal;

public class ActionInternalLeakPower extends ActionEffectInternal
{
    private String condition;
    private String target;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        String message = "";
        switch (target)
        {
            case "centralReactor" :
            {
                message += LeakPower(threat,ship[1][1]) + "\n";
                break;
            }
            case "allReactors" :
            {
                message+= LeakPower(threat,ship[0][1]) + "\n";
                message+= LeakPower(threat,ship[1][1]) + "\n";
                message+= LeakPower(threat,ship[2][1]) + "\n";
                break;
            }
            case "centralShield" :
            {
                message += LeakPower(threat,ship[1][0]) + "\n";
                break;
            }
        }
        return message;
    }

    private String LeakPower(ThreatInternal threat,Section location)
    {
        String message = "";
        switch (condition)
        {
            case "transfer" :
            {
                int power = location.powerCubes;
                int transferValue = location.getMaxPower() - location.powerCubes;
                if(power <= transferValue)
                {
                    location.powerCubes += power;
                    message += "The " + threat.name + " transfer " + power + " power to the Heavy Shield in the upper white section!";
                }
                else
                {
                    int damage = power - transferValue;
                    location.powerCubes += transferValue;
                    message += "The " + threat.name + " transfer " + transferValue + " power to the" + location.BSystem.name + " in the " + location.sectionName + " " + location.zoneName + " section!\n";
                    message += "The " + threat.name + MainActivity.game.ShipDamage(location.zonePos,damage,false,true,true);
                }
                break;
            }
            case "leak" :
            {
                int damage = location.powerCubes;
                message += "The " + threat.name + MainActivity.game.ShipDamage(location.zonePos,damage,false,true,true);
            }
        }
        return message;
    }

    public ActionInternalLeakPower(String condition,String target)
    {
        this.condition = condition;
        this.target = target;
    }
}
