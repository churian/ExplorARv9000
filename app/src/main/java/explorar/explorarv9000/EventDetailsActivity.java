package explorar.explorarv9000;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by michaelliang on 25/9/17.
 */

public class EventDetailsActivity extends AppCompatActivity {

    private SQLiteDatabase mDb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail);

        //DB: Create helper instance
        DbHelper dbHelper = new DbHelper(this);

        //DB: Get readable reference of database and store it in mDb
        mDb = dbHelper.getReadableDatabase();

        //TODO: Must insert fake data

        //DB: call getEventName() and put it in a cursor variable
        Cursor cursor = getEventName();



        //eventdetailbutton: Declare event_detail_button
        final Button event_detail_button = (Button) findViewById(R.id.event_detail_button);

        //eventdetailbutton: Setting action of event_detail_button
        event_detail_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO: Delete this Toast
                Toast.makeText(getBaseContext(), "event_detail_button is clicked", Toast.LENGTH_LONG).show();

                //Build URI TODO: Update this so that it pulls from db build URI
                Uri latLngIntentUri = Uri.parse("http://maps.google.com/maps?daddr=-33.919728, 151.234095(Tyree Room A)");

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

        //eventnametext: Declare textview_event_detail_event_name field
        final TextView textview_event_detail_event_name = (TextView) findViewById(R.id.event_detail_event_name);

        //eventnametext: Pull textview_event_detail_event_name information from database
//        String event_detail_event_name =

    }

    private Cursor getEventName(){
        return mDb.query(
                DbContracts.eventsDBentry.TABLE_NAME,
                new String[]{DbContracts.eventsDBentry.COLUMN_NAME_EVENT},
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}

