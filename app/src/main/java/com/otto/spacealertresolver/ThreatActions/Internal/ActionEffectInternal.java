package com.otto.spacealertresolver.ThreatActions.Internal;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

public abstract class ActionEffectInternal
{
    public abstract String Execute(Section[][] ship, ThreatInternal threat);
}
