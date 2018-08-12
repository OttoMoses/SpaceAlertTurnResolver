package com.example.otto.spacealertresolver.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.*;

import com.example.otto.spacealertresolver.Game;
import com.example.otto.spacealertresolver.R;
import com.example.otto.spacealertresolver.ThreatActions.External.ActionEffectExternal;
import com.example.otto.spacealertresolver.ThreatActions.External.ActionExternalBuff;
import com.example.otto.spacealertresolver.ThreatActions.External.ActionExternalDamageShip;
import com.example.otto.spacealertresolver.ThreatActions.External.ActionExternalShieldDrain;
import com.example.otto.spacealertresolver.ThreatActions.External.ActionExternalToggle;
import com.example.otto.spacealertresolver.ThreatActions.External.OnDamageExternal;
import com.example.otto.spacealertresolver.ThreatActions.External.OnDamageExternalDefault;
import com.example.otto.spacealertresolver.ThreatActions.External.OnDamageExternalExcludeRange;
import com.example.otto.spacealertresolver.ThreatActions.External.OnDamageExternalNoShield;
import com.example.otto.spacealertresolver.ThreatActions.External.OnDamageExternalSelfToggle;
import com.example.otto.spacealertresolver.ThreatActions.External.OnDamageExternalToggle;
import com.example.otto.spacealertresolver.ThreatActions.External.OnDeathExternal;
import com.example.otto.spacealertresolver.ThreatActions.External.OnDeathExternalDamageOthers;
import com.example.otto.spacealertresolver.ThreatActions.External.OnDeathExternalDamageSpaceCount;
import com.example.otto.spacealertresolver.ThreatActions.External.ThreatActionExternal;
import com.example.otto.spacealertresolver.ThreatActions.Internal.ActionEffectInternal;
import com.example.otto.spacealertresolver.ThreatActions.Internal.ActionInternalConditionDamageMove;
import com.example.otto.spacealertresolver.ThreatActions.Internal.ActionInternalDamageShip;
import com.example.otto.spacealertresolver.ThreatActions.Internal.ActionInternalEndGame;
import com.example.otto.spacealertresolver.ThreatActions.Internal.ActionInternalEnergyDrain;
import com.example.otto.spacealertresolver.ThreatActions.Internal.ActionInternalGlobalDamageModifier;
import com.example.otto.spacealertresolver.ThreatActions.Internal.ActionInternalGrow;
import com.example.otto.spacealertresolver.ThreatActions.Internal.ActionInternalKnockOut;
import com.example.otto.spacealertresolver.ThreatActions.Internal.ActionInternalLeakPower;
import com.example.otto.spacealertresolver.ThreatActions.Internal.ActionInternalMoveBlue;
import com.example.otto.spacealertresolver.ThreatActions.Internal.ActionInternalMoveRed;
import com.example.otto.spacealertresolver.ThreatActions.Internal.ActionInternalTurboLift;
import com.example.otto.spacealertresolver.ThreatActions.Internal.OnDamageInternal;
import com.example.otto.spacealertresolver.ThreatActions.Internal.OnDamageInternalCombat;
import com.example.otto.spacealertresolver.ThreatActions.Internal.OnDamageInternalMalfSingle;
import com.example.otto.spacealertresolver.ThreatActions.Internal.OnSpawnSetHealth;
import com.example.otto.spacealertresolver.ThreatActions.Internal.SetInternalPosition;
import com.example.otto.spacealertresolver.ThreatActions.Internal.ThreatActionInternal;
import com.example.otto.spacealertresolver.ThreatString;
import com.example.otto.spacealertresolver.Threats.Threat;
import com.example.otto.spacealertresolver.Threats.ThreatExternal;
import com.example.otto.spacealertresolver.Threats.ThreatInternal;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
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

public class MainActivity extends AppCompatActivity {
    public static Game game;
    Button helpButton;
    Button contactButton;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set content view
        setContentView(R.layout.activity_main);

        //get controls
        Button newGameButton = findViewById(R.id.NewGameButton);

        //set up listeners
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    game = new Game(context);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(context, SettingsScreen.class);
                startActivity(intent);
            }
        });
    }
}