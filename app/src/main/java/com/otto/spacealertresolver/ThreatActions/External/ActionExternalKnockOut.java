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
        String message = "";
        switch (target)
        {
            case "all" :
            {
                message += "The " + threat.name + " attacks all players on the ship knocking them out!\n";
                for (Player p : game.players)
                {
                    if(p.unconscious)
                    {
                        message += p.playerName + " can't be knocked out because they are already knocked out!\n";
                    }
                    else
                    {
                        if(p.flyingInterceptors)
                        {
                            message += p.playerName + " is safely off the ship flying interceptors!\n";
                        }
                        else
                        {
                            message += p.playerName + " is knocked out!\n";
                            p.unconscious = true;
                        }
                    }
                }
                break;
            }
            case "self":
            {

                message += "The " + threat.name + " attacks all players in the " + game.colors[threat.track] + " zone knocking them out!\n";
                for (Player p : game.players)
                {
                    if(p.zonePosition == threat.track)
                    {
                        if(p.unconscious)
                        {
                            message += p.playerName + " can't be delayed because they are already knocked out!\n";
                        }
                        else
                        {
                            if(p.flyingInterceptors)
                            {
                                message += p.playerName + " is safely off the ship flying interceptors!\n";
                            }
                            else
                            {
                                message += p.playerName + " is knocked out!\n";
                                p.unconscious = true;
                            }
                        }
                    }
                }
                break;
            }
        }
        return message;
    }

    public ActionExternalKnockOut(String target)
    {
        this.target = target;
    }
}
