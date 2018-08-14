package com.otto.spacealertresolver.ThreatActions.External;

import android.util.Pair;

import com.otto.spacealertresolver.ExternalDamageBundle;
import com.otto.spacealertresolver.Threats.Threat;
import com.otto.spacealertresolver.Threats.ThreatExternal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class OnDamageExternalExcludeRange extends OnDamageExternal
{
    @Override
    public String Execute(ThreatExternal t, ExternalDamageBundle db)
    {
        String message = "";
        int DBDamage = 0;
        int damage;
        ThreatExternal target = null;
        ExternalDamageBundle bypass = null;
        if(t.rangeThree)
        {
            message += "The " + t.name + " is too far away to target!";
            ArrayList<Threat> targets = new ArrayList<>();
            for(Threat threat : game.activeThreats)
            {
                if(!threat.name.equals(t.name) && threat.track == t.track)
                {
                    targets.add(threat);
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
                    target.bundle = db;
                }
            }

        }
        else
        {
            for(Pair p : db.damageSources)
            {
                DBDamage += (int)p.second;
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
            }
        }
        return message;
    }
}
