package com.aliosman.emall.Adapter.Swipe;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.aliosman.emall.R;

public class SwipeViewHolderBase extends RecyclerView.ViewHolder {
    public LinearLayout frg_view;
    public SwipeViewHolderBase(View itemView) {
        super(itemView);
        frg_view=itemView.findViewById(R.id.SwipeItem_view);
    }
}
