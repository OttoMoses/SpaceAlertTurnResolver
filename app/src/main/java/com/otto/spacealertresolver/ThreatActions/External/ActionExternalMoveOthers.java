package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatExternal;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class ActionExternalMoveOthers extends ActionEffectExternal
{
    int value;
    @Override
    public String Execute(Section[][] ship, ThreatExternal threat)
    {
        String message = "";
        String v = "" + value;
        game.MoveThreats(v);
        message += "The " + threat.name + "moves all other external threats " + value;
        if(value > 1)
        {
            message += " spaces ";
        }
        else
        {
            message += " space ";
        }
        message += "forward";
        return message;
    }

    public ActionExternalMoveOthers(int value)
    {
        this.value = value;
    }
}
