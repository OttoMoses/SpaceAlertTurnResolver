package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import java.util.ArrayList;

public class ActionInternalDamageShip extends ActionEffectInternal
{
    private String target;
    private String damage;
    private String condtion;
    private int damageMulti;
    private ArrayList<Pair<Integer,Integer>> locations;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        StringBuilder message = new StringBuilder();
        //determine which location or locations this action affects
        switch (target)
        {
            case "self":
            {
                locations = threat.locations;
            }
        }
        for (Pair<Integer,Integer> p :locations)
        {
            int damageValue;
            //determine how much damage this action will do
            switch (damage)
            {
                case "healthMulti" :
                {
                    damageValue = (threat.health - threat.damage) * 3;
                    message.append("The ").append(threat.name).append(MainActivity.game.ShipDamage(p.first, damageValue, false, true, threat.plural));
                    break;
                }
                case "EnergyValue":
                {
                    damageValue = ship[p.first][p.second].powerCubes;
                    message.append("The ").append(threat.name).append(MainActivity.game.ShipDamage(p.first, damageValue, false, true, threat.plural));
                    break;
                }
                case "perPlayer":
                {
                    int playerCount = 0;
                    Pair<Integer,Integer> location = threat.locations.get(0);
                    for (Player player : MainActivity.game.players)
                    {
                        if(player.sectionPosition == location.first && player.zonePosition == location.second)
                        {
                            playerCount++;
                        }
                    }
                    message.append("The ").append(threat.name).append(MainActivity.game.ShipDamage(p.first, playerCount, false, true, threat.plural));
                    break;
                }
                default:
                {
                    damageValue = Integer.parseInt(damage);
                    message.append("The ").append(threat.name).append(MainActivity.game.ShipDamage(p.first, damageValue, false, true, threat.plural));
                    break;
                }
            }
        }
        return message.toString();
    }
    public ActionInternalDamageShip(String target,String damage)
    {
        this.damage = damage;
        this.target = target;
    }
}
