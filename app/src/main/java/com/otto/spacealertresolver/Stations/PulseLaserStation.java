package com.otto.spacealertresolver.Stations;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;

/**
 * Created by Otto on 1/28/2018.
 */

public class PulseLaserStation extends ActionStation
{
    @Override
    public String Activate(Player player, boolean heroic)
    {
        String message = "";
        Section location = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
        Section powerSource = MainActivity.game.ship[location.powerX][location.powerY];
        if(!location.hasFired)
        {
            if( powerSource.powerCubes!= 0)
            {
                powerSource.powerCubes--;
                location.hasFired = true;
                if(heroic)
                {
                    location.heroicFire = true;
                }
                //code for gun to do damage here
                message += "fires the pulse laser cannon in the " + location.sectionName + " " + location.zoneName + " section";
                if(powerSource.powerCubes != 0)
                {
                    message += "\n" + "The central reactor has " + powerSource.powerCubes + " power remaining";
                }
                else
                {
                    message += "\n" + "The central reactor has no power remaining";
                }
            }
            else
            {
                message += "tries to fire the pulse laser cannon in the " + location.sectionName + " " + location.zoneName + " section but there isn't enough power in the central reactor";
            }
        }
        else
        {
            message += "tries to fire the pulse laser cannon " + location.sectionName + " " + location.zoneName + " section but it was already fired this round";
        }
        return message;
    }

    @Override
    public void StartGame()
    {
        name = "pulse cannon";
    }
}
