package com.otto.spacealertresolver.Stations;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;

/**
 * Created by Otto on 1/28/2018.
 */

public class MissileStation extends ActionStation
{

    @Override
    public String Activate(Player player, boolean heroic)
    {
        String message = "";
        if(MainActivity.game.missileCount > 0)
        {
            if(!MainActivity.game.missileInSpace)
            {
                MainActivity.game.missileInSpace = true;
                MainActivity.game.missileCount--;
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
        return message;
    }

    @Override
    public void StartGame()
    {
        name = getClass().toString();
    }
}
