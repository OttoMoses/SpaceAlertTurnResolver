package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.Threat;
import com.otto.spacealertresolver.Threats.ThreatExternal;

import static com.otto.spacealertresolver.Activities.MainActivity.*;

public class ActionExternalGlobalBuff extends ActionEffectExternal
{
    private String stat;
    private int value;
    @Override
    public String Execute(Section[][] ship, ThreatExternal threat)
    {
        String message = "";
        switch (stat)
        {
            case "attack":
            {
                game.globalDamageBuff += value;
                if(threat.deathAction.getClass() == (OnDeathExternalRemoveGlobalBonus.class))
                {
                    OnDeathExternalRemoveGlobalBonus action =  (OnDeathExternalRemoveGlobalBonus)threat.deathAction;
                    action.value = value;
                    action.stat = stat;
                }
                message += "The " + threat.name + " increases attack value for all external threats by" + value + "!";
            }
            case "shield" :
            {
                for(Threat t : MainActivity.game.activeThreats)
                {
                    if(t.getClass() == ThreatExternal.class)
                    {
                        ((ThreatExternal) t).shield += value;
                    }
                }
                if(threat.deathAction.getClass() == (OnDeathExternalRemoveGlobalBonus.class))
                {
                    OnDeathExternalRemoveGlobalBonus action =  (OnDeathExternalRemoveGlobalBonus)threat.deathAction;
                    action.value = value;
                    action.stat = stat;
                }
                game.globalDamageBuff += value;
                message += "The " + threat.name + " increases shield value for all external threats by" + value + "!";
            }
        }
        return message;
    }

    public ActionExternalGlobalBuff(String stat, int value)
    {
        this.stat = stat;
        this.value = value;
    }
}
