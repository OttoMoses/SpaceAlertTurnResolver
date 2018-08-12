package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Threats.Threat;
import com.otto.spacealertresolver.Threats.ThreatExternal;

/**
 * Created by Otto on 3/5/2018.
 */

public abstract class OnDeathExternal
{
    public abstract String Execute(ThreatExternal t);
}
