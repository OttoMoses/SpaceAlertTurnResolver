package com.otto.spacealertresolver.Actions;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

/**
 * Created by Otto on 1/22/2018.
 */

public class MoveBlueAction extends PlayerAction
{
    public  MoveBlueAction()
    {
        name = "Move Blue action";
    }
    public  String Execute(Player player)
    {
        System.out.println("Do " + name);
        String message = "";
        Section location = null;
        if(player.zonePosition != 2)
        {
            location = game.ship[player.zonePosition][player.sectionPosition];
            location.playerCount--;
            player.zonePosition++;
            location = game.ship[player.zonePosition][player.sectionPosition];
            location.playerCount++;
            message += "moves to the " + location.sectionName + " " + location.zoneName + " section";
        }
        else
        {
            message += "tries to move towards the blue zone but is already there";
        }
        if(location != null && location.specialDelay)
        {
            message += player.Delay(game.currentRound);
        }
        if(location != null && location.specialKnockout)
        {
            player.unconscious = true;
            message += player.playerName + " is knocked out!";
        }
        return message;
    }
}
