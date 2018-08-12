package com.example.otto.spacealertresolver.ThreatActions.External;

import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.ThreatActions.External.OnDeathExternal;
import com.example.otto.spacealertresolver.Threats.Threat;
import com.example.otto.spacealertresolver.Threats.ThreatExternal;

import java.util.ArrayList;

/**
 * Created by Otto on 3/6/2018.
 */

public class OnDeathExternalDamageOthers extends OnDeathExternal
{
    @Override
    public String Execute(ThreatExternal threat)
    {
        String message = "";
        ArrayList<Threat> targets = MainActivity.game.activeThreats;
        for(Threat t : targets)
        {
            t.damage += 1;
            message += "On dying the " + t.name + " did damage to all other threats!";
        }
        return message;
    }
}
