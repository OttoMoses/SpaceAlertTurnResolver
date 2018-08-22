package com.otto.spacealertresolver.ThreatActions.Internal;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

public class ActionInternalLeakPower extends ActionEffectInternal
{
    private final String condition;
    private final String target;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        String message = "";
        switch (target)
        {
            case "centralReactor" :
            {
                message += LeakPower(threat,ship[1][0],ship);
                break;
            }
            case "allReactors" :
            {
                message+= LeakPower(threat,ship[0][0],ship) + "\n\n";
                message+= LeakPower(threat,ship[1][0],ship) + "\n\n";
                message+= LeakPower(threat,ship[2][0],ship);
                break;
            }
            case "centralShield" :
            {
                message += LeakPower(threat,ship[1][1],ship);
                break;
            }
        }
        return message;
    }

    private String LeakPower(ThreatInternal threat,Section location,Section[][] ship)
    {
        String message = "";
        switch (condition)
        {
            case "transfer" :
            {
                int power = location.powerCubes;
                Section destination = ship[location.zonePos][1];
                int transferValue = destination.getMaxPower() - destination.powerCubes;
                if(power <= transferValue)
                {
                    location.powerCubes += power;
                    location.powerCubes -= transferValue;
                    message += "The " + threat.name + " transfers " + power + " power to the " + destination.BSystem.name;
                    if (location.powerCubes != 0) {
                        message += "\n" + "The central reactor has " + location.powerCubes + " power remaining";
                    } else {
                        message += "\n" + "The central reactor has no power remaining";
                    }
                }
                else
                {
                    int damage = power - transferValue;
                    destination.powerCubes += transferValue;
                    location.powerCubes -= transferValue;
                    message += "The " + threat.name + " transfer " + transferValue + " power to the " + destination.BSystem.name + " in the " + destination.sectionName + " " + destination.zoneName + " section!\n";
                    message += "The " + threat.name + MainActivity.game.ShipDamage(location.zonePos,damage,false,true,true);
                    if (location.powerCubes != 0) {
                        message += "\n" + "The main reactor has " + location.powerCubes + " power remaining";
                    } else {
                        message += "\n" + "The main reactor has no power remaining";
                    }
                }
                break;
            }
            case "leak" :
            {
                int damage = location.powerCubes;
                message += "The " + threat.name + MainActivity.game.ShipDamage(location.zonePos,damage,false,true,true);
                if(location.BSystem.name.equals("main reactor"))
                {
                    if (location.powerCubes != 0) {
                        message += "\n" + "The main reactor has " + location.powerCubes + " power remaining";
                    } else {
                        message += "\n" + "The main reactor has no power remaining";
                    }
                }
                else
                {
                    if (location.powerCubes != 0) {
                        message += "\n" + "The " + location.zoneName + " lateral reactor has " + location.powerCubes + " power remaining";
                    } else {
                        message += "\n" + "The " + location.zoneName + " lateral reactor has no power remaining";
                    }
                }

                break;
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
