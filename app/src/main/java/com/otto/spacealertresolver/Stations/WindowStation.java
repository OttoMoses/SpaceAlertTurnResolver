package com.otto.spacealertresolver.Stations;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

/**
 * Created by Otto on 1/28/2018.
 */

public class WindowStation extends ActionStation {
    @Override
    public String Activate(Player player, boolean heroic)
    {
        int count;
        String message = "";
        if(game.gameType > 4)
        {
            switch (game.phase)
            {
            case 1:
                count = game.observationOne;
                if(count < 5)
                {
                    count++;
                    message += "looks out the window and visually confirms the scanner data";
                }
                else
                {
                    message += "looks out the window but all the data this phase already been confirmed!";
                }
                game.observationOne = count;
                break;
            case 2:
                count = game.observationTwo;
                if(count < 5)
                {
                    count++;
                    message += "looks out the window and visually confirms the scanner data";
                }
                else
                {
                    message += "looks out the window but all the data this phase already been confirmed!";
                }
                game.observationTwo = count;
                break;
            case 3:
                count = game.observationThree;
                if(count < 5)
                {
                    count++;
                    message += "looks out the window and visually confirms the scanner data";
                }
                else
                {
                    message += "looks out the window but all the data this phase already been confirmed!";
                }
                game.observationThree = count;
                break;
            }
        }
        else
        {
            message += "does nothing";
        }
        return  message;
    }

    @Override
    public void StartGame()
    {
        name = getClass().toString();
    }
}
