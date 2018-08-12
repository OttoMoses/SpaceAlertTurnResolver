package com.example.otto.spacealertresolver.ThreatActions.External;

import com.example.otto.spacealertresolver.Section;
import com.example.otto.spacealertresolver.Threats.Threat;
import com.example.otto.spacealertresolver.Threats.ThreatExternal;

/**
 * Created by Otto on 2/26/2018.
 */

public abstract class ActionEffectExternal
{
    public abstract String Execute(Section[][] ship, ThreatExternal threat);
}
