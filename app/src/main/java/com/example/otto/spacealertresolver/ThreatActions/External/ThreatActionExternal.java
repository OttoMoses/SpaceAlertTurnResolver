package com.example.otto.spacealertresolver.ThreatActions.External;

import com.example.otto.spacealertresolver.Section;
import com.example.otto.spacealertresolver.ThreatActions.External.ActionEffectExternal;
import com.example.otto.spacealertresolver.Threats.Threat;
import com.example.otto.spacealertresolver.Threats.ThreatExternal;

import java.util.ArrayList;

/**
 * Created by Otto on 3/4/2018.
 */

public class ThreatActionExternal
{
    private ArrayList<ActionEffectExternal> effects;

    public  String Execute(Section[][] ship, ThreatExternal threat)
    { String actionMessage = "";
        for (ActionEffectExternal tsa :effects)
        {
            actionMessage += tsa.Execute(ship,threat);
        }
        return actionMessage;
    }

    public ThreatActionExternal(ArrayList<ActionEffectExternal> effects)
    {
        this.effects = effects;
    }
}
