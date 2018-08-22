package com.otto.spacealertresolver.ThreatActions.External;

import android.util.Pair;

import com.otto.spacealertresolver.ExternalDamageBundle;
import com.otto.spacealertresolver.Threats.ThreatExternal;

public class OnDamageExternalCount extends OnDamageExternal
{
    public boolean damaged;
    public final int value;
    @Override
    public String Execute(ThreatExternal t, ExternalDamageBundle db)
    {
        String message = "";
        int DBDamage = 0;
        for(Pair p : db.damageSources)
        {
            DBDamage += (int)p.second;
        }
       message += t.TakeDamage(DBDamage,true);
       damaged = true;
       return message;
    }

    public OnDamageExternalCount(int value)
    {
        this.value = value;
    }
}
