package explorar.explorarv9000;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by carregliu on 23/09/2017.
 */

public class SignUp extends Activity {

    DbCreation helper = new DbCreation(this);
}
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organiser_sign_up);
    }

    public void onSignUpClick(View v)
    {
        if(v.getId()== R.id.organiser_signup)
        {
            EditText organisation = (EditText)findViewById(R.id.organisation);
            EditText email = (EditText)findViewById(R.id.email);
            EditText password = (EditText)findViewById(R.id.password);

            String organisationstr = organisation.getText().toString();
            String emailstr = email.getText().toString();
            String passwordstr = password.getText().toString();

            if(!passwordstr.equals(password2str))
            {
                Toast pass = Toast.makeText(SignUp.this, "Passwords don't match!", Toast.LENGTH_SHORT);
                pass.show();
            }
            else
            {
                Organization o = new Organization();
                o.setoName(organisationstr);
                o.setoEmail(emailstr);
                o.setoPassword(passwordstr);

                helper.insertOrganization();

            }
        }

}
