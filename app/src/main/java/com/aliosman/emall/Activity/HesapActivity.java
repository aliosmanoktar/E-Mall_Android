package com.aliosman.emall.Activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.aliosman.emall.Background.ModelPost;
import com.aliosman.emall.Interface.PostInterface;
import com.aliosman.emall.Model.Kullanici;
import com.aliosman.emall.Model.Post.Register;
import com.aliosman.emall.Preferences;
import com.aliosman.emall.R;
import com.aliosman.emall.degiskenler;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

public class HesapActivity extends AppCompatActivity {

    private MaterialEditText Edit_Ad,Edit_Soyad,Edit_KullanciAdi,Edit_Eposta,Edit_Telefon,Edit_Sifre,Edit_SifreTekrar;
    private Button Guncelle;
    private Kullanici k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hesap);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Edit_Ad = findViewById(R.id.hesap_layout_Ad);
        Edit_Soyad =  findViewById(R.id.hesap_layout_Soyad);
        Edit_KullanciAdi = findViewById(R.id.hesap_layout_KullaniciAdi);
        Edit_Eposta = findViewById(R.id.hesap_layout_Eposta);
        Edit_Telefon = findViewById(R.id.hesap_layout_Telefon);
        Edit_Sifre = findViewById(R.id.hesap_layout_Sifre);
        Edit_SifreTekrar = findViewById(R.id.hesap_layout_SifreTekrar);
        Guncelle=findViewById(R.id.hesap_layout_Guncelle);
        Guncelle.setOnClickListener(UpdateListener);
        UpdateBilgi();

    }

    private void UpdateBilgi(){
        k = Preferences.GetKullanici(getBaseContext());
        Edit_Sifre.setText("");
        Edit_SifreTekrar.setText("");
        Edit_Ad.setText(k.getAdi());
        Edit_Soyad.setText(k.getSoyadi());
        Edit_Eposta.setText(k.getEmail());
        Edit_KullanciAdi.setText(k.getKullaniciAdi());
        Edit_Telefon.setText(k.getTelefon());
    }

    private PostInterface postInterface=new PostInterface() {
        private Dialog dialog;
        @Override
        public void Start() {
            dialog=new AwesomeProgressDialog(HesapActivity.this)
                    .setTitle("Bekleyiniz")
                    .setMessage("Bilgiler Kontrol Ediliyor")
                    .setCancelable(false)
                    .show();
        }

        @Override
        public void Post(int code, String value) {
            if (dialog!=null)
                dialog.dismiss();
            if (code==200){
                Preferences.SaveKullanici(getBaseContext(),value);
                UpdateBilgi();
                dialog=new AwesomeSuccessDialog(HesapActivity.this)
                        .setTitle("Başarılı")
                        .setMessage("Veriler Güncellendi")
                        .setDoneButtonText("Tamam")
                        .setDoneButtonClick(() -> {
                            dialog.dismiss();
                        }).show();
            }else{
                dialog=new AwesomeErrorDialog(HesapActivity.this)
                        .setMessage(value)
                        .setTitle("Başarısız")
                        .setButtonText("Tamam")
                        .setErrorButtonClick(()->{
                            dialog.dismiss();
                        }).show();
            }
        }
    };

    private View.OnClickListener UpdateListener = v -> {
        if (!(Edit_Sifre.getText().toString().isEmpty() && Edit_SifreTekrar.getText().toString().isEmpty())){
            if (!Edit_Sifre.getText().toString().equals(Edit_SifreTekrar.getText().toString())){
                postInterface.Post(500,"Şifreler Eşleşmiyor");
                return;
            }
        }
        Register r = new Register()
                .setID(k.getID())
                .setTelefon(Edit_Telefon.getText().toString())
                .setSifre(Edit_Sifre.getText().toString());
        new ModelPost(postInterface).execute(degiskenler.KullaniciUpdateUrl,r.toString());
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return false;
    }
}
