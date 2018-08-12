package com.otto.spacealertresolver.Actions;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.InternalDamageBundle;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;

/**
 * Created by Otto on 1/22/2018.
 */

public class CAction extends PlayerAction
{
    public CAction()
    {
        name = "C action";
    }
    public  String Execute(Player player)
    {
        Section location = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
        String message = "";
        if(location.malfC)
        {
            location.malfCDamage.add(new InternalDamageBundle(player.playerID,false));
            message += " attempts to repair the malfunction in the " + location.sectionName + " " + location.zoneName + " section!";
        }
        else
        {
            message += location.CSystem.Activate(player,false);
        }
        return message;
    }
}
