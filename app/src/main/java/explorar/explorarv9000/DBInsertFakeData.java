package explorar.explorarv9000;

/**
 * Created by michaelliang on 25/9/17.
 */

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;


public class DBInsertFakeData {

        public static void insertFakeData(SQLiteDatabase db){
            if(db == null){
                return;
            }

            //create a list of fake events
            List<ContentValues> list = new ArrayList<ContentValues>();

            //db: beginTransaction();
            db.beginTransaction();

            //db: clear database table
            db.delete (DbContracts.eventsDBentry.TABLE_NAME,null,null);

            ContentValues cv = new ContentValues();
            cv.put(DbContracts.eventsDBentry.COLUMN_NAME_EVENT, "SQL Workshop 101");
            cv.put(DbContracts.eventsDBentry.COLUMN_NAME_HOSTORG, "BITSA UNSW");
            cv.put(DbContracts.eventsDBentry.COLUMN_LOCATION_EVENT, "UNSW Business School G26");
            cv.put(DbContracts.eventsDBentry.COLUMN_DATE_EVENT, "2nd August 2017");
            cv.put(DbContracts.eventsDBentry.COLUMN_STARTTIME_EVENT, "12:00");
            cv.put(DbContracts.eventsDBentry.COLUMN_ENDTIME_EVENT, "2:00");
            cv.put(DbContracts.eventsDBentry.COLUMN_PRICE_EVENT, "FREE");
            cv.put(DbContracts.eventsDBentry.COLUMN_NAME_DESCRIPTION, "Best SQL Workshop at UNSW happening today!");
            cv.put(DbContracts.eventsDBentry.COLUMN_LATITUDE_EVENT, "-33.919728");
            cv.put(DbContracts.eventsDBentry.COLUMN_LONGITUDE_EVENT, "151.234095");
            cv.put(DbContracts.eventsDBentry.COLUMN_EVENT_TYPE , "WORKSHOP");
            list.add(cv);
            db.insert(DbContracts.eventsDBentry.TABLE_NAME, null, cv);

            cv.put(DbContracts.eventsDBentry.COLUMN_NAME_EVENT, "R Workshop 73");
            cv.put(DbContracts.eventsDBentry.COLUMN_NAME_HOSTORG, "WIT UNSW");
            cv.put(DbContracts.eventsDBentry.COLUMN_LOCATION_EVENT, "UNSW Business School G23");
            cv.put(DbContracts.eventsDBentry.COLUMN_DATE_EVENT, "4th August 2017");
            cv.put(DbContracts.eventsDBentry.COLUMN_STARTTIME_EVENT, "2:00");
            cv.put(DbContracts.eventsDBentry.COLUMN_ENDTIME_EVENT, "4:00");
            cv.put(DbContracts.eventsDBentry.COLUMN_PRICE_EVENT, "$5");
            cv.put(DbContracts.eventsDBentry.COLUMN_NAME_DESCRIPTION, "Free Pizza!");
            cv.put(DbContracts.eventsDBentry.COLUMN_LATITUDE_EVENT, "-33.915282");
            cv.put(DbContracts.eventsDBentry.COLUMN_LONGITUDE_EVENT, "151.229160");
            cv.put(DbContracts.eventsDBentry.COLUMN_EVENT_TYPE , "WORKSHOP");
            list.add(cv);
            db.insert(DbContracts.eventsDBentry.TABLE_NAME, null, cv);

            //db: setTransactionSuccessful();
            db.setTransactionSuccessful();

            //db: endTransaction();
            db.endTransaction();





//            final String INSERT_TEST_DATA_ONE = "INSERT INTO " +
//                    DbContracts.eventsDBentry .TABLE_NAME + " (" +
//                    DbContracts.eventsDBentry ._ID + ", " +
//                    DbContracts.eventsDBentry .COLUMN_NAME_EVENT + ", " +
//                    DbContracts.eventsDBentry .COLUMN_NAME_HOSTORG + ", " +
//                    DbContracts.eventsDBentry .COLUMN_LOCATION_EVENT + ", " +
//                    DbContracts.eventsDBentry .COLUMN_DATE_EVENT + ", " +
//                    DbContracts.eventsDBentry .COLUMN_STARTTIME_EVENT + ", " +
//                    DbContracts.eventsDBentry .COLUMN_ENDTIME_EVENT + ", " +
//                    DbContracts.eventsDBentry .COLUMN_PRICE_EVENT + ", " +
//                    DbContracts.eventsDBentry .COLUMN_NAME_DESCRIPTION + ", " +
//                    DbContracts.eventsDBentry .COLUMN_LATITUDE_EVENT + "," +
//                    DbContracts.eventsDBentry .COLUMN_LONGITUDE_EVENT + "," +
//                    DbContracts.eventsDBentry .COLUMN_EVENT_TYPE + ")" +
//                    "VALUES (" +
//                    "SQL Workshop 102," +
//                    "UNSW Business Society," +
//                    "Alumni Lawn," +
//                    "31st December 2017," +
//                    "18:00," +
//                    "20:00," +
//                    "10.00," + //double? price
//                    "Learn to code in SQL and be amazing," +
//                    "-33.919728," +
//                    "151.234095," +
//                    "Workshop);"
//                    ;
//
//            db.execSQL(INSERT_TEST_DATA_ONE);

//
//            DbContracts.eventsDBentry .TABLE_NAME + " (" +
//                    DbContracts.eventsDBentry ._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    DbContracts.eventsDBentry .COLUMN_NAME_EVENT + " TEXT NOT NULL, " +
//                    DbContracts.eventsDBentry .COLUMN_NAME_HOSTORG + " TEXT NOT NULL, " +
//                    DbContracts.eventsDBentry .COLUMN_LOCATION_EVENT + " TEXT NOT NULL," +
//                    DbContracts.eventsDBentry .COLUMN_DATE_EVENT + " TEXT NOT NULL," +
//                    DbContracts.eventsDBentry .COLUMN_STARTTIME_EVENT + " TEXT NOT NULL," +
//                    DbContracts.eventsDBentry .COLUMN_ENDTIME_EVENT + " TEXT NOT NULL," +
//                    DbContracts.eventsDBentry .COLUMN_PRICE_EVENT + " DOUBLE NOT NULL," +
//                    DbContracts.eventsDBentry .COLUMN_NAME_DESCRIPTION + " TEXT NOT NULL, " +
//                    DbContracts.eventsDBentry .COLUMN_LATITUDE_EVENT + " DOUBLE NOT NULL," +
//                    DbContracts.eventsDBentry .COLUMN_LONGITUDE_EVENT + " DOUBLE NOT NULL," +
//                    DbContracts.eventsDBentry .COLUMN_EVENT_TYPE + " TEXT NOT NULL" +
//                    ");";




            //insert all events in one transaction
//            try
//            {
//                db.beginTransaction();
//                //clear the table first
//                db.delete (DbContracts.eventsDBentry.TABLE_NAME,null,null);
//                //go through the list and add one by one
//                for(ContentValues c:list){
//                    db.insert(DbContracts.eventsDBentry.TABLE_NAME, null, c);
//                }
//                db.setTransactionSuccessful();
//            }
//            catch (SQLException e) {
//                //too bad :(
//            }
//            finally
//            {
//                db.endTransaction();
//            }

        }
    }

