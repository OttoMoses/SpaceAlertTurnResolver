package com.otto.spacealertresolver.ThreatActions.Internal;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class OnDeathInternalRemoveDelay extends OnDeathInternal
{
    @Override
    public String Execute(ThreatInternal threat)
    {
        Section location = game.ship[threat.locations.get(0).first][threat.locations.get(0).second];
        location.specialDelay = false;
        return "";
    }
}
