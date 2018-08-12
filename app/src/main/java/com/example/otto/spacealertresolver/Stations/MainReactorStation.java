package com.example.otto.spacealertresolver.Stations;

import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.Player;
import com.example.otto.spacealertresolver.Section;

/**
 * Created by Otto on 1/28/2018.
 */

public class MainReactorStation extends ActionStation
{
    private int fuelRods;
    @Override
    public String Activate(Player player, boolean heroic)
    {

        String message = "";
        Section location = MainActivity.game.ship[player.zonePosition][player.sectionPosition];

        if(fuelRods != 0)
        {
            fuelRods--;
            if(location.powerCubes < location.getMaxPower())
            {
                location.powerCubes = location.getMaxPower();
                message += "refuels the central reactor to it's capacity of " + location.getMaxPower() + " power" +
                        "\n" + "There are " + fuelRods + " fuel rods left";
            }
            else
            {
                message += "wastes a fuel rod refueling the central reactor which was already at capacity" +
                        "\n" + "There are " + fuelRods + " fuel rods left";
            }
        }
        else
        {
            message += "tries to refuel the reactor but there aren't any fuel rods left";
        }

        return message;
    }

    @Override
    public void StartGame()
    {
        fuelRods = 3;
        name = "main reactor";
    }
}
