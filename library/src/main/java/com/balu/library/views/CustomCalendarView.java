package com.balu.library.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.balu.library.R;
import com.balu.library.utils.DateUtils;
import com.balu.library.views.customviews.SquareTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.balu.library.DefaultConstants.DAY_NAME_FORMAT;
import static com.balu.library.DefaultConstants.DEFAULT_FIRST_DAY_OF_WEEK;
import static com.balu.library.DefaultConstants.DEFAULT_SELECTION_TYPE;

/**
 * Created by seken on 26-12-2017.
 */

public class CustomCalendarView extends LinearLayout {
    private int firstDayOfWeek;
    private int selectionType;
    private LinearLayout daysOfWeekTitlesLayout;

    public CustomCalendarView(Context context) {
        super(context);
        initAttributes(context, null, 0, 0);
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs, 0, 0);
    }


    public CustomCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomCalendarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttributes(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initAttributes(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomCalendarView, defStyle, defStyleRes);
        try {
            handleAttributes(typedArray);
        } finally {
            typedArray.recycle();
        }
        setDaysOfWeekTitles();
    }

    /**
     * handling custom attribute values declared through xml.
     *
     * @param typedArray xml declared values getting through typedArray
     */
    private void handleAttributes(TypedArray typedArray) {
        firstDayOfWeek = typedArray.getInteger(R.styleable.CustomCalendarView_firstDayOfTheWeek, DEFAULT_FIRST_DAY_OF_WEEK);
        selectionType = typedArray.getInteger(R.styleable.CustomCalendarView_selectionType, DEFAULT_SELECTION_TYPE);
    }


    /**
     * DaysOfWeekTitles means Sunday,Monday .... Saturday
     */
    private void setDaysOfWeekTitles() {
        if (daysOfWeekTitlesLayout == null) {
            createDaysOfWeekTitle();
        }
    }

    /**
     * Creating Days Of Week titles ie., Sunday,Monday...Saturday
     */
    private void createDaysOfWeekTitle() {
        boolean isTitleAlreadyAdded = daysOfWeekTitlesLayout != null;
        if (!isTitleAlreadyAdded) {
            daysOfWeekTitlesLayout = new LinearLayout(getContext());
            daysOfWeekTitlesLayout.setOrientation(LinearLayout.HORIZONTAL);
            daysOfWeekTitlesLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        } else {
            daysOfWeekTitlesLayout.removeAllViews();
        }

        //creating days of week views
        LinearLayout.LayoutParams textViewParam = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textViewParam.weight = 1;

        for (String title : getWeekdayTitles(firstDayOfWeek)) {
            SquareTextView tvDayTitle = new SquareTextView(getContext());
            tvDayTitle.setText(title);
            tvDayTitle.setLayoutParams(textViewParam);
            tvDayTitle.setGravity(Gravity.CENTER);
            tvDayTitle.setTextColor(applyColor(getContext(), R.color.weekdayTitleColor));
            daysOfWeekTitlesLayout.addView(tvDayTitle);
        }
        if (!isTitleAlreadyAdded) {
            addView(daysOfWeekTitlesLayout);
        }
    }

    public List<String> getWeekdayTitles(int firstDayOfWeek) {
        SimpleDateFormat sdf = new SimpleDateFormat(DAY_NAME_FORMAT, Locale.getDefault());
        final List<String> titles = new ArrayList<>();

        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
        do {
            titles.add(sdf.format(calendar.getTime()));
            DateUtils.addDay(calendar);
        } while (calendar.get(Calendar.DAY_OF_WEEK) != firstDayOfWeek);
        return titles;
    }

    public int applyColor(@NonNull Context context, @ColorRes int colorId) {
        return ContextCompat.getColor(context, colorId);
    }
}
