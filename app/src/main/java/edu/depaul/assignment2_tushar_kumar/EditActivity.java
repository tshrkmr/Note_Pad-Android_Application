package edu.depaul.assignment2_tushar_kumar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class EditActivity extends AppCompatActivity {

    private EditText editNoteContent;
    private EditText editNoteTitle;
    private String clickedTitle = "", clickedContent = "", title, content;
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
            Log.d(TAG, "onCreate: " + clickedTitle);
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
        switch (item.getItemId()) {
            case R.id.edit_save:
                save(title(), content());
                //Log.d(TAG, "onOptionsItemSelected: " + title() + " " + content());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (title().trim().isEmpty()) {
            Toast.makeText(this, "Title Needed To Save", Toast.LENGTH_LONG).show();
            super.onBackPressed();
        }
        else if(title().equals(clickedTitle) && content().equals(clickedContent)){
            super.onBackPressed();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Your note is not saved!");
            builder.setMessage("Save Note?");
            builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                save(title(), content());
                Log.d(TAG, "onClick: Hello");
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void save(String title, String content) {
        if(title().trim().isEmpty()){
            Toast.makeText(this, "Title Needed To Save", Toast.LENGTH_LONG).show();
            finish();
        }
        else if (title().equals(clickedTitle) && content().equals(clickedContent)) {
            finish();
        }else
            {
            Note note = new Note(title, content);
            Intent intent = new Intent();
            intent.putExtra("Note", note);
            if (clickedTitle.trim().isEmpty() && clickedContent.trim().isEmpty())
                intent.putExtra("Result", "NewNote");
            else
                intent.putExtra("Result", "EditedNote");
            setResult(RESULT_OK, intent);
            Log.d(TAG, "save: " + note.toString());
            finish();
        }
    }


    public String title() {
        return title = editNoteTitle.getText().toString();
    }

    public String content() {
        return content = editNoteContent.getText().toString();
    }
}