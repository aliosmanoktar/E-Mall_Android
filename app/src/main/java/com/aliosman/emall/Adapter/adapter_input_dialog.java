package com.aliosman.emall.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import com.aliosman.emall.R;
import com.rengwuxian.materialedittext.MaterialEditText;

public class adapter_input_dialog {

    private Button InputButton;
    private MaterialEditText InputText;
    private Dialog dialog;

    public adapter_input_dialog(Context context) {
        CreateDialog(context);
    }

    private void CreateDialog(Context c){
        dialog=new Dialog(c);
        dialog.setContentView(R.layout.layout_reset_password_input);
        InputButton = dialog.findViewById(R.id.reset_password_layout__Sifirla);
        InputText = dialog.findViewById(R.id.reset_password_layout__Mail);
        dialog.setCanceledOnTouchOutside(true);
    }
    public adapter_input_dialog SetHint(String hint){
        InputText.setHint(hint);
        InputText.setFloatingLabelText(hint);
        return this;
    }

    public adapter_input_dialog SetInputButtonClick(View.OnClickListener l){
        InputButton.setOnClickListener(l);
        return this;
    }

    public Button getInputButton() {
        return InputButton;
    }

    public MaterialEditText getInputText() {
        return InputText;
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