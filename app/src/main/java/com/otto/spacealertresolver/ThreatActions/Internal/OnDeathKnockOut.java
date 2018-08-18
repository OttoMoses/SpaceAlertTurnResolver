package com.otto.spacealertresolver.ThreatActions.Internal;

import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import java.util.ArrayList;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class OnDeathKnockOut extends OnDeathInternal
{
    private String target;
    @Override
    public String Execute(ThreatInternal threat)
    {
        String message = "";
        switch (target)
        {
            case "lateralReactors":
            {
                message += "The " + threat.name + " knocks out players in the lower red and blue sections!\n";
                for(Player p : game.players)
                {
                    if((p.zonePosition == 0 && p.sectionPosition == 0) ||(p.zonePosition == 2 && p.sectionPosition == 0))
                    {
                        p.unconscious = true;
                        message += "\n" + p.playerName + " Is knocked out!";
                    }
                }
            }
            case "lastHit" :
            {
                message += "The " + threat.name + " knocks out the last player to fight it!\n";
                game.players[game.currentPlayer].unconscious = true;
                message += "\n" + game.players[game.currentPlayer].playerName + " Is knocked out!";
            }
        }
        return message;
    }

    public OnDeathKnockOut(String target)
    {
        this.target = target;
    }
}
