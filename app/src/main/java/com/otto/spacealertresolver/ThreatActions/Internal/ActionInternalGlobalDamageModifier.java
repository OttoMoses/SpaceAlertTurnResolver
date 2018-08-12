package com.otto.spacealertresolver.ThreatActions.Internal;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

public class ActionInternalGlobalDamageModifier extends ActionEffectInternal
{
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        MainActivity.game.fissureMod++;
        return "The Fissure widens!\n";
    }
}
