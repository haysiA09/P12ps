package sg.edu.rp.c347.taskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    EditText etName, etDescription;
    Button btnAdd2, btnCancel;
    ArrayAdapter aa;
    ArrayList<Task> task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etName =findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        btnAdd2 = findViewById(R.id.btnAdd2);
        btnCancel = findViewById(R.id.btnCancel);

        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(SecondActivity.this);
                String name = etName.getText().toString();
                final String desc = etDescription.getText().toString();
                db.insertTask(name, desc);
                Log.d("Success" ,"New Task added");
                db.close();
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
