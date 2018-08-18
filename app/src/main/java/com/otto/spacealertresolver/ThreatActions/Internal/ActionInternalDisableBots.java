package com.otto.spacealertresolver.ThreatActions.Internal;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

public class ActionInternalDisableBots extends ActionEffectInternal
{
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        String message = "";
        Section location = ship[threat.locations.get(0).first][threat.locations.get(0).second];
        if (location.hasBots)
        {
            location.hasBots = false;
            message += "The " + threat.name + " destroys the battlebots in the " + location.sectionName + " " + location.zoneName + " section!";
        }
        else
        {
            message += "There are no bots in the " + location.sectionName + " " + location.zoneName + " section for the " + threat.name + " to destroy!";
        }
        return message+ "\n";
    }
}
