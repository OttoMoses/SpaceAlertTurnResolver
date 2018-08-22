package com.otto.spacealertresolver.ThreatActions.Internal;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class OnSpawnSetHealth extends ActionEffectInternal {
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        threat.health = game.missileCount;
        if(threat.health == 0)
        {
            game.deadThreats.add(threat);
        }
        return "";
    }
}
