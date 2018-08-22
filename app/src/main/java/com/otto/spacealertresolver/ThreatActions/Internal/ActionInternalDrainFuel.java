package com.otto.spacealertresolver.ThreatActions.Internal;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Stations.MainReactorStation;
import com.otto.spacealertresolver.Threats.ThreatInternal;

public class ActionInternalDrainFuel extends ActionEffectInternal
{
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        String message = "";
        if(((MainReactorStation)ship[1][0].BSystem).fuelRods != 0)
        {
            ((MainReactorStation)ship[1][0].BSystem).fuelRods--;
            message += "The " + threat.name + " consumes a fuel rod from the main reactor!";
        }
        else
        {
            message += "There are no fuel rods left for the " + threat.name + " to consume!";
        }
        return message;
    }
}
