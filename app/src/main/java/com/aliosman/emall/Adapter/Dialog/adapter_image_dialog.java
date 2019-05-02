package com.aliosman.emall.Adapter.Dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aliosman.emall.Adapter.adapter_dialog_image_swipe;
import com.aliosman.emall.Interface.ImageCloseClick;
import com.aliosman.emall.R;
import com.aliosman.emall.degiskenler;
import java.util.ArrayList;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;

public class adapter_image_dialog extends DialogFragment {

    private String TAG = getClass().getName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullScreenDialogStyle);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.layout_dialog_urun_image,container,false);
        AutoScrollViewPager viewPager=view.findViewById(R.id.urun_image_dialog_layout_viewpager);
        CircleIndicator indicator=view.findViewById(R.id.urun_image_dialog_layout_pageIndicator);
        ArrayList<String> resimler = getArguments().getStringArrayList(degiskenler.ImageShowImageList);
        int position=getArguments().getInt(degiskenler.ImageShowImagePosition);
        viewPager.setAdapter(new adapter_dialog_image_swipe(resimler,getContext(),click));
        viewPager.setCurrentItem(position);
        indicator.setViewPager(viewPager);
        return view;
    }
    ImageCloseClick click= () -> {
       this.dismiss();
    };
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.setCanceledOnTouchOutside(true);
        }
    }
}
