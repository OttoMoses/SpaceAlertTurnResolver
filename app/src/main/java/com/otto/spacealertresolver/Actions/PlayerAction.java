package com.otto.spacealertresolver.Actions;

import com.otto.spacealertresolver.Player;

/**
 * Created by Otto on 1/22/2018.
 */

public abstract class PlayerAction {
    public String name;
    public abstract String Execute(Player player);
}
