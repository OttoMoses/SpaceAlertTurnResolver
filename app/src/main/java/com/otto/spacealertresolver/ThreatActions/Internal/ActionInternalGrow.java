package com.otto.spacealertresolver.ThreatActions.Internal;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

public class ActionInternalGrow extends ActionEffectInternal {
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        OnDamageInternalCombat effect = (OnDamageInternalCombat)threat.damageEffect;
        threat.firesBack = true;
        return "The " + threat.name + " grows up!";
    }
}
