package com.example.otto.spacealertresolver.Actions;

import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.Player;

/**
 * Created by Otto on 1/22/2018.
 */

public class AAction extends PlayerAction
{
    public AAction()
    {
        name = "A action";
    }

    public  String Execute(Player player)
    {
        return MainActivity.game.ship[player.zonePosition][player.sectionPosition].ASystem.Activate(player,false);
    }
}
