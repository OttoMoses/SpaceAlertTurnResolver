package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class OnDeathInternalRemoveEffect extends OnDeathInternal
{
    private final String type;
    @Override
    public String Execute(ThreatInternal threat)
    {
        StringBuilder message = new StringBuilder();
        switch (type)
        {
            case "delay" :
            {
                for(Pair pair : threat.locations)
                {
                    Section location = game.ship[(Integer)pair.first][(Integer)pair.second];
                    if(location.specialDelay)
                    {
                        location.specialDelay = false;
                        message.append("Players are no longer delayed on entering the ").append(location.sectionName).append(" ").append(location.zoneName).append(" section!");
                    }
                }
                break;
            }
            case "knockout" :
            {
                for(Pair pair : threat.locations)
                {
                    Section location = game.ship[(Integer)pair.first][(Integer)pair.second];
                    if(location.specialKnockout)
                    {
                        location.specialKnockout = false;
                        message.append("Players are no longer knocked out on entering the ").append(location.sectionName).append(" ").append(location.zoneName).append(" section!");
                    }
                }
                break;
            }
        }
        return message.toString();
    }

    public OnDeathInternalRemoveEffect(String type) {
        this.type = type;
    }
}
