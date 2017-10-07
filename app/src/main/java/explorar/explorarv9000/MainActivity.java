package explorar.explorarv9000;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import model.Student;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import explorar.explorarv9000.DbCreation;


    public class MainActivity extends Activity{
        Button loginButton = (Button) findViewById(R.id.login_button);
        TextView studentSignup = (TextView) findViewById(R.id.student_signup_link);
        DbCreation DBCreation = new DbCreation(this);
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.student_login);
            Toast.makeText(this,"MAIN ACTIVITY IS EVOKED - SOMETING WENT WRONG", Toast.LENGTH_LONG).show();

            loginButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if(view.getId() == (R.id.login_button);
                    EditText a = (EditText)findViewById(R.id.student_email);
                    String Email = a.getText().toString();
                    EditText b = (EditText)findViewById(R.id.student_password);
                    String Password = b.getText().toString();
                    String sPassword = DBCreation.searchsPassword(pass);
                    if(Password.equals(sPassword)) {
                        Intent i = new Intent(MainActivity.this,StudentHome.class);
                        startActivity(i);
                    }
                    else {
                        Toast temp = Toast.makeText(MainActivity.this, "Username and Password don't match", Toast.LENGTH_SHORT);
                        temp.show();
                    }
                }
            });
            studentSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View V) {
                    if(V.getId() == (R.id.studentSignup));
                    Intent intent = new Intent(MainActivity.this,StudentSignup.class);
                    startActivity(intent);
                }

                @Override
        public void onClick(View v) {
            if(v.getId() == (R.id.organiser_login_button));
            Intent i1 = new Intent(MainActivity.this, OrganizationLogin.class);
            startActivity(i1);
        }

        });
        }
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