package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

public class ActionInternalConditionDamageMove extends ActionEffectInternal
{
    private int damage;
    private String direction;
    private String message = "";
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {

        if(threat.damage != 0)
        {
            if(direction.equals("red"))
            {

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
                message += "The " + threat.name + " moves to the " + location.sectionName + " " + location.zoneName + " section!" + "\n";
            }
            else
            {
                Section location;
                int currentZone = threat.locations.get(0).first;
                int currentSection = threat.locations.get(0).second;
                location = ship[currentZone][currentSection];
                location.combatThreat = false;
                if(currentZone !=3)
                {
                    currentZone++;
                }
                Pair<Integer, Integer> newLocation = new Pair<>(currentZone,currentSection);
                threat.locations.set(0,newLocation);
                location = ship[threat.locations.get(0).first][threat.locations.get(0).second];
                location.combatThreat = true;
                message += "The " + threat.name + " moves to the " + location.sectionName + " " + location.zoneName + " section!" + "\n";
            }
        }
        else
        {
            message += "The " + threat.name +  MainActivity.game.ShipDamage(threat.locations.get(0).second, damage,false,true,threat.plural);
        }
        return message;
    }

    public ActionInternalConditionDamageMove(int damage,String direction)
    {
        this.damage = damage;
        this.direction = direction;
    }
}
