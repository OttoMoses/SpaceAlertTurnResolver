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
        String message = "";
        int DBDamage = 0;
        for(Pair p : db.damageSources)
        {
            DBDamage += (int)p.second;
        }
        message += t.TakeDamage(DBDamage,true);
        return message;
    }
}
