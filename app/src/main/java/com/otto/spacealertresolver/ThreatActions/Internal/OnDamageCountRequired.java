package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.InternalDamageBundle;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class OnDamageCountRequired extends OnDamageInternal
{
    private int count = 0;
    private int roundnumber = 0;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat, Player[] players)
    {
        String message = "";
        Pair<Integer,Integer> locationValue = threat.locations.get(0);
        Section location = ship[locationValue.first][locationValue.second];
        if(location.malfCDamage != null)
        {
            InternalDamageBundle db = location.malfCDamage;
            message += players[db.playerID].playerName + " attempts to defuse the " + threat.name + "!";
            location.malfCDamage = null;
            if(roundnumber != game.currentRound)
            {
                count = 0;
                roundnumber = game.currentRound;
            }
            count++;
            if(count == 3)
            {
                message += "\nThe " +threat.name + " has been defused!\n";
                message += threat.TakeDamage(1,false);
            }
        }
        return message;
    }
}
