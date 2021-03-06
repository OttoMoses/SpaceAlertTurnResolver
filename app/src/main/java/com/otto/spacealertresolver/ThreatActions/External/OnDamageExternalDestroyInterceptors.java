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
        StringBuilder message = new StringBuilder();
        for(Pair p : db.damageSources)
        {
            DBDamage += (int)p.second;
            if(p.first.equals("fighters")&&((int)p.second)>2)
            {
                DBDamage += 6;
                for(Player player : game.players)
                {
                    if(player.flyingInterceptors)
                    {

                        player.unconscious = true;
                        message.append(player.playerName).append(" is knocked out while leading their strafing run on the ").append(t.name).append("!\nThe interceptors are no longer functional!");
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
                message.append("\nThe ").append(t.name).append(" takes ").append(damage).append(" damage!");
            }
            else
            {
                message.append("\nThe ").append(t.name).append(" blocks all damage!");
            }
        }
        return message.toString();
    }
}
