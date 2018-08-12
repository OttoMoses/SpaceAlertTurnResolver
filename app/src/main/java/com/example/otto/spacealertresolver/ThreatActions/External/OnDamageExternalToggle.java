package com.example.otto.spacealertresolver.ThreatActions.External;

import android.util.Pair;

import com.example.otto.spacealertresolver.ExternalDamageBundle;
import com.example.otto.spacealertresolver.Threats.ThreatExternal;

/**
 * Created by Otto on 3/5/2018.
 */

public class OnDamageExternalToggle extends OnDamageExternal
{
    public boolean toggle = false;
    @Override
    public String Execute(ThreatExternal t, ExternalDamageBundle db)
    {
        if(toggle)
        {
            int DBDamage = 0;
            int damage;
            for(Pair p : db.damageSources)
            {
                DBDamage += (int)p.second;
            }
            damage = DBDamage - t.shield;
            t.damage += damage;
            return "The " + t.name + " takes " + damage + " damage!";
        }
        else
        {
            return "The " + t.name + " is currently immune to damage!";
        }
    }
}
