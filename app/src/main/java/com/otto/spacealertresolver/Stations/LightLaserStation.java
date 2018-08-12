package com.otto.spacealertresolver.Stations;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;

/**
 * Created by Otto on 1/28/2018.
 */

public class LightLaserStation extends ActionStation
{
    @Override
    public String Activate(Player player, boolean heroic)
    {
        Section location = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
        String message = "";
        if(!location.hasFired)
        {
            location.hasFired = true;
            //code to fire gun goes here
            message += "fires the light laser cannon in " + location.sectionName+ " " + location.zoneName;
        }
        else
        {
            message += "tries to fire the light laser cannon in " + location.sectionName+ " " + location.zoneName + " but it was already fired this turn";
        }
        return  message;
    }

    @Override
    public void StartGame()
    {
        name = "light laser cannon";
    }
}
