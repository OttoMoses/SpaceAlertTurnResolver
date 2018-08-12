package com.example.otto.spacealertresolver.Actions;

import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.Player;
import com.example.otto.spacealertresolver.Section;

/**
 * Created by Otto on 2/1/2018.
 */

public class TurboLiftAction extends PlayerAction {
    public TurboLiftAction()
    {
        name = "TurboLift action";
    }
    @Override
    public String Execute(Player player)
    {
        System.out.println("Do " + name);
        String message = "";
        Section startLocation = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
            if(player.sectionPosition == 1)
            {
                startLocation.playerCount--;
                player.sectionPosition = 0;
                Section endLocation = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
                endLocation.playerCount++;
                message += "moves to the " + endLocation.sectionName + " " + endLocation.zoneName + " section";
            }
            else
            {
                startLocation.playerCount--;
                player.sectionPosition = 1;
                Section endLocation = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
                endLocation.playerCount++;
                message += "moves to the " + endLocation.sectionName + " " + endLocation.zoneName + " section";
            }
        if(startLocation.liftUsed || startLocation.LiftDamaged)
        {
           message += player.Delay(MainActivity.game.currentRound);
        }
        startLocation.liftUsed = true;
        startLocation.liftUsed = true;
        return message;

    }
}
