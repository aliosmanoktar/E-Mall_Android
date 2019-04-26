package com.aliosman.emall.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import com.aliosman.emall.Interface.UrunActionInterface;
import com.aliosman.emall.Model.Get.Urun;
import com.aliosman.emall.R;

public class adapter_urun_action_dialog {

    private Button SepetAddClick;
    private Button FavoriAddClick;
    private UrunActionInterface click;
    private Context c;
    private Dialog dialog;
    private Urun item;

    public adapter_urun_action_dialog(UrunActionInterface click, Context c, Urun item) {
        this.click = click;
        this.c = c;
        this.item=item;
        CreateDialog(c);
    }

    private void CreateDialog(Context c){
        dialog=new Dialog(c);
        dialog.setContentView(R.layout.layout_urun_action_dialog);
        SepetAddClick = dialog.findViewById(R.id.urun_action_layout_sepetEkle);
        FavoriAddClick = dialog.findViewById(R.id.urun_action_layout_favoriEkle);
        SepetAddClick.setOnClickListener(v -> {
            click.onClick(true,item);
            Hide();
        });
        FavoriAddClick.setOnClickListener(v -> {
            click.onClick(false,item);
            Hide();
        });
        dialog.setCanceledOnTouchOutside(true);
    }

    public Dialog Show(){
        if (!dialog.isShowing())
            dialog.show();
        return dialog;
    }

    public void Hide(){
        if (dialog.isShowing())
            dialog.dismiss();
    }
}