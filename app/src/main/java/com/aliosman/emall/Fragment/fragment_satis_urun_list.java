package com.aliosman.emall.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.aliosman.emall.Activity.SepetActivity;
import com.aliosman.emall.Adapter.Swipe.RecylerItemSwipeHelper;
import com.aliosman.emall.Adapter.adapter_sepet;
import com.aliosman.emall.Background.ModelDelete;
import com.aliosman.emall.Background.ModelDownloadList;
import com.aliosman.emall.Interface.DownloadInterface;
import com.aliosman.emall.Interface.IStepperListener;
import com.aliosman.emall.Interface.RecylerItemSwipeListener;
import com.aliosman.emall.Model.Get.Sepet;
import com.aliosman.emall.Model.Get.Urun;
import com.aliosman.emall.R;
import com.aliosman.emall.degiskenler;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

public class fragment_satis_urun_list extends Fragment implements Step {
    private static IStepperListener listener;
    private RecyclerView recyclerView;
    private adapter_sepet adapter;
    private int KullaniciID= 48;
    private List<Sepet> sepets;
    private String TAG  = getClass().getName();
    private RelativeLayout rootView;
    public static fragment_satis_urun_list newInstance(IStepperListener l){
        listener=l;
        return new fragment_satis_urun_list();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_satis_urun_list,container,false);
        recyclerView=view.findViewById(R.id.layout_satis_urun_list_recycler);
        rootView=view.findViewById(R.id.layout_satis_urun_list_rootView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemTouchHelper.SimpleCallback swipeHelper = new RecylerItemSwipeHelper(0,ItemTouchHelper.LEFT,swipeListener);
        new ItemTouchHelper(swipeHelper).attachToRecyclerView(recyclerView);
        return view;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        for (Sepet item : sepets)
            Log.e(TAG, "verifyStep: "+item.getUrun().getAdi());
        listener.OnNext(0,sepets);
        return null;
    }

    @Override
    public void onSelected() {
        new ModelDownloadList<Urun>(Sepet[].class,downloadInterface).execute(degiskenler.SepetGetUrl+KullaniciID);
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }


    private DownloadInterface<Sepet> downloadInterface=new DownloadInterface<Sepet>() {
        private Dialog dialog;
        @Override
        public void Start() {
            dialog=new AwesomeProgressDialog(getActivity())
                    .setTitle("Lütfen Bekleyiniz")
                    .setMessage("Sepet Listeleniyor")
                    .setCancelable(false)
                    .show();
        }

        @Override
        public void Complete(List<Sepet> items) {
            if (dialog!=null)
                dialog.dismiss();

            SetAdapter(items);
        }
    };
    private void SetAdapter(List<Sepet> sepets){
        sepets = new ArrayList<>(sepets);
        this.sepets=sepets;
        adapter = new adapter_sepet(sepets,null);
        recyclerView.setAdapter(adapter);
    }
    RecylerItemSwipeListener swipeListener = (viewHolder, direction, position) -> {
        Sepet item = (Sepet)viewHolder.itemView.getTag();
        adapter.removeItem(position);
        Snackbar snackbar= Snackbar.make(rootView,"Ürün Silindi",Snackbar.LENGTH_LONG);
        snackbar.setAction("Geri Al", v -> {
            adapter.restoreItem(item,position);
        });
        snackbar.setActionTextColor(getResources().getColor(R.color.colorRed));
        snackbar.show();
    };
}