package com.otto.spacealertresolver.Actions;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.InternalDamageBundle;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;

/**
 * Created by Otto on 1/22/2018.
 */

public class HeroicBattlebotsAction  extends PlayerAction
{
    public HeroicBattlebotsAction()
    {
        name = "Heroic Battlebots action";
    }
    public  String Execute(Player player)
    {
        String message = "";
        Section location = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
        if(player.flyingInterceptors)
        {
            if(MainActivity.game.ship[0][0].malfC)
            {
                message += "heroically attempts to repair the fissure with their interceptors!";
                MainActivity.game.ship[0][0].malfCDamage = new InternalDamageBundle(player.playerID,true);
            }
            else
            {
                message += "leads their bots in a heroic strafing run";
                MainActivity.game.strafingRun = true;
                MainActivity.game.strafeHeroic = true;
            }
        }
        else if(location.combatThreat)
        {
            message += "heroically leads their bots in combat in the " + location.sectionName + " " + location.zoneName + " zone!";
            location.combatDamage = new InternalDamageBundle(player.playerID,true);
        }
        else
        {
            message += "tries to fight but has no target!";
        }
        return message;
    }
}
