package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatExternal;

public class ActionExternalEndGame extends ActionEffectExternal
{
    @Override
    public String Execute(Section[][] ship, ThreatExternal threat)
    {

        String message = "The " + threat.name + " destroys the ship!";
        message += MainActivity.game.EndGame(true);
        return message;
    }
}
