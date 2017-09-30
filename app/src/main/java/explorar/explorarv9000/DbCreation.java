package explorar.explorarv9000;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.app.Activity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

import model.Organization;
import model.Student;

/**
 * Created by benja on 17/09/2017.
 */


// this class is responsible for doing anything with the database

public class DbCreation extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1; //remember to update the version number when any database changes are made
    private static final String DATABASE_NAME = "app.db";

    public DbCreation(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public DbCreation(Context context)
    {
        super(context, DATABASE_NAME , null , DATABASE_VERSION);
    }

    // creates the database tables by running the sql queries e.g. "SQL_CREATE_STUDENT_TABLE"
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_STUDENT_TABLE = "CREATE TABLE " +
                DbContracts.studentDBentry.TABLE_NAME + " (" +
                DbContracts.studentDBentry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbContracts.studentDBentry.COLUMN_zID + " TEXT NOT NULL, " +
                DbContracts.studentDBentry.COLUMN_NAME_STUDENT + " TEXT NOT NULL, " +
                DbContracts.studentDBentry.COLUMN_PASSWORD_STUDENT + " TEXT NOT NULL, " +
                DbContracts.studentDBentry.COLUMN_EMAIL_STUDENT + " TEXT NOT NULL, " +
                DbContracts.studentDBentry.COLUMN_DEGREE_STUDENT + "TEXT NOT NULL" +
                ");";

        final String SQL_CREATE_ORGANISATIONS_TABLE = "CREATE TABLE " +
                DbContracts.organisationsDBentry .TABLE_NAME + " (" +
                DbContracts.organisationsDBentry ._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbContracts.organisationsDBentry .COLUMN_NAME_ORG + " TEXT NOT NULL, " +
                DbContracts.organisationsDBentry .COLUMN_PASSWORD_ORG + " TEXT NOT NULL, " +
                DbContracts.organisationsDBentry .COLUMN_EMAIL_ORG + " TEXT NOT NULL" +
                ");";

        //TODO: MIGHT NEED TO MAKE THIS A DATE FORMAT


        // convert datestring to dateformat but not sure if it show up on the db.


        final String SQL_CREATE_EVENTS_TABLE = "CREATE TABLE " +
                DbContracts.eventsDBentry .TABLE_NAME + " (" +
                DbContracts.eventsDBentry ._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbContracts.eventsDBentry .COLUMN_NAME_EVENT + " TEXT NOT NULL, " +
                DbContracts.eventsDBentry .COLUMN_NAME_HOSTORG + " TEXT NOT NULL, " +
                DbContracts.eventsDBentry .COLUMN_LOCATION_EVENT + " TEXT NOT NULL," +
                DbContracts.eventsDBentry .COLUMN_DATE_EVENT  + " TEXT NOT NULL," +
                DbContracts.eventsDBentry .COLUMN_STARTTIME_EVENT + " TEXT NOT NULL," +
                DbContracts.eventsDBentry .COLUMN_ENDTIME_EVENT + " TEXT NOT NULL," +
                DbContracts.eventsDBentry .COLUMN_PRICE_EVENT + " DOUBLE NOT NULL," +
                DbContracts.eventsDBentry .COLUMN_NAME_DESCRIPTION + " TEXT NOT NULL, " +
                DbContracts.eventsDBentry .COLUMN_LATITUDE_EVENT + " DOUBLE NOT NULL," +
                DbContracts.eventsDBentry .COLUMN_LONGITUDE_EVENT + " DOUBLE NOT NULL," +
                DbContracts.eventsDBentry .COLUMN_EVENT_TYPE + " TEXT NOT NULL" +
                ");";


        sqLiteDatabase.execSQL(SQL_CREATE_STUDENT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ORGANISATIONS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_EVENTS_TABLE);

     }
    // insert a new student row
    public void insertStudent(Student s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContracts.studentDBentry.COLUMN_zID, s.getzID());
        values.put(DbContracts.studentDBentry.COLUMN_NAME_STUDENT, s.getName());
        values.put(DbContracts.studentDBentry.COLUMN_PASSWORD_STUDENT, s.getPassword());
        values.put(DbContracts.studentDBentry.COLUMN_EMAIL_STUDENT, s.getEmail());
        db.insert(DbContracts.studentDBentry.TABLE_NAME, null, values);
        db.close();
        // db.update(DbContracts.studentDBentry.TABLE_NAME, values, COLUMN_USER_ID + " = ?",
        //      new String[]{String.valueOf(s.getzID())});

    }
    //insert a new organisation row
    public void insertOrganization(Organization o) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContracts.organisationsDBentry.COLUMN_NAME_ORG, o.getoName());
        values.put(DbContracts.organisationsDBentry.COLUMN_EMAIL_ORG, o.getoEmail());
        values.put(DbContracts.organisationsDBentry.COLUMN_PASSWORD_ORG, o.getoPassword());
        db.insert(DbContracts.organisationsDBentry.TABLE_NAME, null, values);
        db.close();
    }
    // TODO: insert a new events row
    public void insertEvents() {

    }
    

    public String searchoPassword(String oName) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "Select oName, oPassword from "+DbContracts.organisationsDBentry.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        b = "Not Found";
        if(cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                b = cursor.getString(1);
                break;
            }
            while(cursor.moveToNext());
        }
        return query;
    }

    // drops the current table and creates a new one when a new version is updated
    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  + " + DbContracts.studentDBentry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
