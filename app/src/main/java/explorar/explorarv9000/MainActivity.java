package explorar.explorarv9000;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"MAIN ACTIVITY IS EVOKED - SOMETING WENT WRONG", Toast.LENGTH_LONG).show();
    }
}

