package com.otto.spacealertresolver.ThreatActions.External;

import android.util.Pair;

import com.otto.spacealertresolver.ExternalDamageBundle;
import com.otto.spacealertresolver.Threats.ThreatExternal;

public class OnDamageBuff extends OnDamageExternal
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
                message += "\nThe " + t.name + " takes " + damage + " damage!\n";
            }
            else
            {
                message += "\nThe " + t.name + " blocks all damage!";
            }
        }
        return message;
    }

    public OnDamageBuff(int value, String stat, String source) {
        this.value = value;
        this.stat = stat;
        this.source = source;
    }
}
