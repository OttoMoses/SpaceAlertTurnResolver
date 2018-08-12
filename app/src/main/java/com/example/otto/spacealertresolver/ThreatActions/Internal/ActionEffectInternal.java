package com.example.otto.spacealertresolver.ThreatActions.Internal;

import com.example.otto.spacealertresolver.Section;
import com.example.otto.spacealertresolver.Threats.ThreatInternal;

public abstract class ActionEffectInternal
{
    public abstract String Execute(Section[][] ship, ThreatInternal threat);
}
