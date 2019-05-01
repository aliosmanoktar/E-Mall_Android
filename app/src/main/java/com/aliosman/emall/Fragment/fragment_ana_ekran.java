package com.aliosman.emall.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliosman.emall.Activity.UrunActivity;
import com.aliosman.emall.Adapter.adapter_indirim_urun;
import com.aliosman.emall.Adapter.adapter_populer_urun;
import com.aliosman.emall.Background.ModelDownloadList;
import com.aliosman.emall.Interface.DownloadInterface;
import com.aliosman.emall.Interface.RecylerItemClick;
import com.aliosman.emall.Model.Get.Urun;
import com.aliosman.emall.R;
import com.aliosman.emall.degiskenler;
import java.util.List;

public class fragment_ana_ekran extends Fragment {

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_ana_ekran,container,false);
        RecyclerView indirim_reyclerView = view.findViewById(R.id.ana_ekran_recyclerView_indirim);
        RecyclerView populer_reyclerView = view.findViewById(R.id.ana_ekran_recyclerView_populer);
        populer_reyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        indirim_reyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        new ModelDownloadList<Urun>(Urun[].class, new DownloadInterface() {
            @Override
            public void Complete(List items) {
                populer_reyclerView.setAdapter(new adapter_populer_urun(items,urunClickListener));
            }
        }).execute(degiskenler.PopulerUrunUrl);

        new ModelDownloadList<Urun>(Urun[].class, new DownloadInterface() {
            @Override
            public void Complete(List items) {
                indirim_reyclerView.setAdapter(new adapter_indirim_urun(items,urunClickListener));
            }
        }).execute(degiskenler.IndirimUrunUrl);
        return view;
    }

    private RecylerItemClick<Urun> urunClickListener = item -> {
        Intent i = new Intent(getContext(), UrunActivity.class);
        i.putExtra(degiskenler.UrunShowIDBundleString,item.getID());
        startActivity(i);
    };
}