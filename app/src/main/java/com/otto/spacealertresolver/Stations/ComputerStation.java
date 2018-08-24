package com.otto.spacealertresolver.Stations;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

/**
 * Created by Otto on 1/28/2018.
 */

public class ComputerStation extends ActionStation {
    @Override
    public String Activate(Player player, boolean heroic)
    {
        if(game.gameType > 2)
        {
            game.computerMaintained = true;
            return  "jiggles the mouse";
        }
        else
        {
            return "does nothing";
        }
    }

    @Override
    public void StartGame()
    {
        name = getClass().toString();
    }
}
