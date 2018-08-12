package com.example.otto.spacealertresolver.Threats;

import android.util.Pair;

import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.Player;
import com.example.otto.spacealertresolver.Section;
import com.example.otto.spacealertresolver.ThreatActions.Internal.OnDamageInternal;
import com.example.otto.spacealertresolver.ThreatActions.Internal.OnDeathInternal;
import com.example.otto.spacealertresolver.ThreatActions.Internal.ThreatActionInternal;

import java.util.ArrayList;

public class ThreatInternal extends Threat
{
    public ArrayList<Pair<Integer,Integer>> locations;
    public OnDamageInternal damageEffect;
    public String threatType;
    public ThreatActionInternal xAction;
    public ThreatActionInternal yAction;
    public ThreatActionInternal zAction;
    public OnDeathInternal deathAction;
    public ThreatActionInternal spawnAction;
    public boolean plural;
    public int lastDamaged;

    @Override
    public String ExecuteXAction(Section[][] ship, Player[] players)
    {
        String message = "";
        if(xAction != null)
        {
            return xAction.Execute(ship,this);
        }
        return message;
    }

    @Override
    public String ExecuteYAction(Section[][] ship, Player[] players)
    {
        String message = "";
        if(yAction != null)
        {
            return yAction.Execute(ship,this);
        }
        return message;
    }

    @Override
    public String ExecuteZAction(Section[][] ship, Player[] players)
    {
        String message = "";
        if(zAction != null)
        {
            return zAction.Execute(ship,this);
        }
        return message;
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
        if(damageEffect != null)
        {
            return damageEffect.Execute(ship,this,players);
        }
        return message;
    }

    public String ExecuteDeathAction(Section[][] ship, Player[] players)
    {
        String message = "";
        if(deathAction != null)
        {
            return deathAction.Execute(ship,this);
        }
        return message;
    }
}
