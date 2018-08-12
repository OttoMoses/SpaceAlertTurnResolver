package com.example.otto.spacealertresolver.Stations;

import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.Player;
import com.example.otto.spacealertresolver.Section;

/**
 * Created by Otto on 1/28/2018.
 */

public class BattlebotStation extends ActionStation {
    @Override
    public String Activate(Player player, boolean heroic)
    {
        Section location = MainActivity.game.ship[player.zonePosition][player.sectionPosition];

        String message = "";

            if (player.leadingBots)
            {
                message += "tries to lead a squad of battlebots but already has one";
            }
            else
            {
                if (location.hasBots) {
                    player.leadingBots = true;
                    location.hasBots = false;
                    message += "starts leading a squad of battlbots";
                }
                else
                {
                    message += "tries to lead a squad of battlebots but can't find any";
                }
            }
        return message;
    }

    @Override
    public void StartGame()
    {
        name = getClass().toString();
    }
}
