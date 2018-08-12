package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import java.util.ArrayList;

public class ActionInternalEnergyDrain extends ActionEffectInternal
{
    private String target;
    private String damValue;
    private String condition;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        String message = "";

        //get target or targets
        switch (target)
        {
            case "self":
            {
                Section location = ship[threat.locations.get(0).first][threat.locations.get(0).second];
                message += "\n" + DrainPower(location,threat);
                break;
            }
            case "selfMult":
            {
                for (Pair<Integer,Integer> p :threat.locations)
                {
                    Section location = ship[p.first][p.second];
                    message += "\n" +  DrainPower(location,threat);
                    break;
                }
            }
            case "allReactors" :
            {
                ArrayList<Pair<Integer,Integer>> locations = new ArrayList<>();
                locations.add(new Pair<Integer, Integer>(0,0));
                locations.add(new Pair<Integer, Integer>(1,0));
                locations.add(new Pair<Integer, Integer>(2,0));
                for (Pair<Integer,Integer> p :locations)
                {
                    Section location = ship[p.first][p.second];
                    message += "\n" +  DrainPower(location,threat);
                }
                break;
            }
            case "allShields" :
            {
                ArrayList<Pair<Integer,Integer>> locations = new ArrayList<>();
                locations.add(new Pair<Integer, Integer>(0,1));
                locations.add(new Pair<Integer, Integer>(1,1));
                locations.add(new Pair<Integer, Integer>(2,1));
                for (Pair<Integer,Integer> p :locations)
                {
                    Section location = ship[p.first][p.second];
                    message += "\n" +  DrainPower(location,threat);
                }
                break;
            }
            case "selfReactor" :
            {
                Section location = ship[threat.locations.get(0).first][0];
                message += "\n" +  DrainPower(location,threat);
                break;
            }
            case "selfShield" :
            {
                Section location = ship[threat.locations.get(0).first][1];
                message += "\n" +  DrainPower(location,threat);
                break;
            }
        }
        return message;
    }

    private String DrainPower(Section location, ThreatInternal threat)
    {
        String message = "";
        int damage;
        //get damage amount
        switch (damValue)
        {
            case "all":
            {
                damage = location.powerCubes;
                break;
            }
            default :
            {
                damage = Integer.parseInt(damValue);
                break;
            }
        }
        //determine other conditions
        switch (condition)
        {
            case "elseDamage" :
            {
                if(location.powerCubes < damage)
                {
                    message += "The " + threat.name  + MainActivity.game.ShipDamage(location.zonePos,damage,false,false,threat.plural);
                }
                else
                {
                    message += "The " + location.DrainPower(damage);
                }
                break;
            }
            case "none":
            {
                message += "The " + location.DrainPower(damage);
            }
        }
        return message + "\n";
    }
    public ActionInternalEnergyDrain(String target, String condition, String damValue)
    {
        this.target = target;
        this.condition = condition;
        this.damValue = damValue;
    }
}
