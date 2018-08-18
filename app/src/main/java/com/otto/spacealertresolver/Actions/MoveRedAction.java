package com.otto.spacealertresolver.Actions;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

/**
 * Created by Otto on 1/22/2018.
 */

public class MoveRedAction extends PlayerAction
{
    public MoveRedAction()
    {
        name = "Move Red action";
    }
    public  String Execute(Player player)
    {
        System.out.println("Do " + name);
        String message = "";
        Section location = null;
        if(player.zonePosition != 0)
        {
            location = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
            location.playerCount--;
            player.zonePosition--;
            location = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
            location.playerCount++;
            message += "moves to the " + location.sectionName + " " + location.zoneName + " section";
        }
        else
        {
            message += "tries to move towards the red zone but is already there";
        }
        if(location != null && location.specialDelay)
        {
            message += player.Delay(game.currentRound);
        }
        if(location.specialKnockout)
        {
            player.unconscious = true;
            message += player.playerName + " is knocked out!";
        }
        return message;
    }
}
