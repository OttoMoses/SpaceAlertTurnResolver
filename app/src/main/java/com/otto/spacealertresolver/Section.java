package com.otto.spacealertresolver;

import com.otto.spacealertresolver.Stations.ActionStation;

/**
 * Created by Otto on 1/28/2018.
 */

public class Section
{
    public final String zoneName;
    public final String sectionName;
    public boolean LiftDamaged;
    public boolean combatThreat;
    public boolean malfB;
    public boolean malfC;
    public ActionStation ASystem;
    public ActionStation BSystem;
    public ActionStation CSystem;
    public boolean hasBots;
    public final int powerX;
    public final int powerY;
    public int powerCubes;
    private final int maxPower;
    private final int startPower;
    public boolean hasFired;
    private final int gunDamage;
    private final int gunRange;
    public int gunDamagePenalty;
    public int powerPenalty;
    public int gunRangePenalty;
    public boolean specialDelay;
    public int playerCount;
    public final int zonePos;
    public final int sectionPos;
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
        LiftDamaged = false;
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
            powerCubes -= damage;
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

        if(powerCubes != 0)
        {
            message += "\nThe " + BSystem.name + " has " + powerCubes + " power remaining!";
        }
        else
        {
            message += "\nThe ";
            if(zonePos != 1)
            {
                message += zoneName + " ";
            }
            message += BSystem.name + " has no power remaining!";
        }
        return message;
    }
}
