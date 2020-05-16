package com.example.elis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.FirebaseDatabase;

import java.sql.BatchUpdateException;

public class timepick extends AppCompatActivity  implements TimePickerDialog.OnTimeSetListener {
    private Button buttonON;
    private Button buttonOff;
    private Button buttonBatal;
    private Button buttonSimpan;
    private FirebaseDatabase firebaseDatabase;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Button button = (Button)findViewById(R.id.alrmBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timepick = new JamFragment();
                timepick.show(getSupportFragmentManager(),"Time picker");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView text = (TextView)findViewById(R.id.alarmText);
        text.setText(""+hourOfDay+":"+ minute);
    }
}