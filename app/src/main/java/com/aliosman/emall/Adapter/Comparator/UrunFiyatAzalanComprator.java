package com.aliosman.emall.Adapter.Comparator;

import com.aliosman.emall.Model.Get.Urun;

import java.util.Comparator;

public class UrunFiyatAzalanComprator implements Comparator<Urun> {
    @Override
    public int compare(Urun o1, Urun o2) {
        return Float.compare(o2.getFiyat(),o1.getFiyat());
    }
}
