package com.example.otto.spacealertresolver.ThreatActions.External;

import com.example.otto.spacealertresolver.Activities.MainActivity;
import com.example.otto.spacealertresolver.ThreatActions.External.OnDeathExternal;
import com.example.otto.spacealertresolver.Threats.Threat;
import com.example.otto.spacealertresolver.ThreatTrack;
import com.example.otto.spacealertresolver.Threats.ThreatExternal;

/**
 * Created by Otto on 3/6/2018.
 */

public class OnDeathExternalDamageSpaceCount extends OnDeathExternal
{
    @Override
    public String Execute(ThreatExternal threat)
    {
        int spaceCount = 0;
        String message = "";
        ThreatTrack track = MainActivity.game.threatTracks[threat.track];
        for (int space : track.XSpace)
        {
            if(space <= threat.position)
            {
                spaceCount++;
            }
        }
        for (int space : track.YSpaces)
        {
            if(space <= threat.position)
            {
                spaceCount++;
            }
        }
        int damage = spaceCount * 2;
        if(damage > 0)
        {
            message += "On dying the " + threat.name + MainActivity.game.ShipDamage(threat.track,spaceCount,false,false,false);
        }
        return message;
    }
}
