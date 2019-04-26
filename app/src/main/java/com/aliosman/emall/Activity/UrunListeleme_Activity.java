package com.aliosman.emall.Activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aliosman.emall.Adapter.Comparator.UrunFiyatArtanComprator;
import com.aliosman.emall.Adapter.Comparator.UrunFiyatAzalanComprator;
import com.aliosman.emall.Adapter.Dialog.adapter_urun_action_dialog;
import com.aliosman.emall.Adapter.Dialog.adapter_urun_filter_dialog;
import com.aliosman.emall.Adapter.adapter_urun_list;
import com.aliosman.emall.Background.ModelDownloadList;
import com.aliosman.emall.Interface.ActionUrunFilterInterface;
import com.aliosman.emall.Interface.DownloadInterface;
import com.aliosman.emall.Interface.RecylerItemClick;
import com.aliosman.emall.Interface.UrunActionInterface;
import com.aliosman.emall.Interface.UrunFilterType;
import com.aliosman.emall.Model.Get.Urun;
import com.aliosman.emall.R;
import com.aliosman.emall.degiskenler;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;

import java.util.Collections;
import java.util.List;

public class UrunListeleme_Activity extends AppCompatActivity {

    private List<Urun> uruns;
    private RecyclerView recyclerView;
    private String TAG=getClass().getName();
    private TextView txt_adet;
    private ImageView img_fiter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_listeleme);
        recyclerView = findViewById(R.id.urun_listeleme_layout_recyler);
        txt_adet=findViewById(R.id.urun_listeleme_layout_UrunAdet);
        img_fiter=findViewById(R.id.urun_listeleme_layout_filter);
        img_fiter.setOnClickListener(filterClickListener);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(),2));
        int KategoriID =  getIntent().getIntExtra(degiskenler.KategoriBundleString,0);
        UrunListele(KategoriID);
    }

    private void UrunListele(int KategoriID){
        new ModelDownloadList<Urun>(Urun[].class,downloadInterface).execute(degiskenler.UrunLislemeUrl+KategoriID);
    }

    private void SetAdapter(List<Urun> items){
        txt_adet.setText(items.size()+" Adet Ürün Listelendi");
        adapter_urun_list adapter = new adapter_urun_list(items,ItemClick,ItemLongClick);
        recyclerView.setAdapter(adapter);
    }
    private UrunActionInterface urunAction = (Sepet, item) -> {
        Log.e(TAG, "UrunAction: Sepet="+Sepet+" item=> "+item.getID()+" "+item.getAdi() );
    };

    private RecylerItemClick<Urun> ItemClick = item -> {
        Log.e(TAG, " ItemClick: "+item.getID()+" "+item.getAdi());
    };

    private RecylerItemClick<Urun> ItemLongClick = item -> {
        Log.e(TAG, "ItemLongClick: "+item.getID()+" "+item.getAdi());
        new adapter_urun_action_dialog(urunAction,this,item).Show();
    };

    private ActionUrunFilterInterface SetFilter = type -> {
        Collections.sort(uruns, type == UrunFilterType.AzalanFiyat ? new UrunFiyatAzalanComprator() : new UrunFiyatArtanComprator());
        SetAdapter(uruns);
    };
    private View.OnClickListener filterClickListener = v -> {
        new adapter_urun_filter_dialog(this,SetFilter).Show();
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
            uruns=items;
            SetAdapter(items);
        }
    };
}
