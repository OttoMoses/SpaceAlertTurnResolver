package com.example.otto.spacealertresolver.ThreatActions.Internal;

import com.example.otto.spacealertresolver.InternalDamageBundle;
import com.example.otto.spacealertresolver.Player;
import com.example.otto.spacealertresolver.Section;
import com.example.otto.spacealertresolver.Threats.ThreatInternal;

public class OnDamageInternalCombat extends OnDamageInternal
{
    public boolean firesBack;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat, Player[] players)
    {
        String message = "";
        Section location = ship[threat.locations.get(0).first][threat.locations.get(0).second];
        while(!location.combatDamage.isEmpty() && threat.damage < threat.health)
        {
            InternalDamageBundle db = location.combatDamage.get(0);
            Player player = players[db.playerID];
            threat.damage++;
            message += player.playerName + " deals 1 damage against the " + threat.name + " in the " + location.sectionName + " " +  location.zoneName + " section!\n\n";
            if(firesBack)
            {
                if(db.heroic)
                {
                    message += player.playerName + "'s heroic leadership prevented the " + threat.name + " from damaging their bots!\n";
                }
                else
                {
                    player.damagedBots = true;
                    message += player.playerName + "'s bots were damaged in combat against the " + threat.name + "!\n";
                }
            }
            location.combatDamage.remove(0);
        }
        return message;
    }

    public OnDamageInternalCombat(boolean firesBack)
    {
        this.firesBack = firesBack;
    }
}
