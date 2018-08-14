package com.otto.spacealertresolver.ThreatActions.External;

import android.util.Pair;

import com.otto.spacealertresolver.ExternalDamageBundle;
import com.otto.spacealertresolver.Threats.ThreatExternal;

public class OnDamageExternalMaxValue extends OnDamageExternal
{
    private int maxValue;
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
        if(DBDamage == 0)
        {
            return null;
        }
        else
        {
            if(DBDamage > maxValue)
            {
                message += "\nThe " + t.name + " reduces damage by " + (DBDamage - maxValue) + " and only takes " + maxValue + "!\n";
                DBDamage = maxValue;
            }
            damage = DBDamage - t.shield;
            if(damage > 0)
            {
                t.damage += damage;
                message += "\nThe " + t.name + " takes " + damage + " damage!\n";
            }
            else
            {
                message += "\nThe " + t.name + " blocks all damage!";
            }
        }
        return message;
    }

    public OnDamageExternalMaxValue(int maxValue)
    {
        this.maxValue = maxValue;
    }
}
