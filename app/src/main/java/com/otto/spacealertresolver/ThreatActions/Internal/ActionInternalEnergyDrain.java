package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import java.util.ArrayList;

public class ActionInternalEnergyDrain extends ActionEffectInternal
{
    private final String target;
    private final String damValue;
    private final String condition;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        StringBuilder message = new StringBuilder();

        //get target or targets
        switch (target)
        {
            case "self":
            {
                Section location = ship[threat.locations.get(0).first][threat.locations.get(0).second];
                message.append(DrainPower(location, threat));
                break;
            }
            case "selfMult":
            {
                for (Pair<Integer,Integer> p : threat.locations)
                {
                    Section location = ship[p.first][p.second];
                    message.append(DrainPower(location, threat));
                }
                break;
            }
            case "allReactors" :
            {
                Section location;
                location = ship[0][0];
                message.append(DrainPower(location, threat));
                location = ship[1][0];
                message.append("\n").append(DrainPower(location, threat));
                location = ship[2][0];
                message.append("\n").append(DrainPower(location, threat));
                break;
            }
            case "allShields" :
            {
                Section location;
                location = ship[0][1];
                message.append(DrainPower(location, threat));
                location = ship[1][1];
                message.append("\n").append(DrainPower(location, threat));
                location = ship[2][1];
                message.append("\n").append(DrainPower(location, threat));
                break;
            }
            case "selfReactor" :
            {
                Section location = ship[threat.locations.get(0).first][0];
                message.append(DrainPower(location, threat));
                break;
            }
            case "selfShield" :
            {
                Section location = ship[threat.locations.get(0).first][1];
                message.append(DrainPower(location, threat));
                break;
            }
            case "mainReactor" :
            {
                Section location = ship[1][0];
                message.append(DrainPower(location, threat));
                break;
            }
        }
        return message.toString();
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
                    message += "The "  +  threat.name + " " + location.DrainPower(damage);
                }
                break;
            }
            case "none":
            {
                message += "The " +  threat.name + " " + location.DrainPower(damage);
                break;
            }
        }
        return message;
    }
    public ActionInternalEnergyDrain(String target, String condition, String damValue)
    {
        this.target = target;
        this.condition = condition;
        this.damValue = damValue;
    }
}
