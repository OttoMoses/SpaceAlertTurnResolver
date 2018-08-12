package com.example.otto.spacealertresolver.Stations;

import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.Player;
import com.example.otto.spacealertresolver.Section;

/**
 * Created by Otto on 1/28/2018.
 */

public class InterceptorStation extends ActionStation
{
    @Override
    public String Activate(Player player, boolean heroic)
    {
        String message = "";
        if(player.leadingBots)
        {
            if(MainActivity.game.interceptorsDocked)
            {
                player.flyingInterceptors = true;
                MainActivity.game.interceptorsDocked = false;
                MainActivity.game.strafingRun = true;
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
        return message;
    }

    @Override
    public void StartGame()
    {
        name = getClass().toString();
    }
}
