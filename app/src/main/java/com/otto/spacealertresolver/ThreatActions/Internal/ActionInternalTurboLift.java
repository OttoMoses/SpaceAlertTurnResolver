package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

public class ActionInternalTurboLift extends ActionEffectInternal {
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        String message = "";
        Section location;
        int currentZone = threat.locations.get(0).first;
        int currentSection = threat.locations.get(0).second;
        location = ship[currentZone][currentSection];
        location.combatThreat = false;
        if(currentSection == 1)
        {
            currentSection = 0;
        }
        else
        {
            currentSection = 1;
        }
        threat.locations.set(0,new Pair<Integer, Integer>(currentZone,currentSection));
        location = ship[threat.locations.get(0).first][threat.locations.get(0).second];
        location.combatThreat = true;
        message += "The " + threat.name + " moves to the " + location.sectionName + " " + location.zoneName + " section!";
        return message;
    }
}
