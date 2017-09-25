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

            //create a list of fake guests
            List<ContentValues> list = new ArrayList<ContentValues>();

            ContentValues cv = new ContentValues();
            cv.put(DbContracts.eventsDBentry.COLUMN_NAME_EVENT, "SQL Workshop 1");
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

            cv.put(DbContracts.eventsDBentry.COLUMN_NAME_EVENT, "R Workshop 73");
            cv.put(DbContracts.eventsDBentry.COLUMN_NAME_HOSTORG, "WIT UNSW");
            cv.put(DbContracts.eventsDBentry.COLUMN_LOCATION_EVENT, "UNSW Business School G23");
            cv.put(DbContracts.eventsDBentry.COLUMN_DATE_EVENT, "4th August 2017");
            cv.put(DbContracts.eventsDBentry.COLUMN_STARTTIME_EVENT, "2:00");
            cv.put(DbContracts.eventsDBentry.COLUMN_ENDTIME_EVENT, "4:00");
            cv.put(DbContracts.eventsDBentry.COLUMN_PRICE_EVENT, "$5");
            cv.put(DbContracts.eventsDBentry.COLUMN_NAME_DESCRIPTION, "Free Pizza!");
            cv.put(DbContracts.eventsDBentry.COLUMN_LATITUDE_EVENT, "-33.919225");
            cv.put(DbContracts.eventsDBentry.COLUMN_LONGITUDE_EVENT, "151.230394");
            cv.put(DbContracts.eventsDBentry.COLUMN_EVENT_TYPE , "WORKSHOP");
            list.add(cv);

            //insert all guests in one transaction
            try
            {
                db.beginTransaction();
                //clear the table first
                db.delete (DbContracts.eventsDBentry.TABLE_NAME,null,null);
                //go through the list and add one by one
                for(ContentValues c:list){
                    db.insert(DbContracts.eventsDBentry.TABLE_NAME, null, c);
                }
                db.setTransactionSuccessful();
            }
            catch (SQLException e) {
                //too bad :(
            }
            finally
            {
                db.endTransaction();
            }

        }
    }

