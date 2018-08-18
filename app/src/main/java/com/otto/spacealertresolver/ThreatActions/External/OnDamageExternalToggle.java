package com.otto.spacealertresolver.ThreatActions.External;

import android.util.Pair;

import com.otto.spacealertresolver.ExternalDamageBundle;
import com.otto.spacealertresolver.Threats.ThreatExternal;

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
            return t.TakeDamage(DBDamage,true);
        }
        else
        {
            return "The " + t.name + " is currently immune to damage!";
        }
    }
}
