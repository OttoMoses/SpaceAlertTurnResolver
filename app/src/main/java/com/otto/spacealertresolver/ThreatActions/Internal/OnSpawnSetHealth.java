package com.otto.spacealertresolver.ThreatActions.Internal;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

public class OnSpawnSetHealth extends ActionEffectInternal {
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        threat.health = MainActivity.game.missileCount;
        return "";
    }
}
