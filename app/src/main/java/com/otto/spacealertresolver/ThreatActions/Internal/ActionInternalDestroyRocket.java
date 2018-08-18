package com.otto.spacealertresolver.ThreatActions.Internal;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.Threat;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class ActionInternalDestroyRocket extends ActionEffectInternal {
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        String message = "";
        if(game.missileCount != 0)
        {
            game.missileCount--;
            message += "The " + threat.name + " destroys a missile!";
            for(Threat t : game.activeThreats)
            {
                if(t.name.equals("Unstable Warheads"))
                {
                    t.TakeDamage(1,false);
                }
            }
        }
        else
        {
            message += "There are no missiles for the " + threat.name + " to destroy!";
        }
        return message + "\n";
    }
}
