package com.otto.spacealertresolver.ThreatActions.Internal;

import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

public abstract class OnDamageInternal
{
    public abstract String Execute(Section[][] ship, ThreatInternal threat,Player[] players);
}
