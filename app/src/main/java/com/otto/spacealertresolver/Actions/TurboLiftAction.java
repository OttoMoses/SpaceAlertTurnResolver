package com.otto.spacealertresolver.Actions;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

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
        String message = "";
        Section startLocation = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
        Section endLocation;
            if(player.sectionPosition == 1)
            {
                startLocation.playerCount--;
                player.sectionPosition = 0;
                endLocation = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
                endLocation.playerCount++;
                message += "moves to the " + endLocation.sectionName + " " + endLocation.zoneName + " section";
            }
            else
            {
                startLocation.playerCount--;
                player.sectionPosition = 1;
                endLocation = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
                endLocation.playerCount++;
                message += "moves to the " + endLocation.sectionName + " " + endLocation.zoneName + " section";
            }
        if(game.gameType > 2)
        {
            if(startLocation.liftUsed || startLocation.LiftDamaged)
            {
                message += player.Delay((game.currentRound + 1));
            }
            if(endLocation.specialDelay)
            {
                message += player.Delay((game.currentRound + 1));
            }
            if(endLocation.specialKnockout)
            {
                player.unconscious = true;
                message += player.playerName + " is knocked out!";
            }
            startLocation.liftUsed = true;
            endLocation.liftUsed = true;
        }
        return message;

    }
}
