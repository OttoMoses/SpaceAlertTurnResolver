package com.otto.spacealertresolver.ThreatActions.Internal;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

public class ActionInternalEndGame extends ActionEffectInternal {
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        String message = "";
        switch (threat.name)
        {
            case "Alien" :
            {
                message += "The Alien takes over the ship!";
                break;
            }
            case "Fissure" :
            {
                message += "The ship breaks apart!";
                break;
            }
            case "Nuclear Device" :
            {
                message += "The Nuclear Device detonates!";
            }
        }
        message += MainActivity.game.EndGame(true);
        return message;
    }
}
