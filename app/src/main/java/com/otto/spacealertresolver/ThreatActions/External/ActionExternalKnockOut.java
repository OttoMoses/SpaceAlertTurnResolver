package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatExternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class ActionExternalKnockOut extends ActionEffectExternal
{
    private String target;
    @Override
    public String Execute(Section[][] ship, ThreatExternal threat)
    {
        StringBuilder message = new StringBuilder();
        switch (target)
        {
            case "all" :
            {
                message.append("The ").append(threat.name).append(" attacks all players on the ship knocking them out!");
                for (Player p : game.players)
                {
                    if(p.unconscious)
                    {
                        message.append(p.playerName).append(" can't be knocked out because they are already knocked out!");
                    }
                    else
                    {
                        if(p.flyingInterceptors)
                        {
                            message.append(p.playerName).append(" is safely off the ship flying interceptors!");
                        }
                        else
                        {
                            message.append(p.playerName).append(" is knocked out!");
                            p.unconscious = true;
                        }
                    }
                }
                break;
            }
            case "self":
            {

                message.append("The ").append(threat.name).append(" attacks all players in the ").append(game.colors[threat.track]).append(" zone knocking them out!");
                for (Player p : game.players)
                {
                    if(p.zonePosition == threat.track)
                    {
                        if(p.unconscious)
                        {
                            message.append(p.playerName).append(" can't be knocked out because they are already knocked out!");
                        }
                        else
                        {
                            if(p.flyingInterceptors)
                            {
                                message.append(p.playerName).append(" is safely off the ship flying interceptors!");
                            }
                            else
                            {
                                message.append(p.playerName).append(" is knocked out!");
                                p.unconscious = true;
                            }
                        }
                    }
                }
                break;
            }
        }
        return message.toString();
    }

    public ActionExternalKnockOut(String target)
    {
        this.target = target;
    }
}
