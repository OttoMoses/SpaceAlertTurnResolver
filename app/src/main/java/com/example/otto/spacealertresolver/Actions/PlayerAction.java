package com.example.otto.spacealertresolver.Actions;

import com.example.otto.spacealertresolver.Player;

/**
 * Created by Otto on 1/22/2018.
 */

public abstract class PlayerAction {
    public String name;
    public abstract String Execute(Player player);
}
