package com.example.otto.spacealertresolver.ThreatActions.External;

import com.example.otto.spacealertresolver.Section;
import com.example.otto.spacealertresolver.ThreatActions.External.ActionEffectExternal;
import com.example.otto.spacealertresolver.Threats.Threat;
import com.example.otto.spacealertresolver.Threats.ThreatExternal;
import com.example.otto.spacealertresolver.enums.Stats;

/**
 * Created by Otto on 3/4/2018.
 */

public class ActionExternalBuff extends ActionEffectExternal
{
    private String stat;
    private int amount;
    @Override
    public String Execute(Section[][] ship, ThreatExternal threat)
    {
        String actionText = "\n";
        switch (Stats.valueOf(stat))
        {
            case shieldBuff:
                threat.shield += amount;
                actionText += "The " + threat.name + " raises it's shield by " + amount + "!\n";
                break;
            case speed:
                threat.speed += amount;
                actionText += "The " + threat.name + " raises it's speed by " + amount + "!\n";
                break;
            case damage:
                int damage = threat.damage;
                if(threat.damage == 0)
                {
                    actionText += "The " + threat.name + " tries to heal but hasn't taken any damage!\n";
                }
                else if(amount <= damage)
                {
                    damage = 0;
                    threat.damage = damage;
                    actionText += "The " + threat.name + " Heals to all of it's damage!\n";
                }
                else
                {
                    damage -= amount;
                    threat.damage = damage;
                    actionText += "The " + threat.name + " Heals " + amount + " damage!\n";
                }
                break;
            case shieldSet:
                threat.shield = amount;
                actionText += "The " + threat.name + " sets it's shield to " + amount + "!\n";
        }
        return actionText;
    }

    public ActionExternalBuff(String stat, int amount) {
        this.stat = stat;
        this.amount = amount;
    }
}
