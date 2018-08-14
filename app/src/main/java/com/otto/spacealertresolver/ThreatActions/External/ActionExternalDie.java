package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatExternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class ActionExternalDie extends ActionEffectExternal
{
    @Override
    public String Execute(Section[][] ship, ThreatExternal threat)
    {
        game.deadThreats.add(threat);
        return game.KillThreats(game.deadThreats);
    }
}
