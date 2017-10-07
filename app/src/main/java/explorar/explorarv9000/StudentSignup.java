package explorar.explorarv9000;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import model.Student;

/**
 * Created by Kev on 30/9/17.
 */

public class StudentSignup extends MainActivity{
    DbCreation DBCreation = new DbCreation(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_signup);
    }
    public void onClick(View V) {
        if(V.getId() == (R.id.button2));
        EditText  = (EditText)findViewById(R.id.organisation);
        EditText email = (EditText)findViewById(R.id.email);
        EditText password = (EditText)findViewById(R.id.password);
        String  = student.getText().toString();
        String emailstr = email.getText().toString();
        String passwordstr = password.getText().toString();
        Student s = new Student();
        s.setsName(studentstr);
        s.setsEmail(emailstr);
        s.setsPassword(passwordstr);
        DBCreation.insertStudent(s);
    }

}
}
