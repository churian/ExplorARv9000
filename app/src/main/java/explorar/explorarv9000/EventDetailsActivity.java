package explorar.explorarv9000;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by michaelliang on 25/9/17.
 */

public class EventDetailsActivity extends AppCompatActivity {

    private SQLiteDatabase mDb;
    private String markerTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail);

        /*
        Intents
         */

        //Intent: Get Intent
        Intent mapsActivityIntentThatStartedActivity = getIntent();

        //Intent: Get Information that was packaged in it
        if (mapsActivityIntentThatStartedActivity.hasExtra(Intent.EXTRA_TEXT)){
            markerTitle = mapsActivityIntentThatStartedActivity.getStringExtra(Intent.EXTRA_TEXT);
        }

        /*
        Database
        */

        //DB: Create helper instance
        DbCreation dbCreation = new DbCreation(this);

        //DB: Get readable reference of database and store it in mDb
        mDb = dbCreation.getWritableDatabase();
        Log.i("Michael", "WritableDatabase has been created");

        //DB: Insert Fake Data
        DBInsertFakeData.insertFakeData(mDb);
        Log.i("Michael", "Fake Data has been inserted");

        //DB: call getEventName() and put it in a cursor variable
        Cursor cursor = mDb.rawQuery("Select * from " + DbContracts.eventsDBentry.TABLE_NAME + ";",null);
        Log.i("Michael", "DB data has been inserted into cursor");

        /*
        Database getting data TODO: PUT THIS AS A BACKGROUND TASK - too much work on the main thread
         */

        //DB Data: Move cursor to the row that your data is on
        cursor.moveToPosition(0); //TODO: Make this use markerTitle as a primary key and find the position of the row -- cursor tables start at 0

        //DB Data: hostOrg
        String hostOrg = cursor.getString(cursor.getColumnIndex(DbContracts.eventsDBentry.COLUMN_NAME_HOSTORG));
        Log.i("Michael", "hostOrganisation extracted is " + hostOrg);

        //DB Data: location
        String location = cursor.getString(cursor.getColumnIndex(DbContracts.eventsDBentry.COLUMN_LOCATION_EVENT));
        Log.i("Michael", "location extracted is " + location);

        //DB Data: date
        String date = cursor.getString(cursor.getColumnIndex(DbContracts.eventsDBentry.COLUMN_DATE_EVENT));
        Log.i("Michael", "Date extracted is " + date);

        //DB Data: startTime
        String startTime = cursor.getString(cursor.getColumnIndex(DbContracts.eventsDBentry.COLUMN_STARTTIME_EVENT));
        Log.i("Michael", "startTime extracted is " + startTime);

        //DB Data: endTime
        String endTime = cursor.getString(cursor.getColumnIndex(DbContracts.eventsDBentry.COLUMN_ENDTIME_EVENT));
        Log.i("Michael", "endTime extracted is " + endTime);

        //DB Data: price
        String price = cursor.getString(cursor.getColumnIndex(DbContracts.eventsDBentry.COLUMN_PRICE_EVENT));
        Log.i("Michael", "price extracted is " + price);

        //DB Data: description
        String description = cursor.getString(cursor.getColumnIndex(DbContracts.eventsDBentry.COLUMN_PRICE_EVENT));
        Log.i("Michael", "price extracted is " + price);

        /*
        Set TextViews with Data from DB
         */

        //eventname: Declare textview_event_detail_event_name field
        final TextView textview_event_detail_event_name = (TextView) findViewById(R.id.event_detail_event_name);

        //eventname: set Textview to markerTitle
        textview_event_detail_event_name.setText(markerTitle);


        //hostOrg: Declare textview_event_detail_organiser_name field
        final TextView textview_event_detail_organiser_name = (TextView) findViewById(R.id.event_detail_organiser_name);

        //Host: set Textview to hostOrg

        //TODO: Continue doing this set all textviews - YOURE WORKING ON THIS RN

        /*
        Event Detail Button - Maps
         */

        //eventdetailbutton: Declare event_detail_button
        final Button event_detail_button = (Button) findViewById(R.id.event_detail_button);

        //eventdetailbutton: Setting action of event_detail_button
        event_detail_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO: Delete this Toast
                Toast.makeText(getBaseContext(), "event_detail_button is clicked", Toast.LENGTH_LONG).show();

                //Build URI TODO: Update this so that it pulls from db build URI
                Uri latLngIntentUri = Uri.parse("http://maps.google.com/maps?daddr=-33.919728, 151.234095(UNSW Business School G26)");

                //Intent to open up Google Maps with directions
                Intent openMapintent = new Intent(Intent.ACTION_VIEW, latLngIntentUri);

                //Start openMapIntent activity and check for application that can resolve it
                if (openMapintent.resolveActivity(getPackageManager()) != null) {
                    startActivity(openMapintent);
                } else {
                    Toast.makeText(getBaseContext(), "No applications found to resolve activity", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}

