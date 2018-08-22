package com.otto.spacealertresolver.ThreatActions.External;

import android.util.Pair;

import com.otto.spacealertresolver.ExternalDamageBundle;
import com.otto.spacealertresolver.Threats.ThreatExternal;

public class OnDamageExternalBuff extends OnDamageExternal
{
    private int value;
    private String stat;
    private String source;
    @Override
    public String Execute(ThreatExternal t, ExternalDamageBundle db)
    {
        StringBuilder message = new StringBuilder();
        int DBDamage = 0;
        int damage;
        for(Pair p : db.damageSources)
        {
            DBDamage += (int)p.second;
            if (p.first.equals(source))
            {
                switch (stat)
                {
                    case "shield":
                    {
                        t.shield += value;
                        message.append("The ").append(t.name).append(" gains ").append(value).append(" ").append(stat).append(" from being attacked with a ").append(source).append("!\n");
                        break;
                    }
                }
            }
        }
        if(DBDamage != 0)
        {
            damage = DBDamage - t.shield;
            if(damage > 0)
            {
                t.damage += damage;
                message.append("The ").append(t.name).append(" takes ").append(damage).append(" damage!\n");
            }
            else
            {
                message.append("The ").append(t.name).append(" blocks all damage!\n");
            }
        }
        return message.toString();
    }

    public OnDamageExternalBuff(int value, String stat, String source) {
        this.value = value;
        this.stat = stat;
        this.source = source;
    }
}
