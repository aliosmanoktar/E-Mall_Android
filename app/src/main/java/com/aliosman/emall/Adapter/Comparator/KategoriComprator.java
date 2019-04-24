package com.aliosman.emall.Adapter.Comparator;

import com.aliosman.emall.Model.Get.Kategori;

import java.util.Comparator;

public class KategoriComprator implements Comparator<Kategori> {
    @Override
    public int compare(Kategori o1, Kategori o2) {
        return o1.getAdi().compareToIgnoreCase(o2.getAdi());
    }
}
