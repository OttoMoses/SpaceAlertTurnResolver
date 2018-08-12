package com.example.otto.spacealertresolver.DamageTokens;

import com.example.otto.spacealertresolver.Section;

/**
 * Created by Otto on 3/4/2018.
 */

public class StructuralDamage extends DamageToken
{


    @Override
    public String doDamage(int zone, Section[][] ship)
    {
        Section s = ship[zone][1];
        return "The ship took structural damage in the " + s.zoneName + " zone";
    }
}
