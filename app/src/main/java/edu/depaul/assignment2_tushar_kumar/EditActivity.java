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
    private String clickedTitle, clickedContent, title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editNoteContent = findViewById(R.id.editNoteContent);
        editNoteTitle = findViewById(R.id.editNoteTitle);

        editNoteContent.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        clickedTitle = intent.getStringExtra("title");
        clickedContent = intent.getStringExtra("content");

    }

    public boolean onCreateOptionsMenu(Menu menuIn) {
        getMenuInflater().inflate(R.menu.edit_menu, menuIn);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_save:
                save(title(), content(), date());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Title Needed To Save", Toast.LENGTH_LONG).show();
            super.onBackPressed();
        }
        else if(title().equals(clickedTitle) && content().equals(clickedContent)){
            super.onBackPressed();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Your note is not saved!");
        builder.setMessage("Save Note?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                save(title(), content(), date());
            }
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

    public void save(String title, String content, Date date) {
        Note note = new Note(title, content, date);
        Intent intent = new Intent();
        intent.putExtra("Note", note);
        setResult(RESULT_OK, intent);
        if(title().trim().isEmpty()){
            intent.putExtra("Result", "NoTitle");
            Toast.makeText(this, "Title Needed To Save", Toast.LENGTH_LONG).show();
        }
        else if(title().equals(clickedTitle) && content().equals(clickedContent))
            intent.putExtra("Result", "NoTitle");
        else
            intent.putExtra("Result", "NewNote");
        finish();
    }

    public String title() {
        return title = editNoteTitle.getText().toString();
    }

    public String content() {
        return content = editNoteContent.getText().toString();
    }

    public Date date() {
        return new Date();
    }
}