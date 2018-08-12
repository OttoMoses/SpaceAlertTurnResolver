package com.example.otto.spacealertresolver.DamageTokens;

import com.example.otto.spacealertresolver.Section;

/**
 * Created by Otto on 3/4/2018.
 */

public abstract class DamageToken
{
    int sectionPos;
    public String damageText;

    public abstract String doDamage(int zone, Section[][] ship);
}
