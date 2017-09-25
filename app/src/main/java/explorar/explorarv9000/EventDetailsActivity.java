package explorar.explorarv9000;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by michaelliang on 25/9/17.
 */

public class EventDetailsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail);

        //Declare goEventButton
        final Button goEventButton = (Button) findViewById(R.id.event_details_button);

        //Setting action of goEventButton
        goEventButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO: Delete this Toast
                Toast.makeText(getBaseContext(), "goEventButton is clicked", Toast.LENGTH_LONG).show();

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
    }
}

