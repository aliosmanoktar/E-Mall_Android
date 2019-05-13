package com.aliosman.emall.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aliosman.emall.Activity.SiparisActivity;
import com.aliosman.emall.R;

public class fragment_hesabim extends Fragment {
    LinearLayout AdresBilgileri,Siparislerim,SifreIslemleri,Cikis;
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_hesabim,container,false);
        AdresBilgileri=view.findViewById(R.id.hesap_layout_adress);
        Siparislerim=view.findViewById(R.id.hesap_layout_siparisler);
        SifreIslemleri=view.findViewById(R.id.hesap_layout_password);
        Cikis=view.findViewById(R.id.hesap_layout_cikis);
        Siparislerim.setOnClickListener(SiparisClick);
        return view;
    }
    View.OnClickListener SiparisClick = v -> {
        startActivity(new Intent(getContext(), SiparisActivity.class));
    };
}
