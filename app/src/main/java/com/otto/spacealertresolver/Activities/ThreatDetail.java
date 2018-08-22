package com.otto.spacealertresolver.Activities;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.otto.spacealertresolver.R;
import com.otto.spacealertresolver.ThreatString;

import java.util.ArrayList;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

public class ThreatDetail extends AppCompatActivity
{
    private Spinner nameSpinner;
    private Spinner trackSpinner;
    private ThreatString threatString;
    private int threatTurn;
    private Context context = this;
    ArrayList<String> names;
    ArrayList<String> internalNames;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threat_detail);
        names = new ArrayList<>(game.threatNames);
        internalNames = game.internalThreatNames;

        TextView title = findViewById(R.id.TurnLabel);
        String titleText = "Set the threat to appear at T+" + (threatTurn + 1);
        title.setText(titleText);

        nameSpinner = findViewById(R.id.ThreatNameSpinner);

        ArrayAdapter<String> nameAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,names);
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameSpinner.setAdapter(nameAdapter);

        trackSpinner = findViewById(R.id.ThreatTrackSpinner);

        ArrayAdapter trackAdapter = ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item);
        trackAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        trackSpinner.setAdapter(trackAdapter);

        Button saveButton = findViewById(R.id.saveThreat);
        Button clearButton = findViewById(R.id.clearThreat);

        threatTurn = this.getIntent().getIntExtra("threatID", 0);
        ThreatString ref =  game.selectedThreatStrings[threatTurn];
        threatString = new ThreatString(ref.threatID,ref.trackNum);
        if(threatString.threatID != names.size() + 1)
        {
            nameSpinner.setSelection(threatString.threatID);
        }

        if(threatString.trackNum !=4)
        {
            trackSpinner.setSelection(threatString.trackNum);
        }

        nameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l)
            {
                threatString.threatID = game.threatNames.indexOf(nameSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        trackSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l)
            {
                threatString.trackNum = trackSpinner.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(trackSpinner.getSelectedItemPosition() == 3)
                {
                    if(!internalNames.contains(nameSpinner.getSelectedItem().toString()))
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Can't assign an external threat to the internal track!");
                        builder.setTitle("Threat assignment invalid!");
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    else
                    {
                        if(game.selectedThreatStrings[threatTurn].threatID != game.threatNames.size() + 1)
                        {
                            game.selectedNames.remove(game.threatNames.get(game.selectedThreatStrings[threatTurn].threatID));
                        }
                        game.selectedThreatStrings[threatTurn] = threatString;
                        game.selectedNames.add(nameSpinner.getSelectedItem().toString());
                        finish();
                    }
                }
                else
                {
                    if(internalNames.contains(nameSpinner.getSelectedItem().toString()))
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Can't assign an internal threat to an external track!");
                        builder.setTitle("Threat assignment invalid!");
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    else
                    {
                        if(game.selectedThreatStrings[threatTurn].threatID != game.threatNames.size() + 1)
                        {
                            game.selectedNames.remove(game.threatNames.get(game.selectedThreatStrings[threatTurn].threatID));
                        }
                        game.selectedThreatStrings[threatTurn] = threatString;
                        game.selectedNames.add(nameSpinner.getSelectedItem().toString());
                        finish();
                    }
                }

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(game.selectedThreatStrings[threatTurn].threatID != game.threatNames.size() + 1)
                {
                    game.selectedNames.remove(game.threatNames.get(game.selectedThreatStrings[threatTurn].threatID));
                }
                game.selectedThreatStrings[threatTurn].trackNum = 4;
                game.selectedThreatStrings[threatTurn].threatID = game.threatNames.size() + 1;
                finish();
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(game.selectedNames.size() != 0)
        {
            names.removeAll(game.selectedNames);
        }
    }
}
