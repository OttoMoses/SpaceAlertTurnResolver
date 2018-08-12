package com.otto.spacealertresolver.Threats;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.ExternalDamageBundle;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternal;
import com.otto.spacealertresolver.ThreatActions.External.OnDeathExternal;
import com.otto.spacealertresolver.ThreatActions.External.ThreatActionExternal;

/**
 * Created by Otto on 1/22/2018.
 */

public class ThreatExternal extends Threat
{
    public ExternalDamageBundle bundle = null;
    public Boolean rangeThree = false;
    public OnDamageExternal damageAction;
    public int shield;
    public boolean missileImmune;
    public ThreatActionExternal xAction;
    public ThreatActionExternal yAction;
    public ThreatActionExternal zAction;
    public ThreatActionExternal spawnAction;
    public OnDeathExternal deathAction;

    @Override
    public String ExecuteXAction(Section[][] ship, Player[] players)
    {
        if(xAction != null)
        {
            return xAction.Execute(ship,this);
        }
        else
        {
            return null;
        }
    }

    @Override
    public String ExecuteYAction(Section[][] ship, Player[] players)
    {
        if(yAction != null)
        {
            return yAction.Execute(ship,this);
        }
        else
        {
            return null;
        }
    }

    @Override
    public String ExecuteZAction(Section[][] ship, Player[] players)
    {
        if(zAction != null)
        {
            return zAction.Execute(ship,this);
        }
        else
        {
            return null;
        }
    }

    @Override
    public String ExecuteSpawnAction(Section[][] ship)
    {
        String message = "";
        if(spawnAction != null)
        {
            message += spawnAction.Execute(ship,this);
        }
        else
        {
            message += "The " + this.name + " appears " + "in the " + MainActivity.game.colors[track] + " zone!";
        }
        return message;
    }

    @Override
    public String ExecuteDeathAction(Section[][] ship, Player[] players)
    {
        if(deathAction != null)
        {
            return deathAction.Execute(this);
        }
        else
        {
            return null;
        }
    }

    public String ExecuteDamageAction()
    {
        return damageAction.Execute(this,bundle);
    }

}
