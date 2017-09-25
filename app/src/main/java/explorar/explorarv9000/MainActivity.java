package explorar.explorarv9000;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DbCreation helper = new DbCreation(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.organiser_login_button)
        {
            EditText a = (EditText)findViewById(R.id.organiser_login);
            String str = a.getText().tostring();
            EditText b = (EditText)findViewById(R.id.password)
            String password = b.getText().toString();

            String password = helper.searchoPassword(str);
            if(password.equals(password)) {

                Intent i = new Intent(MainActivity.this, Display.class);
                i.putExtra("Username", str);
                startActivity(i);
            }
            else
            {
                Toast temp = Toast.makeText(MainActivity.this,"Username and Password don't match!",Toast.LENGTH_SHORT);
                temp.show();
            }

        if(v.getId)  == R.id.organiser_signup)
        }
            Intent i = new Intent(MainActivity.this, SignUp.class);

            startActivity(i);


    }

