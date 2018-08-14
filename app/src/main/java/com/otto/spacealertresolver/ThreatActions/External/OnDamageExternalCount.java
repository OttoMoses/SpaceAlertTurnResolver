package com.otto.spacealertresolver.ThreatActions.External;

import android.util.Pair;

import com.otto.spacealertresolver.ExternalDamageBundle;
import com.otto.spacealertresolver.Threats.ThreatExternal;

public class OnDamageExternalCount extends OnDamageExternal
{
    public boolean damaged;
    public int value;
    @Override
    public String Execute(ThreatExternal t, ExternalDamageBundle db)
    {
        String message = "";
        int DBDamage = 0;
        int damage;
        for(Pair p : db.damageSources)
        {
            DBDamage += (int)p.second;
        }
        if(DBDamage != 0)
        {
            damage = DBDamage - t.shield;
            if(damage > 0)
            {
                t.damage += damage;
                damaged = true;
                message += "\nThe " + t.name + " takes " + damage + " damage!\n";
            }
            else
            {
                message += "\nThe " + t.name + " blocks all damage!";
            }
        }
        return message;
    }

    public OnDamageExternalCount(int value)
    {
        this.value = value;
    }
}
