package com.otto.spacealertresolver;

import com.otto.spacealertresolver.Stations.ActionStation;

import java.util.ArrayList;

/**
 * Created by Otto on 1/28/2018.
 */

public class Section
{
    public String zoneName;
    public String sectionName;
    public boolean LiftDamaged;
    public boolean combatThreat;
    public boolean malfB;
    public boolean malfC;
    public ActionStation ASystem;
    public ActionStation BSystem;
    public ActionStation CSystem;
    public boolean hasBots;
    public int powerX;
    public int powerY;
    public int powerCubes;
    private int maxPower;
    private int startPower;
    public boolean hasFired;
    private int gunDamage;
    private int gunRange;
    public int gunDamagePenalty;
    public int powerPenalty;
    public int gunRangePenalty;
    public boolean specialDelay;
    public int playerCount;
    public int zonePos;
    public int sectionPos;
    public boolean heroicFire;
    public boolean liftUsed;
    public boolean specialKnockout;
    public InternalDamageBundle combatDamage;
    public InternalDamageBundle malfBDamage;
    public InternalDamageBundle malfCDamage;

    public Section(String zoneName,String sectionName, boolean bots,int powerX, int powerY,int startPower,int maxPower,int gunDamage,int gunRange,int zonePos,int sectionPos)
    {
        this.zoneName = zoneName;
        this.sectionName = sectionName;
        LiftDamaged = false;
        this.powerX = powerX;
        this.powerY = powerY;
        this.maxPower = maxPower;
        this.startPower = startPower;
        this.gunRange = gunRange;
        this.gunDamage = gunDamage;
        this.combatDamage = null;
        this.malfBDamage = null;
        this.malfCDamage = null;
        this.zonePos = zonePos;
        this.sectionPos = sectionPos;
    }
    public void StartGame()
    {
        powerCubes = startPower;
        hasFired = false;
        heroicFire = false;
        powerPenalty = 0;
        gunDamagePenalty = 0;
        gunRangePenalty = 0;
        liftUsed = false;
        specialDelay = false;
        specialKnockout = false;
    }
    public int getMaxPower()
    {
        return maxPower - powerPenalty;
    }
    public int getGunDamage()
    {
        return  gunDamage - gunDamagePenalty;
    }
    public int getGunRange()
    {
        return  gunRange - gunRangePenalty;
    }
    public String DrainPower(int damage)
    {
        String message = "";
        if(powerCubes > damage)
        {
            message += "drains " + damage + " power from the ";
            powerCubes =- damage;
        }
        else
        {
            message += "drains all power from the ";
            powerCubes = 0;
        }
        if(zonePos != 1)
        {
            message += zoneName;
        }
        message += " " + BSystem.name;

        message += "\n";

        if(powerCubes != 0)
        {
            message += "The " + BSystem.name + " has " + powerCubes + " power remaining!";
        }
        else
        {
            message += "The ";
            if(zonePos != 1)
            {
                message += zoneName + " ";
            }
            message += BSystem.name + " has no power remaining!";
        }
        message += "\n";
        return message;
    }
}
