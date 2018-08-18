package com.otto.spacealertresolver.ThreatActions.Internal;

import android.widget.ListView;

import com.otto.spacealertresolver.InternalDamageBundle;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

public class OnDamageInternalCombat extends OnDamageInternal
{
    public boolean firesBack;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat, Player[] players)
    {
        StringBuilder message = new StringBuilder();
        Section location = ship[threat.locations.get(0).first][threat.locations.get(0).second];
        if(location.combatDamage != null)
        {
            InternalDamageBundle db = location.combatDamage;
            Player player = players[db.playerID];
            message.append(player.playerName + " fights the " + threat.name + "!");
            message.append(threat.TakeDamage(1, false));
            if(firesBack)
            {
                if(db.heroic)
                {
                    message.append("\n").append(player.playerName).append("'s heroic leadership prevented the ").append(threat.name).append(" from damaging their bots!\n");
                }
                else
                {
                    player.damagedBots = true;
                    message.append("\n").append(player.playerName).append("'s bots were damaged in combat against the ").append(threat.name).append("!\n");
                }
            }
            location.combatDamage = null;
        }
        return message.toString();
    }

    public OnDamageInternalCombat(boolean firesBack)
    {
        this.firesBack = firesBack;
    }
}
