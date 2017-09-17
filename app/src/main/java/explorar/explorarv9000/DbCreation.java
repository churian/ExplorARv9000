package explorar.explorarv9000;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.app.Activity;

/**
 * Created by benja on 17/09/2017.
 */

// this class is responsible for doing anything with the database

public class DbCreation extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1; //remember to update the version number when any database changes are made
    private static final String DATABASE_NAME = "app.db";

    public DbCreation (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public DbCreation(Context context)
    {
        super(context, DATABASE_NAME , null , DATABASE_VERSION);
    }

    // creates the initial table by running the sql query "SQL_CREATE_STUDENT_TABLE"
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_STUDENT_TABLE = "CREATE TABLE " +
                DbContracts.studentDBentry.TABLE_NAME + " (" +
                DbContracts.studentDBentry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbContracts.studentDBentry.COLUMN_zID + " TEXT NOT NULL, " +
                DbContracts.studentDBentry.COLUMN_NAME_STUDENT + " TEXT NOT NULL, " +
                DbContracts.studentDBentry.COLUMN_PASSWORD_STUDENT + " TEXT NOT NULL, " +
                DbContracts.studentDBentry.COLUMN_EMAIL_STUDENT + " TEXT NOT NULL, " +
                DbContracts.studentDBentry.COLUMN_DEGREE_STUDENT + "TEXT NOT NULL, " +
                ");";

        final String SQL_CREATE_ORGANISATIONS_TABLE = "CREATE TABLE " +
                DbContracts.organisationsDBentry .TABLE_NAME + " (" +
                DbContracts.organisationsDBentry ._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbContracts.organisationsDBentry .COLUMN_NAME_ORG + " TEXT NOT NULL, " +
                DbContracts.organisationsDBentry .COLUMN_PASSWORD_ORG + " TEXT NOT NULL, " +
                DbContracts.organisationsDBentry .COLUMN_EMAIL_ORG + " TEXT NOT NULL," +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_STUDENT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ORGANISATIONS_TABLE);

    }

    @Override
    public void insertOrgansation(organisation o)
    {
        app.db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put();

    }


    // drops the current table and creates a new one when a new version is updated
    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  + " + DbContracts.studentDBentry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
