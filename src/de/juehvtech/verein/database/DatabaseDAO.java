package de.juehvtech.verein.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.juehvtech.verein.container.Event;

public class DatabaseDAO {
    private Context context;
    private DatabaseHelper database;
    private SQLiteDatabase connection;

    public DatabaseDAO(Context context) {
        this.context = context;
        database = new DatabaseHelper(context);
    }

    private void connect(boolean readonly) {
        if (readonly) {
            connection = database.getReadableDatabase();
        } else {
            connection = database.getWritableDatabase();
        }
    }

    /***
     *
     * @return A list with dummy elements. The Container has only id and name. The rest of attributes are null.
     */
    public List<Event> getEventList(){
        List<Event> eventList = new ArrayList<Event>();
        connect(false);

        // get event list
        Cursor cursor =  connection.query(DatabaseStrings.EVENT_TABLENAME, new String[]{"id", "name"},
                null, null, null,null,"name ASC");
        if (cursor != null){
            cursor.moveToFirst();
            while(cursor.isAfterLast() == false){
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                eventList.add(new Event(name,id));
                cursor.moveToNext();
            }
            cursor.close();
        }

        disconnect();
        return eventList;
    }

//	    public void insertEntry(DatabaseEntry entry) {
//	        connect(false);
//
//	        String sql = "INSERT INTO entrys (date, type, data) VALUES (?,?,?)";
//	        SQLiteStatement stmt = connection.compileStatement(sql);
//	        stmt.clearBindings();
//	        stmt.bindLong(1, entry.getDate().getTime());
//	        stmt.bindLong(2, entry.getType().ordinal());
//	        stmt.bindString(3, entry.toXML());
//	        stmt.execute();
//
//	        disconnect();
//	    }
//
//	    public List<String> getUnsendEntrys() {
//	        connect(false);
//
//	        ArrayList<String> entrys = new ArrayList<String>();
//	        lastResult = connection.rawQuery(DatabaseStrings.GET_UNSEND_ATTENDENCE_SEETS, null);
//	        while (lastResult.moveToNext()) {
//	            entrys.add(lastResult.getString(lastResult.getColumnIndex("data")));
//	        }
//	        lastResult.close();
//	        disconnect();
//	        return entrys;
//	    }
//
//	    public void setAsSynced(Date[] ids) {
//	    }
//
//	    public void clearOldEntrys() {
//	    }

    private void disconnect() {
        connection.close();
    }
}
