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
            monthModel.setDayModelList(getDays(calendar));
            list.add(monthModel);
            calendar.add(Calendar.MONTH, 1);
        }
        return list;
    }

    private List<DayModel> getDays(Calendar calendar) {
        MonthDisplayHelper helper = new MonthDisplayHelper(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
        List<DayModel> list = new ArrayList<>();
        boolean isDayBetweenMonth = false;

        Log.d("xxxx", String.format("first day of month %s", helper.getFirstDayOfMonth()));
        Log.d("xxxx", String.format("week start day %s", helper.getWeekStartDay()));
        Log.d("xxxx", String.format("No of days in a month %s", helper.getNumberOfDaysInMonth()));
        // december month first day row and column [0,5]
        Log.d("xxxx", String.format("month first day [%s,%s]", helper.getRowOf(helper.getWeekStartDay()), helper.getColumnOf(helper.getWeekStartDay())));
        // december month last day row and column [5,0]
        Log.d("xxxx", String.format("month last day [%s,%s]", helper.getRowOf(helper.getNumberOfDaysInMonth()), helper.getColumnOf(helper.getNumberOfDaysInMonth())));


        for (int row = 0; row <= helper.getRowOf(helper.getNumberOfDaysInMonth()); row++) {
            int[] days = helper.getDigitsForRow(row);
            Log.d("xxxx", String.format("%s row days   --->", row) + Arrays.toString(days));
            int column = 0;
            for (int day : days) {
                DayModel dayModel = new DayModel();
                if (helper.isWithinCurrentMonth(row, column)) {
                    dayModel.setDay(String.valueOf(day));
                    dayModel.setNeedToShow(true);
                    Calendar cal = Calendar.getInstance();
                    cal.set(helper.getYear(), helper.getMonth(), day);
                    dayModel.setCalendar(cal);
                }
                list.add(dayModel);
                column++;
            }
        }
        return list;
    }

    public String convertDateToString(String pattern, Calendar calendar) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(calendar.getTime());
    }

    @Override
    public void onDayClick(DayModel dayModel) {
        if (dayModel.getCalendar()!=null)
        Toast.makeText(this, convertDateToString("dd-MM-yyyy", dayModel.getCalendar()), Toast.LENGTH_SHORT).show();
    }


}
