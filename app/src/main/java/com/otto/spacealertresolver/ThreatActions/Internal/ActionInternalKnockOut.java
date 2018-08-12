package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;
import android.util.Printer;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.Threat;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import java.util.ArrayList;

public class ActionInternalKnockOut extends ActionEffectInternal {
    private String target;
    public Pair<Integer,Integer> exclude;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        String message = "";
        switch (target) {
            case "self": {
                Pair<Integer, Integer> location = threat.locations.get(0);
                for (Player p : MainActivity.game.players) {
                    if (p.zonePosition == location.second && p.sectionPosition == location.first) {
                        message += "The " + threat.name;
                        if (threat.plural) {
                            message += " attack ";
                        } else {
                            message += " attacks ";
                        }
                        message += p.playerName + " knocking them out!\n";
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
                            message += "The " + threat.name;
                            if (threat.plural) {
                                message += " attack ";
                            } else {
                                message += " attacks ";
                            }
                            message += p.playerName + " knocking them out!\n";
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
                    if (!(playerLocation.first == location.first && playerLocation.second == location.second))
                    {
                        message += "The " + threat.name;
                        if (threat.plural) {
                            message += " attack ";
                        } else {
                            message += " attacks ";
                        }
                        message += p.playerName + " knocking them out!\n";
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
                        message += "The " + threat.name;
                        if (threat.plural) {
                            message += " attack ";
                        } else {
                            message += " attacks ";
                        }
                        message += p.playerName + " knocking them out!\n";
                        p.unconscious = true;
                    }
                }
                break;
            }
        }
        return message;
    }

    public ActionInternalKnockOut(String target)
    {
        this.target = target;
    }
}
