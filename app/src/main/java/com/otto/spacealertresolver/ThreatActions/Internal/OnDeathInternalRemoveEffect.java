package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class OnDeathInternalRemoveEffect extends OnDeathInternal
{
    String type;
    @Override
    public String Execute(ThreatInternal threat)
    {
        String message = "";
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
                        message +=  "Players are no longer delayed on entering the " + location.sectionName + " " + location.zoneName + " section!";
                    }
                }
            }
            case "knockout" :
            {
                for(Pair pair : threat.locations)
                {
                    Section location = game.ship[(Integer)pair.first][(Integer)pair.second];
                    if(location.specialKnockout)
                    {
                        location.specialKnockout = false;
                        message +=  "Players are no longer knocked out on entering the " + location.sectionName + " " + location.zoneName + " section!";
                    }
                }
            }
        }
        return message;
    }

    public OnDeathInternalRemoveEffect(String type) {
        this.type = type;
    }
}
