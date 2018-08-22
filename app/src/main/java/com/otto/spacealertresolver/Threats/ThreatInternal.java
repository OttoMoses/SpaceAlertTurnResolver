package com.otto.spacealertresolver.Threats;

import android.util.Pair;

import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.ThreatActions.Internal.OnDamageInternal;
import com.otto.spacealertresolver.ThreatActions.Internal.OnDeathInternal;
import com.otto.spacealertresolver.ThreatActions.Internal.ThreatActionInternal;

import java.util.ArrayList;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class ThreatInternal extends Threat
{
    public ArrayList<Pair<Integer,Integer>> locationsDefault;
    public ArrayList<Pair<Integer,Integer>> locations;
    public OnDamageInternal damageEffect;
    public String threatType;
    public ThreatActionInternal xAction;
    public ThreatActionInternal yAction;
    public ThreatActionInternal zAction;
    public OnDeathInternal deathAction;
    public ThreatActionInternal spawnAction;
    public boolean firesBackDefault;
    public boolean firesBack;
    public boolean plural;

    @Override
    public String ExecuteXAction(Section[][] ship, Player[] players)
    {
        if(xAction.effects.size() != 0)
        {
            return "\n" + xAction.Execute(ship,this) + "\n\n";
        }
        else
        {
            return "";
        }
    }

    @Override
    public String ExecuteYAction(Section[][] ship, Player[] players)
    {
        if(yAction.effects.size() != 0)
        {
            return "\n" + yAction.Execute(ship,this) + "\n\n";
        }
        else
        {
            return "";
        }
    }

    @Override
    public String ExecuteZAction(Section[][] ship, Player[] players)
    {
        if(zAction.effects.size() != 0)
        {
            return "\n" + zAction.Execute(ship,this) + "\n\n";
        }
        else
        {
            return "";
        }
    }

    @Override
    public String ExecuteSpawnAction(Section[][] ship)
    {
        String message = "";
        if(spawnAction != null)
        {
            message += spawnAction.Execute(ship,this);
        }
        return message;
    }

    public String ExecuteDamageAction(Section[][] ship, Player[] players)
    {
        String message = "";
        message += damageEffect.Execute(ship,this,players);
        if(message.equals(""))
        {
            return message;
        }
        else
        {
            return "\n" + message + "\n";
        }
    }

    public String ExecuteDeathAction(Section[][] ship, Player[] players)
    {
        String message = "";
        for(Pair p : locations)
        {
            Section location = ship[locations.get(0).first][locations.get(0).second];
            switch (threatType)
            {
                case "combat":
                {
                    location.combatThreat = false;
                    break;
                }
                case "malfC":
                {
                    location.malfC = false;
                    break;
                }
                case "malfB":
                {
                    location.malfB = false;
                    break;
                }
            }
        }
        if(deathAction != null)
        {
            return "\n" +  deathAction.Execute(this) + "\n";
        }
        return message;
    }

    @Override
    public String TakeDamage(int value, boolean shield)
    {
        String message = "";
        damage += value;
        message += "The " + name;
        if(plural)
        {
            message += " take ";
        }
        else
        {
            message += " takes ";
        }
        message += value + " damage!";
        if(damage >= health)
        {
            message += ExecuteDeathAction(game.ship,game.players);
            game.deadThreats.add(this);
        }
        else
        {
            message += "\n";
        }
        return message;
    }

    @Override
    public void ResetThreat()
    {
        if(locations == null)
        {
            locations = new ArrayList<>();
        }
        if(locations.size() != 0)
        {
            locations.clear();
        }
        locations.addAll(locationsDefault);
        speedBoost = 0;
        damage = 0;
        firesBack = firesBackDefault;
        position = 0;
    }

}
