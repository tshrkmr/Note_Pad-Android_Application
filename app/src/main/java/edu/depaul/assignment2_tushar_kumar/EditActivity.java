package edu.depaul.assignment2_tushar_kumar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    private EditText editNoteContent;
    private EditText editNoteTitle;
    private String title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editNoteContent = findViewById(R.id.editNoteContent);
        editNoteTitle = findViewById(R.id.editNoteTitle);

        editNoteContent.setMovementMethod(new ScrollingMovementMethod());

    }

    public boolean onCreateOptionsMenu(Menu menuIn){
        getMenuInflater().inflate(R.menu.edit_menu, menuIn);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_save:
                title = editNoteTitle.getText().toString();
                content = editNoteContent.getText().toString();
                EmptyTitle(title);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void EmptyTitle(String title){
        if(title.isEmpty()){
            Toast.makeText(this, "Title Needed To Save", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}