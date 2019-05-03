package com.aliosman.emall.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.aliosman.emall.Adapter.Dialog.adapter_image_dialog;
import com.aliosman.emall.Adapter.adapter_benzer_urunler;
import com.aliosman.emall.Adapter.adapter_image_swipe;
import com.aliosman.emall.Background.ModelDownloaSingle;
import com.aliosman.emall.Background.ModelDownloadList;
import com.aliosman.emall.Background.ModelPost;
import com.aliosman.emall.Interface.DownloadInterface;
import com.aliosman.emall.Interface.DownloadSingleInterface;
import com.aliosman.emall.Interface.ImageClick;
import com.aliosman.emall.Interface.PostInterface;
import com.aliosman.emall.Interface.RecylerItemClick;
import com.aliosman.emall.Model.Get.Favorite;
import com.aliosman.emall.Model.Get.Sepet;
import com.aliosman.emall.Model.Get.Urun;
import com.aliosman.emall.R;
import com.aliosman.emall.degiskenler;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;
import nl.dionsegijn.steppertouch.StepperTouch;

public class UrunActivity extends AppCompatActivity {
    private AutoScrollViewPager imageList;
    private String TAG=getClass().getName();
    private CircleIndicator indicator;
    private ImageView favorite;
    private StepperTouch UrunAdet;
    private TextView UrunAdi,UrunFiyat,UrunEskiFiyat;
    private int UrunID;
    private Button SepeteEkle,SatinAl;
    private RecyclerView benzerUrunler;
    private SpinKitView yukleniyor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun);
        imageList= findViewById(R.id.urun_layout_imageView);
        indicator=findViewById(R.id.urun_layout_pageIndicator);
        UrunAdi=findViewById(R.id.urun_layout_UrunAdi);
        UrunFiyat = findViewById(R.id.urun_layout_UrunFiyat);
        UrunEskiFiyat=findViewById(R.id.urun_layout_UrunEskiFiyat);
        favorite=findViewById(R.id.urun_layout_favori);
        UrunAdet=findViewById(R.id.urun_layout_UrunAdet);
        SepeteEkle=findViewById(R.id.urun_layout_SepetEkle);
        benzerUrunler=findViewById(R.id.urun_layout_benzerUrunler);
        yukleniyor=findViewById(R.id.urun_layout_yukleniyor);
        benzerUrunler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        UrunAdet.enableSideTap(true);
        UrunAdet.stepper.setMin(0);
        SepeteEkle.setOnClickListener(SepeteEkleClick);
        UrunID = getIntent().getIntExtra(degiskenler.UrunShowIDBundleString,3);
        DownloadUrun(UrunID);
    }

    private void DownloadUrun(int UrunID){
        new ModelDownloaSingle<Urun>(Urun.class,downloadInterface).execute(degiskenler.UrunGerUrunUrl+UrunID);
        new ModelDownloaSingle<Boolean>(Boolean.class,checkUrunInterface).execute(String.format(degiskenler.FavoriteCheck,48,UrunID));
        new ModelDownloadList<Urun>(Urun[].class,downloadBenzerInterface).execute(degiskenler.UrunGetBenzerUrunUrl+UrunID);
    }

    DownloadInterface<Urun> downloadBenzerInterface =new DownloadInterface<Urun>() {
        @Override
        public void Complete(List<Urun> items) {
            int height=benzerUrunler.getHeight();
            yukleniyor.setVisibility(View.GONE);
            benzerUrunler.setAdapter(new adapter_benzer_urunler(items,urunClick,null,height));
        }
    };

    RecylerItemClick<Urun> urunClick = item -> {
        Intent i = new Intent(this, UrunActivity.class);
        i.putExtra(degiskenler.UrunShowIDBundleString,item.getID());
        startActivity(i);
    };
    DownloadSingleInterface<Urun> downloadInterface = new DownloadSingleInterface<Urun>() {
        private Dialog dialog;
        @Override
        public void Start() {

            dialog=new AwesomeProgressDialog(UrunActivity.this)
                    .setTitle("Lütfen Bekleyiniz")
                    .setMessage("Ürün Alınıyor")
                    .setCancelable(false)
                    .show();
        }

        @Override
        public void Complete(Urun item) {
            if (dialog!=null)
                dialog.dismiss();
            SetScreen(item);
        }
    };

    private PostInterface postInterface = new PostInterface() {
        Dialog dialog;
        @Override
        public void Start() {
            dialog=new AwesomeProgressDialog(UrunActivity.this)
                    .setCancelable(false)
                    .setTitle("Bekleyiniz")
                    .setMessage("Ürün Ekleniyor")
                    .show();

        }

        @Override
        public void Post(int code, String value) {
            if (dialog!=null)
                dialog.dismiss();
            if (code==200){
                new AwesomeSuccessDialog(UrunActivity.this)
                        .setTitle("Başarılı")
                        .setMessage(value)
                        .setDoneButtonText("Tamam")
                        .setDoneButtonClick(() -> {
                            dialog.dismiss();
                        }).show();
                favorite.setImageResource(R.drawable.ic_favorite_big);
            }else{
                dialog=new AwesomeErrorDialog(UrunActivity.this)
                        .setMessage(value)
                        .setTitle("Başarısız")
                        .setButtonText("Tamam")
                        .setErrorButtonClick(()->{
                            dialog.dismiss();
                        }).show();
            }
        }
    };

    private View.OnClickListener SepeteEkleClick = v -> {
        if (UrunAdet.stepper.getValue()!=0)
            new ModelPost(postInterface).execute(degiskenler.SepetPostUrl,new Sepet()
                    .setKullaniciID(48).setUrunID(UrunID).setAdet(UrunAdet.stepper.getValue()).toString());
        else
            postInterface.Post(500,"Urun Adeti 0 Olamaz!");
    };

    private View.OnClickListener favoriteClick = v -> {
        new ModelPost(postInterface).execute(degiskenler.FavoritePostUrl,new Favorite()
                .setKullaniciID(48).setUrunID(UrunID).toString());

    };

    DownloadSingleInterface<Boolean> checkUrunInterface = new DownloadSingleInterface<Boolean>() {
        @Override
        public void Complete(Boolean item) {
            if (item){
                favorite.setOnClickListener(null);
                favorite.setImageResource(R.drawable.ic_favorite_big);
            }else {
                favorite.setOnClickListener(favoriteClick);
                favorite.setImageResource(R.drawable.ic_favorite_border);
            }
        }
    };

    private void SetScreen(Urun item){
        UrunAdi.setText(item.getAdi());
        UrunFiyat.setText(item.getFiyat()+" ₺");
        if (item.getOran()!=0){
            UrunEskiFiyat.setVisibility(View.VISIBLE);
            UrunEskiFiyat.setText(item.getEskiFiyat()+" ₺");
        }
        SetImageAdapter(item.getResimler());
    }

    private void SetImageAdapter(List<String> images){
        adapter_image_swipe adapter = new adapter_image_swipe(images,getApplicationContext(),Imageclick);
        imageList.setAdapter(adapter);
        indicator.setViewPager(imageList);
    }

    private ImageClick Imageclick= (position, resimler) -> {
        adapter_image_dialog dialog = new adapter_image_dialog();
        Bundle bundle = new Bundle();
        bundle.putInt(degiskenler.ImageShowImagePosition,position);
        bundle.putStringArrayList(degiskenler.ImageShowImageList,new ArrayList<>(resimler));
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager().beginTransaction(), "ImageShow");
    };

}