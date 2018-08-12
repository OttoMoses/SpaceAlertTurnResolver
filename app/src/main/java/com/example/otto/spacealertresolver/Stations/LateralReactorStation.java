package com.example.otto.spacealertresolver.Stations;


import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.Player;
import com.example.otto.spacealertresolver.Section;

/**
 * Created by Otto on 1/28/2018.
 */

public class LateralReactorStation extends ActionStation
{
    @Override
    public String Activate(Player player, boolean heroic)
    {
        String message = "";
        Section location = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
        Section powerSource = MainActivity.game.ship[location.powerX][location.powerY];
        int powerDiff = location.getMaxPower() - location.powerCubes;

        if(powerSource.powerCubes >= powerDiff)
        {
            if(!heroic)
            {
                location.powerCubes += powerDiff;
                powerSource.powerCubes -= powerDiff;
                message += "powers the lateral reactor in the " +  location.sectionName+ " " + location.zoneName + " section"  + "to it's maximum capacity of " + location.getMaxPower() + "\n";
                if (powerSource.powerCubes != 0)
                {
                    message += "\n" + "The main reactor has " + powerSource.powerCubes + " power remaining";
                }
                else
                {
                    message += "\n" + "The main reactor has no power remaining";
                }
            }
            else
            {
                location.powerCubes += powerDiff;
                powerSource.powerCubes -= powerDiff;
                message += "powers the lateral reactor in the " + location.sectionName+ " " + location.zoneName + " section" + "to it's maximum capacity of " + location.getMaxPower() + " and adds a heroic bonus of 1 power for free" + "\n";
                if (powerSource.powerCubes != 0)
                {
                    message += "The main reactor has " + powerSource.powerCubes + " power remaining";
                }
                else
                {
                    message += "The main reactor has no power remaining";
                }
            }
        }
        else
        {
            location.powerCubes += powerSource.powerCubes;
            message += "transfers " + powerSource.powerCubes + " to the lateral reactor in the " + location.sectionName+ " " + location.zoneName + "\n";
            powerSource.powerCubes = 0;
            message += "The main reactor has no power remaining";
        }
        return message;
    }

    @Override
    public void StartGame()
    {
        name = "lateral reactor";
    }
}
