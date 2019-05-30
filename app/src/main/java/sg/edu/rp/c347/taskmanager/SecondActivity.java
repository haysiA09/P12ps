package sg.edu.rp.c347.taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {
    int reqCode=12345;

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

                String[]info={name};
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 5);

                Intent intent = new Intent(SecondActivity.this,
                        ScheduledNotificationReceiver.class);
                intent.putExtra("info",info);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        SecondActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);
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
