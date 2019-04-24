package com.aliosman.emall.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.aliosman.emall.Fragment.*;
import com.aliosman.emall.R;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class AnaEkran extends AppCompatActivity {
    private final  String TAG = getClass().getName();
    Fragment fr_anaEkran,fr_kategoriler,fr_hesabim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anaekran);
        NavigationTabStrip navigationTabStrip=findViewById(R.id.ana_ekran_navigation);
        navigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {

            }

            @Override
            public void onEndTabSelected(String title, int index) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (index) {
                    case 0:
                        if (fr_anaEkran==null)
                            fr_anaEkran=new fragment_ana_ekran();
                        transaction.replace(R.id.ana_ekran_framelayout,fr_anaEkran);
                        break;
                    case 1:
                        if (fr_kategoriler==null)
                            fr_kategoriler=new fragment_kategoriler();
                        transaction.replace(R.id.ana_ekran_framelayout,fr_kategoriler);
                        break;
                    case 2:
                        if (fr_hesabim==null)
                            fr_hesabim=new fragment_hesabim();
                        transaction.replace(R.id.ana_ekran_framelayout,fr_hesabim);
                }
                transaction.commitAllowingStateLoss();
            }
        });
        navigationTabStrip.setTabIndex(0,true);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.ana_ekran_framelayout,new fragment_ana_ekran());
        transaction.commitAllowingStateLoss();
        FloatingActionMenu menu = findViewById(R.id.ana_ekran_fab_menu);
        FloatingActionButton menu_sepet = findViewById(R.id.ana_ekran_fab_menu_item_sepet);
        FloatingActionButton menu_favori = findViewById(R.id.ana_ekran_fab_menu_item_favoriler);
        menu_sepet.setOnClickListener(v -> {
            menu.close(true);
        });
        menu_favori.setOnClickListener(v -> {
           menu.close(true);
        });
    }
}