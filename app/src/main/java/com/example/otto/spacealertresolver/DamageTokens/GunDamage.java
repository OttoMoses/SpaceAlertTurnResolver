package com.example.otto.spacealertresolver.DamageTokens;

import com.example.otto.spacealertresolver.Section;
import com.example.otto.spacealertresolver.Stations.ActionStation;

/**
 * Created by Otto on 3/4/2018.
 */

public class GunDamage extends DamageToken
{
    @Override
    public String doDamage(int zone, Section[][] ship)
    {
        Section s = ship[zone][sectionPos];
        s.gunDamagePenalty++;
        return "The " + s.ASystem.name + " has been damaged reducing its effectiveness";

    }
    public GunDamage(int deck)
    {
        sectionPos = deck;
    }

}
