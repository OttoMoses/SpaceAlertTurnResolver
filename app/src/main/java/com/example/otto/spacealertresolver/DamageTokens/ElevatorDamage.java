package com.example.otto.spacealertresolver.DamageTokens;

import com.example.otto.spacealertresolver.Section;

/**
 * Created by Otto on 3/4/2018.
 */

public class ElevatorDamage extends DamageToken
{
    @Override
    public String doDamage(int zone, Section[][] ship)
    {
        Section s = ship[zone][1];
        s.LiftDamaged = true;
        s = ship[zone][0];
        s.LiftDamaged = true;
        return "The elevators in zone " + s.zoneName + " have been damaged causing delays!";
    }

}
