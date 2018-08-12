package com.example.otto.spacealertresolver.ThreatActions.Internal;

import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.Section;
import com.example.otto.spacealertresolver.Threats.ThreatInternal;

public class ActionInternalGlobalDamageModifier extends ActionEffectInternal
{
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        MainActivity.game.fissureMod++;
        return "The Fissure widens!\n";
    }
}
