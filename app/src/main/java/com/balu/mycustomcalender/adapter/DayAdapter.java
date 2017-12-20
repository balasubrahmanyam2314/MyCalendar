package com.balu.mycustomcalender.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balu.mycustomcalender.R;
import com.balu.mycustomcalender.interfaces.OnDayClickListener;
import com.balu.mycustomcalender.models.DayModel;

import java.util.List;

/**
 * Created by seken on 19-12-2017.
 */

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.MonthViewHolder> {

    private Context context;
    private List<DayModel> dayModelList;
    private OnDayClickListener dayClickListener;

    public DayAdapter(Context context, List<DayModel> dayModelList) {
        this.context = context;
        this.dayModelList = dayModelList;
        setHasStableIds(false);
    }

    public void setDayClickListener(OnDayClickListener dayClickListener) {
        this.dayClickListener = dayClickListener;
    }

    @Override
    public MonthViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.day_item, parent, false);
        return new MonthViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MonthViewHolder holder, final int position) {
        final DayModel dayModel = dayModelList.get(position);
        holder.day.setText(dayModel.getDay());
        if (dayModel.isDaySelected()) {
            holder.itemView.setBackgroundColor(applyColor(R.color.green));
        } else {
            holder.itemView.setBackgroundColor(applyColor(R.color.transparent));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dayClickListener != null) {
                    dayModel.setDaySelected(!dayModel.isDaySelected());
                    dayClickListener.onDayClick(dayModel);
                    notifyItemChanged(position);
                }
            }
        });
    }

    private int applyColor(@ColorRes int colorId) {
        return ContextCompat.getColor(context, colorId);
    }

    @Override
    public int getItemCount() {
        return dayModelList != null ? dayModelList.size() : 0;
    }

    public class MonthViewHolder extends RecyclerView.ViewHolder {
        private TextView day;

        public MonthViewHolder(View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.day_Tv);
        }


    }
}
