package de.juehvtech.verein;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import de.juehvtech.verein.container.TrainingContentContainer;

public class EventContentActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_content);
        // restore container (if any)
        Intent buttler = getIntent();
        container = TrainingContentContainer.fromBundle(buttler.getExtras());

        // init wigets
        contentTextfield = (EditText) findViewById(R.id.trainingContentTextfield);
        noteTextfield = (EditText) findViewById(R.id.trainingNoteTextfield);

        // init buttons

        // set data from container (if any)
        if (container != null) {
            contentTextfield.setText(container.getContent());
            noteTextfield.setText(container.getNote());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSave:
                // creating data
                container = new TrainingContentContainer(contentTextfield.getText()
                        .toString(), noteTextfield.getText().toString());

                // Packing Data
                Intent returnContainer = new Intent(EventContentActivity.this,
                        EventSheetActivity.class);
//                returnContainer.putExtras(container.toBundle());

                // Sending Data
                setResult(RESULT_OK, returnContainer);

                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    // attributes
    private EditText contentTextfield;
    private EditText noteTextfield;
    private TrainingContentContainer container = null;

    // constants
}
