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
        StringBuilder message = new StringBuilder();
        ArrayList<Threat> targets = MainActivity.game.activeThreats;
        message.append("On dying the ").append(threat.name).append(" did damage to all other external threats!");
        for(Threat t : targets)
        {
            if(t.getClass() == ThreatExternal.class)
            {
                message.append(t.TakeDamage(1,false));
                if(((ThreatExternal)t).damageAction.getClass() == OnDamageExternalCount.class)
                {
                    ((OnDamageExternalCount)((ThreatExternal) t).damageAction).damaged = true;
                }
            }
        }
        return message.toString();
    }
}
