package com.aliosman.emall.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aliosman.emall.Adapter.Comparator.KategoriComprator;
import com.aliosman.emall.Adapter.adapter_kategori_list;
import com.aliosman.emall.Background.ModelDownloadList;
import com.aliosman.emall.Interface.DownloadInterface;
import com.aliosman.emall.Interface.RecylerItemClick;
import com.aliosman.emall.Model.Get.Kategori;
import com.aliosman.emall.R;
import com.aliosman.emall.degiskenler;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class fragment_kategoriler extends Fragment {

    private String TAG = getClass().getName();
    private RecyclerView recyclerView;
    private List<Kategori> items=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_kategoriler,container,false);
        recyclerView=view.findViewById(R.id.kategori_layout_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        if (items==null){
            ReplaceAdapter(1);
        }else{
            setAdapter(items);
        }
        return view;
    }

    private void ReplaceAdapter(int UstID){
        new ModelDownloadList<Kategori>(Kategori[].class,downloadInterface).execute(degiskenler.KategoriListUrl+UstID);
    }

    private void ReplaceAdapterBack(int ID){
        new ModelDownloadList<Kategori>(Kategori[].class,downloadInterface).execute(degiskenler.KategoriListBackUrl+ID);
    }

    private RecylerItemClick<Kategori> kategoriClick= item -> {
        if (item.isAltKategori())
            ReplaceAdapter(item.getID());
        else if (item.getID()==-1)
            ReplaceAdapterBack(item.getUstID());
    };

    private void setAdapter(List<Kategori> items){
        this.items=items;
        recyclerView.setAdapter(new adapter_kategori_list(items,kategoriClick));
    }

    private DownloadInterface<Kategori> downloadInterface=new DownloadInterface<Kategori>() {
        private Dialog dialog;
        @Override
        public void Start() {
            dialog=new AwesomeProgressDialog(getActivity())
                    .setTitle("Lütfen Bekleyiniz")
                    .setMessage("Kategoriler Listeleniyor")
                    .setCancelable(false)
                    .show();
        }

        @Override
        public void Complete(List<Kategori> items) {
            if (dialog!=null)
                dialog.dismiss();
            items=new ArrayList<>(items);
            Collections.sort(items,new KategoriComprator());
            items.add(0,new Kategori()
                    .setAdi("Hepsini Gör")
                    .setID(0)
                    .setAltKategori(false));
            if (items.get(1).getUstID()!=0)
                items.add(0,new Kategori()
                        .setAdi("Geri")
                        .setID(-1)
                        .setUstID(items.get(1).getUstID())
                        .setAltKategori(false));
            setAdapter(items);
        }
    };
}