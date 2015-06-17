package com.canning.todo.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.canning.todo.adapters.DisplayAdapter;
import com.canning.todo.database.DatabaseHelper;

import java.util.ArrayList;

import listTable.todo.com.todo.R;

public class DisplayActivity extends Activity {

    private DatabaseHelper mHelper;
    private SQLiteDatabase dataBase;

    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> desc = new ArrayList<>();
    private ArrayList<String> priority = new ArrayList<>();

    private ListView todoList;
    private AlertDialog.Builder build;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);

        todoList = (ListView) findViewById(R.id.List);

        mHelper = new DatabaseHelper(this);

        Button buttonAdd = (Button) findViewById(R.id.btnAdd);
        Button buttonHome = (Button) findViewById(R.id.buttonHome);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), AddActivity.class);
                i.putExtra("update", false);

                startActivity(i);
            }
        });

        buttonHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("update", false);

                startActivity(i);
            }
        });

        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                Intent i = new Intent(getApplicationContext(), AddActivity.class);

                i.putExtra("title", title.get(arg2));
                i.putExtra("description", desc.get(arg2));
                i.putExtra("id", id.get(arg2));
                i.putExtra("priority", priority.get(arg2));

                i.putExtra("update", true);

                startActivity(i);
            }
        });

        todoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int arg2, long arg3) {

                build = new AlertDialog.Builder(DisplayActivity.this);

                build.setTitle("Delete " + title.get(arg2));

                build.setMessage("Are you sure you want to delete?");

                build.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                                int which) {

                                Toast.makeText(
                                        getApplicationContext(),
                                        title.get(arg2)
                                                + " is deleted.", Toast.LENGTH_SHORT);

                                dataBase.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.KEY_ID + "=" + id.get(arg2), null);

                                displayList();
                                dialog.cancel();
                            }
                        });

                build.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = build.create();
                alert.show();

                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        displayList();
        super.onResume();
    }

    private void displayList() {
        dataBase = mHelper.getWritableDatabase();
        Cursor mCursor = dataBase.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);

        id.clear();
        title.clear();
        desc.clear();
        priority.clear();

        if(mCursor.moveToFirst()) {
            do {
                id.add(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                title.add(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.KEY_TITLE)));
                desc.add(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.KEY_DESCRIPTION)));
                priority.add(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.KEY_PRIORITY)));

            } while (mCursor.moveToNext());
        }
        DisplayAdapter displayAdapter = new DisplayAdapter(DisplayActivity.this, id, title, desc, priority);
        todoList.setAdapter(displayAdapter);

        mCursor.close();
    }
}
