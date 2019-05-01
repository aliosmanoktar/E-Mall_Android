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
import com.aliosman.emall.Adapter.Swipe.ReyclerItemSwipeHelper;
import com.aliosman.emall.Adapter.adapter_favorite;
import com.aliosman.emall.Background.ModelDelete;
import com.aliosman.emall.Background.ModelDownloadList;
import com.aliosman.emall.Interface.DownloadInterface;
import com.aliosman.emall.Interface.RecylerItemClick;
import com.aliosman.emall.Interface.ReyclerItemSwipeListener;
import com.aliosman.emall.Model.Get.Favorite;
import com.aliosman.emall.R;
import com.aliosman.emall.degiskenler;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeInfoDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import java.util.ArrayList;
import java.util.List;

public class FavorilerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String TAG=getClass().getName();
    private View RootView;
    private adapter_favorite adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoriler);
        RootView=findViewById(R.id.favoriler_layout_RootView);
        recyclerView=findViewById(R.id.favoriler_layout_recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        ItemTouchHelper.SimpleCallback swipeHelper = new ReyclerItemSwipeHelper(0,ItemTouchHelper.LEFT,swipeListener);
        new ItemTouchHelper(swipeHelper).attachToRecyclerView(recyclerView);
        Button clearAll = findViewById(R.id.favoriler_layout_TumunuTemizle);
        clearAll.setOnClickListener(ClearAllClick);
        new ModelDownloadList<Favorite>(Favorite[].class,downloadInterface).execute(degiskenler.FavoriteGetUrl+48);
    }

    private View.OnClickListener ClearAllClick = v -> {
        new AwesomeInfoDialog(this)
                .setPositiveButtonText("Evet")
                .setNegativeButtonText("Hayır")
                .setNegativeButtonTextColor(R.color.colorWhite)
                .setPositiveButtonbackgroundColor(R.color.colorRed)
                .setNegativeButtonbackgroundColor(R.color.colorGreen)
                .setTitle("Uyarı")
                .setMessage("Tüm Favori Ürünlerini Silmek İstediğinizden Eminmisiniz?")
                .setPositiveButtonClick(() -> {
                    new ModelDelete().execute(degiskenler.FavoriteClearAllUrl+48);
                    SetAdapter(new ArrayList<>());
                })
                .setNegativeButtonClick(() -> {

                })
                .show();
    };

    private DownloadInterface<Favorite> downloadInterface=new DownloadInterface<Favorite>() {
        private Dialog dialog;
        @Override
        public void Start() {
            dialog=new AwesomeProgressDialog(FavorilerActivity.this)
                    .setTitle("Lütfen Bekleyiniz")
                    .setMessage("Favoriler Listeleniyor")
                    .setCancelable(false)
                    .show();
        }

        @Override
        public void Complete(List<Favorite> items) {
            if (dialog!=null)
                dialog.dismiss();
            SetAdapter(items);
        }
    };

    private ReyclerItemSwipeListener swipeListener = (viewHolder, direction, position) -> {
        Favorite item = (Favorite)viewHolder.itemView.getTag();
        adapter.removeItem(position);
        Snackbar snackbar= Snackbar.make(RootView,"Ürün Silindi",Snackbar.LENGTH_LONG);
        snackbar.addCallback(new Snackbar.Callback(){
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                Log.e(TAG, "onDismissed: "+event);
                if (event==Snackbar.Callback.DISMISS_EVENT_TIMEOUT){
                    new ModelDelete().execute(degiskenler.FavoriteDeleteIDUrl+item.getID());
                }
            }
        });
        snackbar.setAction("Geri Al", v -> {
            adapter.restoreItem(item,position);
        });
        snackbar.setActionTextColor(getResources().getColor(R.color.colorRed));
        snackbar.show();
    };

    private void SetAdapter(List<Favorite> favorites){
        favorites=new ArrayList<>(favorites);
        adapter=new adapter_favorite(favorites,favoriteItemClick);
        recyclerView.setAdapter(adapter);
    }
    private RecylerItemClick<Favorite> favoriteItemClick = item -> {
        Intent i = new Intent(getApplicationContext(),UrunActivity.class);
        i.putExtra(degiskenler.UrunShowIDBundleString,item.getUrunID());
        startActivity(i);
    };
}