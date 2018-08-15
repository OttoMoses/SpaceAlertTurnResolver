package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.InternalDamageBundle;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class OnDamageInternalMalfMultiBonus extends OnDamageInternal {
    private String target;
    private int potentialBonus;
    private int round;
    private int systemCount = 0;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat, Player[] players)
    {
        if(round != game.currentRound)
        {
            round = game.currentRound;
            systemCount = 0;
        }
        StringBuilder message = new StringBuilder();
        int realBonus = 0;
        switch (target)
        {
            case "C" :
            {
                for(Pair<Integer,Integer> pair : threat.locations)
                {
                    Section location = ship[pair.first][pair.second];
                    if(location.malfCDamage != null)
                    {
                        systemCount++;
                        if(systemCount == threat.locations.size())
                        {
                            realBonus = potentialBonus;
                        }
                        ProcessDamageBundle(location.malfCDamage,threat,realBonus);
                        location.malfCDamage = null;
                    }
                }
                break;
            }
            case "B" :
            {
                for(Pair<Integer,Integer> pair : threat.locations)
                {
                    Section location = ship[pair.first][pair.second];
                    if(location.malfBDamage != null)
                    {
                        systemCount++;
                        if(systemCount == threat.locations.size())
                        {
                            realBonus = potentialBonus;
                        }
                        ProcessDamageBundle(location.malfBDamage,threat,realBonus);
                        location.malfBDamage = null;
                    }
                }
                break;
            }
            default :
            {
                System.out.print(threat.name + " has an error in it's damage action");
                break;
            }
        }
        return message.toString();
    }
    private String ProcessDamageBundle(InternalDamageBundle bundle, ThreatInternal threat,int bonus)
    {
        String message = "";
        if(bundle.heroic)
        {
            threat.damage += 2 + bonus;
            message += game.players[bundle.playerID].playerName + " heroically repairs the " + threat.name + " for " + (2 + bonus) + " damage!";
        }
        else
        {
            threat.damage += 1 + bonus;
            message += game.players[bundle.playerID].playerName + " repairs the " + threat.name + " for " + (1 + bonus) + " damage!";
        }
        return message;
    }

    public OnDamageInternalMalfMultiBonus(String target, int potentialBonus)
    {
        this.target = target;
        this.potentialBonus = potentialBonus;
    }
}
