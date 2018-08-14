package com.otto.spacealertresolver.Threats;

import android.support.annotation.NonNull;

import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;

/**
 * Created by Otto on 1/22/2018.
 */

public abstract class Threat implements Comparable<Threat>
{
public String name;
public String spawnMessage;
public int position;
public int speed;
public int health;
public int damage;
public int healthBonus;
public int shieldBonus;
public int speedBoost;
public int deathScore;
public int escapeScore;
public int track;

public abstract String ExecuteXAction(Section[][] ship, Player[] players);
public abstract String ExecuteYAction(Section[][] ship, Player[] players);
public abstract String ExecuteZAction(Section[][] ship, Player[] players);
public abstract String ExecuteSpawnAction(Section[][] ship);
public abstract String ExecuteDeathAction(Section[][] ship, Player[] players);
    @Override
    public int compareTo(@NonNull Threat threat) {
        return (this.position < threat.position? -1 :
                (this.position == threat.position ? 0 : 1));
    }
}
