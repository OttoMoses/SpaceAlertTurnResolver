package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatExternal;

/**
 * Created by Otto on 2/26/2018.
 */

public abstract class ActionEffectExternal
{
    public abstract String Execute(Section[][] ship, ThreatExternal threat);
}
