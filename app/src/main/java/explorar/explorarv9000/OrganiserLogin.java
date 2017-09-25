package explorar.explorarv9000;

import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by carregliu on 23/09/2017.
 */

public class OrganiserLogin extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organiser_sign_up);
        String username = getIntent().getStringExtra("username");

        TextView tv =  (TextView)findViewById(R.id.organiser_login);
        tv.setText(username);

    }
}
