package com.example.otto.spacealertresolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Otto on 1/28/2018.
 */

public class ThreatTrack
{
    private String name;
    public int trackID;
    public int[] YSpaces;
    public int[] XSpace;
    public int[] EndSpace;
    public int rangeOne;
    public int rangeTwo;

    public ThreatTrack(String name)
    {
        this.name = name;
        this.trackID = 0;
    }
}
