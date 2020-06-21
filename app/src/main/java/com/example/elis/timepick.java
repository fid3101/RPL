package com.example.elis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.FirebaseDatabase;

import java.sql.BatchUpdateException;
import java.text.DateFormat;
import java.util.Calendar;

public class timepick extends AppCompatActivity  implements TimePickerDialog.OnTimeSetListener {
    private Button buttonON;
    private Button buttonOff;
    private Button buttonBatal;
    private Button buttonSimpan;
    private TextView mTextView;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_timepick);

        mTextView = findViewById(R.id.alarmText);

        Button button = (Button)findViewById(R.id.alrmBtn);
        buttonBatal = (Button)findViewById(R.id.alarmBatal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timepick = new JamFragment();
                timepick.show(getSupportFragmentManager(),"Time picker");
            }
        });
        buttonBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),homeActivity.class));
            }
        });
        final Button cancel = findViewById(R.id.alarmBatal);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cancelAlarm();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);

        updateTimetext(c);
        startAlarm(c);
    }

    private void updateTimetext(Calendar c){
        String timeText = "Alarm :";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c);
        mTextView.setText(timeText);
    }
    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    }

}