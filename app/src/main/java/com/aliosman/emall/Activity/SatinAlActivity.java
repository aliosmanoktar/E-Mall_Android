package com.aliosman.emall.Activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aliosman.emall.Adapter.adapter_stepper_pager;
import com.aliosman.emall.Background.ModelPost;
import com.aliosman.emall.Interface.IStepperListener;
import com.aliosman.emall.Interface.PostInterface;
import com.aliosman.emall.Model.Get.Sepet;
import com.aliosman.emall.Model.Post.Satis;
import com.aliosman.emall.R;
import com.aliosman.emall.degiskenler;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

public class SatinAlActivity extends AppCompatActivity {
    private StepperLayout layout;
    private String TAG = getClass().getName();
    private List<Sepet> urunler=new ArrayList<>();
    private int AdresID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satin_al);
        layout=findViewById(R.id.satinAl_layout_stepperlayout);
        layout.setShowErrorMessageEnabled(true);
        layout.setShowErrorStateEnabled(true);
        layout.setListener(completeListener);
        layout.setCompleteButtonEnabled(true);
        layout.setAdapter(new adapter_stepper_pager(getSupportFragmentManager(), this,listener));
    }

    IStepperListener listener= (position, o) -> {
        if (position==1)
            AdresID=(int)o;
        else if (position==0)
            urunler=(List<Sepet>)o;
    };

    StepperLayout.StepperListener completeListener = new StepperLayout.StepperListener() {
        @Override
        public void onCompleted(View completeButton) {
            new ModelPost(postInterface).execute(degiskenler.SiparisPostUrl,new Satis().setAdresID(AdresID).setItems(urunler).setKullaniciID(48).toString());
        }

        @Override
        public void onError(VerificationError verificationError) {

        }

        @Override
        public void onStepSelected(int newStepPosition) {

        }

        @Override
        public void onReturn() {

        }
    };


    private PostInterface postInterface=new PostInterface() {
        private Dialog dialog;
        @Override
        public void Start() {
            dialog=new AwesomeProgressDialog(SatinAlActivity.this)
                    .setTitle("Bekleyiniz")
                    .setMessage("Satın Alma İşlemi Gerçekleştiriliyor")
                    .setCancelable(false)
                    .show();
        }

        @Override
        public void Post(int code, String value) {
            if (dialog!=null)
                dialog.dismiss();
            if (code==200){
                dialog=new AwesomeSuccessDialog(SatinAlActivity.this)
                        .setTitle("Başarılı")
                        .setMessage(value)
                        .setDoneButtonText("Tamam")
                        .setDoneButtonClick(() -> {
                            dialog.dismiss();
                            SatinAlActivity.super.finish();
                        }).show();
            }else{
                dialog=new AwesomeErrorDialog(SatinAlActivity.this)
                        .setMessage(value)
                        .setTitle("Başarısız")
                        .setButtonText("Tamam")
                        .setErrorButtonClick(()->{
                            dialog.dismiss();
                        }).show();
            }
        }
    };
}