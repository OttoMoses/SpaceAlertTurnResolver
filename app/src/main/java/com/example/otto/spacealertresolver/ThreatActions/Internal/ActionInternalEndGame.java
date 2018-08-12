package com.example.otto.spacealertresolver.ThreatActions.Internal;

import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.Game;
import com.example.otto.spacealertresolver.Section;
import com.example.otto.spacealertresolver.Threats.ThreatInternal;

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
        }
        message += MainActivity.game.EndGame(true);
        return message;
    }
}
