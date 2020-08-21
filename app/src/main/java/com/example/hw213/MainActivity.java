package com.example.hw213;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Button mChooseStartDate;
    private Button mChooseEndDate;
    private CalendarView mStartDateCalendar;
    private CalendarView mEndDateCalendar;
    private Button mBtnOK;

    private long mStartDate;
    private String mStartDateTxt;
    private long mEndDate;
    private String mEndDateTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStartDate > mEndDate) {
                    Toast.makeText(MainActivity.this, getResources().getText(R.string.error), Toast.LENGTH_SHORT).show();
                    mChooseStartDate.setText(getResources().getText(R.string.start_date));
                    mChooseEndDate.setText(getResources().getText(R.string.end_date));
                } else {
                    Toast.makeText(MainActivity.this, getResources().getText(R.string.start) + mStartDateTxt + getResources().getText(R.string.end) + mEndDateTxt, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mChooseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStartDateCalendar.setVisibility(View.VISIBLE);
                mEndDateCalendar.setVisibility(View.GONE);
            }
        });

        mChooseEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEndDateCalendar.setVisibility(View.VISIBLE);
                mStartDateCalendar.setVisibility(View.GONE);
            }
        });


        mStartDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                mStartDateTxt = year + "-" + month + "-" + dayOfMonth;
                mChooseStartDate.setText(String.format("%s  %s", getString(R.string.start_date), mStartDateTxt));
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(year, month, dayOfMonth);
                mStartDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });

        mEndDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                mEndDateTxt = year + "-" + month + "-" + dayOfMonth;
                mChooseEndDate.setText(String.format("%s  %s", getString(R.string.end_date), mEndDateTxt));
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(year, month, dayOfMonth);
                mEndDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });

    }

    private void initViews() {
        mChooseStartDate = findViewById(R.id.chooseStartDate);
        mChooseEndDate = findViewById(R.id.chooseEndDate);
        mStartDateCalendar = findViewById(R.id.startDateCalendar);
        mEndDateCalendar = findViewById(R.id.endDateCalendar);
        mBtnOK = findViewById(R.id.btnOK);
        mStartDateCalendar.setVisibility(View.GONE);
        mEndDateCalendar.setVisibility(View.GONE);
    }
}