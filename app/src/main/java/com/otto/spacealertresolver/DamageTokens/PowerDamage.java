package com.otto.spacealertresolver.DamageTokens;

import com.otto.spacealertresolver.Section;

/**
 * Created by Otto on 3/4/2018.
 */

public class PowerDamage extends DamageToken
{
    @Override
    public String doDamage(int zone, Section[][] ship)
    {
        Section s = ship[zone][sectionPos];
        s.powerPenalty++;
        if(s.powerCubes > s.getMaxPower())
        {
            s.powerCubes = s.getMaxPower();
        }
        return "The " + s.BSystem.name + " was damaged reducing its power capacity";
    }

    public PowerDamage(int deck)
    {
        sectionPos = deck;
    }
}
