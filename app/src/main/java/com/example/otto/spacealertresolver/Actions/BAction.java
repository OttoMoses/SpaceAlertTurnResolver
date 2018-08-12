package com.example.otto.spacealertresolver.Actions;

import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.InternalDamageBundle;
import com.example.otto.spacealertresolver.Player;
import com.example.otto.spacealertresolver.Section;

/**
 * Created by Otto on 1/22/2018.
 */

public class BAction extends PlayerAction
{
    public BAction()
    {
        name = "B action";
    }
    public  String Execute(Player player)
    {
        Section location = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
        String message = "";
        if(location.malfB)
        {
            location.malfBDamage.add(new InternalDamageBundle(player.playerID,false));
            message += " attempts to repair the malfunction in the " + location.sectionName + " " + location.zoneName + " section!";
        }
        else
        {
            message += location.BSystem.Activate(player,false);
        }
        return message;
    }
}
