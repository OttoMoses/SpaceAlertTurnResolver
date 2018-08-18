package com.otto.spacealertresolver.ThreatActions.External;

import android.util.Pair;

import com.otto.spacealertresolver.ExternalDamageBundle;
import com.otto.spacealertresolver.Threats.ThreatExternal;

/**
 * Created by Otto on 3/5/2018.
 */

public class OnDamageExternalSelfToggle extends OnDamageExternal
{
    private boolean toggle;
    @Override
    public String Execute(ThreatExternal t, ExternalDamageBundle db)
    {
        if(toggle)
        {
            int DBDamage = 0;
            for(Pair p : db.damageSources)
            {
                DBDamage += (int)p.second;
            }
            return t.TakeDamage(DBDamage,true);
        }
        else
        {
            toggle = true;
            return "The " + t.name + " is no longer immune to damage!\n";
        }
    }

    public OnDamageExternalSelfToggle()
    {
        this.toggle = false;
    }
}
