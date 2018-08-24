package com.otto.spacealertresolver.Stations;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

/**
 * Created by Otto on 1/28/2018.
 */

public class BattlebotStation extends ActionStation {
    @Override
    public String Activate(Player player, boolean heroic)
    {
        Section location = game.ship[player.zonePosition][player.sectionPosition];

        String message = "";
        if(game.gameType > 3)
        {
            if (player.leadingBots)
            {
                message += "tries to lead a squad of battlebots but already has one";
            }
            else
            {
                if (location.hasBots) {
                    player.leadingBots = true;
                    location.hasBots = false;
                    message += "starts leading a squad of battlebots";
                }
                else
                {
                    message += "tries to lead a squad of battlebots but can't find any";
                }
            }
        }
        else
        {
            message += "does nothing";
        }
        return message;
    }

    @Override
    public void StartGame()
    {
        name = getClass().toString();
    }
}
