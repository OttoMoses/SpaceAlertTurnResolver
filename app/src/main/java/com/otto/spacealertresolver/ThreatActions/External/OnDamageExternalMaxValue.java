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
        if(DBDamage != 0)
        {
            if(DBDamage > maxValue)
            {
                message += "The " + t.name + " reduces damage by " + (DBDamage - maxValue) + "!";
                DBDamage = maxValue;
            }
            message += t.TakeDamage(DBDamage,true);
        }
        return message;
    }

    public OnDamageExternalMaxValue(int maxValue)
    {
        this.maxValue = maxValue;
    }
}
