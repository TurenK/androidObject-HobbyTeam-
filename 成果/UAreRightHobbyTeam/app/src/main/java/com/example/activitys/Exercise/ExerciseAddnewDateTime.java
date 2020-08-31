package com.example.activitys.Exercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.activitys.R;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ExerciseAddnewDateTime extends Activity {

    @InjectView(R.id.backBtn)
    Button backBtn;
    @InjectView(R.id.titleBar)
    LinearLayout titleBar;
    @InjectView(R.id.datepicker)
    DatePicker datepicker;
    @InjectView(R.id.timepicker)
    TimePicker timepicker;
    @InjectView(R.id.submitBTN)
    Button submitBTN;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private static final int CALCULATE = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_addnew_date_time);
        ButterKnife.inject(this);
        datePicker = (DatePicker) findViewById(R.id.datepicker);
        timePicker = (TimePicker) findViewById(R.id.timepicker);
        timePicker.setIs24HourView(true);
        initData();
    }

    public void initData() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int thisYear, int monthOfYear, int dayOfMonth) {
                // Toast.makeText(ExerciseAddnewDateTime.this, year + "年" + monthOfYear + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
                //   R.id.TimeFrame.setText(DateTimeActivity.this, year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                year = thisYear;
                month = monthOfYear;
                day = dayOfMonth;
            }
        });
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minuteOfHour) {
                //Toast.makeText(ExerciseAddnewDateTime.this, hourOfDay + "时" + minuteOfHour + "分", Toast.LENGTH_SHORT).show();
                hour = hourOfDay;
                minute = minuteOfHour;
            }
        });
    }

    @OnClick({R.id.backBtn, R.id.submitBTN})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backBtn: {
                ExerciseAddnewDateTime.this.finish();
                break;
            }
            case R.id.submitBTN: {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("year",year);
                bundle.putInt("month",month);
                bundle.putInt("day",day);
                bundle.putInt("hour",hour);
                bundle.putInt("minute",minute);
                intent.putExtras(bundle);
                setResult(CALCULATE,intent);
                ExerciseAddnewDateTime.this.finish();
                break;
            }
        }
    }
}
