package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

public class SetInternalPosition extends ActionEffectInternal
{
    private final String special;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        StringBuilder message = new StringBuilder();
        Section location;
        switch (threat.threatType)
        {
            case "combat":
                for (Pair<Integer,Integer> p: threat.locations)
                {
                    location = ship[p.first][p.second];
                    location.combatThreat = true;
                    message.append("\nThe ").append(threat.name);
                    if (threat.plural) {
                        message.append(" appear ");
                    } else {
                        message.append(" appears ");
                    }
                    message.append("in the ").append(location.sectionName).append(" ").append(location.zoneName).append(" section!");
                    message.append(ApplyCondition(location));
                }
                break;

            case "malfC":
                for (Pair<Integer,Integer> p: threat.locations)
                {
                    location = ship[p.first][p.second];
                    location.malfC = true;
                    message.append("\nA Malfunction of the type ").append(threat.name).append(" occurs in the ").append(location.sectionName).append(" ").append(location.zoneName).append(" C system!").append(ApplyCondition(location));
                    message.append("\n").append(ApplyCondition(location));
                }
                break;
            case "malfB":
                for (Pair<Integer,Integer> p: threat.locations)
                {
                    location = ship[p.first][p.second];
                    location.malfB = true;
                    message.append("\nA Malfunction of the type ").append(threat.name).append(" occurs in the ").append(location.sectionName).append(" ").append(location.zoneName).append(" B system!").append(ApplyCondition(location));
                    message.append("\n").append(ApplyCondition(location));
                }
                break;
        }
        return message.toString();
    }
    private String ApplyCondition(Section location)
    {
        switch (special)
        {
            case "delay":
            {
                location.specialDelay = true;
                return "\nPlayers entering the " + location.sectionName + " " + location.zoneName + " section will be delayed!";
            }
            default:
            {
                return "";
            }
        }
    }
    public SetInternalPosition(String special)
    {
        this.special = special;
    }
}
