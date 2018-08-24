package com.otto.spacealertresolver.Stations;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

/**
 * Created by Otto on 1/28/2018.
 */

public class InterceptorStation extends ActionStation
{
    @Override
    public String Activate(Player player, boolean heroic)
    {
        String message = "";
        if(game.gameType > 3)
        {
            if(player.leadingBots)
            {
                if(game.interceptorsDocked)
                {
                    player.flyingInterceptors = true;
                    game.interceptorsDocked = false;
                    game.strafingRun = true;
                    message += " takes off in interceptors with their squad of battlebots and performs a strafing run";
                    //code for strafing run here
                }
                else
                {
                    message += "tries to take off in interceptors but they aren't docked";
                }
            }
            else
            {
                message += "tries to take off in interceptors but has no battlebots to lead";
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
