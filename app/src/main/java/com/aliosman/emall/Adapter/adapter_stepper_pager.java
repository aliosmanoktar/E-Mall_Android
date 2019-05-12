package com.aliosman.emall.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.aliosman.emall.Fragment.fragment_satis_adres_list;
import com.aliosman.emall.Fragment.fragment_satis_urun_list;
import com.aliosman.emall.Interface.IStepperListener;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class adapter_stepper_pager extends AbstractFragmentStepAdapter {
    private IStepperListener listener;
    public adapter_stepper_pager(@NonNull FragmentManager fm, @NonNull Context context,IStepperListener listener) {
        super(fm, context);
        this.listener=listener;
    }

    @Override
    public Step createStep(int position){
        if (position==0)
            return fragment_satis_urun_list.newInstance(listener);
        else
            return fragment_satis_adres_list.newInstance(listener);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(int position) {
        StepViewModel.Builder b = new StepViewModel.Builder(context);
        if (position==1)
            b.setEndButtonLabel("Tamamla");
        return b.create();
    }
}
