package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button addNote;
    FloatingActionButton fabAdd;
    DatabaseHelper databaseHelper;
    LinearLayout llView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVar();
        showNotes();

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_note_lay);

                EditText setTitle, setContent;
                Button btnAdd;

                setTitle = dialog.findViewById(R.id.setTitle);
                setContent = dialog.findViewById(R.id.setContent);
                btnAdd = dialog.findViewById(R.id.btnadd);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = setTitle.getText().toString();
                        String content = setContent.getText().toString();

                        if (!content.equals("")){

                            databaseHelper.noteDao().addNote(new Note(title, content));
                            showNotes();

                            dialog.dismiss();

                        } else {
                            Toast.makeText(MainActivity.this, "Please Enter Content!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                dialog.show();
            }
        });

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabAdd.performClick();
            }
        });

    }

    private void showNotes() {
        ArrayList<Note> arrNotes = (ArrayList<Note>) databaseHelper.noteDao().getNotes();

        if (arrNotes.size()>0){
            llView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            recyclerView.setAdapter(new RecyclerViewAdapter(this, arrNotes));

        }else{
            llView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    private void initVar() {
        recyclerView = findViewById(R.id.recyclerView);
        addNote = findViewById(R.id.addNote);
        fabAdd= findViewById(R.id.fabAdd);
        llView = findViewById(R.id.llView);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        databaseHelper = DatabaseHelper.getInstance(this);
    }
}