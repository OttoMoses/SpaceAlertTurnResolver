package com.example.otto.spacealertresolver.ThreatActions.External;

import com.example.otto.spacealertresolver.ExternalDamageBundle;
import com.example.otto.spacealertresolver.Threats.ThreatExternal;

/**
 * Created by Otto on 3/5/2018.
 */

public abstract class OnDamageExternal
{
    public abstract String Execute(ThreatExternal t, ExternalDamageBundle db);
}
