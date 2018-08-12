package com.otto.spacealertresolver.ThreatActions.Internal;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import java.util.ArrayList;

public class ThreatActionInternal
{
    private ArrayList<ActionEffectInternal> effects;

    public String Execute(Section[][] ship, ThreatInternal threatInternal)
    {
        String actionMessage = "";
        for(ActionEffectInternal effect : effects)
        {
            actionMessage += effect.Execute(ship,threatInternal);
        }
        return actionMessage;
    }
    public ThreatActionInternal(ArrayList<ActionEffectInternal> effects) {this.effects = effects;}
}
