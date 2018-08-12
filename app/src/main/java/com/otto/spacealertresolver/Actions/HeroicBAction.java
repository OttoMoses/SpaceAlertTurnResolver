package com.otto.spacealertresolver.Actions;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.InternalDamageBundle;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;

/**
 * Created by Otto on 1/22/2018.
 */

public class HeroicBAction extends PlayerAction
{
    public HeroicBAction()
    {
        name = "Heroic B action";
    }
    public  String Execute(Player player)
    {

        Section location = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
        String message = "";
        if(location.malfB)
        {
            location.malfBDamage.add(new InternalDamageBundle(player.playerID,true));
            message += " attempts to repair the malfunction in the " + location.sectionName + " " + location.zoneName + " section!";
        }
        else
        {
            message += location.BSystem.Activate(player,true);
        }
        return message;
    }
}
