package com.otto.spacealertresolver.Threats;

import android.support.annotation.NonNull;

import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

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
public int speedBoost;
public int deathScore;
public int escapeScore;
public int track;
public String cardNum;
public boolean dead = false;

public abstract String ExecuteXAction(Section[][] ship, Player[] players);
public abstract String ExecuteYAction(Section[][] ship, Player[] players);
public abstract String ExecuteZAction(Section[][] ship, Player[] players);
public abstract String ExecuteSpawnAction(Section[][] ship);
public abstract String ExecuteDeathAction(Section[][] ship, Player[] players);
public abstract String TakeDamage(int value,boolean shield);
public abstract void ResetThreat();
public int GetSpeed()
{
    return speed + speedBoost;
}
    @Override
    public int compareTo(@NonNull Threat threat)
    {
        return (this.position < threat.position? -1 :
                (this.position == threat.position ? 0 : 1));
    }
}
