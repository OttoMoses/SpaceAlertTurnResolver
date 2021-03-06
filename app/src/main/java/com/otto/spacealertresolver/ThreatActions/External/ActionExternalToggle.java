package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatExternal;

/**
 * Created by Otto on 3/6/2018.
 */

public class ActionExternalToggle extends ActionEffectExternal
{
    @Override
    public String Execute(Section[][] ship, ThreatExternal threat)
    {
        if(threat.damageAction.getClass().toString().equals("class com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalToggle"))
        {
            OnDamageExternalToggle damageEffect = (OnDamageExternalToggle)threat.damageAction;
            threat.toggle = true;
            return "The " + threat.name + " is no longer immune to damage!";
        }
        else
        {
            return  "The " + threat.name + " has an error in it's XML";
        }
    }
}
