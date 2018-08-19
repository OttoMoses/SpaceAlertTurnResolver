package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import java.util.ArrayList;

public class ActionInternalKnockOut extends ActionEffectInternal {
    private String target;
    public Pair<Integer,Integer> exclude;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        StringBuilder message = new StringBuilder();
        switch (target) {
            case "self": {
                Pair<Integer, Integer> location = threat.locations.get(0);
                for (Player p : MainActivity.game.players) {
                    if (p.zonePosition == location.second && p.sectionPosition == location.first) {
                        message.append("The ").append(threat.name);
                        if (threat.plural) {
                            message.append(" attack ");
                        } else {
                            message.append(" attacks ");
                        }
                        message.append(p.playerName).append(" knocking them out!");
                        p.unconscious = true;
                    }
                }
                break;
            }
            case "selfMulti": {
                ArrayList<Pair<Integer, Integer>> locations = threat.locations;
                for (Pair<Integer, Integer> location : locations) {
                    for (Player p : MainActivity.game.players) {
                        if (p.zonePosition == location.second && p.sectionPosition == location.first) {
                            message.append("The ").append(threat.name);
                            if (threat.plural) {
                                message.append(" attack ");
                            } else {
                                message.append(" attacks ");
                            }
                            message.append(p.playerName).append(" knocking them out!");
                            p.unconscious = true;
                        }
                    }
                }
                break;
            }
            case "allBut": {
                Pair<Integer, Integer> location = exclude;
                for (Player p : MainActivity.game.players) {
                    Pair<Integer,Integer> playerLocation = new Pair<>(p.zonePosition,p.sectionPosition);
                    if (!(playerLocation.first.equals(location.first) && playerLocation.second.equals(location.second)))
                    {
                        message.append("The ").append(threat.name);
                        if (threat.plural) {
                            message.append(" attack ");
                        } else {
                            message.append(" attacks ");
                        }
                        message.append(p.playerName).append(" knocking them out!");
                        p.unconscious = true;
                    }
                }
                break;
            }
            case "leadingBots" :
            {
                for (Player p : MainActivity.game.players) {
                    if (p.leadingBots)
                    {
                        message.append("The ").append(threat.name);
                        if (threat.plural) {
                            message.append(" attack ");
                        } else {
                            message.append(" attacks ");
                        }
                        message.append(p.playerName).append(" knocking them out!");
                        p.unconscious = true;
                    }
                }
                break;
            }
            case "SelfNoBots":
            {
                Pair<Integer, Integer> location = threat.locations.get(0);
                for (Player p : MainActivity.game.players) {
                    if ((p.zonePosition == location.second && p.sectionPosition == location.first) && (!p.leadingBots))
                    {
                        message.append("The ").append(threat.name);
                        if (threat.plural) {
                            message.append(" attack ");
                        } else {
                            message.append(" attacks ");
                        }
                        message.append(p.playerName).append(" knocking them out!");
                        p.unconscious = true;
                    }
                }
                break;
            }
        }
        return message.toString();
    }

    public ActionInternalKnockOut(String target)
    {
        this.target = target;
    }
}
