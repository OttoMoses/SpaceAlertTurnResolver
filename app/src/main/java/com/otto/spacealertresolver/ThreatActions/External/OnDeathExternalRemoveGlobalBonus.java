package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Threats.Threat;
import com.otto.spacealertresolver.Threats.ThreatExternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class OnDeathExternalRemoveGlobalBonus extends OnDeathExternal
{
    public String stat = "";
    public int value;
    @Override
    public String Execute(ThreatExternal t)
    {
        String message = "";
        if(!stat.equals(""))
        {
            switch (stat)
            {
                case "attack" :
                {
                    game.globalDamageBuff -= value;
                    message += "The attack value of external threats is no longer being increased by the " + t.name + "!";
                    break;
                }
                case "shield" :
                {
                        game.globalShieldBuff -=value;
                        for(Threat threat : MainActivity.game.activeThreats)
                        {
                            if(threat.getClass() == ThreatExternal.class)
                            {
                                ((ThreatExternal) threat).shieldBoost -= value;
                            }
                        }
                        message += "The shield value of external threats is no longer being increased by the " + t.name + "!";
                    break;
                }
            }
        }
        return message;
    }
}
