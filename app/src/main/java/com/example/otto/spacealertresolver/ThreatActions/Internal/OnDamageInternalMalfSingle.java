package com.example.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.InternalDamageBundle;
import com.example.otto.spacealertresolver.Player;
import com.example.otto.spacealertresolver.Section;
import com.example.otto.spacealertresolver.Threats.Threat;
import com.example.otto.spacealertresolver.Threats.ThreatInternal;

import java.io.Console;

public class OnDamageInternalMalfSingle extends OnDamageInternal
{
    public String system;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat, Player[] players)
    {
        String message = "";
        Pair<Integer,Integer> locationValue = threat.locations.get(0);
        Section location = ship[locationValue.first][locationValue.second];

        switch (system)
        {
            case "B" :
            {
                if(!location.malfBDamage.isEmpty())
                {
                    for (InternalDamageBundle db:location.malfBDamage)
                    {
                        message += ProcessDamageBundle(db,threat);
                    }
                }
            }
            case "C" :
            {
                if(!location.malfCDamage.isEmpty())
                {
                    for (InternalDamageBundle db:location.malfCDamage)
                    {
                        message += ProcessDamageBundle(db,threat);
                    }
                }
            }
            default :
            {
                System.out.print(threat.name + " has an error in it's damage action");
            }
        }
        return message;
    }
    private String ProcessDamageBundle(InternalDamageBundle bundle, ThreatInternal threat)
    {
        String message = "";
        if(bundle.heroic)
        {
            threat.damage+=2;
            message += MainActivity.game.players[bundle.playerID].playerName + " heroically repairs the " + threat.name + " for 2 damage!";
        }
        else
        {
            threat.damage++;
            message += MainActivity.game.players[bundle.playerID].playerName + " repairs the " + threat.name + " for 1 damage!";
        }
        return message;
    }

    public OnDamageInternalMalfSingle(String system)
    {
        this.system = system;
    }
}
