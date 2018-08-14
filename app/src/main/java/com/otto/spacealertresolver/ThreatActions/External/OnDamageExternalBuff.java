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
        String message = "";
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
                        message += "The " + t.name + " gains " + value + " " + stat + " from being attacked with a " + source + "!\n";
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
                message += "The " + t.name + " takes " + damage + " damage!\n";
            }
            else
            {
                message += "The " + t.name + " blocks all damage!\n";
            }
        }
        return message;
    }

    public OnDamageExternalBuff(int value, String stat, String source) {
        this.value = value;
        this.stat = stat;
        this.source = source;
    }
}
