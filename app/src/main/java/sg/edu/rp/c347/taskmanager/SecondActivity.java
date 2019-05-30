package sg.edu.rp.c347.taskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    EditText etName, etDescription;
    Button btnAdd2, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etName =findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        btnAdd2 = findViewById(R.id.btnAdd2);
        btnCancel = findViewById(R.id.btnCancel);

        String name = etName.getText().toString();
        String desc = etDescription.getText().toString();

        //method
    }
}
