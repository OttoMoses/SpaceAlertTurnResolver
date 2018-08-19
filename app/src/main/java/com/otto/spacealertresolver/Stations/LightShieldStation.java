package com.otto.spacealertresolver.Stations;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;

/**
 * Created by Otto on 1/28/2018.
 */

public class LightShieldStation extends ActionStation
{
    @Override
    public String Activate(Player player , boolean heroic)
    {
        String message = "";
        Section location = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
        Section powerSource = MainActivity.game.ship[location.powerX][location.powerY];
        int shieldDiff = location.getMaxPower() - location.powerCubes;
        if(location.getMaxPower() != location.powerCubes)
        {
            if (powerSource.powerCubes >= shieldDiff) {
                powerSource.powerCubes -= shieldDiff;
                location.powerCubes += shieldDiff;
                message += "powers the shields in the " + location.sectionName + " " + location.zoneName + " section to their max capacity of " + location.getMaxPower();
                if (powerSource.powerCubes != 0) {
                    message += "\n" + "The " + powerSource.zoneName + " lateral reactor has " + powerSource.powerCubes + " power remaining";
                } else {
                    message += "\n" + "The " + powerSource.zoneName + " lateral reactor has no power remaining";
                }
            } else if (powerSource.powerCubes != 0) {
                location.powerCubes += powerSource.powerCubes;
                message += " transfers " + powerSource.powerCubes + " power to the shields in " + location.sectionName + " " + location.zoneName;
                powerSource.powerCubes = 0;
                message += "\n\n" + "The " + powerSource.zoneName + "lateral reactor has no power remaining";
            } else {
                message += "tries to transfer power to the shields in " + location.sectionName + " " + location.zoneName +
                        " but there isn't any power in the " + powerSource.zoneName + "lateral reactor";
            }
        }
        else
        {
            message += "tries to transfer power to the shields in the " + location.sectionName + " " + location.zoneName + " section but the shields are at capacity";
        }
        return  message;
    }

    @Override
    public void StartGame()
    {
        name = "light shield";
    }
}
