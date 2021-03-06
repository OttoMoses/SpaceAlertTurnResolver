package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class ActionInternalDelayPlayers extends ActionEffectInternal
{
    private final String target;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        StringBuilder message = new StringBuilder();
        switch (target)
        {
            case  "all" :
            {
                message.append("The ").append(threat.name).append(" attacks all players on the ship delaying their actions!");
                for(Player p : game.players)
                {
                    message.append(p.Delay((game.currentRound + 1)));
                }
                break;
            }
            case "self" :
            {
                for(Pair pair : threat.locations)
                {
                    Section location = ship[(Integer)pair.first][(Integer)pair.second];
                    if(pair != threat.locations.get(0))
                    {
                        message.append("\n");
                    }
                    message.append("The ").append(threat.name).append(" attacks all players in the ").append(location.sectionName).append(" ").append(location.zoneName).append(" section ").append("delaying their actions!");
                }
                for(Player p : game.players)
                {
                    for(Pair pair : threat.locations)
                    {
                        if(p.zonePosition == (Integer)pair.first && p.sectionPosition == (Integer)pair.second)
                        {
                            message.append(p.Delay((game.currentRound + 1)));
                        }
                    }
                }
                break;
            }
        }
        return message.toString();
    }

    public ActionInternalDelayPlayers(String target) {
        this.target = target;
    }
}
