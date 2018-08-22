package com.otto.spacealertresolver.ThreatActions.Internal;

import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import java.util.ArrayList;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class OnDeathInternalKnockOut extends OnDeathInternal
{
    private String target;
    @Override
    public String Execute(ThreatInternal threat)
    {
        StringBuilder message = new StringBuilder();
        switch (target)
        {
            case "lateralReactors":
            {
                message.append("The ").append(threat.name).append(" attacks players in the lower red and blue sections!\n");
                for(Player p : game.players)
                {
                    if((p.zonePosition == 0 && p.sectionPosition == 0) ||(p.zonePosition == 2 && p.sectionPosition == 0))
                    {
                        p.unconscious = true;
                        message.append(p.playerName).append(" Is knocked out!");
                    }
                }
                break;
            }
            case "lastHit" :
            {
                message.append("The ").append(threat.name).append(" attacks the player who killed it!\n");
                game.players[game.currentPlayer].unconscious = true;
                message.append(game.players[game.currentPlayer].playerName).append(" Is knocked out!");
            }
            break;
        }
        return message.toString();
    }

    public OnDeathInternalKnockOut(String target)
    {
        this.target = target;
    }
}
