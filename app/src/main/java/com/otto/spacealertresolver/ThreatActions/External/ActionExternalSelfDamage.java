package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatExternal;

public class ActionExternalSelfDamage extends ActionEffectExternal
{
    private int value;
    @Override
    public String Execute(Section[][] ship, ThreatExternal threat)
    {
        threat.damage += value;
        return "The " + threat.name + " takes " + value + " damage from its own attack!";
    }

    public ActionExternalSelfDamage(int value)
    {
        this.value = value;
    }
}
