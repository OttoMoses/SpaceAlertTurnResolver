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
import com.otto.spacealertresolver.Activities.MainActivity;
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
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalDelayPlayers;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalDie;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalEndGame;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalGlobalBuff;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalKnockOut;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalMoveOthers;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalSelfDamage;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternal;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalBuff;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalBypassSource;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalCount;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalDestroyInterceptors;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalMaxValue;
import com.otto.spacealertresolver.ThreatActions.External.OnDeathExternal;
import com.otto.spacealertresolver.ThreatActions.External.OnDeathExternalRemoveGlobalBonus;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionEffectInternal;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalConditionDamageMove;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalDamageShip;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalDelayPlayers;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalDestroyRocket;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalDisableBots;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalDrainFuel;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalEndGame;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalEnergyDrain;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalGlobalDamageModifier;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalGrow;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalKnockOut;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalLeakPower;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalMoveBlue;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalMoveRed;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalDefault;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalToggle;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalSelfToggle;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalNoShield;
import com.otto.spacealertresolver.ThreatActions.External.OnDeathExternalDamageOthers;
import com.otto.spacealertresolver.ThreatActions.External.OnDeathExternalDamageSpaceCount;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalShieldDrain;
import com.otto.spacealertresolver.ThreatActions.External.ThreatActionExternal;
import com.otto.spacealertresolver.ThreatActions.External.ActionEffectExternal;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalToggle;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalMoveToPlayerCount;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalSpread;
import com.otto.spacealertresolver.ThreatActions.Internal.ActionInternalTurboLift;
import com.otto.spacealertresolver.ThreatActions.Internal.OnDamageCountRequired;
import com.otto.spacealertresolver.ThreatActions.Internal.OnDamageInternal;
import com.otto.spacealertresolver.ThreatActions.Internal.OnDamageInternalCombat;
import com.otto.spacealertresolver.ThreatActions.Internal.OnDamageInternalCombatMulti;
import com.otto.spacealertresolver.ThreatActions.Internal.OnDamageInternalMalfMultiBonus;
import com.otto.spacealertresolver.ThreatActions.Internal.OnDamageInternalMalfSingle;
import com.otto.spacealertresolver.ThreatActions.Internal.OnDeathInternal;
import com.otto.spacealertresolver.ThreatActions.Internal.OnDeathInternalRemoveEffect;
import com.otto.spacealertresolver.ThreatActions.Internal.OnDeathInternalKnockOut;
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

public class Game {
    public PlayerAction[] actions;
    public ArrayList<String> threatNames;
    public Player[] players;
    public int numRounds;
    public int gameType;
    public int currentRound;
    public Section[][] ship;
    public boolean computerMaintained;
    public boolean interceptorsDocked;
    public int missileCount;
    public boolean missileInSpace;
    public boolean strafingRun;
    public ThreatTrack[] threatTracks;
    public ThreatString[] selectedThreatStrings;
    private Threat[] selectedThreats;
    public ArrayList<Threat> activeThreats;
    public String[] colors;
    public ArrayList<Threat> deadThreats;
    public int observationOne;
    public int observationTwo;
    public int observationThree;
    public int phase;
    public ArrayList<String> internalThreatNames;
    public boolean strafeHeroic;
    public int fissureMod;
    public int globalDamageBuff;
    public int globalShieldBuff;
    private String shipName;
    private ArrayList<DamageToken> redDamage;
    private ArrayList<DamageToken> whiteDamage;
    private ArrayList<DamageToken> blueDamage;
    private ArrayList<Threat> escapedThreats;
    private ArrayList<Threat> externalThreats;
    private ArrayList<Threat> allThreats;
    public ArrayList<String> selectedNames;
    public ArrayList<Threat> internalThreats;
    private int[] observationScore;
    private boolean missileDamage;
    private boolean gameEnd;
    public int currentPlayer;

    public Game()
    {
        colors = new String[]
                {
                        "Red",
                        "white",
                        "Blue",
                        "Internal",
                };
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
        observationScore = new int[]{0, 1, 2, 3, 5, 7};
    }

    //Public method to set game-wide variables when starting a new session
    public void SetupGame(int numPlayers, String name,ArrayList<String> threatNames,ArrayList<String> internalNames,ArrayList<Threat> allThreats,ArrayList<Threat> internalThreats,ArrayList<Threat> externalThreats) {
        shipName = name;
        //set variables that depend on game type

        gameType = 2;
        numRounds = 12;
        threatTracks = new ThreatTrack[4];

        //import xml data
        this.threatNames = threatNames;
        this.internalThreatNames = internalNames;
        this.allThreats = allThreats;
        this.internalThreats = internalThreats;
        this.externalThreats = externalThreats;
        selectedNames = new ArrayList<>();
        // create the right number of threat tracks and an array to store them;

        threatTracks[0] = new ThreatTrack("Red");
        threatTracks[1] = new ThreatTrack("white");
        threatTracks[2] = new ThreatTrack("Blue");
        threatTracks[3] = new ThreatTrack("Internal");
        selectedThreatStrings = new ThreatString[8];
        Arrays.fill(selectedThreatStrings, new ThreatString(threatNames.size() + 1, 4));

        //create creates an array to store players, populates it with unnamed players, and populates their action arrays with blank actions
        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            Player p = new Player("Unnamed Player", numRounds);
            for (int roundCount = 0; roundCount < numRounds; roundCount++) {
                p.actionsDefault[roundCount] = actions[0];
            }
            players[i] = p;
            p.playerID = i;
        }
        InitializeShip();
    }


    public void StartGame() {
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
        globalDamageBuff = 0;
        gameEnd = false;
        BuildDamageDecks();

        //set player starting values
        for (Player p : players)
        {
            p.actions = p.actionsDefault.clone();
            p.zonePosition = 1;
            p.sectionPosition = 1;
            p.leadingBots = false;
            p.flyingInterceptors = false;
            p.unconscious = false;
        }

        //set station and section starting values
        for (int zone = 0; zone < ship.length; zone++) {
            for (int section = 0; section < ship[zone].length; section++) {
                Section s = ship[zone][section];
                if (s.CSystem.getClass() == BattlebotStation.class) {
                    s.hasBots = true;
                }
                s.ASystem.StartGame();
                s.BSystem.StartGame();
                s.CSystem.StartGame();
                s.StartGame();
            }
        }

        // get threats
        selectedThreats = new Threat[8];
        for(int i = 0; i < selectedThreatStrings.length; i++)
        {
            ThreatString ts = selectedThreatStrings[i];
            if(ts.trackNum != 4)
            {
                Threat threat = allThreats.get(ts.threatID);
                threat.track = ts.trackNum;
                threat.ResetThreat();
                selectedThreats[i] = threat;
            }
            else
            {
                selectedThreats[i] = null;
            }
        }

        //check threat tracks
        for (ThreatTrack t : threatTracks) {
            if (t.XSpace == null) {
                t.XSpace = new int[]{6};
            }
            if (t.YSpaces == null) {
                t.YSpaces = new int[]{};
            }
            if (t.EndSpace == null) {
                t.EndSpace = new int[]{10};
            }
        }
    }

    private void BuildDamageDecks() {
        redDamage = new ArrayList<>(Arrays.asList(new ElevatorDamage(), new GunDamage(1), new GunDamage(0), new StructuralDamage(), new PowerDamage(1), new PowerDamage(0)));
        whiteDamage = new ArrayList<>(Arrays.asList(new ElevatorDamage(), new GunDamage(1), new PulseGunDamage(0), new StructuralDamage(), new PowerDamage(1), new PowerDamage(0)));
        blueDamage = new ArrayList<>(Arrays.asList(new ElevatorDamage(), new GunDamage(1), new GunDamage(0), new StructuralDamage(), new PowerDamage(1), new PowerDamage(0)));
        Collections.shuffle(redDamage);
        Collections.shuffle(whiteDamage);
        Collections.shuffle(blueDamage);

    }

    private void InitializeShip() {
        //initialize sections
        ship = new Section[3][2];
        ship[0][0] = new Section("red", "lower", true, 1, 0, 2, 3, 2, 3, 0, 0);
        ship[0][1] = new Section("red", "upper", false, 0, 0, 1, 2, 4, 3, 0, 1);
        ship[1][0] = new Section("white", "lower", false, 1, 0, 3, 5, 1, 2, 1, 0);
        ship[1][1] = new Section("white", "upper", false, 1, 0, 1, 3, 5, 3, 1, 1);
        ship[2][0] = new Section("blue", "lower", false, 1, 0, 2, 3, 2, 3, 2, 0);
        ship[2][1] = new Section("blue", "upper", true, 2, 0, 1, 2, 4, 3, 2, 1);

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
        if (currentRound == 4 || currentRound == 8) {
            phase++;
        }

        //special text for first round
        if (currentRound == 1) {
            roundMessage += "\n" + shipName + " Hyperspace jumps into an unknown sector! \n";
        }

        roundMessage += "At T + " + currentRound + ":\n";
        roundMessage += MaintenanceCheck();
        roundMessage += "\n----Threat Spawn Step----\n";
        roundMessage += SpawnThreats(currentRound);
        roundMessage += "\n----Player Action Step----\n";
        roundMessage += ProcessActions(currentRound);

        //generate targets for damage processes
        ArrayList<Threat> externalTargets = new ArrayList<>(activeThreats);
        externalTargets.removeAll(internalThreats);

        roundMessage += "\n----Calculate Threat Damage Step----\n";
        roundMessage += DamageThreats(externalTargets);
        roundMessage += KillThreats(activeThreats);
        roundMessage += "\n----Threat Move Step----\n";
        roundMessage += MoveThreats("game");
        if (escapedThreats.size() != 0) {
            activeThreats.removeAll(escapedThreats);
        }
        //special text for last round
        if (currentRound == numRounds) {
            roundMessage += "\n" + shipName + " Hyperspace jumps back to known space!";
        }

        return roundMessage;
    }

    private String MaintenanceCheck() {
        StringBuilder message = new StringBuilder();
        if (currentRound == 3 || currentRound == 6 || currentRound == 10) {
            message.append("\n----Computer Maintenance Check----\n");
            if (!computerMaintained)
            {
                message.append("\nThe computer shuts down delaying all players on the ship this round!\n");
                for (Player p : players)
                {
                    message.append(p.Delay(currentRound));
                }
                message.append("\n");
            }
            else
            {
                message.append("\nThe computer was correctly maintained and stays on this round \n");
            }
            computerMaintained = false;
        }
        return message.toString();
    }

    public String MoveThreats(String source) {
        StringBuilder message = new StringBuilder();
        for (Threat t : activeThreats)
        {
            message.append("\n");
            if (!gameEnd)
            {
                int movement;
                if (source.equals("game"))
                {
                    movement = t.GetSpeed();
                }
                else
                {
                    movement = Integer.parseInt(source);
                }
                ThreatTrack track = threatTracks[t.track];
                int newPosition = t.position + movement;
                if (t.getClass().equals(ThreatExternal.class)) {
                    message.append("The ").append(t.name).append(" moves ").append(movement).append(" spaces closer to the ship.\n");
                } else {
                    message.append("The ").append(t.name);
                    if (((ThreatInternal) t).plural) {
                        message.append(" move ");
                    } else {
                        message.append(" moves ");
                    }
                    message.append(movement).append(" spaces on the internal track.\n");
                }
                //check for attack spaces
                for (int space : track.XSpace) {
                    if ((space < newPosition && space > t.position) || space == newPosition) {
                        message.append(t.ExecuteXAction(ship, players));
                    }
                }
                for (int space : track.YSpaces)
                {
                    if ((space < newPosition && space > t.position) || space == newPosition)
                    {
                        message.append(t.ExecuteYAction(ship, players));
                    }
                }
                for (int space : track.EndSpace) {
                    if (space < newPosition || space == newPosition) {
                        message.append(t.ExecuteZAction(ship, players));
                        if(!gameEnd)
                        {
                            if (t.getClass().equals(ThreatExternal.class))
                            {
                                message.append("The ").append(t.name).append(" escapes!\n\n");
                            }
                            else
                            {
                                if (((ThreatInternal) t).threatType.contains("combat"))
                                {
                                    if (((ThreatInternal) t).plural)
                                    {
                                        message.append("The ").append(t.name).append(" escape!\n\n");
                                    }
                                    else
                                    {
                                        message.append("The ").append(t.name).append(" escapes!\n\n");
                                    }
                                }
                                else
                                {
                                    if (((ThreatInternal) t).plural)
                                    {
                                        message.append("The ").append(t.name).append(" are now unfixable!\n\n");
                                    }
                                    else
                                    {
                                        message.append("The ").append(t.name).append(" is now unfixable!\n\n");
                                    }
                                }
                            }
                        }
                        escapedThreats.add(t);
                    }
                    else {
                        if (t.getClass().equals(ThreatExternal.class)) {
                            message.append("The ").append(t.name).append(" is now ").append(threatTracks[t.track].EndSpace[0] - t.position).append(" space away from the ship!\n");
                        } else {
                            message.append("The ").append(t.name);
                            if (((ThreatInternal) t).plural) {
                                message.append(" are now  ");
                            } else {
                                message.append(" is now ");
                            }
                            message.append(threatTracks[t.track].EndSpace[0] - t.position).append(" space away from escaping!\n");
                        }
                        t.position = newPosition;
                    }
                }
            }
        }
        for (Threat t : escapedThreats) {
            activeThreats.remove(t);
        }
        if (message.toString().equals("\n")) {
            message.append("There are no threats to move this turn.\n");
        }
        return message.toString();
    }

    private void ResetState() {
        //Reset gun fire state and internal damage bundles
        for (Section[] aShip : ship)
        {
            for (Section s : aShip)
            {
                s.hasFired = false;
                s.heroicFire = false;
                s.combatDamage = null;
                s.malfCDamage = null;
                s.malfBDamage= null;
                s.liftUsed = false;
            }
        }

        //reset player delay count
        for(Player p : players)
        {
            p.delayed = false;
        }

        //reset strafing run boolean
        strafingRun = false;
        strafeHeroic = false;

        //advance missile track
        missileDamage = false;
        if (missileInSpace)
        {
            missileDamage = true;
            missileInSpace = false;
        }
    }

    private String SpawnThreats(int round) {
        //spawn Threats
        String message = "";
        if (round <= 8) {
            if (selectedThreats[currentRound - 1] != null) {
                Threat t = selectedThreats[round - 1];
                if (t.getClass() == ThreatExternal.class) {
                    ((ThreatExternal) t).shield += globalShieldBuff;
                }
                activeThreats.add(t);
                if(t.getClass().equals(ThreatExternal.class))
                {
                    message += "\n";
                }
                message += t.ExecuteSpawnAction(ship);
            }
        }
        if (message.equals("\n")) {
            message += "No threats spawned this turn.\n";
        } else {
            message += "\n";
        }
        return message;
    }

    private String ProcessActions(int round)
    {
        //process player actions
        StringBuilder message = new StringBuilder();
        for (Player p : players)
        {
            currentPlayer = p.playerID;
            if (p.unconscious)
            {
                message.append("\n").append(p.playerName).append(" is knocked out and can't take any actions!");
            }
            else
            {
                //special check for players who are in space
                if (p.flyingInterceptors)
                {
                    switch (p.actions[round - 1].name)
                    {
                        case "BattleBots action":
                            message.append("\n").append(actions[7].Execute(p)).append("\n");
                            break;
                        case "Heroic Battlebots action":
                            message.append("\n").append(actions[11].Execute(p)).append("\n");
                            break;
                        default:
                            message.append("\n").append(p.playerName).append(" returns to the ship\n");
                            p.flyingInterceptors = false;
                            interceptorsDocked = true;
                            break;
                    }
                }
                else
                {
                    message.append("\n").append(p.playerName).append(" ").append(p.actions[round - 1].Execute(p)).append("\n");
                }
            }
            ArrayList<Threat> internalTargets = new ArrayList<>(activeThreats);
            internalTargets.removeAll(externalThreats);
            if (internalTargets.size() != 0)
            {
                message.append(InternalThreatDamage(internalTargets));
                message.append(KillThreats(activeThreats));
            }
        }
        return message.toString();
    }

    private String ExternalThreatDamage(ArrayList<Threat> targets) {
        StringBuilder message = new StringBuilder();
        ArrayList<Threat> invalid = new ArrayList<>();
        for(Threat t : targets)
        {
            //clear existing damage bundles
            ((ThreatExternal)t).bundle = null;

            //remove invalid targets
            if(t.name.contains("Satellite") && ((ThreatExternal) t).rangeThree)
            {
                invalid.add(t);
            }
            if(((ThreatExternal) t).damageAction.getClass() == OnDamageExternalToggle.class)
            {
                if(((ThreatExternal) t).damageAction.getClass() == OnDamageExternalSelfToggle.class && ((ThreatExternal) t).toggle)
                {
                    invalid.add(t);
                }
            }
        }
        targets.removeAll(invalid);

        //computeDamage
        ArrayList<ThreatExternal> redThreats = new ArrayList<>();
        ArrayList<ThreatExternal> whiteThreats = new ArrayList<>();
        ArrayList<ThreatExternal> blueThreats = new ArrayList<>();
        ArrayList<ThreatExternal> rangeOneThreats = new ArrayList<>();
        ArrayList<ThreatExternal> rangeTwoThreats = new ArrayList<>();
        ArrayList<ThreatExternal> rangeThreeThreats = new ArrayList<>();
        ExternalDamageBundle[][] bundles = new ExternalDamageBundle[][]
                {
                        {new ExternalDamageBundle(), new ExternalDamageBundle(), new ExternalDamageBundle()},
                        {new ExternalDamageBundle(), new ExternalDamageBundle(), new ExternalDamageBundle()},
                        {new ExternalDamageBundle(), new ExternalDamageBundle(), new ExternalDamageBundle()}
                };
        for (Threat threat : targets) {
            ThreatExternal t = (ThreatExternal) threat;
            switch (t.track) {
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

            if (t.position <= threatTracks[t.track].rangeTwo) {
                if (threatTracks[t.track].rangeTwo == 0) {
                    rangeTwoThreats.add(t);
                } else {
                    t.rangeThree = true;
                    rangeThreeThreats.add(t);
                }
            } else if (t.position >= threatTracks[t.track].rangeTwo && t.position < threatTracks[t.track].rangeOne) {
                rangeTwoThreats.add(t);
            } else {
                rangeOneThreats.add(t);
            }
        }
        //build gun damage bundles
        for (int zoneNum = 0; zoneNum < ship.length; zoneNum++) {
            Section[] zone = ship[zoneNum];
            for (int deck = 0; deck < zone.length; deck++) {
                Section s = zone[deck];
                if (s.hasFired) {
                    int damage = s.getGunDamage();
                    if (s.heroicFire)
                    {
                        damage += 1;
                    }
                    int range = s.getGunRange();
                    String source = s.ASystem.name;
                    Pair<String, Integer> damageSource = new Pair<>(source, damage);

                    for (int i = 1; i <= range; i++) {
                        if (s.ASystem.name.equals("pulse cannon")) {
                            bundles[0][i - 1].damageSources.add(damageSource);
                            bundles[1][i - 1].damageSources.add(damageSource);
                            bundles[2][i - 1].damageSources.add(damageSource);
                        } else {
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
        if (redThreats.size() != 0) {
            if (rangeThreeThreats.contains(redThreats.get(0))) {
                ExternalDamageBundle bundle = bundles[0][2];
                if (bundle.damageSources.size() != 0) {
                    redThreats.get(0).bundle = bundle;
                }
            } else if (rangeTwoThreats.contains(redThreats.get(0))) {
                ExternalDamageBundle bundle = bundles[0][1];
                if (bundle.damageSources.size() != 0) {
                    redThreats.get(0).bundle = bundle;
                }
            } else {
                ExternalDamageBundle bundle = bundles[0][0];
                if (bundle.damageSources.size() != 0) {
                    redThreats.get(0).bundle = bundle;
                }
            }
        }

        //push gun damage bundle to white zone
        if (whiteThreats.size() != 0) {
            if (rangeThreeThreats.contains(whiteThreats.get(0))) {
                ExternalDamageBundle bundle = bundles[1][0];
                if (bundle.damageSources.size() != 0) {
                    whiteThreats.get(0).bundle = bundle;
                }
            } else if (rangeTwoThreats.contains(whiteThreats.get(0))) {
                ExternalDamageBundle bundle = bundles[1][1];
                if (bundle.damageSources.size() != 0) {
                    whiteThreats.get(0).bundle = bundle;
                }
            } else {
                ExternalDamageBundle bundle = bundles[1][0];
                if (bundle.damageSources.size() != 0) {
                    whiteThreats.get(0).bundle = bundle;
                }
            }
        }

        //push gun damage bundle to blue zone
        if (blueThreats.size() != 0) {
            if (rangeThreeThreats.contains(blueThreats.get(0))) {
                ExternalDamageBundle bundle = bundles[2][2];
                if (bundle.damageSources.size() != 0) {
                    blueThreats.get(0).bundle = bundle;
                }
            } else if (rangeTwoThreats.contains(blueThreats.get(0))) {
                ExternalDamageBundle bundle = bundles[2][1];
                if (bundle.damageSources.size() != 0) {
                    blueThreats.get(0).bundle = bundle;
                }
            } else {
                ExternalDamageBundle bundle = bundles[2][0];
                if (bundle.damageSources.size() != 0) {
                    blueThreats.get(0).bundle = bundle;
                }
            }
        }

        //check for missile and push missile damage bundle
        if (missileDamage) {
            ArrayList<ThreatExternal> missileTargets = new ArrayList<>();
            ThreatExternal priotiryTarget = null;

            //build list of valid missile targets
            if (rangeOneThreats.size() != 0) {
                for (ThreatExternal t : rangeOneThreats) {
                    switch (t.missileImmune) {
                        case 0: {
                            missileTargets.add(t);
                            break;
                        }
                        case 1: {
                            break;
                        }
                        case 2: {
                            missileTargets.add(t);
                            break;
                        }
                        case 3: {
                            priotiryTarget = t;
                            break;
                        }
                    }
                }
            }

            if (rangeTwoThreats.size() != 0) {
                for (ThreatExternal t : rangeTwoThreats) {
                    if (t.missileImmune != 1) {
                        missileTargets.add(t);
                    }
                }
            }

            if (priotiryTarget == null)
            {
                if(missileTargets.size() != 0)
                {
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
                    if (target.missileImmune == 2)
                    {
                        message.append("The ").append(target.name).append(" is targeted by a missile but it dodges!\n");
                    }
                    else
                    {
                        if (target.bundle != null)
                        {
                            target.bundle.damageSources.add(damageSource);
                        }
                        else
                        {
                            ExternalDamageBundle db = new ExternalDamageBundle();
                            db.damageSources.add(damageSource);
                            target.bundle = db;
                        }
                    }
                }
                else
                {
                    message.append("The missile fired last turn has no valid targets!\n");
                }
            }
            else
            {
                Pair<String, Integer> damageSource = new Pair<>("missile", 3);
                message.append("The ").append(priotiryTarget.name).append(" forces a missile to target it!\n");
                if (priotiryTarget.bundle != null)
                {
                    priotiryTarget.bundle.damageSources.add(damageSource);
                }
                else
                {
                    ExternalDamageBundle db = new ExternalDamageBundle();
                    db.damageSources.add(damageSource);
                    priotiryTarget.bundle = db;
                }
            }
        }
        //check for fighters and push fighter damage bundles

        if (strafingRun) {
            int damage;
            if (strafeHeroic) {
                damage = 2;
            } else {
                damage = 1;
            }
            if (rangeOneThreats.size() != 0) {
                if (rangeOneThreats.size() == 1) {
                    if (strafeHeroic) {
                        damage = 4;
                    } else {
                        damage = 3;
                    }
                }
                Pair<String, Integer> ds = new Pair<>("fighters", damage);
                for (ThreatExternal t : rangeOneThreats) {
                    if (t.bundle != null) {
                        t.bundle.damageSources.add(ds);
                    } else {
                        ExternalDamageBundle db = new ExternalDamageBundle();
                        db.damageSources.add(ds);
                        t.bundle = db;
                    }
                }
            }
        }

        //process damage bundles
        for (Threat t : targets) {
            if (t.getClass().equals(ThreatExternal.class)) {
                if (((ThreatExternal) t).bundle != null) {
                    message.append(((ThreatExternal) t).ExecuteDamageAction());
                }
            }
        }
        if (message.toString().equals("")) {
            return "No external threats have taken damage this turn.";
        } else {
            return message.toString();
        }
    }

    private String InternalThreatDamage(ArrayList<Threat> targets)
    {
        StringBuilder message = new StringBuilder("");
        for (Threat threat : targets)
        {
            ThreatInternal t = (ThreatInternal) threat;
            message.append(t.ExecuteDamageAction(ship, players));
        }
        return message.toString();
    }

    private String DamageThreats(ArrayList<Threat> externalTargets) {
        StringBuilder message = new StringBuilder();
        if (!externalTargets.isEmpty()) {
            message.append("\n").append(ExternalThreatDamage(externalTargets)).append("\n");
        } else {
            message.append("\nThere are no external threats this turn.\n");
        }
        //special check for nemesis threat
        for (Threat t : activeThreats) {
            if (t.getClass() == ThreatExternal.class) {
                if (((ThreatExternal) t).damageAction.getClass() == OnDamageExternalCount.class) {
                    if (((OnDamageExternalCount) ((ThreatExternal) t).damageAction).damaged) {
                        int value = ((OnDamageExternalCount) ((ThreatExternal) t).damageAction).value;
                        message.append("\nBecause it took damage this turn the ").append(t.name).append(" attacks all zones for ").append(value).append(" damage!\n");
                        ((OnDamageExternalCount) ((ThreatExternal) t).damageAction).damaged = false;
                        message.append("The ").append(t.name).append(MainActivity.game.ShipDamage(0, value, false, false, false)).append("\n\n");
                        message.append("The ").append(t.name).append(MainActivity.game.ShipDamage(1, value, false, false, false)).append("\n\n");
                        message.append("The ").append(t.name).append(MainActivity.game.ShipDamage(2, value, false, false, false));
                    }
                }
            }
        }
        return message.toString();
    }

    public String KillThreats(ArrayList<Threat> targets) {
        StringBuilder message = new StringBuilder();
        for (Threat t : targets) {
            if (t.damage >= t.health) {
                if(t.getClass() == ThreatExternal.class)
                message.append("\n");
                t.ExecuteDeathAction(ship, players);
                deadThreats.add(t);
                if (t.getClass().equals(ThreatInternal.class) && ((ThreatInternal) t).plural) {
                    message.append("The ").append(t.name).append(" were destroyed!\n");
                } else {
                    message.append("The ").append(t.name).append(" was destroyed!\n");
                }
            }
        }
        if (deadThreats.size() != 0) {
            activeThreats.removeAll(deadThreats);
        }
        return message.toString();
    }

    public String ShipDamage(int zone, int damage, boolean bypassBonus, boolean internal, boolean plural) {
        StringBuilder damageMessage = new StringBuilder();
        int realDamage = damage + globalDamageBuff;
        ArrayList<DamageToken> tokens = null;
        if (plural) {
            damageMessage.append(" attack");
        } else {
            damageMessage.append(" attacks");
        }
        damageMessage.append(" the ").append(colors[zone]).append(" zone for ").append(realDamage).append(" damage!");
        Section shield = ship[zone][1];
        switch (zone) {
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
        if (shield.powerCubes != 0 && !internal) {
            if (shield.powerCubes >= realDamage) {
                damageMessage.append("The shield in the ").append(shield.zoneName).append(" zone blocks all the damage!");
                shield.powerCubes -= realDamage;
                realDamage = 0;
                if (shield.powerCubes != 0) {
                    damageMessage.append("\nThere is ").append(shield.powerCubes).append(" power left in the ").append(shield.zoneName).append(" zone shield.");
                } else {
                    damageMessage.append("\nThere is no power remaining in the ").append(shield.zoneName).append(" zone shield!");
                }
            } else {
                realDamage -= shield.powerCubes;
                damageMessage.append("The ").append(shield.zoneName).append(" zone shield blocked ").append(shield.powerCubes).append(" damage!");
                shield.powerCubes = 0;
                damageMessage.append("\nThere is no power remaining in the ").append(shield.zoneName).append(" zone shield!");
            }

        }
        if (bypassBonus) {
            realDamage = realDamage * 2;
        }
        if ((fissureMod == 1 && zone == 0) || fissureMod == 2) {
            realDamage = realDamage * 2;
            damageMessage.append("\n Damage is doubled by the fissure!");
        }
        for (int i = 0; i < realDamage; i++) {
            if (tokens != null)
            {
                damageMessage.append("\n");
                if (tokens.size() != 0)
                {
                    DamageToken d = tokens.get(0);
                    damageMessage.append(d.doDamage(zone, ship));
                    tokens.remove(d);
                }
                else {
                    damageMessage.append("\nThe Ship is destroyed!");
                    break;
                }
            }
        }
        return damageMessage.toString();
    }

    public String EndGame(boolean dead)
    {
        gameEnd = true;
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
        endMessage += "\n" + deathScore + " points for defeated threats";

        //add points for survived threats
        for (Threat t : escapedThreats) {
            escapeScore += t.escapeScore;
        }
        endPoints += escapeScore;
        endMessage += "\n" + escapeScore + " points for survived threats";

        //add points for manual observations
        windowScore += observationScore[observationOne];
        windowScore += observationScore[observationTwo];
        windowScore += observationScore[observationThree];
        endPoints += windowScore;
        endMessage += "\n" + windowScore + " points for manual confirmation of scanner data";

        //subtract points for damage done
        damagePenalty += (6 - redDamage.size());
        damagePenalty += (6 - whiteDamage.size());
        damagePenalty += (6 - blueDamage.size());
        endPoints -= damagePenalty;
        endMessage += "\n" + damagePenalty + " negative points for total damage to the ship";

        //find most damaged zone
        List<ArrayList<DamageToken>> allTheLists = new ArrayList<>(Arrays.asList(whiteDamage, redDamage, blueDamage));
        Collections.sort(allTheLists, new Comparator<ArrayList>() {
            public int compare(ArrayList a1, ArrayList a2) {
                return a2.size() + a1.size();
            }
        });
        ArrayList mostDamaged = allTheLists.get(0);
        //subtract damage to the most damaged zone
        mostDamagedPenalty += (6 - mostDamaged.size());
        endPoints -= mostDamagedPenalty;
        endMessage += "\n" + mostDamagedPenalty + " negative points for damage to the most damaged zone";

        //subtract points for knocked out players
        for (Player p : players) {
            if (p.unconscious) {
                knockedOutPenalty += 2;
            }
        }
        endPoints -= knockedOutPenalty;
        endMessage += "\n" + knockedOutPenalty + " negative points knocked out players";

        //subtract points for damaged bots
        for (Player p : players) {
            if (p.damagedBots) {
                damagedBotsPenalty += 1;
            }
        }
        endPoints -= damagedBotsPenalty;
        endMessage += "\n" + damagedBotsPenalty + " negative points damaged battlebot squads";

        if (!dead) {
            endMessage += "\nFinal score: " + endPoints;
        } else {
            endMessage += "\nFinal score: 0! \nDestroyed ships don't score points!";
        }
        endMessage += "\nThanks for playing!";
        return endMessage;
    }
}
