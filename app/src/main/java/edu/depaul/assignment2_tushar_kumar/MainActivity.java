package edu.depaul.assignment2_tushar_kumar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private final List<Note> noteList = new ArrayList<>();
    private int position;
    private static final int Request_Code = 123;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        for (int i = 0; i < 5; i++) {
//            Note n = new Note(
//                    "This is note " + (i+1),
//                    "This text is the content of note number " + (i+1));
//            noteList.add(n);
//        }
        recyclerView = findViewById(R.id.recyclerView);

        noteAdapter = new NoteAdapter(noteList, this);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadFile();
        updateActionBar();
    }

    @Override
    protected void onResume() {
        updateActionBar();
        super.onResume();
    }

    @Override
    protected void onPause() {
        saveFile();
        super.onPause();
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
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.d(TAG, "onActivityResult: OnActivityResult Called");
        if(requestCode == Request_Code){
            if(resultCode == RESULT_OK){
                Note note = (Note) data.getSerializableExtra("Note");
                String result = data.getStringExtra("Result");
                if(result.equals("NewNote")){
                    noteList.add(0, note);
                    noteAdapter.notifyDataSetChanged();
                    //Log.d(TAG, "onActivityResult: "+ note.toString());
                }
                if(result.equals("EditedNote")){
                    noteList.remove(position);
                    noteList.add(0, note);
                    noteAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        position = recyclerView.getChildLayoutPosition(view);
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("title", noteList.get(position).getTitle());
        intent.putExtra("content", noteList.get(position).getContent());
        startActivityForResult(intent, Request_Code);
    }

    private void openEditActivity(){
        Intent intent = new Intent(this, EditActivity.class);
        startActivityForResult(intent, Request_Code);
    }

    @Override
    public boolean onLongClick(View view) {
        position = recyclerView.getChildLayoutPosition(view);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Note " + noteList.get(position).getTitle() + "?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            //Note selectedNote = noteList.get(position);
            noteList.remove(position);
           updateActionBar();
        });

        builder.setNegativeButton("No", (dialogInterface, i) -> {
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        return false;
    }

    private void updateActionBar(){
        if(noteList.size()>0){
            getSupportActionBar().setTitle(getString(R.string.app_name) + " (" + noteList.size() + ")");
        }
        noteAdapter.notifyDataSetChanged();
    }

    private void saveFile(){
        Log.d(TAG, "saveFile: saved");
        try {
            FileOutputStream fos = getApplicationContext().
                    openFileOutput(getString(R.string.notes_file), Context.MODE_PRIVATE);

            JsonWriter writer = new JsonWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
            writer.setIndent("  ");
            writer.beginArray();
            for (Note n : noteList) {
                writer.beginObject();
                writer.name("title").value(n.getTitle());
                writer.name("text").value(n.getContent());
                writer.name("lastDate").value(n.getDate().getTime());
                writer.endObject();
            }
            writer.endArray();
            writer.close();
            //Log.d(TAG, "saveFile: " + noteList);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "writeJSONData: " + e.getMessage());
        }
    }

    private void loadFile(){
        try {
            FileInputStream fis = getApplicationContext().
                    openFileInput(getString(R.string.notes_file));

            // Read string content from file
            byte[] data = new byte[(int) fis.available()]; // this technique is good for small files
            int loaded = fis.read(data);
            //Log.d(TAG, "readJSONData: Loaded " + loaded + " bytes");
            fis.close();
            String json = new String(data);

            // Create JSON Array from string file content
            JSONArray noteArr = new JSONArray(json);
            for (int i = 0; i < noteArr.length(); i++) {
                JSONObject nObj = noteArr.getJSONObject(i);

                // Access note data fields
                String title = nObj.getString("title");
                String text = nObj.getString("text");
                long dateMS = nObj.getLong("lastDate");

                // Create Note and add to ArrayList
                Note n = new Note(title, text);
                n.setDate(dateMS);
                noteList.add(n);
            }
            //Log.d(TAG, "readJSONData: " + noteList);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "readJSONData: " + e.getMessage());
        }
    }
}