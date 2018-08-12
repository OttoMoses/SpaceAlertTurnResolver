package com.example.otto.spacealertresolver.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.otto.spacealertresolver.Adapters.ThreatAdapter;
import com.example.otto.spacealertresolver.R;
import com.example.otto.spacealertresolver.ThreatString;

public class ThreatsList extends AppCompatActivity
{
    private ListView threatsListView;
    private ThreatString[] threatStrings;
    private ThreatAdapter threatAdapter;
    private Context context = this;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threats_list);
        threatsListView = findViewById(R.id.ThreatsList);

        threatsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Intent threatData = new Intent(context,ThreatDetail.class);
                threatData.putExtra("threatID", position);
                startActivity(threatData);
            }
        });
    }
    @Override
    protected  void onResume()
    {
        super.onResume();
        threatStrings = MainActivity.game.selectedThreats;
        threatAdapter = new ThreatAdapter(this, threatStrings);
        threatsListView.setAdapter(threatAdapter);
    }
}
