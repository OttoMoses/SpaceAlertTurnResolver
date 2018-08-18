package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class ActionInternalSetEffect extends ActionEffectInternal
{
    String type;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        StringBuilder message = new StringBuilder();
        switch (type)
        {
            case "knockout" :
            {
               for(Pair pair : threat.locations)
               {
                   Section location = game.ship[(Integer)pair.first][(Integer)pair.second];
                   if(!location.specialKnockout)
                   {
                       location.specialKnockout = true;
                       message.append("\nPlayers entering the " + location.sectionName + " " + location.zoneName + " section will be knocked out!");
                   }
               }
            }
        }
        return message.toString();
    }
}
