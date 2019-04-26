package com.aliosman.emall.Adapter.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.aliosman.emall.Interface.ActionUrunFilterInterface;
import com.aliosman.emall.Interface.UrunFilterType;
import com.aliosman.emall.R;

public class adapter_urun_filter_dialog extends base_dialog_adapter {

    private ActionUrunFilterInterface filter;
    private RadioButton azalanFiyat,artanFiyat;
    private Button uygula;
    private TextView uyari;

    public adapter_urun_filter_dialog(Context c, ActionUrunFilterInterface filter) {
        super(c);
        this.filter = filter;
    }
    protected void CreateDialog(Context c){
        dialog=new Dialog(c);
        dialog.setContentView(R.layout.layout_urun_filter_dialog);
        azalanFiyat = dialog.findViewById(R.id.urun_filter_layout_azalanFiyat);
        artanFiyat = dialog.findViewById(R.id.urun_filter_layout_artanFiyat);
        uygula=dialog.findViewById(R.id.urun_filter_layout_uygula);
        uyari=dialog.findViewById(R.id.urun_filter_layout_uyari);
        dialog.setCanceledOnTouchOutside(true);
        uygula.setOnClickListener(v -> {
            if (!artanFiyat.isChecked() && !azalanFiyat.isChecked()){
                uyari.setVisibility(View.VISIBLE);
                return;
            }else{
                filter.Filter(azalanFiyat.isChecked() ? UrunFilterType.AzalanFiyat : UrunFilterType.ArtanFiyat);
                Hide();
            }
        });
    }
}