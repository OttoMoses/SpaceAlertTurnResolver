package com.otto.spacealertresolver.ThreatActions.External;

import android.util.Pair;

import com.otto.spacealertresolver.ExternalDamageBundle;
import com.otto.spacealertresolver.Threats.Threat;
import com.otto.spacealertresolver.Threats.ThreatExternal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class OnDamageExternalBypassSource extends OnDamageExternal
{
    String source;
    @Override
    public String Execute(ThreatExternal t, ExternalDamageBundle db)
    {
        String message = "";
        int DBDamage = 0;
        int damage;
        ThreatExternal target = null;
        Pair bypass = null;
        for(Pair p : db.damageSources)
        {
            if(p.first.equals("heavy laser cannon"))
            {
                ArrayList<Threat> targets = new ArrayList<>();
                for(Threat threat : game.activeThreats)
                {
                    if(!threat.name.equals(t.name) && threat.track == t.track)
                    {
                        int range;
                        if (t.position <= game.threatTracks[t.track].rangeTwo) {
                            if (game.threatTracks[t.track].rangeTwo == 0) {
                                range = 2;
                            } else {
                                t.rangeThree = true;
                                range = 3;
                            }
                        } else if (t.position >= game.threatTracks[t.track].rangeTwo && t.position < game.threatTracks[t.track].rangeOne) {
                            range = 2;
                        } else {
                            range = 1;
                        }
                        if(range <= game.ship[t.track][1].getGunRange())
                        {
                            targets.add(threat);
                        }
                    }
                    if(targets.size() != 0)
                    {
                        Collections.sort(targets, new Comparator<Threat>() {
                            @Override
                            public int compare(Threat threat, Threat t1) {
                                return threat.compareTo(t1);
                            }
                        });
                        target = (ThreatExternal) threat;
                    }
                    bypass = p;
                }

            }
            else
            {
                DBDamage += (int)p.second;
            }
        }
        if(DBDamage == 0)
        {
            return null;
        }
        else
        {
            damage = DBDamage - t.shield;
            if(damage > 0)
            {
                t.damage += damage;
                message += "\nThe " + t.name + " takes " + damage + " damage!\n";
            }
            else
            {
                message += "\nThe " + t.name + " blocks all damage!";
            }
            if(bypass != null)
            {
                if(target != null)
                {
                    target.bundle = new ExternalDamageBundle();
                    target.bundle.damageSources.add(bypass);
                    message += "\nThe " + source + " can't target the " + t.name + " and instead targets the " + target.name + "!";
                    target.damageAction.Execute(target,target.bundle);
                }
                else
                {
                    message += "\nThe " + source + " can't target the " + t.name +"!";
                }
            }
        }
        return message;
    }

    public OnDamageExternalBypassSource(String source)
    {
        this.source = source;
    }
}
