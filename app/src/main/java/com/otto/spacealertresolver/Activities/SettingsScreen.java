package com.otto.spacealertresolver.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.util.Size;
import android.view.View;
import android.widget.*;

import com.otto.spacealertresolver.R;
import com.otto.spacealertresolver.ThreatActions.External.ActionEffectExternal;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalBuff;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalDamageShip;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalDelayPlayers;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalDie;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalEndGame;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalGlobalBuff;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalKnockOut;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalMoveOthers;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalSelfDamage;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalShieldDrain;
import com.otto.spacealertresolver.ThreatActions.External.ActionExternalToggle;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternal;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalBuff;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalBypassSource;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalCount;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalDefault;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalDestroyInterceptors;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalMaxValue;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalNoShield;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalSelfToggle;
import com.otto.spacealertresolver.ThreatActions.External.OnDamageExternalToggle;
import com.otto.spacealertresolver.ThreatActions.External.OnDeathExternal;
import com.otto.spacealertresolver.ThreatActions.External.OnDeathExternalDamageOthers;
import com.otto.spacealertresolver.ThreatActions.External.OnDeathExternalDamageSpaceCount;
import com.otto.spacealertresolver.ThreatActions.External.OnDeathExternalRemoveGlobalBonus;
import com.otto.spacealertresolver.ThreatActions.External.ThreatActionExternal;
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
import com.otto.spacealertresolver.ThreatActions.Internal.OnDeathInternalKnockOut;
import com.otto.spacealertresolver.ThreatActions.Internal.OnDeathInternalRemoveEffect;
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class SettingsScreen extends AppCompatActivity
{

    private EditText shipNameEntry;
    private EditText playerCount;
    private Spinner difficultySpinner;
    private Button setupGame;
    ProgressBar progressBar;
    TextView progressLabel;
    TextView progressUpdateText;
    XMLTask task;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings_screen);

        progressBar = findViewById(R.id.progressBar);
        progressLabel = findViewById(R.id.ProgressLabel);
        progressUpdateText = findViewById(R.id.progressTextUpdate);

        //Get Controls
        shipNameEntry = findViewById(R.id.ShipNameEntry);
        playerCount = findViewById(R.id.PlayerCountEntry);
        setupGame = findViewById(R.id.SetupGameButton);
        difficultySpinner = findViewById(R.id.difficultySpinner);
        context = this;

        //setup spinner
        final ArrayAdapter difficultyAdapter = ArrayAdapter.createFromResource(this,R.array.difficulty_array,android.R.layout.simple_spinner_item);
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficultyAdapter);
        //setup button
        setupGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageText = "";
                if(shipNameEntry.getText().toString().equals(""))
                {
                    messageText += "Ship name can not be empty.";
                }
                if(playerCount.getText().toString().equals(""))
                {
                    if(!messageText.equals(""))
                    {
                        messageText += "\n";
                    }
                    messageText += "Player number can not be empty.";
                }
                else if(Integer.parseInt(playerCount.getText().toString()) == 0)
                {
                    if (!messageText.equals(""))
                    {
                        messageText += "\n";
                    }
                    messageText += "Player number can not be zero.";
                }
                else if(Integer.parseInt(playerCount.getText().toString()) > 5)
                {
                    if (!messageText.equals(""))
                    {
                        messageText += "\n";
                    }
                    messageText += "Max number of players is 5.";
                }
                if (messageText.equals(""))
                {
                    // start a new game
                    task.execute(difficultySpinner.getSelectedItem().toString());
                }
                else
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Problem with entered data");
                    alert.setMessage(messageText);
                    alert.setCancelable(true);
                    AlertDialog dialog =  alert.create();
                    dialog.show();
                }
            }
        });
    }
    private class XMLTask extends AsyncTask<String,Integer,ArrayList<ArrayList>>
    {
        ArrayList<Threat> allThreats;
        ArrayList<Threat> internalThreats;
        ArrayList<Threat> externalThreats;
        ArrayList<String> internalNames;
        ArrayList<String> threatNames;
        @Override
        protected ArrayList<ArrayList> doInBackground(String... strings)
        {
            String difficulty = strings[0];
            try
            {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder parser = factory.newDocumentBuilder();
                InputStream in = context.getAssets().open("threats.xml");
                Document document = parser.parse(in);
                XPathFactory xPathFactory = XPathFactory.newInstance();
                XPath xPath = xPathFactory.newXPath();
                internalThreats = new ArrayList<>();
                externalThreats = new ArrayList<>();
                allThreats = new ArrayList<>();
                threatNames = new ArrayList<>();
                internalNames = new ArrayList<>();
                XPathExpression expression;
                if(difficulty.equals("Basic"))
                {
                    expression = xPath.compile("/Threats/threat[difficulty='basic']");
                }
                else
                {
                    expression = xPath.compile("/Threats/threat");
                }
                NodeList threatList = (NodeList)expression.evaluate(document, XPathConstants.NODESET);
                int maxProgress = threatList.getLength();
                for(int i = 0; i < threatList.getLength();i++) {
                    if (isCancelled())
                    {
                        break;
                    }
                    Node node = threatList.item(i);
                    expression = xPath.compile("./type");
                    String threatType = (String) expression.evaluate(node, XPathConstants.STRING);
                    if (threatType.equals("external"))
                    {
                        ThreatExternal threat = BuildThreatExternal(xPath, node);
                        allThreats.add(threat);
                        externalThreats.add(threat);
                        threatNames.add(threat.cardNum + " " + threat.name);
                    }
                    else
                    {
                        ThreatInternal threat = BuildThreatInternal(xPath, node);
                        allThreats.add(threat);
                        internalThreats.add(threat);
                        internalNames.add(threat.cardNum + " " + threat.name);
                        threatNames.add(threat.cardNum + " " + threat.name);
                    }
                    publishProgress(maxProgress);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (ParserConfigurationException e)
            {
                e.printStackTrace();
            }
            catch (SAXException e)
            {
                e.printStackTrace();
            }
            catch (XPathExpressionException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            progressBar.setMax(values[0]);
            progressBar.setProgress(progressBar.getProgress()+1);
            String updateText = progressBar.getProgress() + "/" + progressBar.getMax();
            progressUpdateText.setText(updateText);
        }

        @Override
        protected void onCancelled()
        {

            super.onCancelled();
            progressBar.setVisibility(View.INVISIBLE);
            progressLabel.setVisibility(View.INVISIBLE);
            progressUpdateText.setVisibility(View.INVISIBLE);
            progressBar.setProgress(0);
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressLabel.setVisibility(View.VISIBLE);
            progressUpdateText.setVisibility(View.VISIBLE);
            shipNameEntry.setEnabled(false);
            playerCount.setEnabled(false);
            difficultySpinner.setEnabled(false);
            setupGame.setEnabled(false);
        }

        @Override
        protected void onPostExecute(ArrayList<ArrayList> arrayLists)
        {
            super.onPostExecute(arrayLists);
            progressBar.setVisibility(View.INVISIBLE);
            progressLabel.setVisibility(View.INVISIBLE);
            progressUpdateText.setVisibility(View.INVISIBLE);
            progressBar.setProgress(0);
            int numPlayers = Integer.valueOf(playerCount.getText().toString());
            String shipName = shipNameEntry.getText().toString();
            MainActivity.game.SetupGame(numPlayers, shipName,threatNames,internalNames,allThreats,internalThreats,externalThreats);
            Intent intent = new Intent(context,GameSetup.class);
            startActivity(intent);
        }

        private ThreatInternal BuildThreatInternal(XPath xPath, Node node) throws XPathExpressionException
        {
            ThreatInternal threat = new ThreatInternal();
            XPathExpression expression;
            threat.track = 3;
            expression = xPath.compile("./name");
            threat.name = (String) expression.evaluate(node, XPathConstants.STRING);
            expression = xPath.compile("./cardID");
            threat.cardNum = (String) expression.evaluate(node, XPathConstants.STRING);
            expression = xPath.compile("./plural");
            threat.plural = Boolean.parseBoolean((String) expression.evaluate(node, XPathConstants.STRING));
            expression = xPath.compile("./speed");
            threat.speed = Integer.parseInt((String) expression.evaluate(node, XPathConstants.STRING));
            expression = xPath.compile("./health");
            threat.health = Integer.parseInt((String) expression.evaluate(node, XPathConstants.STRING));
            expression = xPath.compile(("./firesBack"));
            threat.firesBackDefault = (boolean)expression.evaluate(node, XPathConstants.BOOLEAN);
            expression = xPath.compile("./deathScore");
            threat.deathScore = Integer.parseInt((String) expression.evaluate(node, XPathConstants.STRING));
            expression = xPath.compile("./escapeScore");
            threat.escapeScore = Integer.parseInt((String) expression.evaluate(node, XPathConstants.STRING));
            expression = xPath.compile(("./threatType"));
            threat.threatType = (String) expression.evaluate(node, XPathConstants.STRING);
            expression = xPath.compile(("./spawnMessage"));
            threat.spawnMessage = (String) expression.evaluate(node, XPathConstants.STRING);

            NodeList dataSet;
            Element data;
            expression = xPath.compile("./xAction/effect");
            dataSet = (NodeList) expression.evaluate(node, XPathConstants.NODESET);
            threat.xAction = GetInternalActionCommand(dataSet, xPath);
            expression = xPath.compile("./yAction/effect");
            dataSet = (NodeList) expression.evaluate(node, XPathConstants.NODESET);
            threat.yAction = GetInternalActionCommand(dataSet, xPath);
            expression = xPath.compile("./zAction/effect");
            dataSet = (NodeList) expression.evaluate(node, XPathConstants.NODESET);
            threat.zAction = GetInternalActionCommand(dataSet, xPath);

            expression = xPath.compile("./spawnAction/effect");
            dataSet = (NodeList) expression.evaluate(node, XPathConstants.NODESET);
            threat.spawnAction = GetInternalSpawnCommand(dataSet, xPath);

            expression = xPath.compile("./locations/location");
            dataSet = (NodeList) expression.evaluate(node, XPathConstants.NODESET);
            threat.locationsDefault = GetLocations(dataSet);

            expression = xPath.compile("./damageAction");
            data = (Element) expression.evaluate(node, XPathConstants.NODE);
            threat.damageEffect = GetInternalDamageEffect(data, xPath);

            expression = xPath.compile("./deathAction/effect");
            data = (Element) expression.evaluate(node, XPathConstants.NODE);
            threat.deathAction = GetInternalDeathEffect(data,xPath);
            return threat;
        }
        private ThreatExternal BuildThreatExternal(XPath xPath, Node node) throws XPathExpressionException
        {
            ThreatExternal threat = new ThreatExternal();
            XPathExpression expression;
            expression = xPath.compile("./name");
            threat.name = (String) expression.evaluate(node, XPathConstants.STRING);
            expression = xPath.compile("./cardID");
            threat.cardNum = (String) expression.evaluate(node, XPathConstants.STRING);
            expression = xPath.compile("./movement");
            threat.speed = Integer.parseInt((String) expression.evaluate(node, XPathConstants.STRING));
            expression = xPath.compile("./health");
            threat.health = Integer.parseInt((String) expression.evaluate(node, XPathConstants.STRING));
            expression = xPath.compile("./shield");
            threat.shield = Integer.parseInt((String) expression.evaluate(node, XPathConstants.STRING));
            expression = xPath.compile("./deathScore");
            threat.deathScore = Integer.parseInt((String) expression.evaluate(node, XPathConstants.STRING));
            expression = xPath.compile("./escapeScore");
            threat.escapeScore = Integer.parseInt((String) expression.evaluate(node, XPathConstants.STRING));
            expression = xPath.compile("./rocketImmune");
            threat.missileImmune = Integer.parseInt((String) expression.evaluate(node, XPathConstants.STRING));
            expression = xPath.compile(("./spawnMessage"));
            threat.spawnMessage = (String) expression.evaluate(node, XPathConstants.STRING);

            //select action command objects
            NodeList action;
            expression = xPath.compile("./xAction/effect");
            action = (NodeList) expression.evaluate(node, XPathConstants.NODESET);
            threat.xAction = GetExternalActionCommand(action, xPath);

            expression = xPath.compile("./yAction/effect");
            action = (NodeList) expression.evaluate(node, XPathConstants.NODESET);
            threat.yAction = GetExternalActionCommand(action, xPath);

            expression = xPath.compile("./zAction/effect");
            action = (NodeList) expression.evaluate(node, XPathConstants.NODESET);
            threat.zAction = GetExternalActionCommand(action, xPath);

            //get damage command object
            Element element;
            expression = xPath.compile("./damageAction/effect");
            element = (Element) expression.evaluate(node, XPathConstants.NODE);
            threat.damageAction = GetExternalDamageCommand(element, xPath);

            expression = xPath.compile("./deathAction/effect");
            element = (Element) expression.evaluate(node, XPathConstants.NODE);
            threat.deathAction = GetExternalDeathEffect(element);
            return threat;
        }
        private ThreatActionExternal GetExternalActionCommand(NodeList action, XPath xPath) throws XPathExpressionException
        {
            ThreatActionExternal threatAction;
            ArrayList<ActionEffectExternal> effects = new ArrayList<>();
            for (int i = 0; i < action.getLength(); i++) {
                Element item = (Element) action.item(i);
                String commandType = item.getAttribute("type");
                switch (commandType) {
                    case "damage":
                        XPathExpression expression = xPath.compile("./zone");
                        int zone = Integer.parseInt((String) expression.evaluate(item, XPathConstants.STRING));

                        expression = xPath.compile("./damage");
                        String damage = (String) expression.evaluate(item, XPathConstants.STRING);

                        expression = xPath.compile("./condition");
                        String condition = (String) expression.evaluate(item, XPathConstants.STRING);

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
                        effects.add(new ActionExternalDamageShip(zone, damage, bypassBonus, condition, damageMulti));
                        break;
                    case "buff": {
                        expression = xPath.compile("./stat");
                        String stat = (String) expression.evaluate(item, XPathConstants.STRING);
                        expression = xPath.compile("./amount");
                        String value = (String) expression.evaluate(item, XPathConstants.STRING);
                        effects.add(new ActionExternalBuff(stat, value));
                        break;
                    }
                    case "shieldDrain": {
                        expression = xPath.compile("./amount");
                        String amount = (String) expression.evaluate(item, XPathConstants.STRING);
                        effects.add(new ActionExternalShieldDrain(amount));
                        break;
                    }
                    case "toggle": {
                        effects.add(new ActionExternalToggle());
                        break;
                    }
                    case "globalBuff": {
                        expression = xPath.compile("./stat");
                        String stat = (String) expression.evaluate(item, XPathConstants.STRING);
                        expression = xPath.compile("./value");
                        int value = Integer.parseInt((String) expression.evaluate(item, XPathConstants.STRING));
                        effects.add(new ActionExternalGlobalBuff(stat, value));
                        break;
                    }
                    case "moveOthers": {
                        expression = xPath.compile("./value");
                        int value = Integer.parseInt((String) expression.evaluate(item, XPathConstants.STRING));
                        effects.add(new ActionExternalMoveOthers(value));
                        break;
                    }
                    case "knockOut": {
                        expression = xPath.compile("./target");
                        String target = (String) expression.evaluate(item, XPathConstants.STRING);
                        effects.add(new ActionExternalKnockOut(target));
                        break;

                    }
                    case "delay": {
                        expression = xPath.compile("./target");
                        String target = (String) expression.evaluate(item, XPathConstants.STRING);
                        effects.add(new ActionExternalDelayPlayers(target));
                        break;

                    }
                    case "selfDamage": {
                        expression = xPath.compile("./value");
                        int value = Integer.parseInt((String) expression.evaluate(item, XPathConstants.STRING));
                        effects.add(new ActionExternalSelfDamage(value));
                        break;
                    }
                    case "endGame": {
                        effects.add(new ActionExternalEndGame());
                        break;
                    }
                    case "die": {
                        effects.add(new ActionExternalDie());
                        break;
                    }
                }
            }
            threatAction = new ThreatActionExternal(effects);
            return threatAction;
        }
        private ThreatActionInternal GetInternalActionCommand(NodeList action, XPath xPath) throws XPathExpressionException
        {
            ThreatActionInternal threatAction;
            ArrayList<ActionEffectInternal> effects = new ArrayList<>();
            for (int i = 0; i < action.getLength(); i++) {
                Element item = (Element) action.item(i);
                String commandType = item.getAttribute("type");
                switch (commandType) {
                    case "moveBlue": {
                        effects.add(new ActionInternalMoveBlue());
                        break;
                    }
                    case "moveRed": {
                        effects.add(new ActionInternalMoveRed());
                        break;
                    }
                    case "changeDeck": {
                        effects.add(new ActionInternalTurboLift());
                        break;
                    }
                    case "damageShip": {
                        XPathExpression expression = xPath.compile("./target");
                        String target = (String) expression.evaluate(item, XPathConstants.STRING);

                        expression = xPath.compile("./damage");
                        String damage = (String) expression.evaluate(item, XPathConstants.STRING);

                        effects.add(new ActionInternalDamageShip(target, damage));
                        break;
                    }
                    case "drainPower": {
                        XPathExpression expression = xPath.compile("./target");
                        String target = (String) expression.evaluate(item, XPathConstants.STRING);

                        expression = xPath.compile("./value");
                        String value = (String) expression.evaluate(item, XPathConstants.STRING);

                        expression = xPath.compile("./condition");
                        String condition = (String) expression.evaluate(item, XPathConstants.STRING);

                        effects.add(new ActionInternalEnergyDrain(target, condition, value));
                        break;
                    }
                    case "leakPower": {
                        XPathExpression expression = xPath.compile("./target");
                        String target = (String) expression.evaluate(item, XPathConstants.STRING);
                        expression = xPath.compile("./condition");
                        String condition = (String) expression.evaluate(item, XPathConstants.STRING);

                        effects.add(new ActionInternalLeakPower(condition, target));
                        break;
                    }
                    case "conditionalDamageMove": {
                        XPathExpression expression = xPath.compile("./damage");
                        int damage = Integer.parseInt((String) expression.evaluate(item, XPathConstants.STRING));

                        expression = xPath.compile("./direction");
                        String direction = (String) expression.evaluate(item, XPathConstants.STRING);

                        effects.add(new ActionInternalConditionDamageMove(damage, direction));
                        break;
                    }
                    case "knockOut": {
                        XPathExpression expression = xPath.compile("./target");
                        String target = (String) expression.evaluate(item, XPathConstants.STRING);
                        ActionInternalKnockOut effect = new ActionInternalKnockOut(target);
                        if (target.equals("allBut")) {
                            expression = xPath.compile("./locationX");
                            int locationX = Integer.parseInt((String) expression.evaluate(item, XPathConstants.STRING));
                            expression = xPath.compile("./locationY");
                            int locationY = Integer.parseInt((String) expression.evaluate(item, XPathConstants.STRING));
                            effect.exclude = new Pair<>(locationX, locationY);
                        }
                        effects.add(effect);
                        break;
                    }
                    case "endGame": {
                        effects.add(new ActionInternalEndGame());
                        break;
                    }
                    case "grow": {
                        effects.add(new ActionInternalGrow());
                        break;
                    }
                    case "globalDamageMod": {
                        effects.add(new ActionInternalGlobalDamageModifier());
                        break;
                    }
                    case "spread" :
                    {
                        XPathExpression expression = xPath.compile("./direction");
                        String direction = (String) expression.evaluate(item, XPathConstants.STRING);
                        effects.add(new ActionInternalSpread(direction));
                        break;
                    }
                    case "destroyMissile":
                    {
                        effects.add(new ActionInternalDestroyRocket());
                        break;
                    }
                    case "disableBots":
                    {
                        effects.add(new ActionInternalDisableBots());
                        break;
                    }
                    case "comsumeFuel" :
                    {
                        effects.add(new ActionInternalDrainFuel());
                        break;
                    }
                    case "moveToPlayerCount":
                    {
                        effects.add(new ActionInternalMoveToPlayerCount());
                        break;
                    }
                    case "delay" :
                    {
                        XPathExpression expression = xPath.compile("./target");
                        String target = (String) expression.evaluate(item, XPathConstants.STRING);
                        effects.add(new ActionInternalDelayPlayers(target));
                        break;
                    }

                }
            }
            threatAction = new ThreatActionInternal(effects);
            return threatAction;
        }
        private ThreatActionInternal GetInternalSpawnCommand(NodeList action, XPath xPath) throws XPathExpressionException
        {
            ArrayList<ActionEffectInternal> effects = new ArrayList<>();
            for (int i = 0; i < action.getLength(); i++) {
                ActionEffectInternal effect;
                Element item = (Element) action.item(i);
                String commandType = item.getAttribute("type");
                switch (commandType) {
                    case "setPosition": {
                        XPathExpression expression = xPath.compile("./special");
                        String special = (String) expression.evaluate(item, XPathConstants.STRING);
                        effect = new SetInternalPosition(special);
                        effects.add(effect);
                        break;
                    }
                    case "setHealth": {
                        effect = new OnSpawnSetHealth();
                        effects.add(effect);
                        break;
                    }
                }
            }
            return new ThreatActionInternal(effects);
        }
        private ArrayList<Pair<Integer, Integer>> GetLocations(NodeList data)
        {
            ArrayList<Pair<Integer, Integer>> locations = new ArrayList<>();
            for (int i = 0; i < data.getLength(); i++) {
                Pair<Integer, Integer> location;
                Element item = (Element) data.item(i);
                int section = Integer.parseInt(item.getAttribute("section"));
                int zone = Integer.parseInt(item.getAttribute("zone"));
                location = new Pair<>(zone, section);
                locations.add(location);
            }
            return locations;
        }
        private OnDamageExternal GetExternalDamageCommand(Element damType, XPath xPath) throws XPathExpressionException
        {
            OnDamageExternal effect;
            String type = damType.getAttribute("type");
            switch (type) {
                case "toggleExt":
                    effect = new OnDamageExternalToggle();
                    break;
                case "toggleInt": {
                    effect = new OnDamageExternalSelfToggle();
                    break;
                }
                case "noShield": {
                    XPathExpression expression = xPath.compile("./trigger");
                    String trigger = (String) expression.evaluate(damType, XPathConstants.STRING);
                    effect = new OnDamageExternalNoShield(trigger);
                    break;
                }
                case "bypass": {
                    XPathExpression expression = xPath.compile("./source");
                    String source = (String) expression.evaluate(damType, XPathConstants.STRING);
                    effect = new OnDamageExternalBypassSource(source);
                    break;
                }
                case "maxValue": {
                    XPathExpression expression = xPath.compile("./value");
                    int value = Integer.parseInt((String) expression.evaluate(damType, XPathConstants.STRING));
                    effect = new OnDamageExternalMaxValue(value);
                    break;
                }
                case "destroyInterceptors": {
                    effect = new OnDamageExternalDestroyInterceptors();
                    break;
                }
                case "buff": {
                    XPathExpression expression = xPath.compile("./value");
                    int value = Integer.parseInt((String) expression.evaluate(damType, XPathConstants.STRING));
                    expression = xPath.compile("./stat");
                    String stat = (String) expression.evaluate(damType, XPathConstants.STRING);
                    expression = xPath.compile("./source");
                    String source = (String) expression.evaluate(damType, XPathConstants.STRING);
                    effect = new OnDamageExternalBuff(value, stat, source);
                    break;
                }
                case "count": {
                    XPathExpression expression = xPath.compile("./value");
                    int value = Integer.parseInt((String) expression.evaluate(damType, XPathConstants.STRING));
                    effect = new OnDamageExternalCount(value);
                    break;
                }
                default:
                    effect = new OnDamageExternalDefault();
                    break;
            }
            return effect;
        }
        private OnDamageInternal GetInternalDamageEffect(Element damType, XPath xPath) throws XPathExpressionException
        {
            OnDamageInternal effect = null;
            String type = damType.getAttribute("type");
            switch (type) {
                case "combat": {
                    effect = new OnDamageInternalCombat();
                    break;
                }
                case "malfSingle": {

                    effect = new OnDamageInternalMalfSingle(damType.getTextContent());
                    break;
                }
                case "malfMultiBonus": {
                    XPathExpression expression = xPath.compile("./target");
                    String target = (String) expression.evaluate(damType, XPathConstants.STRING);
                    expression = xPath.compile("./bonus");
                    int bonus = Integer.parseInt((String) expression.evaluate(damType, XPathConstants.STRING));
                    effect = new OnDamageInternalMalfMultiBonus(target, bonus);
                    break;
                }
                case "combatMulti":
                {
                    effect = new OnDamageInternalCombatMulti();
                    break;
                }
                case "countRequired" :
                {
                    effect = new OnDamageCountRequired();
                    break;
                }
            }
            return effect;
        }
        private OnDeathExternal GetExternalDeathEffect(Element deathType)
        {
            OnDeathExternal effect;
            String type = deathType.getAttribute("type");
            switch (type) {
                case "damageOthers": {
                    effect = new OnDeathExternalDamageOthers();
                    break;
                }
                case "damageSpaceCount": {
                    int multi = Integer.parseInt(deathType.getTextContent());
                    effect = new OnDeathExternalDamageSpaceCount(multi);
                    break;
                }
                case "removeGlobalBonus": {
                    effect = new OnDeathExternalRemoveGlobalBonus();
                    break;
                }
                default:
                {
                    effect = null;
                    break;
                }
            }
            return effect;
        }
        private OnDeathInternal GetInternalDeathEffect(Element deathType, XPath xPath) throws XPathExpressionException
        {
            OnDeathInternal effect;
            String type = deathType.getAttribute("type");
            switch (type) {
                case "removeDelay":
                {
                    XPathExpression expression = xPath.compile("./effectType");
                    String effectType = (String) expression.evaluate(deathType, XPathConstants.STRING);
                    effect = new OnDeathInternalRemoveEffect(effectType);
                    break;
                }
                case "knockout":
                {
                    XPathExpression expression = xPath.compile("./target");
                    String target = (String) expression.evaluate(deathType, XPathConstants.STRING);
                    effect = new OnDeathInternalKnockOut(target);
                    break;
                }
                default:
                    effect = null;
                    break;
            }
            return effect;
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        task.cancel(true);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        difficultySpinner.setSelection(0);
        task = new XMLTask();
        shipNameEntry.setEnabled(true);
        playerCount.setEnabled(true);
        difficultySpinner.setEnabled(true);
        setupGame.setEnabled(true);
    }
}
