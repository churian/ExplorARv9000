package explorar.explorarv9000;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);
    }

    //TODO: For Jenny's ListView Below - Ignore For Now

//    ListView list;
//    String[] web = {
//            "Google Plus",
//            "Twitter",
//            "Swag",
//
//    } ;
//    Integer[] imageId = {
//            R.drawable.ic_logo_vertical_01,
//            R.drawable.ic_logo_vertical_01,
//            R.drawable.ic_logo_horizontal_02,
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.organiser_events);
//
//        CustomList adapter1 = new
//                CustomList(MainActivity.this, web, imageId);
//        list=(ListView)findViewById(R.id.organiser_events_upcoming_list);
//        list.setAdapter(adapter1);
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                Toast.makeText(MainActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        CustomList adapter2 = new
//                CustomList(MainActivity.this, web, imageId);
//        list=(ListView)findViewById(R.id.organiser_events_past_list);
//        list.setAdapter(adapter2);
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                Toast.makeText(MainActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }

}

