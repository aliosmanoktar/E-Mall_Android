package com.aliosman.emall.Adapter.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import com.aliosman.emall.Interface.UrunActionInterface;
import com.aliosman.emall.Model.Get.Urun;
import com.aliosman.emall.R;

public class adapter_urun_action_dialog extends base_dialog_adapter {

    private Button SepetAddClick;
    private Button FavoriAddClick;
    private UrunActionInterface click;
    private Urun item;

    public adapter_urun_action_dialog(UrunActionInterface click, Context c, Urun item) {
        super(c);
        this.click = click;
        this.item=item;
    }

    protected void CreateDialog(Context c){
        dialog=new Dialog(c);
        dialog.setContentView(R.layout.layout_urun_action_dialog);
        SepetAddClick = dialog.findViewById(R.id.urun_action_layout_sepetEkle);
        FavoriAddClick = dialog.findViewById(R.id.urun_action_layout_favoriEkle);
        dialog.setCanceledOnTouchOutside(true);
        SepetAddClick.setOnClickListener(v -> {
            click.onClick(true,item);
            Hide();
        });
        FavoriAddClick.setOnClickListener(v -> {
            click.onClick(false,item);
            Hide();
        });
    }
}