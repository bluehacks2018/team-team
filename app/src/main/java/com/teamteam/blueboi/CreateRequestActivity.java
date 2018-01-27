package com.teamteam.blueboi;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class CreateRequestActivity extends AppCompatActivity {
    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mma");
    Calendar start, end;

    TextView tv_date, tv_time;
    ImageButton button_datetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);

        start = Calendar.getInstance();
        start.set(Calendar.MINUTE, 0);
        start.add(Calendar.DAY_OF_MONTH, 1);
        end = Calendar.getInstance();
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.DAY_OF_MONTH, start.get(Calendar.DAY_OF_MONTH));
        end.add(Calendar.HOUR, start.get(Calendar.HOUR) + 1);

        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_time = (TextView) findViewById(R.id.tv_time);

        tv_date.setText("" + sdfDate.format(start.getTime()).toString());
        tv_time.setText("" + sdfTime.format(start.getTime()).toString() + " - " + sdfTime.format(end.getTime()).toString());


        button_datetime = (ImageButton) findViewById(R.id.button_datetime);

        button_datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker();
            }
        });
    }

    private void datePicker(){

        // Get Current Date

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        start.set(Calendar.YEAR, year);
                        start.set(Calendar.MONTH, monthOfYear);
                        start.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        end.set(Calendar.YEAR, year);
                        end.set(Calendar.MONTH, monthOfYear);
                        end.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        tv_date.setText(sdfDate.format(start.getTime()));
                        //*************Call Time Picker Here ********************
                        timePickerFrom();
                    }
                }, start.get(Calendar.YEAR), start.get(Calendar.MONTH), start.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void timePickerFrom(){

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        start.set(Calendar.HOUR, hourOfDay);
                        start.set(Calendar.MINUTE, minute);
                        timePickerTo();
                    }
                }, start.get(Calendar.HOUR), start.get(Calendar.DAY_OF_MONTH), false);
        timePickerDialog.show();
    }

    private void timePickerTo(){

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        end.set(Calendar.HOUR, hourOfDay);
                        end.set(Calendar.MINUTE, minute);
                        tv_time.setText(sdfTime.format(start.getTime()) + " - " + sdfTime.format(end.getTime()));
                    }
                }, end.get(Calendar.HOUR), end.get(Calendar.DAY_OF_MONTH), false);
        timePickerDialog.show();
    }
}
