package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.ThreatActions.External.ActionEffectExternal;
import com.otto.spacealertresolver.Threats.ThreatExternal;

/**
 * Created by Otto on 3/4/2018.
 */

public class ActionExternalShieldDrain extends ActionEffectExternal
{
    private String amount;
    @Override
    public String Execute(Section[][] ship, ThreatExternal threat)
    {
        String actionText = "";
        if(!amount.equals("all"))
        {
            int value = Integer.parseInt(amount);
            ship[0][1].powerCubes -= value;
            ship[1][1].powerCubes -= value;
            ship[2][1].powerCubes -= value;
            actionText += "The " + threat.name + " Drains " + value + " power from the all shields!\n";

        }
        else
        {
            ship[0][1].powerCubes = 0;
            ship[1][1].powerCubes = 0;
            ship[2][1].powerCubes = 0;
            actionText += "The " + threat.name + " Drains all power from the all shields!\n";
        }
        return actionText;
    }

    public ActionExternalShieldDrain(String amount)
    {
        this.amount = amount;
    }
}
