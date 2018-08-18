package com.otto.spacealertresolver.ThreatActions.External;

import android.util.Pair;

import com.otto.spacealertresolver.ExternalDamageBundle;
import com.otto.spacealertresolver.Threats.ThreatExternal;

/**
 * Created by Otto on 3/6/2018.
 */

public class OnDamageExternalNoShield extends OnDamageExternal
{

    private String trigger;

    @Override
    public String Execute(ThreatExternal t, ExternalDamageBundle db)
    {
        String message = "";
        int DBDamage = 0;
        int damage;
        Boolean toggle = false;
        for(Pair p : db.damageSources)
        {
            DBDamage += (int)p.second;
            if(p.first.equals(trigger))
            {
                toggle = true;
            }
        }
        if(DBDamage == 0)
        {
            return null;
        }
        else
        {
            if(toggle)
            {
                message += "\nThe " + t.name + " has no shield this turn!";
                message += t.TakeDamage(DBDamage,false);
            }
            else
            {
                message += t.TakeDamage(DBDamage,true);
            }
        }
        return message;
    }

    public OnDamageExternalNoShield(String trigger) {
        this.trigger = trigger;
    }
}
