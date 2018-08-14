package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.ThreatActions.External.ActionEffectExternal;
import com.otto.spacealertresolver.Threats.Threat;
import com.otto.spacealertresolver.Threats.ThreatExternal;
import com.otto.spacealertresolver.enums.Stats;

/**
 * Created by Otto on 3/4/2018.
 */

public class ActionExternalBuff extends ActionEffectExternal
{
    private String stat;
    private String amount;
    private int value;
    @Override
    public String Execute(Section[][] ship, ThreatExternal threat)
    {
        String actionText = "\n";
        switch (amount)
        {
            case "halfHealth":
            {
                value = threat.damage / 2;
            }
            case "default":
            {
                value = Integer.parseInt(amount);
            }
        }
        switch (Stats.valueOf(stat))
        {
            case shieldBuff:
                threat.shield += value;
                actionText += "The " + threat.name + " raises it's shield by " + amount + "!\n";
                break;
            case speed:
                threat.speed += value;
                actionText += "The " + threat.name + " raises it's speed by " + amount + "!\n";
                break;
            case damage:
                int damage = threat.damage;
                if(threat.damage == 0)
                {
                    actionText += "The " + threat.name + " tries to heal but hasn't taken any damage!\n";
                }
                else if(value <= damage)
                {
                    damage = 0;
                    threat.damage = damage;
                    actionText += "The " + threat.name + " Heals all of it's damage!\n";
                }
                else
                {
                    damage -= value;
                    threat.damage = damage;
                    actionText += "The " + threat.name + " Heals " + amount + " damage!\n";
                }
                break;
            case shieldSet:
                threat.shield = value;
                actionText += "The " + threat.name + " sets it's shield to " + amount + "!\n";
        }
        return actionText;
    }

    public ActionExternalBuff(String stat, String amount) {
        this.stat = stat;
        this.amount = amount;
    }
}
