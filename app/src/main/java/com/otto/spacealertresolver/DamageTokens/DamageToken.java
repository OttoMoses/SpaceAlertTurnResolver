package com.otto.spacealertresolver.DamageTokens;

import com.otto.spacealertresolver.Section;

/**
 * Created by Otto on 3/4/2018.
 */

public abstract class DamageToken
{
    int sectionPos;

    public abstract String doDamage(int zone, Section[][] ship);
}
