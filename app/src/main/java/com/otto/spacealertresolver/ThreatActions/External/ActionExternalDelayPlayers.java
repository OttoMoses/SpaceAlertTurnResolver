package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatExternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class ActionExternalDelayPlayers extends ActionEffectExternal
{
    private final String target;
    @Override
    public String Execute(Section[][] ship, ThreatExternal threat)
    {
        StringBuilder message = new StringBuilder();
        switch (target)
        {
            case "all" :
            {
                message.append("The ").append(threat.name).append(" attacks all players on the ship delaying their actions!");
                for (Player p : game.players)
                {
                message.append(p.Delay((game.currentRound + 1)));
                }
                break;
            }
            case "self":
            {

                message.append("The ").append(threat.name).append(" attacks all players in the ").append(game.colors[threat.track]).append(" zone delaying their actions!");
                for (Player p : game.players)
                {
                    if(p.zonePosition == threat.track)
                    {
                        message.append(p.Delay((game.currentRound + 1)));
                    }
                }
                break;
            }
        }
        return message.toString();
    }

    public ActionExternalDelayPlayers(String target)
    {
        this.target = target;
    }
}
