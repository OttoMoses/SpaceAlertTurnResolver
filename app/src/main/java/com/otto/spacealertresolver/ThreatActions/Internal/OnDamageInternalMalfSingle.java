package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Game;
import com.otto.spacealertresolver.InternalDamageBundle;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class OnDamageInternalMalfSingle extends OnDamageInternal
{
    public String system;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat, Player[] players)
    {
        StringBuilder message = new StringBuilder();
        Pair<Integer,Integer> locationValue = threat.locations.get(0);
        Section location = ship[locationValue.first][locationValue.second];

        switch (system)
        {
            case "B" :
            {
                if(location.malfBDamage != null)
                {
                    InternalDamageBundle db = location.malfBDamage;
                    message.append(ProcessDamageBundle(db, threat));
                    location.malfBDamage = null;
                }
                break;
            }
            case "C" :
            {
                if(location.malfCDamage != null)
                {
                    InternalDamageBundle db = location.malfCDamage;
                    message.append(ProcessDamageBundle(db, threat));
                    location.malfCDamage = null;
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
    private String ProcessDamageBundle(InternalDamageBundle bundle, ThreatInternal threat)
    {
        String message = "";
        if(bundle.heroic)
        {
            message += game.players[bundle.playerID].playerName + " heroically repairs the " + threat.name + "!";
            message += threat.TakeDamage(2,false);
        }
        else
        {
            message += game.players[bundle.playerID].playerName + " repairs the " + threat.name + "!";
            message += threat.TakeDamage(1,false);
        }
        return message;
    }

    public OnDamageInternalMalfSingle(String system)
    {
        this.system = system;
    }
}
