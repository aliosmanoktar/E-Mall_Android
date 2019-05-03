package com.aliosman.emall.Interface;

import android.support.v7.widget.RecyclerView;

public interface RecylerItemSwipeListener {
    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
}