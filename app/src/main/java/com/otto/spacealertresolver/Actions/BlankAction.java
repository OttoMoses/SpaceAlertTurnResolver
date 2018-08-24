package com.otto.spacealertresolver.Actions;

import com.otto.spacealertresolver.Player;

/**
 * Created by Otto on 1/22/2018.
 */

public class BlankAction extends PlayerAction
{
    public BlankAction()
    {
        name = "Blank action";
    }
    public  String Execute(Player player)
    {
        return "does nothing.";
    }
}
