package edu.depaul.assignment2_tushar_kumar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends AppCompatActivity {

    private EditText editNoteContent;
    private EditText editNoteTitle;
    private String clickedTitle = "";
    private String clickedContent = "";
    private static final String TAG = "EditActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editNoteContent = findViewById(R.id.editNoteContent);
        editNoteTitle = findViewById(R.id.editNoteTitle);

        editNoteContent.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        if(intent.hasExtra("title")){
            clickedTitle = intent.getStringExtra("title");
            editNoteTitle.setText(clickedTitle);
        }
        if(intent.hasExtra("content")){
            clickedContent = intent.getStringExtra("content");
            editNoteContent.setText(clickedContent);
        }
    }

    public boolean onCreateOptionsMenu(Menu menuIn) {
        getMenuInflater().inflate(R.menu.edit_menu, menuIn);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_save) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(checkNote()) {
            super.onBackPressed();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Your note is not saved!");
            builder.setMessage("Save Note '" + title() +"'?");
            builder.setPositiveButton("Yes", (dialogInterface, i) -> returnNote());

            builder.setNegativeButton("No", (dialogInterface, i) -> super.onBackPressed());
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private void save() {
        if(checkNote()){
            finish();
        }else
            returnNote();
    }

    private boolean checkNote(){
        if(title().trim().isEmpty()){
            Toast.makeText(this, "Title Needed To Save", Toast.LENGTH_LONG).show();
            return true;
        }
        else return title().equals(clickedTitle) && content().equals(clickedContent);
    }

    private void returnNote(){
        Note note = new Note(title(), content(), formatDate());
        Intent intent = new Intent();
        intent.putExtra("Note", note);
        if (clickedTitle.trim().isEmpty() && clickedContent.trim().isEmpty())
            intent.putExtra("Result", "NewNote");
        else
            intent.putExtra("Result", "EditedNote");
        setResult(RESULT_OK, intent);
        finish();
    }


    private String title() {
        String title = editNoteTitle.getText().toString();
        return title ;
    }

    private String content() {
        String content = editNoteContent.getText().toString();
        return content ;
    }

    private String formatDate(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM d, h:mm a");
        return dateFormat.format(date);
    }
}