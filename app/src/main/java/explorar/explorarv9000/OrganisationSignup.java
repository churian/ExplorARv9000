package explorar.explorarv9000;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by carregliu on 17/09/2017.
 */

public class OrganisationSignup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organiser_sign_up)
    }

    public void onSignUpClick(View v)
    {
        if(v.getId()== R.id.organiser_login_button)
        {
            EditText name = (EditText)findViewById(R.id.organisation)
            EditText email = (EditText)findViewById(R.id.email)
            EditText password = (EditText)findViewById(R.id.password)

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String password = password(getText().toString();
        }
    }
}
