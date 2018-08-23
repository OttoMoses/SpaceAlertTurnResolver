package com.otto.spacealertresolver.ThreatActions.Internal;

import com.otto.spacealertresolver.Threats.ThreatInternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class OnDeathInternalRemoveGlobalDamageModifier extends OnDeathInternal
{
    @Override
    public String Execute(ThreatInternal threat)
    {
        game.fissureMod = 0;
        return "The fissure is now sealed";
    }
}
