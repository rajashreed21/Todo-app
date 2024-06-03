package com.example.todoapp;

import static android.system.Os.remove;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.Buffer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> items;
    private ListView list;
    private Button button;

    private ArrayAdapter<String> itemsadapter;

    @Override
    protected void onCreate(Bundle savedInsatanceState) {

        super.onCreate(savedInsatanceState);
        setContentView(R.layout.activity_main);


        list = findViewById(R.id.list);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                additem(view);

            }
        });

        items = new ArrayList<>();
        itemsadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        list.setAdapter(itemsadapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return remove(position);
            }
        });

    }

    private boolean remove(int position) {
        Context context = getApplicationContext();
        Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show();
        items.remove(position);
        itemsadapter.notifyDataSetChanged();
        return true;
    }

    private void additem(View view) {
        EditText input = findViewById(R.id.edit_text);
        String itemtext = input.getText().toString();

        if (!(itemtext.equals(""))) {
            itemsadapter.add(itemtext);
            input.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "please enter text", Toast.LENGTH_LONG).show();
        }
    }
}


