package com.otto.spacealertresolver.ThreatActions.External;

import android.util.Pair;

import com.otto.spacealertresolver.ExternalDamageBundle;
import com.otto.spacealertresolver.Threats.ThreatExternal;

/**
 * Created by Otto on 3/5/2018.
 */

public class OnDamageExternalDefault extends OnDamageExternal {

    @Override
    public String Execute(ThreatExternal t, ExternalDamageBundle db)
    {
        int DBDamage = 0;
        int damage;
        for(Pair p : db.damageSources)
        {
            DBDamage += (int)p.second;
        }
        if(DBDamage == 0)
        {
            return null;
        }
        else
        {
            damage = DBDamage - t.shield;
            if(damage > 0)
            {
                t.damage += damage;
                return "\nThe " + t.name + " takes " + damage + " damage!\n";
            }
            else
            {
                return "\nThe " + t.name + " blocks all damage!";
            }
        }
    }
}
