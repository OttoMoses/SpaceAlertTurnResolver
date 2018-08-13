package com.otto.spacealertresolver;

import com.otto.spacealertresolver.Actions.PlayerAction;
import com.otto.spacealertresolver.Activities.MainActivity;

/**
 * Created by Otto on 1/22/2018.
 */

public class Player
{
    public PlayerAction[] actions;
    public String playerName;
    public boolean leadingBots;
    public boolean flyingInterceptors;
    public boolean unconscious;
    public int zonePosition;
    public int sectionPosition;
    public Boolean damagedBots;
    public int playerID;

    public Player(String name, int numRounds)
    {
        playerName = name;
        unconscious = false;
        leadingBots = false;
        flyingInterceptors = false;
        damagedBots = false;

        //create array to store player actions
        actions = new PlayerAction[numRounds];
    }
    public String Delay(int round)
    {
        String message = "";
        for(int roundCount = actions.length - 1; roundCount >= round; roundCount--)
        {
            if(roundCount == actions.length)
            {
                actions[actions.length] = MainActivity.game.actions[0];
            }
            else
            {
                actions[roundCount] = actions[roundCount - 1];
            }
        }
        message +="\n" +  playerName + " delays their actions starting at T + " + round;
        return  message;
    }
}