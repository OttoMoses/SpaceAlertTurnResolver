package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.ThreatActions.External.ActionEffectExternal;
import com.otto.spacealertresolver.Threats.ThreatExternal;
import com.otto.spacealertresolver.enums.Stats;

/**
 * Created by Otto on 3/4/2018.
 */

public class ActionExternalBuff extends ActionEffectExternal
{
    private String stat;
    private String amount;

    @Override
    public String Execute(Section[][] ship, ThreatExternal threat)
    {
        String actionText = "";
        int value;
        switch (amount)
        {
            case "halfHealth":
            {
                value = threat.damage / 2;
                break;
            }
            default:
            {
                value = Integer.parseInt(amount);
                break;
            }
        }
        switch (Stats.valueOf(stat))
        {
            case shieldBuff:
                threat.shieldBoost += value;
                actionText += "The " + threat.name + " raises its shield by " + value + "!";
                break;
            case speed:
                threat.speedBoost += value;
                actionText += "The " + threat.name + " raises its speed by " + value + "!";
                break;
            case damage:
                int damage = threat.damage;
                if(threat.damage == 0)
                {
                    actionText += "The " + threat.name + " tries to heal but hasn't taken any damage!";
                }
                else if(value >= damage)
                {
                    damage = 0;
                    threat.damage = damage;
                    actionText += "The " + threat.name + " Heals all of its damage!";
                }
                else
                {
                    damage -= value;
                    threat.damage = damage;
                    actionText += "The " + threat.name + " Heals " + value + " damage!";
                }
                break;
            case shieldSet:
                threat.shield = value;
                actionText += "The " + threat.name + " sets its shield to " + value + "!";
        }
        return actionText;
    }

    public ActionExternalBuff(String stat, String amount) {
        this.stat = stat;
        this.amount = amount;
    }
}
