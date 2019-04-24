package com.aliosman.emall.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.aliosman.emall.Background.ModelPost;
import com.aliosman.emall.Interface.PostInterface;
import com.aliosman.emall.Model.Post.Register;
import com.aliosman.emall.R;
import com.aliosman.emall.degiskenler;

public class fragment_register extends Fragment {
    private MaterialEditText Edit_Ad,Edit_Soyad,Edit_KullanciAdi,Edit_Eposta,Edit_Telefon,Edit_Sifre,Edit_SifreTekrar;
    private MaterialSpinner SpinnerCinsiyet;
    private Button Btn_Register;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.layout_register,container,false);
        Edit_Ad = view.findViewById(R.id.register_layout_Ad);
        Edit_Soyad =  view.findViewById(R.id.register_layout_Soyad);
        Edit_KullanciAdi = view.findViewById(R.id.register_layout_KullaniciAdi);
        Edit_Eposta = view.findViewById(R.id.register_layout_Eposta);
        Edit_Telefon = view.findViewById(R.id.register_layout_Telefon);
        Edit_Sifre = view.findViewById(R.id.register_layout_Sifre);
        Edit_SifreTekrar = view.findViewById(R.id.register_layout_SifreTekrar);
        SpinnerCinsiyet=view.findViewById(R.id.register_layout_Cinsiyet);
        Btn_Register = view.findViewById(R.id.register_layout_Register);
        Btn_Register.setOnClickListener(btn_register_click);
        SpinnerCinsiyet.setItems("Cinsiyet Seçiniz","Kadın","Erkek");
        return view;
    }
    private PostInterface postInterface=new PostInterface() {
        private Dialog dialog;
        @Override
        public void Start() {
            dialog=new AwesomeProgressDialog(getActivity())
                    .setTitle("Bekleyiniz")
                    .setMessage("Bilgiler Kayıt Ediliyor")
                    .setCancelable(false)
                    .show();
        }

        @Override
        public void Post(int code, String value) {
            if (dialog!=null)
                dialog.dismiss();
            if (code==200){
                dialog=new AwesomeSuccessDialog(getActivity())
                        .setTitle("Kayıt Başarılı")
                        .setMessage("Giriş Yapabilirsiniz")
                        .setDoneButtonText("Tamam")
                        .setDoneButtonClick(() -> {
                            dialog.dismiss();
                        }).show();
            }else{
                dialog=new AwesomeErrorDialog(getActivity())
                        .setMessage(value)
                        .setTitle("Başarısız")
                        .setButtonText("Tamam")
                        .setErrorButtonClick(()->{
                            dialog.dismiss();
                        }).show();
            }
        }
    };
    private View.OnClickListener btn_register_click= v -> {
        boolean hata=false;
        if (Edit_KullanciAdi.getText().length()>12)
            hata=true;
        if (Edit_Ad.getText().toString().isEmpty()){
            hata=true;
            Edit_Ad.setError("İsim Alanı Boş Olamaz");
        }
        if (Edit_Soyad.getText().toString().isEmpty()){
            hata=true;
            Edit_Soyad.setError("Soyisim Alanı Boş Olamaz");
        }
        if (Edit_Sifre.getText().toString().isEmpty()){
            hata=true;
            Edit_Sifre.setError("Şifre Alanı Boş Olamaz");
        }
        if (Edit_SifreTekrar.getText().toString().isEmpty()){
            hata=true;
            Edit_SifreTekrar.setError("Şifre Tekrar Alanı Boş Olamaz");
        }
        if (!Edit_SifreTekrar.getText().toString().isEmpty() && !Edit_SifreTekrar.getText().toString().equals(Edit_Sifre.getText().toString())){
            hata=true;
            Edit_SifreTekrar.setError("Şifreler Eşleşmiyor");
            Edit_Sifre.setError("Şifreler Eşleşmiyor");
        }
        if (hata)
            return;
        Register r = new Register()
                .setAdi(Edit_Ad.getText().toString())
                .setSoyadi(Edit_Soyad.getText().toString())
                .setKullaniciAdi(Edit_KullanciAdi.getText().toString())
                .setEmail(Edit_Eposta.getText().toString())
                .setTelefon(Edit_Telefon.getText().toString())
                .setCinsiyet(GetCinsiyet());
        new ModelPost(postInterface).execute(degiskenler.RegisterUrl,r.toString());
    };
    private String GetCinsiyet(){
        if (SpinnerCinsiyet.getSelectedIndex()==1)
            return "1";
        else if (SpinnerCinsiyet.getSelectedIndex()==2)
            return "0";
        else return null;
    }
}