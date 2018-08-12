package com.otto.spacealertresolver.Stations;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;

/**
 * Created by Otto on 1/28/2018.
 */

public class ComputerStation extends ActionStation {
    @Override
    public String Activate(Player player, boolean heroic)
    {
        MainActivity.game.computerMaintained = true;
        return  "jiggles the mouse";
    }

    @Override
    public void StartGame()
    {
        name = getClass().toString();
    }
}
