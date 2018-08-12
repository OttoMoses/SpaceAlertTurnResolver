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
        ThreatExternal t = (ThreatExternal) threat;
        if(t.damageAction.getClass().toString().equals("class com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalToggle"))
        {
            OnDamageExternalToggle damageEffect = (OnDamageExternalToggle)t.damageAction;
            damageEffect.toggle = true;
            return "\nThe " + threat.name + " is no longer immune to damage!\n";
        }
        else
        {
            return  "if you see this message I fucked up the XML somewhere!\n";
        }
    }
}
