package com.example.otto.spacealertresolver.Stations;

import com.example.otto.spacealertresolver.Player;

/**
 * Created by Otto on 1/28/2018.
 */

public abstract class ActionStation
{
    public String name;
    public abstract String Activate(Player player,boolean heroic);
    public abstract void StartGame();
}
