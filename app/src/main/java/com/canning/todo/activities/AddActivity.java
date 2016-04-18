package com.canning.todo.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.canning.todo.MainActivity;
import com.canning.todo.database.DatabaseHelper;

import listTable.todo.com.todo.R;

public class AddActivity extends Activity implements OnClickListener {
    private EditText edit_title, edit_desc, edit_priority;
    private DatabaseHelper mHelper;
    private SQLiteDatabase dataBase;
    private String id, title, desc, priority;
    private boolean isUpdate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        Button btn_save = (Button) findViewById(R.id.buttonSave);
        Button buttonHome = (Button) findViewById(R.id.buttonHome);

        edit_title = (EditText)findViewById(R.id.title_editTxt);
        edit_desc = (EditText)findViewById(R.id.desc_editTxt);
        edit_priority = (EditText) findViewById(R.id.priority_editTxt);

        buttonHome.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("update", false);

                startActivity(i);
            }
        });

        isUpdate = getIntent().getExtras().getBoolean("update");
        if(isUpdate)
        {
            id = getIntent().getExtras().getString("id");
            title = getIntent().getExtras().getString("title");
            desc = getIntent().getExtras().getString("description");
            priority = getIntent().getExtras().getString("priority");

            edit_title.setText(title);
            edit_desc.setText(desc);
            edit_priority.setText(priority);
        }

        btn_save.setOnClickListener(this);

        mHelper = new DatabaseHelper(this);
    }

    // saveButton click event
    public void onClick(View v) {
        title = edit_title.getText().toString().trim();
        desc = edit_desc.getText().toString().trim();
        priority = edit_priority.getText().toString().trim();

        if(title.length() > 0 && desc.length() > 0 && priority.length() > 0)
        {
            saveData();
        }
        else
        {
            AlertDialog.Builder alertBuilder=new AlertDialog.Builder(AddActivity.this);
            alertBuilder.setTitle("Error!");
            alertBuilder.setMessage("Please enter in some data to each field!");
            alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            alertBuilder.create().show();
        }

    }

    /**
     * save data into SQLite
     */
    private void saveData(){
        dataBase=mHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(DatabaseHelper.KEY_TITLE, title);
        values.put(DatabaseHelper.KEY_DESCRIPTION, desc);
        values.put(DatabaseHelper.KEY_PRIORITY, priority);

        System.out.println("");
        if(isUpdate)
        {
            //update database with new data
            dataBase.update(DatabaseHelper.TABLE_NAME, values, DatabaseHelper.KEY_ID + "=" + id, null);
        }
        else
        {
            //insert data into database
            dataBase.insert(DatabaseHelper.TABLE_NAME, null, values);
        }
        //close database
        dataBase.close();
        finish();
    }
}