package com.canning.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.canning.todo.activities.AddActivity;
import com.canning.todo.activities.DisplayActivity;
import com.canning.todo.activities.HelpActivity;
import com.canning.todo.camera.CameraActivity;

import listTable.todo.com.todo.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button butAdd = (Button) findViewById(R.id.buttonAdd);
        Button butDisplay = (Button) findViewById(R.id.buttonDisplay);
        Button butCamera = (Button) findViewById(R.id.buttonCamera);
        Button butHelp = (Button) findViewById(R.id.help);

        butAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, AddActivity.class);
                i.putExtra("update", false);

                startActivity(i);
            }
        });

        butDisplay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, DisplayActivity.class);
                i.putExtra("update", false);

                startActivity(i);
            }
        });

        butCamera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, CameraActivity.class);

                startActivity(i);
            }
        });

        butHelp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, HelpActivity.class);

                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }
}
