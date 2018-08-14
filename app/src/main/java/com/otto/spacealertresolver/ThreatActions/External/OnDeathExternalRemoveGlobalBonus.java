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
                }
                case "shield" :
                {
                    for(Threat threat : MainActivity.game.activeThreats)
                    {
                        if(threat.getClass() == ThreatExternal.class)
                        {
                            ((ThreatExternal) threat).shield -= value;
                        }
                    }
                    game.globalDamageBuff -= value;
                    message += "The shield value of external threats is no longer being increased by the " + t.name + "!";
                }
            }
        }
        return message;
    }
}
