package com.example.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.example.otto.spacealertresolver.Section;
import com.example.otto.spacealertresolver.ThreatActions.External.ActionEffectExternal;
import com.example.otto.spacealertresolver.Threats.Threat;
import com.example.otto.spacealertresolver.Threats.ThreatInternal;
import com.example.otto.spacealertresolver.enums.InternalThreatTypes;

public class SetInternalPosition extends ActionEffectInternal {
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        String message = "";
        Section location;
        switch (InternalThreatTypes.valueOf(threat.threatType))
        {
            case combat:
                location = ship[threat.locations.get(0).first][threat.locations.get(0).second];
                location.combatThreat = true;
                message +=
                        "The " + threat.name;
                if(threat.plural)
                {
                    message += " appear ";
                }
                else
                {
                    message += " appears ";
                }
                message += "in the " + location.sectionName + " " + location.zoneName + " section!";
                break;
            case malfC:
                for (Pair<Integer,Integer> p: threat.locations)
                {
                    location = ship[p.first][p.second];
                    location.malfC = true;
                    message += "A Malfunction of the type " + threat.name + " occurs in the " + location.sectionName + " " + location.zoneName + " C system!";
                }
                break;
            case malfB:
                for (Pair<Integer,Integer> p: threat.locations)
                {
                    location = ship[p.first][p.second];
                    location.malfB = true;
                    message += "A Malfunction of the type " + threat.name + " occurs in the " + location.sectionName + " " + location.zoneName + " B system!";
                }
                break;
        }
        return message;
    }
}
