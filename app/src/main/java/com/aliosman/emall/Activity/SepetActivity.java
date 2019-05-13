package com.aliosman.emall.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.aliosman.emall.Adapter.Swipe.RecylerItemSwipeHelper;
import com.aliosman.emall.Adapter.adapter_sepet;
import com.aliosman.emall.Background.ModelDelete;
import com.aliosman.emall.Background.ModelDownloadList;
import com.aliosman.emall.Interface.DownloadInterface;
import com.aliosman.emall.Interface.RecylerItemClick;
import com.aliosman.emall.Interface.RecylerItemSwipeListener;
import com.aliosman.emall.Model.Get.Sepet;
import com.aliosman.emall.Model.Kullanici;
import com.aliosman.emall.Preferences;
import com.aliosman.emall.R;
import com.aliosman.emall.degiskenler;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeInfoDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import java.util.ArrayList;
import java.util.List;

public class SepetActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String TAG=getClass().getName();
    private View RootView;
    private adapter_sepet adapter;
    private Kullanici kullanici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sepet);
        kullanici= Preferences.GetKullanici(getBaseContext());
        RootView=findViewById(R.id.sepet_layout_RootView);
        recyclerView=findViewById(R.id.sepet_layout_recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        ItemTouchHelper.SimpleCallback swipeHelper = new RecylerItemSwipeHelper(0,ItemTouchHelper.LEFT,swipeListener);
        new ItemTouchHelper(swipeHelper).attachToRecyclerView(recyclerView);
        Button clearAll = findViewById(R.id.sepet_layout_TumunuTemizle);
        Button SatinAl = findViewById(R.id.sepet_layout_satinAl);
        if (kullanici!=null) {
            SatinAl.setOnClickListener(satinAlClick);
            clearAll.setOnClickListener(ClearAllClick);
            new ModelDownloadList<Sepet>(Sepet[].class, downloadInterface).execute(degiskenler.SepetGetUrl + kullanici.getID());
        }else ShowNotLoginDialog();
    }

    private void ShowNotLoginDialog(){
        new AwesomeInfoDialog(this)
                .setPositiveButtonText("Geç")
                .setNegativeButtonText("Giriş Yap")
                .setNegativeButtonTextColor(R.color.colorWhite)
                .setPositiveButtonbackgroundColor(R.color.colorRed)
                .setNegativeButtonbackgroundColor(R.color.colorGreen)
                .setTitle("Uyarı")
                .setMessage("Sepetim Özelliğini Kullanabilmeniz için giriş yapmanız gereklidir")
                .setPositiveButtonClick(() -> {

                })
                .setNegativeButtonClick(() -> {
                    StartLoginActivity();
                }).show();

    }

    private void StartLoginActivity(){
        Intent i = new Intent(getBaseContext(),Login_Activity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private View.OnClickListener ClearAllClick = v -> {
        new AwesomeInfoDialog(this)
                .setPositiveButtonText("Evet")
                .setNegativeButtonText("Hayır")
                .setNegativeButtonTextColor(R.color.colorWhite)
                .setPositiveButtonbackgroundColor(R.color.colorRed)
                .setNegativeButtonbackgroundColor(R.color.colorGreen)
                .setTitle("Uyarı")
                .setMessage("Tüm Sepet Ürünlerini Silmek İstediğinizden Eminmisiniz?")
                .setPositiveButtonClick(() -> {
                    new ModelDelete().execute(degiskenler.SepetClearAllUrl+48);
                    SetAdapter(new ArrayList<>());
                })
                .setNegativeButtonClick(() -> {

                })
                .show();
    };

    private RecylerItemSwipeListener swipeListener = (viewHolder, direction, position) -> {
        Sepet item = (Sepet)viewHolder.itemView.getTag();
        adapter.removeItem(position);
        Snackbar snackbar= Snackbar.make(RootView,"Ürün Silindi",Snackbar.LENGTH_LONG);
        snackbar.addCallback(new Snackbar.Callback(){
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                Log.e(TAG, "onDismissed: "+event);
                if (event==Snackbar.Callback.DISMISS_EVENT_TIMEOUT){
                    new ModelDelete().execute(degiskenler.SepetDeleteIDUrl+item.getID());
                }
            }
        });
        snackbar.setAction("Geri Al", v -> {
            adapter.restoreItem(item,position);
        });
        snackbar.setActionTextColor(getResources().getColor(R.color.colorRed));
        snackbar.show();
    };

    private DownloadInterface<Sepet> downloadInterface=new DownloadInterface<Sepet>() {
        private Dialog dialog;
        @Override
        public void Start() {
            dialog=new AwesomeProgressDialog(SepetActivity.this)
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
        adapter = new adapter_sepet(sepets,SepetteItemClick);
        recyclerView.setAdapter(adapter);
    }

    private RecylerItemClick<Sepet> SepetteItemClick = item -> {
        Intent i = new Intent(getApplicationContext(),UrunActivity.class);
        i.putExtra(degiskenler.UrunShowIDBundleString,item.getUrunID());
        startActivity(i);
    };

    private View.OnClickListener satinAlClick = v -> {
      startActivity(new Intent(getBaseContext(),SatinAlActivity.class));
    };
}
