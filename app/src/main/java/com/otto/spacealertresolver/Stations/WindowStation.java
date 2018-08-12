package com.otto.spacealertresolver.Stations;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Player;

/**
 * Created by Otto on 1/28/2018.
 */

public class WindowStation extends ActionStation {
    @Override
    public String Activate(Player player, boolean heroic)
    {
        int count;
        String message = "";
            switch (MainActivity.game.phase)
            {
                case 1:
                    count = MainActivity.game.observationOne;
                    if(count < 5)
                    {
                        count++;
                        message += "looks out the window and visually confirms the scanner data";
                    }
                    else
                    {
                        message += "looks out the window but all the data this phase already been confirmed!";
                    }
                    break;
                case 2:
                    count = MainActivity.game.observationTwo;
                    if(count < 5)
                    {
                        count++;
                        message += "looks out the window and visually confirms the scanner data";
                    }
                    else
                    {
                        message += "looks out the window but all the data this phase already been confirmed!";
                    }
                    break;
                case 3:
                    count = MainActivity.game.observationThree;
                    if(count < 5)
                    {
                        count++;
                        message += "looks out the window and visually confirms the scanner data";
                    }
                    else
                    {
                        message += "looks out the window but all the data this phase already been confirmed!";
                    }
            }
        return  message;
    }

    @Override
    public void StartGame()
    {
        name = getClass().toString();
    }
}
