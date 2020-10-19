package edu.depaul.assignment2_tushar_kumar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private RecyclerView recyclerView;
    private final List<Note> noteList = new ArrayList<>();
    private static final int Edit_Request_code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        NoteAdapter noteAdapter = new NoteAdapter(noteList, this);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menuIn){
        getMenuInflater().inflate(R.menu.notes_menu, menuIn);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:
                openEditActivity();;
                return true;
            case R.id.menu_about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==Edit_Request_code){
            if(resultCode == RESULT_OK){
                Note note = (Note) data.getSerializableExtra("NOTE");
            }
        }
    }

    @Override
    public void onClick(View view) {
        openEditActivity();
    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    public void openEditActivity(){
        Intent intent1 = new Intent(this, EditActivity.class);
        startActivity(intent1);
    }
}