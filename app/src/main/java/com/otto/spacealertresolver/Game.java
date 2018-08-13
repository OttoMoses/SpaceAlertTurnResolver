package com.otto.spacealertresolver;

import android.content.Context;
import android.util.Pair;

import com.otto.spacealertresolver.Actions.AAction;
import com.otto.spacealertresolver.Actions.BAction;
import com.otto.spacealertresolver.Actions.BattleBotsAction;
import com.otto.spacealertresolver.Actions.BlankAction;
import com.otto.spacealertresolver.Actions.CAction;
import com.otto.spacealertresolver.Actions.HeroicAAction;
import com.otto.spacealertresolver.Actions.HeroicBAction;
import com.otto.spacealertresolver.Actions.HeroicBattlebotsAction;
import com.otto.spacealertresolver.Actions.HeroicMoveAction;
import com.otto.spacealertresolver.Actions.MoveBlueAction;
import com.otto.spacealertresolver.Actions.MoveRedAction;
import com.otto.spacealertresolver.Actions.PlayerAction;
import com.otto.spacealertresolver.Actions.TurboLiftAction;
import com.otto.spacealertresolver.DamageTokens.DamageToken;
import com.otto.spacealertresolver.DamageTokens.ElevatorDamage;
import com.otto.spacealertresolver.DamageTokens.GunDamage;
import com.otto.spacealertresolver.DamageTokens.PowerDamage;
import com.otto.spacealertresolver.DamageTokens.PulseGunDamage;
import com.otto.spacealertresolver.DamageTokens.StructuralDamage;
import com.otto.spacealertresolver.Stations.ActionStation;
import com.otto.spacealertresolver.Stations.BattlebotStation;
import com.otto.spacealertresolver.Stations.ComputerStation;
import com.otto.spacealertresolver.Stations.HeavyLaserStation;
import com.otto.spacealertresolver.Stations.HeavyShieldStation;
import com.otto.spacealertresolver.Stations.InterceptorStation;
import com.otto.spacealertresolver.Stations.LateralReactorStation;
import com.otto.spacealertresolver.Stations.LightLaserStation;
import com.otto.spacealertresolver.Stations.LightShieldStation;
import com.otto.spacealertresolver.Stations.MainReactorStation;
import com.otto.spacealertresolver.Stations.MissileStation;
import com.otto.spacealertresolver.Stations.PulseLaserStation;
import com.otto.spacealertresolver.Stations.SuperHeavyLaserStation;
import com.otto.spacealertresolver.Stations.WindowStation;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalBuff;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalDamageShip;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternal;
import com.otto.spacealertresolver.ThreatActions.External.OnDeathExternal;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionEffectInternal;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalConditionDamageMove;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalDamageShip;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalEndGame;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalEnergyDrain;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalGlobalDamageModifier;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalGrow;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalKnockOut;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalLeakPower;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalMoveBlue;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalMoveRed;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalDefault;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalExcludeRange;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalToggle;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalSelfToggle;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalNoShield;
import com.otto.spacealertresolver.ThreatActions.External.OnDeathExternalDamageOthers;
import com.otto.spacealertresolver.ThreatActions.External.OnDeathExternalDamageSpaceCount;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalShieldDrain;
import com.otto.spacealertresolver.ThreatActions.External.ThreatActionExternal;
import com.otto.spacealertresolver.ThreatActions.External.ActionEffectExternal;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalToggle;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalTurboLift;
import com.otto.spacealertresolver.ThreatActions.Internal.OnDamageInternal;
import com.otto.spacealertresolver.ThreatActions.Internal.OnDamageInternalCombat;
import com.otto.spacealertresolver.ThreatActions.Internal.OnDamageInternalMalfMultiBonus;
import com.otto.spacealertresolver.ThreatActions.Internal.OnDamageInternalMalfSingle;
import com.otto.spacealertresolver.ThreatActions.Internal.OnSpawnSetHealth;
import com.otto.spacealertresolver.ThreatActions.Internal.SetInternalPosition;
import com.otto.spacealertresolver.ThreatActions.Internal.ThreatActionInternal;
import com.otto.spacealertresolver.Threats.Threat;
import com.otto.spacealertresolver.Threats.ThreatExternal;
import com.otto.spacealertresolver.Threats.ThreatInternal;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by Otto on 1/22/2018.
 */

public class Game 
{
    public PlayerAction[] actions;
    public ArrayList<String> threatNames;
    public Player[] players;
    public int numRounds;
    private String shipName;
    public int gameType;
    public int currentRound;
    public Section[][] ship;
    public boolean computerMaintained;
    public boolean interceptorsDocked;
    public int missileCount;
    public boolean missileInSpace;
    public boolean strafingRun;
    public ThreatTrack[] threatTracks;
    public ThreatString[] selectedThreats;
    private Threat[] builtThreats;
    public ArrayList<Threat> activeThreats;
    public String[] colors;
    private Context context;
    private ArrayList<DamageToken> redDamage;
    private ArrayList<DamageToken> whiteDamage;
    private ArrayList<DamageToken> blueDamage;
    private ArrayList<Threat> deadThreats;
    private ArrayList<Threat> escapedThreats;
    private ArrayList<Threat> internalThreats;
    private ArrayList<Threat> externalThreats;
    public int gamePhase;
    public int observationOne;
    public int observationTwo;
    public  int observationThree;
    private int[] observationScore;
    public int phase;
    private boolean missileDamage;
    public ArrayList<String> internalThreatNames;
    public boolean strafeHeroic;
    public int fissureMod;
    private boolean gameEnd;

    public Game(Context c) throws ParserConfigurationException, SAXException, IOException {
        colors = new String[]
                {
                        "Red",
                        "white",
                        "Blue",
                        "Internal",
                };
        context = c;
        //create array of all action object flyweights
        actions = new PlayerAction[]
        {
                new BlankAction(),
                new MoveRedAction(),
                new MoveBlueAction(),
                new HeroicMoveAction(),
                new AAction(),
                new BAction(),
                new CAction(),
                new BattleBotsAction(),
                new HeroicAAction(),
                new HeroicBAction(),
                new HeroicBattlebotsAction(),
                new TurboLiftAction()
        };
        observationScore = new int[] {0,1,2,3,5,7};
        threatNames = new ArrayList<>();
        internalThreatNames = new ArrayList<>();
        getThreatNames();
    }
    //Public method to set game-wide variables when starting a new session
    public void SetupGame(int numPlayers, String name){
        shipName = name;
        //set variables that depend on game type

        gameType = 2;
        numRounds = 12;
        threatTracks = new ThreatTrack[4];

        // create the right number of threat tracks and an array to store them;

        threatTracks[0] = new ThreatTrack("Red");
        threatTracks[1] = new ThreatTrack("white");
        threatTracks[2] = new ThreatTrack("Blue");
        threatTracks[3] = new ThreatTrack("Internal");
        selectedThreats = new ThreatString[8];
        Arrays.fill(selectedThreats, new ThreatString(threatNames.size() + 1,4));

        //create creates an array to store players, populates it with unnamed players, and populates their action arrays with blank actions
        players = new Player[numPlayers];
        for(int i = 0; i < numPlayers; i++)
        {
            Player p = new Player("Unnamed Player",numRounds);
            for(int roundCount = 0; roundCount < numRounds; roundCount++)
            {
                p.actions[roundCount] = actions[0];
            }
            players[i] = p;
            p.playerID = i;
        }

        InitializeShip();
    }
    public void StartGame() throws IOException, SAXException, ParserConfigurationException {
        // set game state starting values
        computerMaintained = false;
        currentRound = 0;
        missileCount = 3;
        missileInSpace = false;
        activeThreats = new ArrayList<>();
        deadThreats = new ArrayList<>();
        escapedThreats = new ArrayList<>();
        interceptorsDocked = true;
        observationOne = 0;
        observationTwo = 0;
        observationThree = 0;
        phase = 1;
        fissureMod = 0;
        BuildDamageDecks();

        //set player starting values
        for (Player p: players)
        {
            p.zonePosition = 1;
            p.sectionPosition = 1;
            p.leadingBots = false;
            p.flyingInterceptors = false;
            p.unconscious = false;
        }

        //set station and section starting values
        for(int zone = 0; zone < ship.length; zone++)
        {
            for(int section = 0; section < ship[zone].length; section++)
            {
                Section s = ship[zone][section];
                if(s.CSystem.getClass() == BattlebotStation.class)
                {
                    s.hasBots = true;
                }
                s.ASystem.StartGame();
                s.BSystem.StartGame();
                s.CSystem.StartGame();
                s.StartGame();
            }
        }

        // build threats
        builtThreats = ThreatAssembler(gameType);

        //check threat tracks
        for (ThreatTrack t :threatTracks)
        {
            if(t.XSpace == null)
            {
                t.XSpace = new int[]{6};
            }
            if(t.YSpaces == null)
            {
                t.YSpaces = new int[]{};
            }
            if(t.EndSpace == null)
            {
                t.EndSpace = new int[]{10};
            }
        }
    }
    private void BuildDamageDecks()
    {
        redDamage = new ArrayList<DamageToken>(Arrays.asList(new ElevatorDamage(), new GunDamage(1),new GunDamage(0),new StructuralDamage(),new PowerDamage(1),new PowerDamage(0)));
        whiteDamage = new ArrayList<DamageToken>(Arrays.asList(new ElevatorDamage(), new GunDamage(1),new PulseGunDamage(0),new StructuralDamage(),new PowerDamage(1),new PowerDamage(0)));
        blueDamage = new ArrayList<DamageToken>(Arrays.asList(new ElevatorDamage(), new GunDamage(1),new GunDamage(0),new StructuralDamage(),new PowerDamage(1),new PowerDamage(0)));
        Collections.shuffle(redDamage);
        Collections.shuffle(whiteDamage);
        Collections.shuffle(blueDamage);

    }

    private void InitializeShip()
    {
        //initialize sections
        ship = new Section[3][2];
        ship[0][0] = new Section("red","lower",true,1,0,2,3,2,3,0,0);
        ship[0][1] = new Section("red","upper",false,0,0,1,2,4,3,0,1);
        ship[1][0] = new Section("white","lower",false,1,0,3,5,1,2,1,0);
        ship[1][1] = new Section("white","upper",false,1,0,1,3,5,3,1,1);
        ship[2][0] = new Section("blue","lower",false,1,0,2,3,2,3,2,0);
        ship[2][1] = new Section("blue","upper",true,2,0,1,2,4,3,2,1);

        //initialize stations
        ActionStation[] stations = new ActionStation[]
                {
                        //0
                        new BattlebotStation(),
                        //1
                        new ComputerStation(),
                        //2
                        new HeavyLaserStation(),
                        //3
                        new HeavyShieldStation(),
                        //4
                        new InterceptorStation(),
                        //5
                        new LateralReactorStation(),
                        //6
                        new LightLaserStation(),
                        //7
                        new LightShieldStation(),
                        //8
                        new MainReactorStation(),
                        //9
                        new MissileStation(),
                        //10
                        new PulseLaserStation(),
                        //11
                        new SuperHeavyLaserStation(),
                        //12
                        new WindowStation(),
                };
        ship[0][0].ASystem = stations[6];
        ship[0][1].ASystem = stations[2];
        ship[1][0].ASystem = stations[10];
        ship[1][1].ASystem = stations[11];
        ship[2][0].ASystem = stations[6];
        ship[2][1].ASystem = stations[2];

        ship[0][0].BSystem = stations[5];
        ship[0][1].BSystem = stations[7];
        ship[1][0].BSystem = stations[8];
        ship[1][1].BSystem = stations[3];
        ship[2][0].BSystem = stations[5];
        ship[2][1].BSystem = stations[7];

        ship[0][0].CSystem = stations[0];
        ship[0][1].CSystem = stations[4];
        ship[1][0].CSystem = stations[12];
        ship[1][1].CSystem = stations[1];
        ship[2][0].CSystem = stations[9];
        ship[2][1].CSystem = stations[0];

    }


    public String AdvanceTurn()
    {
        String roundMessage = "\n";

        //reset the game state for a new turn
        ResetState();

        //increment round
        currentRound++;

        //increment phase
        if(currentRound == 4 || currentRound == 8)
        {
            phase++;
        }

        //special text for first round
        if(currentRound == 1)
        {
            roundMessage += "\n" + shipName + " Hyperspace jumps into an unknown sector! \n";
        }

        roundMessage += "At T + " + currentRound + ":\n";
        roundMessage += MaintenanceCheck();
        roundMessage += "\n----Threat Spawn Step----\n";
        roundMessage += SpawnThreats(currentRound);
        roundMessage += "\n----Player Action Step----\n";
        roundMessage += ProcessActions(currentRound);

        //generate targets for damage processes
        ArrayList<Threat> internalTargets = new ArrayList<>(activeThreats);
        internalTargets.removeAll(externalThreats);
        ArrayList<Threat> externalTargets = new ArrayList<>(activeThreats);
        externalTargets.removeAll(internalThreats);

        roundMessage += "\n----Calculate Threat Damage Step----\n";
        roundMessage += DamageThreats(externalTargets,internalTargets);
        roundMessage += KillThreats(activeThreats);
        roundMessage += "\n----Threat Move Step----\n";
        roundMessage += MoveThreats();
        if(escapedThreats.size() != 0)
        {
            activeThreats.removeAll(escapedThreats);
        }
        //special text for last round
        if(currentRound == numRounds)
        {
            roundMessage+= "\n" + shipName + " Hyperspace jumps back to known space!";
        }

        return roundMessage;
    }

    private String MaintenanceCheck()
    {
        String message= "";
        if(currentRound == 3 || currentRound == 6 || currentRound == 10)
        {
            message += "\n----Computer Maintenance Check----\n";
            if(!computerMaintained)
            {
                message += "\nThe computer shuts down delaying all players on the ship this round \n";
                for (Player p: players)
                {
                    if(!p.flyingInterceptors)
                    {
                        message += p.Delay(currentRound);
                    }
                    else
                    {
                        message += p.playerName + "\nis safely off the ship and unaffected by the blackout \n";
                    }
                }
            }
            else
            {
                message += "\nThe computer was correctly maintained and stays on this round \n";
            }
            computerMaintained = false;
        }
        return message;
    }

    private String MoveThreats()
    {
        String message = "\n";
        for(Threat t:activeThreats)
        {
            ThreatTrack track = threatTracks[t.track];
            int newPosition = t.position + t.speed;
            if(t.getClass().equals(ThreatExternal.class))
            {
                message += "\nThe " + t.name + " moves " + t.speed + " spaces closer to the ship\n";
            }
            else
            {
                message +=
                "The " + t.name;
                if(((ThreatInternal) t).plural)
                {
                    message += " move ";
                }
                else
                {
                    message += " moves ";
                }
                message += t.speed +  " spaces on the internal track\n";
            }
            //check for attack spaces
            for (int space : track.XSpace)
            {
                if((space < newPosition && space > t.position) || space == newPosition)
                {
                    message += t.ExecuteXAction(ship,players);
                }
            }
            for (int space : track.YSpaces)
            {
                if((space < newPosition && space > t.position) || space == newPosition)
                {
                    message += t.ExecuteYAction(ship,players);
                }
            }
            for (int space : track.EndSpace)
            {
                if( space < newPosition || space == newPosition)
                {

                    message += t.ExecuteZAction(ship,players);
                    message += "\n";
                    if(t.getClass().equals(ThreatExternal.class))
                    {
                        message += "The " + t.name + " Escapes!";
                    }
                    else
                    {
                        if(((ThreatInternal) t).threatType.contains("combat"))
                        {
                            if(((ThreatInternal) t).plural)
                            {
                                message += "The " + t.name + " escape!";
                            }
                            else
                            {
                                message += "The " + t.name + " escapes!";
                            }
                        }
                        else
                        {
                            if(!gameEnd)
                            {
                                if(((ThreatInternal) t).plural)
                                {
                                    message += "The " + t.name + " are now unfixable!";
                                }
                                else
                                {
                                    message += "The " + t.name + " is now unfixable!";
                                }
                            }
                        }
                    }
                    escapedThreats.add(t);
                }

                else
                {
                    if(t.getClass().equals(ThreatExternal.class))
                    {
                        message += "The " + t.name + " is now  " + (threatTracks[t.track].EndSpace[0] -  t.position) +  " space away from the ship!";
                    }
                    else
                    {
                        message += "The " + t.name;
                                if(((ThreatInternal)t).plural)
                                {
                                   message += " are now  ";
                                }
                                else
                                {
                                    message += " is now ";
                                }
                                message += (threatTracks[t.track].EndSpace[0] -  t.position) +  " space away from escaping!";
                    }
                    t.position = newPosition;
                }
            }
        }
        for(Threat t : escapedThreats)
        {
            activeThreats.remove(t);
        }
        if(message == "\n")
        {
            message += "There are no threats to move this turn.\n";
        }
        else
        {
            message += "\n";
        }
        return message;
    }
    private void ResetState()
    {
        //Reset gun fire state and internal damage bundles
        for (Section[] aShip : ship) {
            for (Section s : aShip) {
                s.hasFired = false;
                s.combatDamage.clear();
                s.malfCDamage.clear();
                s.malfBDamage.clear();
            }
        }
        //reset strafing run boolean
        strafingRun = false;
        strafeHeroic = false;

        //advance missile track
         missileDamage = false;
        if(missileInSpace)
        {
            missileDamage = true;
            missileInSpace = false;
        }
    }

    private String SpawnThreats(int round)
    {
        //spawn Threats
        String message = "\n";
        if(round <= 8)
        {
            if (builtThreats[currentRound - 1] != null) {
                Threat t = builtThreats[round - 1];
                activeThreats.add(t);
                message += t.ExecuteSpawnAction(ship);
            }
        }
        if(message == "\n")
        {
            message += "No threats spawned this turn.\n";
        }
        else
        {
            message += "\n";
        }
        return message;
    }

    private String ProcessActions(int round)
    {
        //process player actions
        String message = "";
        for (Player p : players)
        {
            //special check for players who are in space
            if(p.flyingInterceptors)
            {
                switch (p.actions[round - 1].name) {
                    case "Battlebots action":
                        actions[7].Execute(p);
                        break;
                    case "Heroic Battlebots action":
                        actions[11].Execute(p);
                        break;
                    default:
                        message += "\n" + p.playerName + " returns to the ship";
                        p.flyingInterceptors = false;
                        interceptorsDocked = true;
                        break;
                }
            }
            message += "\n" + p.playerName + " " +
                    p.actions[round - 1].Execute(p) + "\n";
        }
        return message;
    }

    private String ExternalThreatDamage(ArrayList<Threat> targets)
    {
        String message = "";

        //computeDamage
        ArrayList<ThreatExternal> redThreats = new ArrayList<>();
        ArrayList<ThreatExternal> whiteThreats = new ArrayList<>();
        ArrayList<ThreatExternal> blueThreats = new ArrayList<>();
        ArrayList<ThreatExternal> rangeOneThreats = new ArrayList<>();
        ArrayList<ThreatExternal> rangeTwoThreats = new ArrayList<>();
        ArrayList<ThreatExternal> rangeThreeThreats = new ArrayList<>();
        ExternalDamageBundle[][] bundles = new ExternalDamageBundle[][]
                {
                        {new ExternalDamageBundle(),new ExternalDamageBundle(),new ExternalDamageBundle()},
                        {new ExternalDamageBundle(),new ExternalDamageBundle(),new ExternalDamageBundle()},
                        {new ExternalDamageBundle(),new ExternalDamageBundle(),new ExternalDamageBundle()}
                };
        for (Threat threat:targets)
        {
            ThreatExternal t = (ThreatExternal)threat;
            switch (t.track)
            {
                case 0:
                    redThreats.add(t);
                    break;
                case 1:
                    whiteThreats.add(t);
                    break;
                case 2:
                    blueThreats.add(t);
                    break;
            }

            if(t.position <= threatTracks[t.track].rangeTwo)
            {
                if(threatTracks[t.track].rangeTwo == 0)
                {
                    rangeTwoThreats.add(t);
                }
                else
                {
                    t.rangeThree = true;
                    rangeThreeThreats.add(t);
                }
            }
            else if(t.position >= threatTracks[t.track].rangeTwo && t.position < threatTracks[t.track].rangeOne)
            {
                rangeTwoThreats.add(t);
            }
            else
            {
                rangeOneThreats.add(t);
            }
        }
        //build gun damage bundles
        for (int zoneNum = 0; zoneNum < ship.length; zoneNum++)
        {
            Section[] zone = ship[zoneNum];
            for(int deck = 0; deck< zone.length; deck++)
            {
                Section s = zone[deck];
                if(s.hasFired)
                {
                    int damage = s.getGunDamage();
                    if(s.heroicFire)
                    {
                        damage +=1;
                    }
                    int range = s.getGunRange();
                    String source = s.ASystem.name;
                    Pair<String,Integer> damageSource = new Pair<>(source,damage);

                    for(int i = 1; i <= range; i++)
                    {
                        if(s.ASystem.name.equals("pulse cannon"))
                        {
                            bundles[0][i - 1].damageSources.add(damageSource);
                            bundles[1][i - 1].damageSources.add(damageSource);
                            bundles[2][i - 1].damageSources.add(damageSource);
                        }
                        else
                        {
                            bundles[zoneNum][i - 1].damageSources.add(damageSource);
                        }
                    }
                }
            }
        }

        //sort threats
        Collections.sort(redThreats, new Comparator<Threat>() {
            @Override
            public int compare(Threat threat, Threat t1) {
                return threat.compareTo(t1);
            }
        });
        Collections.sort(whiteThreats, new Comparator<Threat>() {
            @Override
            public int compare(Threat threat, Threat t1) {
                return threat.compareTo(t1);
            }
        });
        Collections.sort(blueThreats, new Comparator<Threat>() {
            @Override
            public int compare(Threat threat, Threat t1) {
                return threat.compareTo(t1);
            }
        });
        //push gun damage bundle to red zone
        if(redThreats.size() != 0)
        {
            if (rangeThreeThreats.contains(redThreats.get(0)))
            {
                ExternalDamageBundle bundle = bundles[0][2];
                if(bundle.damageSources.size() != 0)
                {
                    redThreats.get(0).bundle = bundle;
                }
            }
            else if (rangeTwoThreats.contains(redThreats.get(0)))
            {
                ExternalDamageBundle bundle = bundles[0][1];
                if(bundle.damageSources.size() != 0)
                {
                    redThreats.get(0).bundle = bundle;
                }
            }
            else
            {
                ExternalDamageBundle bundle = bundles[0][0];
                if(bundle.damageSources.size() != 0)
                {
                    redThreats.get(0).bundle = bundle;
                }
            }
        }

        //push gun damage bundle to white zone
        if(whiteThreats.size() != 0)
        {
            if(rangeThreeThreats.contains(whiteThreats.get(0)))
            {
                ExternalDamageBundle bundle = bundles[1][0];
                if(bundle.damageSources.size() != 0)
                {
                    whiteThreats.get(0).bundle = bundle;
                }
            }
            else if(rangeTwoThreats.contains(whiteThreats.get(0)))
            {
                ExternalDamageBundle bundle = bundles[1][1];
                if(bundle.damageSources.size() != 0)
                {
                    whiteThreats.get(0).bundle = bundle;
                }
            }
            else
            {
                ExternalDamageBundle bundle = bundles[1][0];
                if(bundle.damageSources.size() != 0)
                {
                    whiteThreats.get(0).bundle = bundle;
                }
            }
        }

        //push gun damage bundle to blue zone
        if(blueThreats.size() != 0) {
            if (rangeThreeThreats.contains(blueThreats.get(0)))
            {
                ExternalDamageBundle bundle = bundles[2][2];
                if(bundle.damageSources.size() != 0)
                {
                    blueThreats.get(0).bundle = bundle;
                }
            }
            else if (rangeTwoThreats.contains(blueThreats.get(0)))
            {
                ExternalDamageBundle bundle = bundles[2][1];
                if(bundle.damageSources.size() != 0)
                {
                    blueThreats.get(0).bundle = bundle;
                }
            }
            else
            {
                ExternalDamageBundle bundle = bundles[2][0];
                if(bundle.damageSources.size() != 0)
                {
                    blueThreats.get(0).bundle = bundle;
                }
            }
        }

        //check for missile and push missile damage bundle
        if(missileDamage) {
            ArrayList<ThreatExternal> missileTargets = new ArrayList<>();

            //build list of valid missile targets
            if (rangeOneThreats.size() != 0) {
                for (ThreatExternal t : rangeOneThreats) {
                    if (!t.missileImmune) {
                        missileTargets.add(t);
                    }
                }
            }

            if (rangeTwoThreats.size() != 0) {
                for (ThreatExternal t : rangeTwoThreats) {
                    if (!t.missileImmune) {
                        missileTargets.add(t);
                    }
                }
            }

            // sort target list
            Collections.sort(missileTargets, new Comparator<Threat>() {
                @Override
                public int compare(Threat threat, Threat t1) {
                    return threat.compareTo(t1);
                }
            });

            //push missile damage bundle to closest valid target
            ThreatExternal target = missileTargets.get(0);
            Pair<String, Integer> damageSource = new Pair<>("missile", 3);
            if (target.bundle != null) {
                target.bundle.damageSources.add(damageSource);
            } else {
                ExternalDamageBundle db = new ExternalDamageBundle();
                db.damageSources.add(damageSource);
                target.bundle = db;
            }
        }
        //check for fighters and push fighter damage bundles

        if(strafingRun)
        {
            int damage;
            if(strafeHeroic)
            {
                damage = 2;
            }
            else
            {
                damage = 1;
            }
            if(rangeOneThreats.size() != 0)
            {
                if(rangeOneThreats.size() == 1)
                {
                    if(strafeHeroic)
                    {
                        damage = 4;
                    }
                    else
                    {
                        damage = 3;
                    }
                }
                Pair<String,Integer> ds = new Pair<>("fighters",damage);
                for (ThreatExternal t : rangeOneThreats)
                {
                    if (t.bundle != null)
                    {
                        t.bundle.damageSources.add(ds);
                    }
                    else
                    {
                        ExternalDamageBundle db = new ExternalDamageBundle();
                        db.damageSources.add(ds);
                        t.bundle.damageSources.add(ds);
                    }
                }
            }
        }

        //process damage bundles
        for (Threat t:targets)
        {
            if(t.getClass().equals(ThreatExternal.class))
            {
                if(((ThreatExternal) t).bundle != null)
                {
                    message += ((ThreatExternal) t).ExecuteDamageAction();
                }
            }
        }
        if(message == "")
        {
            return "No external threats have taken damage this turn.\n";
        }
        else
        {
            return message;
        }
    }

    private String InternalThreatDamage(ArrayList<Threat> targets)
    {
        String message = "";
        for(Threat threat : targets)
        {
            ThreatInternal t = (ThreatInternal)threat;
            message += t.ExecuteDamageAction(ship,players);
        }
        if(message == "")
        {
            return "No internal threats have taken damage this turn.\n";
        }
        else
        {
            return message;
        }
    }

    private String DamageThreats(ArrayList<Threat> externalTargets,ArrayList<Threat> internalTargets)
    {
        String message = "\n";
        if(!internalTargets.isEmpty())
        {message += InternalThreatDamage(internalTargets);}
        else{message += "There are no internal threats this turn.\n";}
        if(!externalTargets.isEmpty()){message += ExternalThreatDamage(externalTargets);}
        else{message += "There are no external threats this turn.";}
        message += "\n";
        return message;
    }

    private String KillThreats(ArrayList<Threat> targets)
    {
        String message = "";
        for(Threat t : targets)
        {
            if(t.damage >= t.health)
            {
                t.ExecuteDeathAction(ship,players);
                deadThreats.add(t);
                if(t.getClass().equals(ThreatInternal.class) && ((ThreatInternal)t).plural)
                {
                    message += "The " + t.name + " were destroyed!\n";
                }
                else
                {
                    message += "The " + t.name + " was destroyed!\n";
                }
            }
        }
        if(deadThreats.size() != 0)
        {
            activeThreats.removeAll(deadThreats);
        }
        return message;
    }

    public String ShipDamage(int zone, int damage,boolean bypassBonus, boolean internal,boolean plural)
    {
        ArrayList<DamageToken> tokens = null;
        String damageMessage = "";
        if(plural)
        {
            damageMessage += " attack";
        }
        else
        {
            damageMessage += " attacks";
        }
        damageMessage += " the " + colors[zone] + " zone " + " for " + damage + " damage!\n";
        Section shield = ship[zone][1];
        switch (zone)
        {
            case 0:
                 tokens = redDamage;
                 break;
            case 1:
                tokens = whiteDamage;
                break;
            case 2:
                tokens = blueDamage;
                break;
        }
        if(shield.powerCubes != 0 && !internal)
        {
            if(shield.powerCubes >= damage)
            {
                damageMessage += "The shield in the " + shield.zoneName + " zone blocks all the damage!\n";
                shield.powerCubes -= damage;
                damage = 0;
                if(shield.powerCubes != 0)
                {
                    damageMessage += "There is " + shield.powerCubes + " power left in the " + shield.zoneName + " zone shield.\n";
                }
                else
                {
                    damageMessage += "there is no power remaining in the " + shield.zoneName + " zone shield\n";
                }
            }
            else
            {
                damage -= shield.powerCubes;
                damageMessage += "The " + shield.zoneName + " zone shield blocked " + shield.powerCubes + "damage\n";
                shield.powerCubes = 0;
                damageMessage += "there is no power remaining in the " + shield.zoneName + " zone shield\n";
            }

        }
        if(bypassBonus)
        {
            damage = damage * 2;
        }
        if((fissureMod == 1 && zone == 0) || fissureMod == 2)
        {
            damage = damage *2;
            damageMessage += "\n Damage is doubled by the fissure!";
        }
        damageMessage += "\n";
        for(int i = 0; i < damage; i++)
        {
            if(tokens.size() != 0)
            {
                DamageToken d = tokens.get(0);
                damageMessage += d.doDamage(zone,ship) + "\n";
                tokens.remove(d);
            }
            else
            {
                damageMessage += "The Ship is destroyed!";
                break;
            }
        }
        return damageMessage;
    }
    public String EndGame(boolean dead)
    {
        String endMessage = "\n---End Game Scoring---\n";
        int endPoints = 0;
        int deathScore = 0;
        int escapeScore = 0;
        int windowScore = 0;
        int damagePenalty = 0;
        int knockedOutPenalty = 0;
        int mostDamagedPenalty = 0;
        int damagedBotsPenalty = 0;

        //add points for defeated threats
        for (Threat t : deadThreats) {
            deathScore += t.deathScore;
        }
        endPoints += deathScore;
        endMessage+="\n" + deathScore + " points for defeated threats";

        //add points for survived threats
        for (Threat t : escapedThreats) {
            escapeScore += t.escapeScore;
        }
        endPoints += escapeScore;
        endMessage+="\n" + escapeScore + " points for survived threats";

        //add points for manual observations
        windowScore += observationScore[observationOne];
        windowScore += observationScore[observationTwo];
        windowScore += observationScore[observationThree];
        endPoints += windowScore;
        endMessage+="\n" + windowScore + " points for manual confirmation of scanner data";

        //subtract points for damage done
        damagePenalty += ( 6 - redDamage.size());
        damagePenalty += ( 6 - whiteDamage.size());
        damagePenalty += ( 6 - blueDamage.size());
        endPoints -= damagePenalty;
        endMessage+="\n" + damagePenalty + " negative points for total damage to the ship";

        //find most damaged zone
        List<ArrayList<DamageToken>> allTheLists = new ArrayList<>(Arrays.asList(whiteDamage,redDamage,blueDamage));
        Collections.sort(allTheLists, new Comparator<ArrayList>(){
            public int compare(ArrayList a1, ArrayList a2) {
                return a2.size() + a1.size();
            }
        });
        ArrayList mostDamaged = allTheLists.get(0);
        //subtract damage to the most damaged zone
        mostDamagedPenalty += (6 - mostDamaged.size());
        endPoints -= mostDamagedPenalty;
        endMessage+="\n" + mostDamagedPenalty + " negative points for damage to the most damaged zone";

        //subtract points for knocked out players
        for(Player p : players)
        {
            if(p.unconscious)
            {
                knockedOutPenalty += 2;
            }
        }
        endPoints -= knockedOutPenalty;
        endMessage+="\n" + knockedOutPenalty + " negative points knocked out players";

        //subtract points for damaged bots
        for(Player p : players)
        {
            if(p.damagedBots)
            {
                damagedBotsPenalty += 1;
            }
        }
        endPoints -= damagedBotsPenalty;
        endMessage+="\n" + damagedBotsPenalty + " negative points damaged battlebot squads";

        if(!dead)
        {
            endMessage += "\nFinal score: " + endPoints;
        }
        else
        {
            endMessage += "Final score: 0! \nDestroyed ships don't score points!";
        }
        endMessage += "\nThanks for playing!";
        return endMessage;
    }

    private void getThreatNames() throws IOException, SAXException, ParserConfigurationException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = factory.newDocumentBuilder();
        InputStream in = context.getAssets().open("threats.xml");
        Document document = parser.parse(in);
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        try
        {
            XPathExpression expression = xPath.compile("/Threats/threat");
            NodeList nl = (NodeList) expression.evaluate(document,XPathConstants.NODESET);
            for(int i = 0; i <  nl.getLength(); i++)
            {
                Node n = nl.item(i);
                expression = xPath.compile("./cardID");
                String cardNumber =  (String) expression.evaluate(n,XPathConstants.STRING);
                expression = xPath.compile("./name");
                String name =  (String) expression.evaluate(n,XPathConstants.STRING);
                expression = xPath.compile("./type");
                String type = (String) expression.evaluate(n,XPathConstants.STRING);

                if(type.equals("internal"))
                {
                    internalThreatNames.add(cardNumber + " - " + name);
                }
                threatNames.add(cardNumber + " - " + name);
            }
        }
        catch (XPathExpressionException e)
        {
            e.printStackTrace();
        }
    }
    //threat assembler
    private Threat[] ThreatAssembler(int gametype) throws ParserConfigurationException, IOException, SAXException
    {
        Threat[] builtThreats  = new Threat[8];
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = factory.newDocumentBuilder();
        InputStream in = context.getAssets().open("threats.xml");
        Document document = parser.parse(in);
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        internalThreats = new ArrayList<>();
        externalThreats = new ArrayList<>();
        for(int turn = 0; turn < selectedThreats.length; turn++)
        {
            ThreatString t = selectedThreats[turn];
            if(t.threatID !=threatNames.size() + 1)
            {
                try
                {
                    System.out.println(t.threatID);
                    XPathExpression expression = xPath.compile("/Threats/threat[" + (t.threatID + 1) + "]");
                    Node node = (Node) expression.evaluate(document, XPathConstants.NODE);
                    expression = xPath.compile("./type");
                    String threatType = (String)expression.evaluate(node,XPathConstants.STRING);
                    if(!threatType.equals("internal"))
                    {
                        ThreatExternal threat = BuildThreatExternal(xPath,node,t.trackNum);
                        builtThreats[turn] = threat;
                        externalThreats.add(threat);
                    }
                    else
                    {
                        ThreatInternal threat = BuildThreatInternal(xPath,node,t.trackNum);
                        builtThreats[turn] = threat;
                        internalThreats.add(threat);

                    }
                }
                catch (XPathExpressionException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return builtThreats;
    }

    private ThreatInternal BuildThreatInternal(XPath xPath, Node node, int trackNum) throws XPathExpressionException
    {
        ThreatInternal threat = new ThreatInternal();
        XPathExpression expression;
        threat.track = 3;
        expression = xPath.compile("./name");
        threat.name = (String) expression.evaluate(node, XPathConstants.STRING);
        expression = xPath.compile("./plural");
        threat.plural = Boolean.parseBoolean((String) expression.evaluate(node,XPathConstants.STRING));
        expression = xPath.compile("./speed");
        threat.speed = Integer.parseInt((String) expression.evaluate(node,XPathConstants.STRING));
        expression = xPath.compile("./health");
        threat.health = Integer.parseInt((String) expression.evaluate(node,XPathConstants.STRING));
        expression = xPath.compile("./deathScore");
        threat.deathScore = Integer.parseInt((String) expression.evaluate(node,XPathConstants.STRING));
        expression = xPath.compile("./escapeScore");
        threat.escapeScore = Integer.parseInt((String) expression.evaluate(node,XPathConstants.STRING));
        expression = xPath.compile(("./threatType"));
        threat.threatType = (String) expression.evaluate(node, XPathConstants.STRING);
        expression = xPath.compile(("./spawnMessage"));
        threat.spawnMessage = (String) expression.evaluate(node, XPathConstants.STRING);

        NodeList dataSet;
        Element data;
        expression = xPath.compile("./xAction/effect");
        dataSet = (NodeList) expression.evaluate(node,XPathConstants.NODESET);
        threat.xAction = GetInternalActionCommand(dataSet,xPath);
        expression = xPath.compile("./yAction/effect");
        dataSet = (NodeList) expression.evaluate(node,XPathConstants.NODESET);
        threat.yAction = GetInternalActionCommand(dataSet,xPath);
        expression = xPath.compile("./zAction/effect");
        dataSet = (NodeList) expression.evaluate(node,XPathConstants.NODESET);
        threat.zAction = GetInternalActionCommand(dataSet,xPath);

        expression = xPath.compile("./spawnAction/effect");
        dataSet = (NodeList) expression.evaluate(node,XPathConstants.NODESET);
        threat.spawnAction = GetInternalSpawnCommand(dataSet,xPath);

        expression = xPath.compile("./locations/location");
        dataSet = (NodeList) expression.evaluate(node,XPathConstants.NODESET);
        threat.locations = GetLocations(dataSet);

        expression = xPath.compile("./damageAction");
        data = (Element)expression.evaluate(node,XPathConstants.NODE);
        threat.damageEffect = GetInternalDamageEffect(data,xPath);
        return threat;
    }
    private ThreatExternal BuildThreatExternal(XPath xPath, Node node, int trackNum) throws XPathExpressionException
    {
        ThreatExternal threat = new ThreatExternal();
        threat.track = trackNum;
        XPathExpression expression;
        expression = xPath.compile("./name");
        threat.name = (String) expression.evaluate(node, XPathConstants.STRING);
        expression = xPath.compile("./movement");
        threat.speed = Integer.parseInt((String) expression.evaluate(node,XPathConstants.STRING));
        expression = xPath.compile("./health");
        threat.health = Integer.parseInt((String) expression.evaluate(node,XPathConstants.STRING));
        expression = xPath.compile("./shield");
        threat.shield = Integer.parseInt((String) expression.evaluate(node,XPathConstants.STRING));
        expression = xPath.compile("./deathScore");
        threat.deathScore = Integer.parseInt((String) expression.evaluate(node,XPathConstants.STRING));
        expression = xPath.compile("./escapeScore");
        threat.escapeScore = Integer.parseInt((String) expression.evaluate(node,XPathConstants.STRING));
        expression = xPath.compile("./rocketImmune");
        threat.missileImmune = Boolean.parseBoolean((String) expression.evaluate(node,XPathConstants.STRING));
        expression = xPath.compile(("./spawnMessage"));
        threat.spawnMessage = (String) expression.evaluate(node, XPathConstants.STRING);

        //select action command objects
        NodeList action;
        expression = xPath.compile("./xAction/effect");
        action = (NodeList) expression.evaluate(node,XPathConstants.NODESET);
        threat.xAction = GetExternalActionCommand(action,xPath);

        expression = xPath.compile("./yAction/effect");
        action = (NodeList) expression.evaluate(node,XPathConstants.NODESET);
        threat.yAction = GetExternalActionCommand(action,xPath);

        expression = xPath.compile("./zAction/effect");
        action = (NodeList) expression.evaluate(node,XPathConstants.NODESET);
        threat.zAction = GetExternalActionCommand(action,xPath);

        //get damage command object
        Element element;
        expression = xPath.compile("./damageAction/effect");
        element = (Element) expression.evaluate(node,XPathConstants.NODE);
        threat.damageAction = GetExternalDamageCommand(element,xPath);

        expression = xPath.compile("./deathAction/effect");
        element = (Element) expression.evaluate(node,XPathConstants.NODE);
        threat.deathAction = GetExternalDeathEffect(element);
        return threat;
    }

    private ThreatActionExternal GetExternalActionCommand(NodeList action, XPath xPath) throws XPathExpressionException
    {
        ThreatActionExternal threatAction;
        ArrayList<ActionEffectExternal> effects = new ArrayList<>();
        for(int i = 0; i<action.getLength();i++)
        {
            Element item = (Element)action.item(i);
            String commandType = item.getAttribute("type");
            switch (commandType) {
                case "damage":
                    XPathExpression expression = xPath.compile("./zone");
                    int zone = Integer.parseInt((String) expression.evaluate(item, XPathConstants.STRING));

                    expression = xPath.compile("./damage");
                    String damage = (String) expression.evaluate(item, XPathConstants.STRING);

                    expression = xPath.compile("./condition");
                    String condition = (String) expression.evaluate(item, XPathConstants.STRING);

                    expression = xPath.compile("./conditionValue");
                    int conditionValue = Integer.parseInt((String) expression.evaluate(item, XPathConstants.STRING));

                    expression = xPath.compile("./bypassBonus");
                    boolean bypassBonus = Boolean.parseBoolean((String) expression.evaluate(item, XPathConstants.STRING));

                    String data;
                    int damageMulti;
                    expression = xPath.compile("./damageMulti");
                    data = (String) expression.evaluate(item, XPathConstants.STRING);
                    if (!data.equals("")) {
                        damageMulti = Integer.parseInt(data);
                    } else {
                        damageMulti = 1;
                    }
                    effects.add(new ActionExternalDamageShip(zone, damage, bypassBonus, condition, conditionValue, damageMulti));
                    break;
                case "buff":
                    expression = xPath.compile("./stat");
                    String stat = (String) expression.evaluate(item, XPathConstants.STRING);

                    expression = xPath.compile("./amount");
                    int value = Integer.parseInt((String) expression.evaluate(item, XPathConstants.STRING));
                    effects.add(new ActionExternalBuff(stat, value));
                    break;
                case "shieldDrain":
                    expression = xPath.compile("./amount");
                    String amount = (String) expression.evaluate(item, XPathConstants.STRING);
                    effects.add(new ActionExternalShieldDrain(amount));
                    break;
                case "toggle":
                    effects.add(new ActionExternalToggle());
                    break;
            }
        }
        threatAction = new ThreatActionExternal(effects);
        return threatAction;
    }

    private ThreatActionInternal GetInternalActionCommand(NodeList action, XPath xPath) throws XPathExpressionException {
        ThreatActionInternal threatAction;
        ArrayList<ActionEffectInternal> effects = new ArrayList<>();
        for(int i = 0; i<action.getLength();i++)
        {
            Element item = (Element)action.item(i);
            String commandType = item.getAttribute("type");
            switch (commandType)
            {
                case "moveBlue":
                {
                    effects.add(new ActionInternalMoveBlue());
                    break;
                }
                case "moveRed":
                {
                    effects.add(new ActionInternalMoveRed());
                    break;
                }
                case "turbolift":
                {
                    effects.add(new ActionInternalTurboLift());
                }
                case "damage":
                {
                    XPathExpression expression = xPath.compile("./target");
                    String target = (String) expression.evaluate(item, XPathConstants.STRING);

                    expression = xPath.compile("./damage");
                    String damage = (String) expression.evaluate(item, XPathConstants.STRING);

                    effects.add(new ActionInternalDamageShip(target,damage));
                    break;
                }
                case "drainPower" :
                {
                    XPathExpression expression = xPath.compile("./target");
                    String target = (String) expression.evaluate(item, XPathConstants.STRING);

                    expression = xPath.compile("./value");
                    String value = (String) expression.evaluate(item, XPathConstants.STRING);

                    expression = xPath.compile("./condition");
                    String condition = (String) expression.evaluate(item, XPathConstants.STRING);

                    effects.add(new ActionInternalEnergyDrain(target,condition,value));
                    break;
                }
                case "leakPower" :
                {
                    XPathExpression expression = xPath.compile("./target");
                    String target = (String) expression.evaluate(item, XPathConstants.STRING);
                    expression = xPath.compile("./condition");
                    String condition = (String) expression.evaluate(item, XPathConstants.STRING);

                    effects.add(new ActionInternalLeakPower(condition,target));
                    break;
                }
                case "conditionalDamageMove" :
                {
                    XPathExpression expression = xPath.compile("./damage");
                    int damage = Integer.parseInt((String) expression.evaluate(item, XPathConstants.STRING));

                    expression = xPath.compile("./direction");
                    String direction = (String) expression.evaluate(item,XPathConstants.STRING);

                    effects.add(new ActionInternalConditionDamageMove(damage,direction));
                    break;
                }
                case "knockOut" :
                {
                    XPathExpression expression = xPath.compile("./target");
                    String target = (String) expression.evaluate(item,XPathConstants.STRING);
                    ActionInternalKnockOut effect = new ActionInternalKnockOut(target);
                    if(target.equals("allBut"))
                    {
                        expression = xPath.compile("./locationX");
                        int locationX = Integer.parseInt((String)expression.evaluate(item,XPathConstants.STRING));
                        expression = xPath.compile("./locationY");
                        int locationY = Integer.parseInt((String)expression.evaluate(item,XPathConstants.STRING));
                        effect.exclude = new Pair<>(locationX,locationY);
                    }
                    effects.add(effect);
                    break;
                }
                case "endGame" :
                {
                    effects.add(new ActionInternalEndGame());
                    break;
                }
                case "grow" :
                {
                    effects.add(new ActionInternalGrow());
                    break;
                }
                case "globalDamageMod" :
                {
                    effects.add(new ActionInternalGlobalDamageModifier());
                    break;
                }
            }
        }
        threatAction = new ThreatActionInternal(effects);
        return threatAction;
    }

    private ThreatActionInternal GetInternalSpawnCommand(NodeList action, XPath xPath)
    {
        ArrayList<ActionEffectInternal> effects = new ArrayList<>();
        for(int i = 0; i<action.getLength();i++)
        {
            ActionEffectInternal effect;
            Element item = (Element) action.item(i);
            String commandType = item.getAttribute("type");
            switch (commandType)
            {
                case "setPosition" :
                {
                   effect =  new SetInternalPosition();
                   effects.add(effect);
                }
                case "setHealth" :
                {
                    effect = new OnSpawnSetHealth();
                    effects.add(effect);
                }
            }
        }
        return new ThreatActionInternal(effects);
    }

    private ArrayList<Pair<Integer,Integer>> GetLocations(NodeList data)
    {
        ArrayList<Pair<Integer,Integer>> locations = new ArrayList<>();
        for(int i = 0; i<data.getLength();i++)
        {
            Pair<Integer,Integer> location;
            Element item = (Element) data.item(i);
            int section = Integer.parseInt(item.getAttribute("section"));
            int zone = Integer.parseInt(item.getAttribute("zone"));
            location = new Pair<>(zone,section);
            locations.add(location);
        }
        return locations;
    }

    private OnDamageExternal GetExternalDamageCommand(Element damType, XPath xPath) throws XPathExpressionException {
        OnDamageExternal effect;
        String type = damType.getAttribute("type");
        switch (type) {
            case "toggleExt":
                effect = new OnDamageExternalToggle();
                break;
            case "toggleInt":
                effect = new OnDamageExternalSelfToggle();
                break;
            case "noShield":
                XPathExpression expression = xPath.compile("./trigger");
                String trigger = (String) expression.evaluate(damType, XPathConstants.STRING);
                effect = new OnDamageExternalNoShield(trigger);
                break;
            case "noRange":
                effect = new OnDamageExternalExcludeRange();
                break;
            default:
                effect = new OnDamageExternalDefault();
                break;
        }
        return effect;
    }
    private OnDamageInternal GetInternalDamageEffect(Element damType,XPath xPath) throws XPathExpressionException {
        OnDamageInternal effect = null;
        String type = damType.getAttribute("type");
        switch (type)
        {
            case "combat":
            {
                effect = new OnDamageInternalCombat(Boolean.parseBoolean(damType.getTextContent()));
                break;
            }
            case "malfSingle":
            {

                effect = new OnDamageInternalMalfSingle(damType.getTextContent());
                break;
            }
            case "malfMultiBonus":
            {
                XPathExpression expression = xPath.compile("./target");
                String target = (String) expression.evaluate(damType, XPathConstants.STRING);
                expression = xPath.compile("./bonus");
                int bonus = Integer.parseInt((String) expression.evaluate(damType, XPathConstants.STRING));
                effect = new OnDamageInternalMalfMultiBonus(target,bonus);
            }
        }
        return effect;
    }
    private OnDeathExternal GetExternalDeathEffect(Element deathType)
    {
        OnDeathExternal effect;
        String type = deathType.getAttribute("type");
        switch (type) {
            case "damageOthers":
                effect = new OnDeathExternalDamageOthers();
                break;
            case "damageSpaceCount":
                effect = new OnDeathExternalDamageSpaceCount();
                break;
            default:
                effect = null;
                break;
        }
        return effect;
    }
}
