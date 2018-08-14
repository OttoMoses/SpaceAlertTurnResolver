package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;
import com.otto.spacealertresolver.enums.InternalThreatTypes;

public class SetInternalPosition extends ActionEffectInternal {
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        StringBuilder message = new StringBuilder();
        Section location;
        switch (InternalThreatTypes.valueOf(threat.threatType))
        {
            case combat:
                location = ship[threat.locations.get(0).first][threat.locations.get(0).second];
                location.combatThreat = true;
                message.append("The ").append(threat.name);
                if(threat.plural)
                {
                    message.append(" appear ");
                }
                else
                {
                    message.append(" appears ");
                }
                message.append("in the ").append(location.sectionName).append(" ").append(location.zoneName).append(" section!");
                break;
            case malfC:
                for (Pair<Integer,Integer> p: threat.locations)
                {
                    location = ship[p.first][p.second];
                    location.malfC = true;
                    message.append("A Malfunction of the type ").append(threat.name).append(" occurs in the ").append(location.sectionName).append(" ").append(location.zoneName).append(" C system!\n");
                }
                break;
            case malfB:
                for (Pair<Integer,Integer> p: threat.locations)
                {
                    location = ship[p.first][p.second];
                    location.malfB = true;
                    message.append("A Malfunction of the type ").append(threat.name).append(" occurs in the ").append(location.sectionName).append(" ").append(location.zoneName).append(" B system!");
                }
                break;
        }
        return message.toString();
    }
}
