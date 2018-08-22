package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import java.util.ArrayList;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class ActionInternalDamageShip extends ActionEffectInternal
{
    private final String target;
    private final String damage;
    private ArrayList<Pair<Integer,Integer>> locations;
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        StringBuilder message = new StringBuilder();
        locations = new ArrayList<>();
        //determine which location or locations this action affects
        switch (target)
        {
            case "self":
            {
                locations.addAll(threat.locations);
                break;
            }
            case "all" :
            {
                locations.add(new Pair<Integer, Integer>(0,1));
                locations.add(new Pair<Integer, Integer>(1,1));
                locations.add(new Pair<Integer, Integer>(2,1));
                break;
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
                    message.append("The ").append(threat.name).append(game.ShipDamage(p.first, damageValue, false, true, threat.plural));
                    //the only threat that uses this is unstable warheads so i'm going to stick this here, move it if another threat uses this later
                    game.missileCount = 0;
                    break;
                }
                case "EnergyValue":
                {
                    damageValue = ship[p.first][p.second].powerCubes;
                    message.append("The ").append(threat.name).append(game.ShipDamage(p.first, damageValue, false, true, threat.plural));
                    break;
                }
                case "perPlayer":
                {
                    int playerCount = 0;
                    Pair<Integer,Integer> location = threat.locations.get(0);
                    for (Player player : game.players)
                    {
                        if(player.sectionPosition == location.first && player.zonePosition == location.second)
                        {
                            playerCount++;
                        }
                    }
                    message.append("The ").append(threat.name).append(game.ShipDamage(p.first, playerCount, false, true, threat.plural));
                    break;
                }
                default:
                {
                    damageValue = Integer.parseInt(damage);
                    message.append("The ").append(threat.name).append(game.ShipDamage(p.first, damageValue, false, true, threat.plural));
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
