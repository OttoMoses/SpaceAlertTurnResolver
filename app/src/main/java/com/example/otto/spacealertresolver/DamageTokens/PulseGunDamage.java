package com.example.otto.spacealertresolver.DamageTokens;

import com.example.otto.spacealertresolver.Section;

import java.util.Collections;

/**
 * Created by Otto on 3/4/2018.
 */

public class PulseGunDamage extends DamageToken
{
    @Override
    public String doDamage(int zone, Section[][] ship)
    {
        Section s = ship[zone][sectionPos];
        s.gunRangePenalty++;
        return "The " + s.ASystem.name + " was damaged reducing its range";
    }

    public PulseGunDamage(int deck)
    {
        sectionPos = deck;
    }
}
