package com.balu.mycustomcalender.models;

import java.util.List;

/**
 * Created by seken on 19-12-2017.
 */

public class MonthModel {
    private List<DayModel> dayModelList;
    private String monthName;

    public List<DayModel> getDayModelList() {
        return dayModelList;
    }

    public void setDayModelList(List<DayModel> dayModelList) {
        this.dayModelList = dayModelList;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }
}
