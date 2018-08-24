package com.otto.spacealertresolver.Stations;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

/**
 * Created by Otto on 1/28/2018.
 */

public class MissileStation extends ActionStation
{

    @Override
    public String Activate(Player player, boolean heroic)
    {
        String message = "";
        if(game.gameType > 2)
        {
            if(game.missileCount > 0)
            {
                if(!game.missileInSpace)
                {
                    game.missileInSpace = true;
                    game.missileCount--;
                    message += "fires a missile into space";
                }
                else
                {
                    message += "tries to fire a missile but one was already fired this round";
                }
            }
            else
            {
                message += "tries to fire a missile but there aren't any left";
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
