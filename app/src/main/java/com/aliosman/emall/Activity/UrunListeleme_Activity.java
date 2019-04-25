package com.aliosman.emall.Activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.aliosman.emall.Adapter.adapter_urun_list;
import com.aliosman.emall.Background.ModelDownloadList;
import com.aliosman.emall.Interface.DownloadInterface;
import com.aliosman.emall.Interface.RecylerItemClick;
import com.aliosman.emall.Model.Get.Urun;
import com.aliosman.emall.R;
import com.aliosman.emall.degiskenler;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;

import java.util.List;

public class UrunListeleme_Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String TAG=getClass().getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_listeleme);
        recyclerView = findViewById(R.id.urun_listeleme_layout_recyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        int KategoriID =  getIntent().getIntExtra(degiskenler.KategoriBundleString,0);
        UrunListele(KategoriID);
    }
    private void UrunListele(int KategoriID){
        new ModelDownloadList<Urun>(Urun[].class,downloadInterface).execute(degiskenler.UrunLislemeUrl+KategoriID);
    }

    private void SetAdapter(List<Urun> items){
        adapter_urun_list adapter = new adapter_urun_list(items,ItemClick);
        recyclerView.setAdapter(adapter);
    }

    private RecylerItemClick<Urun> ItemClick = item -> {
        Log.e(TAG, " ItemClick: "+item.getID()+" "+item.getAdi());
    };
    private DownloadInterface<Urun> downloadInterface =new DownloadInterface<Urun>() {
        private Dialog dialog;
        @Override
        public void Start() {
            dialog=new AwesomeProgressDialog(UrunListeleme_Activity.this)
                    .setTitle("Lütfen Bekleyiniz")
                    .setMessage("Kategoriler Listeleniyor")
                    .setCancelable(false)
                    .show();
        }
        @Override
        public void Complete(List<Urun> items) {
            if (dialog!=null)
                dialog.dismiss();
            SetAdapter(items);
        }
    };
}
