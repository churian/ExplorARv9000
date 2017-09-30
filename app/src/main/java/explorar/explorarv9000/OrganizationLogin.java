package explorar.explorarv9000;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import model.Organization;

/**
 * Created by carregliu on 29/09/2017.
 */

public class OrganizationLogin extends MainActivity {
    DbCreation helper = new DbCreation(this);
    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
    //    super.onCreate(savedInstanceState);
    //    setContentView(R.layout.organiser_login);
    //    String organisation = getIntent().getStringExtra("oName");
    //    TextView tv = (TextView)findViewById(R.id.organiser_login);
    //    tv.setText(organisation);
    //}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organiser_login);
    }
        public void onButtonLoginClick(View v) {
        if(v.getId() == (R.id.organiser_login_button));
        EditText a = (EditText)findViewById(R.id.organiser_login);
        String name = a.getText().toString();
        EditText b = (EditText)findViewById(R.id.organiser_password);
        String pass = b.getText().toString();
        String oPassword = helper.searchoPassword(pass);
        if(pass.equals(oPassword)) {
            Intent i = new Intent(OrganizationLogin.this,OrganizerHome.class);
            startActivity(i);
        }
        else {
            Toast temp = Toast.makeText(OrganizationLogin.this, "Username and Password don't match", Toast.LENGTH_SHORT);
            temp.show();
        }
    }
    public void onButtonSignUpClick(View v) {
        if(v.getId() == (R.id.organiser_login_button));
        Intent i = new Intent(OrganizationLogin.this,OrganizationSignup.class);
        startActivity(i);
    }
}
