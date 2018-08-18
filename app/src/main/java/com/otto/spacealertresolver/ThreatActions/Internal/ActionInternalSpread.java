package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import java.util.ArrayList;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class ActionInternalSpread extends ActionEffectInternal
{
    private String direction;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        String message = "";
        Section location;
        switch (direction)
        {
            case "right" :
            {
                location = ship[(threat.locations.get(0).first + 1)][threat.locations.get(0).second];
                break;
            }
            case "left" :
            {
                location = ship[(threat.locations.get(0).first - 1)][threat.locations.get(0).second];
                break;
            }
            default:
            {
                return "The " + threat.name + " has an error in it's XML";
            }
        }
        if(location.specialDelay)
        {
            message += "The " + threat.name + " tries to spread to the " + location.sectionName + " " + location.zoneName + " section but there is already something there!";
        }
        else
        {
            ThreatInternal progeny = new ThreatInternal();
            progeny.locations = new ArrayList<Pair<Integer, Integer>>();
            progeny.locations.add(new Pair<Integer, Integer>(location.zonePos,location.sectionPos));
            progeny.xAction = threat.xAction;
            progeny.yAction = threat.yAction;
            progeny.zAction = threat.zAction;
            progeny.track = threat.track;
            progeny.spawnAction = threat.spawnAction;
            progeny.damageEffect = threat.damageEffect;
            progeny.health = 1;
            progeny.speed = threat.speed;
            progeny.name = "Progeny Slime";
            progeny.plural = threat.plural;
            progeny.deathAction = threat.deathAction;
            progeny.threatType = threat.threatType;
            progeny.position = threat.position + progeny.speed;
            game.activeThreats.add((game.activeThreats.indexOf(threat) + 1),progeny);
            game.internalThreats.add(progeny);
            location.combatThreat = true;
            location.specialDelay = true;
            message += "The " + threat.name + " spreads to the "  + location.sectionName + " " + location.zoneName + " section!";
        }
        return message + "\n";
    }

    public ActionInternalSpread(String direction) {
        this.direction = direction;
    }
}
