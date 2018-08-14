package com.otto.spacealertresolver.ThreatActions.External;

import android.util.Pair;

import com.otto.spacealertresolver.ExternalDamageBundle;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Threats.ThreatExternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class OnDamageExternalDestroyInterceptors extends OnDamageExternal
{
    @Override
    public String Execute(ThreatExternal t, ExternalDamageBundle db)
    {
        int DBDamage = 0;
        int damage;
        String message = "";
        for(Pair p : db.damageSources)
        {
            DBDamage += (int)p.second;
            if(p.first.equals("fighters"))
            {
                DBDamage += 6;
                for(Player player : game.players)
                {
                    if(player.flyingInterceptors)
                    {

                        player.unconscious = true;
                        message+= player.playerName + " is knocked out while leading their strafing run on the " + t.name + "!\nThe interceptors are no longer functional!";
                    }
                }
            }
        }
        if(DBDamage == 0)
        {
            damage = DBDamage - t.shield;
            if(damage > 0)
            {
                t.damage += damage;
                message += "\nThe " + t.name + " takes " + damage + " damage!\n";
            }
            else
            {
                message += "\nThe " + t.name + " blocks all damage!\n";
            }
        }
        return message;
    }
}
