package com.example.otto.spacealertresolver.Actions;

import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.Player;
import com.example.otto.spacealertresolver.Section;

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
        if(player.zonePosition != 0)
        {
            Section location = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
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
        return message;
    }
}
