package com.otto.spacealertresolver.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.otto.spacealertresolver.R;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public class ResultsScreen extends AppCompatActivity {

    private TextView result;
    private Button advanceTurn;
    private Button finish;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results_screen);

        result = findViewById(R.id.ResultsView);
        advanceTurn = findViewById(R.id.AdvanceTurn);
        finish = findViewById(R.id.FinishButton);

        result.setMovementMethod(new ScrollingMovementMethod());

        advanceTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String turnText = MainActivity.game.AdvanceTurn();
                result.append(turnText);
                if(MainActivity.game.currentRound == MainActivity.game.numRounds)
                {
                    advanceTurn.setVisibility(View.GONE);
                    finish.setVisibility(View.VISIBLE);
                    result.append(MainActivity.game.EndGame(false));
                }
                else if(turnText.contains("The Ship is destroyed!"))
                {
                    advanceTurn.setVisibility(View.GONE);
                    finish.setVisibility(View.VISIBLE);
                    result.append(MainActivity.game.EndGame(true));
                }
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context,SettingsScreen.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected  void onResume()
    {
        super.onResume();
        try {
            MainActivity.game.StartGame();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        result.setText("Press 'advance turn' when ready to start.");
    }
}
