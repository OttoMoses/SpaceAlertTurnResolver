package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.ThreatActions.External.OnDeathExternal;
import com.otto.spacealertresolver.Threats.Threat;
import com.otto.spacealertresolver.Threats.ThreatExternal;

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
            if(t.getClass() == ThreatExternal.class)
            {
                t.damage += 1;
                if(((ThreatExternal)t).damageAction.getClass() == OnDamageExternalCount.class)
                {
                    ((OnDamageExternalCount)((ThreatExternal) t).damageAction).damaged = true;
                }
            }
            message += "On dying the " + t.name + " did damage to all other external threats!";
        }
        return message;
    }
}
