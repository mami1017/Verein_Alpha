package de.juehvtech.verein;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import de.juehvtech.verein.administration.MenuActivity;
import de.juehvtech.verein.container.AttendanceSheetContainer;
import de.juehvtech.verein.container.ContainerType;
import de.juehvtech.verein.container.Event;
import de.juehvtech.verein.container.TrainingContentContainer;
import de.juehvtech.verein.database.DatabaseDAO;
import de.juehvtech.verein.dialogs.MemberChooser;
import de.juehvtech.verein.dialogs.MemberChooser.MemberChooserListener;

public class EventSheetActivity extends FragmentActivity implements
        MemberChooserListener {
    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_sheet);

        // init widgets
        trainingContentButton = (Button) findViewById(R.id.ashTrainingDetailsButton);
        attendanceSheetsSpinner = (Spinner) findViewById(R.id.ashTrainingSpinner);
        personListView = (ListView) findViewById(R.id.ashPersonList);

        // init buttons
        trainingContentButton
                .setOnClickListener(new TrainingDetailsButtonListener());

        // fill list data
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        progressDialog = ProgressDialog.show(
                EventSheetActivity.this,
                EventSheetActivity.this.getResources().getString(
                        R.string.dialog_loading_titel),
                EventSheetActivity.this.getResources().getString(
                        R.string.dialog_loading_titel), true);

        personListView.setClickable(true);
        personListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        personDataList = new ArrayList<String>();
        personListAdapter = new ArrayAdapter<String>(
                EventSheetActivity.this,
                android.R.layout.simple_list_item_multiple_choice,
                personDataList);
        personListView.setAdapter(personListAdapter);

        // fill spinner data
        attendanceSheetsList = new ArrayList<String>();
        new AttendanceSheetsLoader().execute();

        attendanceSheetsAdapter = new ArrayAdapter<String>(
                EventSheetActivity.this,
                android.R.layout.simple_spinner_item, attendanceSheetsList);
        attendanceSheetsAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        attendanceSheetsSpinner.setAdapter(attendanceSheetsAdapter);
        attendanceSheetsSpinner
                .setOnItemSelectedListener(new EventListListener());

        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        progressDialog = ProgressDialog.show(
                EventSheetActivity.this,
                EventSheetActivity.this.getResources().getString(
                        R.string.dialog_loading_titel),
                EventSheetActivity.this.getResources().getString(
                        R.string.dialog_loading_titel), true);
        // Lade daten des aktuellen Sheets aus der Datenbank
        new DatabaseLoader().execute(new Integer[]{Integer.valueOf(0)});
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        new DatabaseSaver().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menuAdd:
                FragmentManager fm = getSupportFragmentManager();
                // TODO Load member from database
                MemberChooser chooseMemberDialog = new MemberChooser(new String[]{
                        "Afghanistan", "Albania", "Algeria"});
                chooseMemberDialog.show(fm, "fragment_member_cooser");

                break;
            case R.id.menuPref:
                intent = new Intent(EventSheetActivity.this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.menuSync:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onFinishMemberChooser(String member) {
        // add member to list
        // attendantData.put(member.first, member.second);
        // attendantDataList.clear();
        // attendantDataList.addAll(attendantData.keySet());
        // attendantListAdapter.notifyDataSetChanged();

        // TODO check if member exist, if not create in database

        loadedSheet
                .addAttendant(null);
        personDataList.clear();
        personDataList.addAll(loadedSheet.getAttendantsAsNames());
        personListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            try {
            if (data.getExtras().getString("key")
                    .equals(ContainerType.TRAINING_DETAILS.toString())) {
                TrainingContentContainer content = (TrainingContentContainer) data
                        .getExtras().getSerializable("content");
                loadedSheet.setTrainingContent(content);
            }
            } catch (NullPointerException ex) { // do nothing
            }
        }
        // wird aus der datenbank geladen ... daher dieses loeschen
        // umbauen auf ohne result
        // bei jedem resume neu aus der datenbank laden
        // spinner dabei beachten
        // bzw spinner speichern und bei stop zuruecksetzen

    }

    // nested classes
    class TrainingDetailsButtonListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(),
                    EventContentActivity.class);
            if (loadedSheet.getTrainingContent() != null) {
                intent.putExtra("key",
                        ContainerType.TRAINING_DETAILS.toString());
//                intent.putExtra("content", loadedSheet.getTrainingContent()
//                        .toBundle());
            }
            startActivityForResult(intent, TRAINING_DETAILS);
        }

    }

    // Listener for click on Spinner
    class EventListListener implements OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {

            // save database
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            progressDialog = ProgressDialog.show(
                    EventSheetActivity.this,
                    EventSheetActivity.this.getResources().getString(
                            R.string.dialog_loading_titel),
                    EventSheetActivity.this.getResources().getString(
                            R.string.dialog_loading_titel), true);
            // TODO save sheet to database
            //new SaveAndLoadSheet().execute(new Integer[]{Integer
             //       .valueOf(arg2)});

            // fucking shit start
            //
            // String msg = "Pos: " + arg2 + " ID: " + arg3;// +
            // // container.getToastString();
            // Toast.makeText(AttendanceSheetActivity.this, msg,
            // Toast.LENGTH_LONG)
            // .show();
            //
            // switch (arg2) {
            //
            // case 0:
            //
            // Toast.makeText(AttendanceSheetActivity.this, "action",
            // Toast.LENGTH_SHORT).show();
            // String person = "Deine Mudda";
            // if (!person.equals("")) {
            // personDataList.add(person);
            // personListAdapter.notifyDataSetChanged();
            // // TODO set as selected value
            // }
            // break;
            // default:
            // personDataList.clear();
            // personListAdapter.notifyDataSetChanged();
            // break;
            // }

            // stop

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // do nothing
        }

    }

    class DatabaseSaver extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            // TODO save to database

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
        }
    }

    private class DatabaseLoader extends
            AsyncTask<Integer, Void, AttendanceSheetContainer> {

        @Override
        protected AttendanceSheetContainer doInBackground(Integer... params) {
            // TODO load from database
            AttendanceSheetContainer container = new AttendanceSheetContainer(
                    0, 0);
//            container.addAttendant(new Person("test1", 0, false));
//            container.addAttendant(new Attendant("test2", 0, false));
            return container;
        }

        @Override
        protected void onPostExecute(AttendanceSheetContainer result) {
            // set container
            loadedSheet = result;
            // set list
            int count = personListView.getAdapter().getCount();
            for (int i = 0; i < count; i++) {
                personListView.setItemChecked(i, false);
            }
            personDataList.clear();
            personDataList.addAll(loadedSheet.getAttendantsAsNames());
            personListAdapter.notifyDataSetChanged();

            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

    private class AttendanceSheetsLoader extends
            AsyncTask<Void, Void, List<Event>> {
        private DatabaseDAO dao = new DatabaseDAO(EventSheetActivity.this);

        @Override
        protected List<Event> doInBackground(Void... params) {
            // load (dummy) events from database
            List<Event> events = dao.getEventList();

            return events;
        }

        @Override
        protected void onPostExecute(List<Event> result) {
            // set list
            eventList = result;
            attendanceSheetsList.clear();
            attendanceSheetsList.add("test");
            for ( Event event : eventList){
                attendanceSheetsList.add(event.getName());
            }
            attendanceSheetsAdapter.notifyDataSetChanged();

            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

    private class SaveAndLoadSheet extends
            AsyncTask<Integer, Void, AttendanceSheetContainer> {

        @Override
        protected AttendanceSheetContainer doInBackground(Integer... params) {
            // TODO save sheet to database (static class)


            // TODO load new from database
            AttendanceSheetContainer container = new AttendanceSheetContainer(
                    0, 0);
//            container.addAttendant(new Attendant("test1", 0, false));
//            container.addAttendant(new Attendant("test2", 0, false));
            return container;
        }

        @Override
        protected void onPostExecute(AttendanceSheetContainer result) {
            // set container
            loadedSheet = result;
            // set list
            int count = personListView.getAdapter().getCount();
            for (int i = 0; i < count; i++) {
                personListView.setItemChecked(i, false);
            }
            personDataList.clear();
            personDataList.addAll(loadedSheet.getAttendantsAsNames());
            personListAdapter.notifyDataSetChanged();


            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

    // attributes
    private Button trainingContentButton;
    private Spinner attendanceSheetsSpinner;
    private ListView personListView;
    private ArrayAdapter<String> personListAdapter;
    private ArrayAdapter<String> attendanceSheetsAdapter;
    private ArrayList<String> personDataList;
    private ArrayList<String> attendanceSheetsList;
    private AttendanceSheetContainer loadedSheet;
    private ProgressDialog progressDialog;

    // caching
    private List<Event> eventList = new ArrayList<Event>(); // needed for string the event ids
    // constants
    private static final int TRAINING_DETAILS = 1;
}