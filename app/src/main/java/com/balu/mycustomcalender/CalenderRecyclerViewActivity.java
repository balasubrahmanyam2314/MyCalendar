package com.balu.mycustomcalender;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.widget.Toast;

import com.balu.mycustomcalender.adapter.MonthAdapter;
import com.balu.mycustomcalender.interfaces.OnDayClickListener;
import com.balu.mycustomcalender.models.DayModel;
import com.balu.mycustomcalender.models.MonthModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by seken on 19-12-2017.
 */

public class CalenderRecyclerViewActivity extends AppCompatActivity implements OnDayClickListener {

    private RecyclerView calenderRecyclerView;
    private LinearLayoutManager layoutManager;
    private MonthAdapter monthAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_recyclerrview);
        calenderRecyclerView = (RecyclerView) findViewById(R.id.calender_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        calenderRecyclerView.setLayoutManager(layoutManager);
        monthAdapter = new MonthAdapter(this, getMonthsList(), this);
        calenderRecyclerView.setAdapter(monthAdapter);
    }

    private List<MonthModel> getMonthsList() {
        Calendar calendar = Calendar.getInstance();
        List<MonthModel> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            MonthModel monthModel = new MonthModel();
            monthModel.setMonthName(convertDateToString("MMMM yyyy", calendar));
            monthModel.setDayModelList(getDays(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)));
            list.add(monthModel);
            calendar.add(Calendar.MONTH, 1);
        }
        return list;
    }

    private List<DayModel> getDays(int year, int month) {
        MonthDisplayHelper helper = new MonthDisplayHelper(year, month);
        List<DayModel> list = new ArrayList<>();
        for (int row = 0; row < getNumOfRowsForTheMonth(year, month); row++) {
            int[] days = helper.getDigitsForRow(row);
            Log.d("xxxx", String.format("%s row days   --->", row) + Arrays.toString(days));
            for (int day : days) {
                if (helper.isWithinCurrentMonth(helper.getRowOf(day), helper.getColumnOf(day))) {
                    DayModel dayModel = new DayModel();
                    dayModel.setDay(String.valueOf(day));
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(helper.getYear(), helper.getMonth(), day);
                    dayModel.setCalendar(calendar);
                    list.add(dayModel);
                }
            }
        }
        return list;
    }

    private int getNumOfRowsForTheMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        MonthDisplayHelper displayHelper = new MonthDisplayHelper(year, month);
        return displayHelper.getRowOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH)) + 1;
    }

    public String convertDateToString(String pattern, Calendar calendar) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(calendar.getTime());
    }

    @Override
    public void onDayClick(DayModel dayModel) {
        Toast.makeText(this, convertDateToString("dd-MM-yyyy", dayModel.getCalendar()), Toast.LENGTH_SHORT).show();
    }


}
