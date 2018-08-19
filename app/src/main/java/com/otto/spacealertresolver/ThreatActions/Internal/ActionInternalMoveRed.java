package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

public class ActionInternalMoveRed extends ActionEffectInternal
{

    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        String message = "";
        Section location;
        int currentZone = threat.locations.get(0).first;
        int currentSection = threat.locations.get(0).second;
        location = ship[currentZone][currentSection];
        location.combatThreat = false;
        if(currentZone !=0)
        {
            currentZone--;
        }
        Pair<Integer, Integer> newLocation = new Pair<>(currentZone,currentSection);
        threat.locations.set(0,newLocation);
        location = ship[threat.locations.get(0).first][threat.locations.get(0).second];
        location.combatThreat = true;
        message += "The " + threat.name + " moves to the " + location.sectionName + " " + location.zoneName + " section!";
        return message;
    }
}
