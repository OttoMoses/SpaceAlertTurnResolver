package com.example.otto.spacealertresolver.ThreatActions.External;

import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.Section;
import com.example.otto.spacealertresolver.ThreatActions.External.ActionEffectExternal;
import com.example.otto.spacealertresolver.Threats.ThreatExternal;
import com.example.otto.spacealertresolver.enums.DamageConditions;

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
        String actionText = "";
        switch (DamageConditions.valueOf(condition))
        {
            case none:
                actionText = DealDamage(ship,threat);
            break;
            case ifDamaged:
                if(threat.damage != 0)
                {
                    actionText = DealDamage(ship,threat);
                }
                else
                {
                    actionText = "Because the " + threat.name + " is undamaged it skips it's attack!";
                }
                break;
            case ifDamagedAmount:
                DealDamage(ship,threat);
                break;
        }
        return actionText;
    }
    private String DealDamage(Section[][] ship, ThreatExternal threat)
    {
        String damageMessage = "\n";
        switch (target)
        {
            case 1:
                //damage all zones for given damage value
                damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(0,damageValue,bypassBonus,false,false);
                damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(1,damageValue,bypassBonus,false,false);
                damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(2,damageValue,bypassBonus,false,false);
                break;
            case 2:
                //damage the zone the threat is in for given damage value
                damageMessage += "The " + threat.name + " attacks the " + MainActivity.game.colors[threat.track] + " zone for " + damageValue + " damage!\n"
                        + MainActivity.game.ShipDamage(threat.track,damageValue,bypassBonus,false,false);
                break;
            case 3:
            {
                //damage zones other than the one the threat is in for given damage value
                if(threat.track != 0)
                {
                    damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(1,damageValue,bypassBonus,false,false);
                    damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(2,damageValue,bypassBonus,false,false);
                }
                if(threat.track != 1)
                {
                    damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(0,damageValue,bypassBonus,false,false);
                    damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(2,damageValue,bypassBonus,false,false);
                }
                if(threat.track != 2)
                {
                    damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(0,damageValue,bypassBonus,false,false);
                    damageMessage += "The " + threat.name +MainActivity.game.ShipDamage(1,damageValue,bypassBonus,false,false);
                }
            }

        }
        return  damageMessage;
    }

    public ActionExternalDamageShip(int target, String damage, boolean bypassBonus, String condition, int conditionValue, int damageMulti)
    {
        this.damageMulti = damageMulti;
        this.target = target;
        this.bypassBonus = bypassBonus;
        this.condition = condition;
        this.damage = damage;

    }
}
