package com.balu.mycustomcalender.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balu.mycustomcalender.CalenderRecyclerViewActivity;
import com.balu.mycustomcalender.ItemOffsetDecoration;
import com.balu.mycustomcalender.R;
import com.balu.mycustomcalender.interfaces.OnDayClickListener;
import com.balu.mycustomcalender.models.MonthModel;

import java.util.List;

/**
 * Created by seken on 19-12-2017.
 */

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.CalenderViewHolder> {

    private Context context;
    private List<MonthModel> monthModelList;
    private OnDayClickListener dayClickListener;

    public MonthAdapter(Context context, List<MonthModel> monthModelList, OnDayClickListener dayClickListener) {
        this.context = context;
        this.monthModelList = monthModelList;
        this.dayClickListener = dayClickListener;
        setHasStableIds(true);
    }


    @Override
    public CalenderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.month_item, parent, false);
        return new CalenderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CalenderViewHolder holder, int position) {
        holder.monthName.setText(monthModelList.get(position).getMonthName());
        DayAdapter dayAdapter = new DayAdapter(context, monthModelList.get(position).getDayModelList());
        dayAdapter.setDayClickListener(dayClickListener);
        holder.monthRecyclerView.setAdapter(dayAdapter);
    }

    @Override
    public int getItemCount() {
        return monthModelList != null ? monthModelList.size() : 0;
    }


    public class CalenderViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView monthRecyclerView;
        private GridLayoutManager gridLayoutManager;
        private TextView monthName;

        public CalenderViewHolder(View itemView) {
            super(itemView);
            monthRecyclerView = (RecyclerView) itemView.findViewById(R.id.month_recyclerview);
            monthName = (TextView) itemView.findViewById(R.id.month_name_TV);
            gridLayoutManager = new GridLayoutManager(context, 7);
            monthRecyclerView.setLayoutManager(gridLayoutManager);
            ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(context, R.dimen.item_offset);
            monthRecyclerView.addItemDecoration(itemDecoration);
            monthRecyclerView.setNestedScrollingEnabled(false);
        }
    }
}
