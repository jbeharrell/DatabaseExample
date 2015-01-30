package com.example.jon.databaseexample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ListActivity {
    private NamesDataSource dataSource;
    ArrayAdapter<Name> adapter;
    private ArrayList<Name> names;

    private Button add;
    private EditText firstName, lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button) findViewById(R.id.add);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);

        // Creating new data source and opening the database
        dataSource = new NamesDataSource(this);
        dataSource.open();

        names = (ArrayList) dataSource.getAll();

        // Use adapter to populate listView with array items
        adapter = new ArrayAdapter<Name>(this, android.R.layout.simple_expandable_list_item_1, names);
        setListAdapter(adapter);

        // Creating onClickListener to add name to database
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dataSource.insert(firstName.getText().toString(), lastName.getText().toString());
                refresh();
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);

        dataSource.delete(names.get(position));
        refresh();
    }

    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }

    // Refreshes the listView after the database has been altered
    private void refresh(){
        names = (ArrayList) dataSource.getAll();
        adapter.clear();
        adapter.addAll(names);
        adapter.notifyDataSetChanged();
    }
}
