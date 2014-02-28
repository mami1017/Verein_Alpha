package de.juehvtech.verein.database;

interface DatabaseStrings {
    static final String NAME = "Verein_Alpha_DB";
    static final int VERSION = 1;

    // Tables
    static final String PERSON_TABLENAME = "person";
    static final String TRAINER_TABLENAME = "trainer";
    static final String EVENT_TABLENAME = "event";
    static final String SHEET_TABLENAME = "sheet";
    static final String SHEET_PERSON_MAP_TABLENAME = "sheet_person_map";
    static final String EVENT_PERSON_MAP_TABLENAME = "event_person_map";

    // reset
    static final String RESET_STRING ="DROP TABLE IF EXISTS "+
            PERSON_TABLENAME + "; DROP TABLE IF EXISTS"+
            TRAINER_TABLENAME + "; DROP TABLE IF EXISTS"+
            EVENT_TABLENAME + "; DROP TABLE IF EXISTS"+
            SHEET_TABLENAME + "; DROP TABLE IF EXISTS"+
            SHEET_PERSON_MAP_TABLENAME + "; DROP TABLE IF EXISTS"+
            EVENT_PERSON_MAP_TABLENAME + ";";

    // create
    static final String PERSON_CREATION_STRING = "CREATE TABLE IF NOT EXISTS "
            + PERSON_TABLENAME
            + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, " +
            "is_visitor INTEGER NOT NULL);";

    static final String TRAININER_CREATION_STRING = "CREATE TABLE IF NOT EXISTS "
            + TRAINER_TABLENAME
            + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);";

    static final String EVENT_CREATION_STRING = "CREATE TABLE IF NOT EXISTS "
            + EVENT_TABLENAME
            + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, " +
            "trainer_id INTEGER NOT NULL, day INTEGER NOT NULL, time INTEGER NOT NULL," +
            "duration INTEGER NOT NULL);";

    static final String SHEET_CREATION_STRING = "CREATE TABLE IF NOT EXISTS "
            + SHEET_TABLENAME
            + " (id INTEGER PRIMARY KEY AUTOINCREMENT, event_id INTEGER NOT NULL, topic TEXT NOT NULL, " +
            "notes TEXT NOT NULL);";

    static final String SHEET_PERSON_MAP_CREATION_STRING = "CREATE TABLE IF NOT EXISTS "
            + SHEET_PERSON_MAP_TABLENAME
            + " (sheet_id INTEGER NOT NULL, person_id INTEGER NOT NULL, " +
            "is_attendant INTEGER NOT NULL);";

    static final String EVENT_PERSON_MAP_CREATION_STRING = "CREATE TABLE IF NOT EXISTS "
            + EVENT_PERSON_MAP_TABLENAME
            + " (event_id INTEGER NOT NULL, person_id INTEGER NOT NULL);";

//    static final String NONE_MEMBER_TABLENAME = "none_member";
//    static final String NONE_MEMBER_CREATION_STRING = "CREATE TABLE "
//            + NONE_MEMBER_TABLENAME
//            + " (key INTEGER PRIMARY KEY, name TEXT NOT NULL, email TEXT, " +
//            "fon TEXT);";

    // insert

    // select
//    static final String GET_UNSEND_ATTENDENCE_SHEETS = "SELECT data FROM "
//            + SHEET_TABLENAME + " WHERE sended=0;";

}
