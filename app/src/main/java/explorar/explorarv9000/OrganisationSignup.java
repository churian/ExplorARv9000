package explorar.explorarv9000;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import model.Organization;

/**
 * Created by carregliu on 17/09/2017.
 */

public class OrganisationSignup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organiser_sign_up);
    }

    public void onSignUpClick(View v)
    {
        if(v.getId()== R.id.organiser_login_button)
        {
            EditText oName = (EditText)findViewById(R.id.organisation);
            EditText oEmail = (EditText)findViewById(R.id.email);
            EditText oPassword = (EditText)findViewById(R.id.password);

            String oNamestr = oName.getText().toString();
            String oEmailstr = oEmail.getText().toString();
            String oPasswordstr = oPassword.getText().toString();

        }
    }
}
