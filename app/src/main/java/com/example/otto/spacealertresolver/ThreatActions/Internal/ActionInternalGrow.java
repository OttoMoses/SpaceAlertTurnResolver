package com.example.otto.spacealertresolver.ThreatActions.Internal;

import com.example.otto.spacealertresolver.Section;
import com.example.otto.spacealertresolver.Threats.ThreatInternal;

public class ActionInternalGrow extends ActionEffectInternal {
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        OnDamageInternalCombat effect = (OnDamageInternalCombat)threat.damageEffect;
        effect.firesBack = true;
        return "The " + threat.name + " grows up!\n";
    }
}
