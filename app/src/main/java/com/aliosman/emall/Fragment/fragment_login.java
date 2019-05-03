package com.aliosman.emall.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aliosman.emall.Activity.AnaEkran;
import com.aliosman.emall.Adapter.Dialog.adapter_input_dialog;
import com.aliosman.emall.Background.ModelPost;
import com.aliosman.emall.Interface.PostInterface;
import com.aliosman.emall.Model.Post.Login;
import com.aliosman.emall.Model.Post.ResetPassword;
import com.aliosman.emall.Preferences;
import com.aliosman.emall.R;
import com.aliosman.emall.degiskenler;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import static android.util.Patterns.EMAIL_ADDRESS;

public class fragment_login extends Fragment {
    private String TAG = getClass().getName();
    private MaterialEditText kullaniciAdi,password;
    private Button giris,gec;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_login,container,false);
        kullaniciAdi = view.findViewById(R.id.login_layout_username);
        password = view.findViewById(R.id.login_layout_password);
        giris = view.findViewById(R.id.login_layout_giris);
        gec=view.findViewById(R.id.login_layout_gec);
        giris.setOnClickListener(giris_listener);
        TextView txt_resetPassword=view.findViewById(R.id.login_layout_resetPassword);
        txt_resetPassword.setOnClickListener(sifreSifirlamaListener);
        gec.setOnClickListener(gecListener);
        return view;
    }


    private PostInterface postInterface=new PostInterface() {
        private Dialog dialog;
        @Override
        public void Start() {
            dialog=new AwesomeProgressDialog(getActivity())
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
                Preferences.SaveKullanici(getContext(),value);
                dialog=new AwesomeSuccessDialog(getActivity())
                        .setTitle("Başarılı")
                        .setMessage("Giriş Başarılı")
                        .setDoneButtonText("Tamam")
                        .setDoneButtonClick(() -> {
                            dialog.dismiss();
                            ShowAnaEkran();
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
    private void ShowAnaEkran(){
        Intent i = new Intent(getContext(),AnaEkran.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
    private PostInterface ResetPasswordInterface=new PostInterface() {
        private Dialog dialog;
        @Override
        public void Start() {
            dialog=new AwesomeProgressDialog(getActivity())
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
                dialog=new AwesomeSuccessDialog(getActivity())
                        .setTitle("Başarılı")
                        .setMessage(value)
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

    private View.OnClickListener sifreSifirlamaListener= v ->{
        adapter_input_dialog dialog=new adapter_input_dialog(getActivity())
                .SetHint("E Mail");
        dialog.SetInputButtonClick(l -> {
            String mail = dialog.getInputText().getText().toString();
            if (!CheckEmail(mail)){
                dialog.getInputText().setError("Geçerli bir mail adresi giriniz");
                return;
            }else{
                dialog.Hide();
                new ModelPost(ResetPasswordInterface).execute(degiskenler.ResetPassword,new ResetPassword(mail).toString());
            }
        });
        dialog.Show();
    };

    private View.OnClickListener giris_listener= v -> {
        boolean hata=false;
        if (kullaniciAdi.getText().toString().isEmpty()) {
            kullaniciAdi.setError("Kullanıcı Adı Girilmesi Gerekmektedir");
            hata=true;
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Şifre Girilmesi Gerekmektedir");
            hata=true;
        }
        if (hata)
            return;

        new ModelPost(postInterface).execute(degiskenler.LoginUrl,
                new Login(kullaniciAdi.getText().toString(),password.getText().toString()).toString());
    };

    private View.OnClickListener gecListener = v -> {
        ShowAnaEkran();
    };

    private boolean CheckEmail(String target){
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return EMAIL_ADDRESS.matcher(target).matches();
        }
    }

}
