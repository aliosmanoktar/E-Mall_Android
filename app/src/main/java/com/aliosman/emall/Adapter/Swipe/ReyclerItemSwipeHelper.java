package com.aliosman.emall.Adapter.Swipe;

import android.graphics.Canvas;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.aliosman.emall.Interface.ReyclerItemSwipeListener;

public class ReyclerItemSwipeHelper extends ItemTouchHelper.SimpleCallback {

    private String TAG = getClass().getName();
    private ReyclerItemSwipeListener listener;

    public ReyclerItemSwipeHelper(int dragDirs, int swipeDirs,ReyclerItemSwipeListener listener) {
        super(dragDirs, swipeDirs);
        this.listener=listener;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView,RecyclerView.ViewHolder viewHolder,RecyclerView.ViewHolder viewHolder1) {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        if (listener!=null){
            listener.onSwiped(viewHolder,i,viewHolder.getAdapterPosition());
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                   clearView(null,viewHolder);
                }
            }, 500);
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView,RecyclerView.ViewHolder viewHolder) {
        View frg=((SwipeViewHolderBase)viewHolder).frg_view;
        getDefaultUIUtil().clearView(frg);
    }

    @Override
    public void onChildDraw(Canvas c,RecyclerView recyclerView,RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View frg=((SwipeViewHolderBase)viewHolder).frg_view;
        getDefaultUIUtil().onDraw(c,recyclerView,frg,dX/4,dY,actionState,isCurrentlyActive);
    }

    @Override
    public void onChildDrawOver(Canvas c,RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View frg=((SwipeViewHolderBase)viewHolder).frg_view;
        getDefaultUIUtil().onDrawOver(c,recyclerView,frg,dX,dY,actionState,isCurrentlyActive);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder!=null){
            View frg=((SwipeViewHolderBase)viewHolder).frg_view;
            getDefaultUIUtil().onSelected(frg);
        }
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }
}