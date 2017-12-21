package com.balu.mycustomcalender.models;

import java.util.Calendar;

/**
 * Created by seken on 19-12-2017.
 */

public class DayModel {
    private String day;
    private String costOfDay;
    private Calendar calendar;
    private boolean isDaySelected;
    private boolean isDisabled;
    private boolean isNeedToShow;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCostOfDay() {
        return costOfDay;
    }

    public void setCostOfDay(String costOfDay) {
        this.costOfDay = costOfDay;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public boolean isDaySelected() {
        return isDaySelected;
    }

    public void setDaySelected(boolean daySelected) {
        isDaySelected = daySelected;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public boolean isNeedToShow() {
        return isNeedToShow;
    }

    public void setNeedToShow(boolean needToShow) {
        isNeedToShow = needToShow;
    }
}
