package com.aliosman.emall.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.aliosman.emall.Fragment.*;
import com.aliosman.emall.R;
import com.aliosman.emall.degiskenler;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class AnaEkran extends AppCompatActivity {
    private SearchView searchView;
    private final  String TAG = getClass().getName();
    private Fragment fr_anaEkran,fr_kategoriler,fr_hesabim;
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
           startActivity(new Intent(getBaseContext(),FavorilerActivity.class));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.search_menu_item);
        searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Ürün Arama...");
        searchView.setOnQueryTextListener(searchListener);
        return true;

    }
    private SearchView.OnQueryTextListener searchListener =new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            Intent i=new Intent(getBaseContext(), UrunListeleme_Activity.class);
            i.putExtra(degiskenler.UrunArananBundleString,s);
            startActivity(i);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            return false;
        }
    };
}