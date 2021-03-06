package com.otto.spacealertresolver.ThreatActions.External;

import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.ThreatActions.External.ActionEffectExternal;
import com.otto.spacealertresolver.Threats.ThreatExternal;

import java.util.ArrayList;

/**
 * Created by Otto on 3/4/2018.
 */

public class ThreatActionExternal
{
    public final ArrayList<ActionEffectExternal> effects;

    public  String Execute(Section[][] ship, ThreatExternal threat)
    { StringBuilder actionMessage = new StringBuilder();
        for (ActionEffectExternal effect :effects)
        {
            if(effect != effects.get(0))
            {
                actionMessage.append("\n");
            }
            actionMessage.append(effect.Execute(ship, threat));
        }
        return actionMessage.toString();
    }

    public ThreatActionExternal(ArrayList<ActionEffectExternal> effects)
    {
        this.effects = effects;
    }
}
