package com.otto.spacealertresolver.Actions;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.InternalDamageBundle;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;

/**
 * Created by Otto on 1/22/2018.
 */

public class BattleBotsAction extends PlayerAction
{
    public BattleBotsAction()
    {
        name = "BattleBots action";
    }
    public  String Execute(Player player)
    {
        String message = "";
        Section location = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
        if(player.flyingInterceptors)
        {
            if(MainActivity.game.ship[0][0].malfC)
            {
                message += "attempts to repair the fissure with their interceptors!";
                MainActivity.game.ship[0][0].malfCDamage.add(new InternalDamageBundle(player.playerID,false));
            }
            else
            {
                message += "leads their bots in a strafing run!";
                MainActivity.game.strafingRun = true;
                MainActivity.game.strafeHeroic = false;
            }
        }
        else if(location.combatThreat)
        {
            message += "leads their bots in combat in the " + location.sectionName + " " + location.zoneName + " zone!";
            location.combatDamage.add(new InternalDamageBundle(player.playerID,false));
        }
        else
        {
            message += "Tries to fight but has no target!";
        }
        return message;
    }
}
