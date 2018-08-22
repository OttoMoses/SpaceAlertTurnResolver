package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.ThreatActions.External.ActionEffectExternal;
import com.otto.spacealertresolver.Threats.ThreatExternal;

/**
 * Created by Otto on 3/4/2018.
 */

public class ActionExternalDamageShip extends ActionEffectExternal
{
    private int target;
    private String damage;
    private boolean bypassBonus;
    private String condition;
    private int damageMulti;
    private int damageValue;

    @Override
    public String Execute(Section[][] ship, ThreatExternal threat)
    {
        switch (damage)
        {
            case "health":
                damageValue = (threat.health - threat.damage);
                break;
            case "healthMulti":
                damageValue = ((threat.health - threat.damage) * damageMulti);
                break;
            default:
                damageValue = Integer.parseInt(damage);
                break;
        }
        String actionText;
        switch (condition)
        {
            case "none":
                {
                actionText = DealDamage(ship, threat,false);
                }
            break;
            case "ifDamaged":
                {
                    if (threat.damage != 0)
                    {
                        actionText = DealDamage(ship, threat,false);
                    }
                    else
                    {
                        actionText = "Because the " + threat.name + " is undamaged it skips it's attack!";
                    }
                break;
                }
            case "noShield":
                {

                    actionText = DealDamage(ship, threat,true);
                    break;
                }
                default:
                {
                    int value = Integer.parseInt(condition);
                    if(threat.damage < value)
                    {
                        actionText = DealDamage(ship, threat,false);
                    }
                    else
                    {
                        actionText = "Because the " + threat.name + " has at least " + value + " damage it skips its attack!";
                    }
                    break;
                }
        }
        return actionText;
    }
    private String DealDamage(Section[][] ship, ThreatExternal threat,boolean ignoreShields)
    {
        String damageMessage = "";
        switch (target)
        {
            case 1:
                //damage all zones for given damage value
                damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(0,damageValue,bypassBonus,ignoreShields,false) + "\n\n";
                damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(1,damageValue,bypassBonus,ignoreShields,false) + "\n\n";
                damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(2,damageValue,bypassBonus,ignoreShields,false);
                break;
            case 2:
                //damage the zone the threat is in for given damage value
                damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(threat.track,damageValue,bypassBonus,ignoreShields,false);
                break;
            case 3:
            {
                //damage zones other than the one the threat is in for given damage value
                if(threat.track == 0)
                {
                    damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(1,damageValue,bypassBonus,ignoreShields,false) + "\n\n";
                    damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(2,damageValue,bypassBonus,ignoreShields,false);
                }
                if(threat.track == 1)
                {
                    damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(0,damageValue,bypassBonus,ignoreShields,false) + "\n\n";
                    damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(2,damageValue,bypassBonus,ignoreShields,false);
                }
                if(threat.track == 2)
                {
                    damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(0,damageValue,bypassBonus,ignoreShields,false) + "\n\n";
                    damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(1,damageValue,bypassBonus,ignoreShields,false);
                }
                break;
            }
            case 4:
            {
                //damage only the side zones
                damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(0,damageValue,bypassBonus,ignoreShields,false) + "\n\n";
                damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(2,damageValue,bypassBonus,ignoreShields,false);
                break;
            }

        }
        return  damageMessage;
    }

    public ActionExternalDamageShip(int target, String damage, boolean bypassBonus, String condition, int damageMulti)
    {
        this.damageMulti = damageMulti;
        this.target = target;
        this.bypassBonus = bypassBonus;
        this.condition = condition;
        this.damage = damage;
    }
}
