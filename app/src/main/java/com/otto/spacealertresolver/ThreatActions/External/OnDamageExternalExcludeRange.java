package com.otto.spacealertresolver.ThreatActions.External;

import android.util.Pair;

import com.otto.spacealertresolver.ExternalDamageBundle;
import com.otto.spacealertresolver.Threats.ThreatExternal;

/**
 * Created by Otto on 3/6/2018.
 */

public class OnDamageExternalExcludeRange extends OnDamageExternal
{


    @Override
    public String Execute(ThreatExternal threat, ExternalDamageBundle db)
    {
        int DBDamage = 0;
        int damage;
        if(!threat.rangeThree)
        {
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
                damage = DBDamage - threat.shield;
                if (damage > 0)
                {
                    threat.damage += damage;
                    return "\nThe " + threat.name + " takes " + damage + " damage!\n";
                }
                else
                {
                    return "\nThe " + threat.name + " blocks all damage!";
                }
            }
        }
        else
        {
            return "\nThe " + threat.name + " is too far away to target!";
        }
    }
}
