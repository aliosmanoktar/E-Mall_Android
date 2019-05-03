package com.aliosman.emall.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aliosman.emall.Fragment.fragment_ana_ekran;
import com.aliosman.emall.Fragment.fragment_hesabim;
import com.aliosman.emall.Fragment.fragment_kategoriler;
import com.aliosman.emall.Fragment.fragment_login;
import com.aliosman.emall.Fragment.fragment_register;
import com.aliosman.emall.Preferences;
import com.aliosman.emall.R;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

public class Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Preferences.GetKullanici(this)!=null)
            ShowAnaEkran();
        NavigationTabStrip navigationTabStrip=findViewById(R.id.login_ekran_navigation);
        navigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {

            }

            @Override
            public void onEndTabSelected(String title, int index) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (index) {
                    case 0:
                        transaction.replace(R.id.login_ekran_framelayout,new fragment_login());
                        break;
                    case 1:
                        transaction.replace(R.id.login_ekran_framelayout,new fragment_register());
                }
                transaction.commitAllowingStateLoss();
            }
        });
        navigationTabStrip.setTabIndex(0,true);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.login_ekran_framelayout,new fragment_login());
        transaction.commitAllowingStateLoss();
        getSupportActionBar().hide();
    }
    private void ShowAnaEkran(){
        Intent i = new Intent(getBaseContext(),AnaEkran.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
