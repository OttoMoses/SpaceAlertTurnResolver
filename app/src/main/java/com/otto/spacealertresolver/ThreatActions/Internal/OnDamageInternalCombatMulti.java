package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.InternalDamageBundle;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class OnDamageInternalCombatMulti extends OnDamageInternal
{
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat, Player[] players)
    {
        StringBuilder message = new StringBuilder();
        Pair toBeRemoved = null;
        for(Pair pair : threat.locations)
        {
            Section location = game.ship[(Integer)pair.first][(Integer)pair.second];
            if(location.combatDamage != null)
            {
                InternalDamageBundle db = location.combatDamage;
                Player player = players[db.playerID];
                message.append(player.playerName + " destroys the " + threat.name + " in the " + location.sectionName + " " + location.zoneName + " section!\n");
                toBeRemoved = pair;
                location.combatThreat = false;
                location.specialKnockout = false;
                message.append(threat.TakeDamage(1, false));
                if(threat.firesBack)
                {
                    if(db.heroic)
                    {
                        message.append("\n").append(player.playerName).append("'s heroic leadership prevented the ").append(threat.name).append(" from damaging their bots!");
                    }
                    else
                    {
                        player.damagedBots = true;
                        message.append("\n").append(player.playerName).append("'s bots were damaged in combat against the ").append(threat.name).append("!");
                    }
                }
                location.combatDamage = null;
            }
        }
        threat.locations.remove(toBeRemoved);
        return message.toString();
    }
}
