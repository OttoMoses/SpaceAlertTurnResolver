package com.example.otto.spacealertresolver.Actions;

import android.util.Pair;

import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.Player;
import com.example.otto.spacealertresolver.Section;

/**
 * Created by Otto on 1/22/2018.
 */

public class HeroicMoveAction extends PlayerAction
{
    private Pair<Integer,Integer> destination;
    public HeroicMoveAction()
    {
        name = "Heroic Move action";
    }
    public  String Execute(Player player)
    {
        player.zonePosition = destination.first;
        player.sectionPosition = destination.second;
        Section location = MainActivity.game.ship[player.zonePosition][player.sectionPosition];
        return "moves directly to the " + location.sectionName + " " + location.zoneName + " section!";
    }

    public HeroicMoveAction(Pair<Integer, Integer> destination)
    {
        this.destination = destination;
        Section location = MainActivity.game.ship[destination.first][destination.second];
        this.name = "Heroic Move action" + "(" + location.sectionName + " " + location.zoneName + " section)";
    }
}
