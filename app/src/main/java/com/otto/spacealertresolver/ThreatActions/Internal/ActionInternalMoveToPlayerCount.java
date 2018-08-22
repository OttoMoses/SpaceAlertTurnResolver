package com.otto.spacealertresolver.ThreatActions.Internal;

import android.util.Pair;

import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Section;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class ActionInternalMoveToPlayerCount extends ActionEffectInternal
{
    @Override
    public String Execute(Section[][] ship, ThreatInternal threat)
    {
        String message = "";
        ArrayList<Pair<Integer,Integer>> sections = new ArrayList<>();
        Pair<Integer,Integer> currentSection = threat.locations.get(0);
        if(currentSection.first == 1)
        {
            sections.add(new Pair<Integer, Integer>(0,currentSection.second));
            sections.add(new Pair<Integer, Integer>(2,currentSection.second));
        }
        else
        {
            sections.add(new Pair<Integer, Integer>(1,currentSection.second));
        }
        if(currentSection.second == 1)
        {
            sections.add(new Pair<Integer, Integer>(currentSection.first,0));
        }
        else
        {
            sections.add(new Pair<Integer, Integer>(currentSection.first,1));
        }
        ArrayList<Pair<Integer,Pair<Integer,Integer>>> options = new ArrayList<>();
        for(Pair pair : sections)
        {
            int playerCount = 0;
            for(Player p : game.players)
            {
                if((p.zonePosition == (Integer)pair.first) && (p.sectionPosition == (Integer)pair.second))
                {
                    playerCount++;
                }
            }
            options.add(new Pair<Integer, Pair<Integer, Integer>>(playerCount,pair));
        }
        Collections.sort(options, new Comparator<Pair<Integer, Pair<Integer, Integer>>>() {
            @Override
            public int compare(Pair<Integer, Pair<Integer, Integer>> x, Pair<Integer, Pair<Integer, Integer>> y)
            {
                return y.first.compareTo(x.first);
            }

        });
        if(!options.get(0).first.equals(options.get(1).first))
        {
            Pair<Integer,Integer> choice = options.get(0).second;
            ship[choice.first][choice.second].combatThreat = true;
            ship[threat.locations.get(0).first][threat.locations.get(0).second].combatThreat = false;
            threat.locations.set(0,choice);
            message += "The " + threat.name + " moves to the " + ship[threat.locations.get(0).first][threat.locations.get(0).second].sectionName + " " + ship[threat.locations.get(0).first][threat.locations.get(0).second].zoneName + " section!";
        }
        else
        {
            message += "The " + threat.name + " can't decide where to move to!";
        }
        return message;
    }
}
