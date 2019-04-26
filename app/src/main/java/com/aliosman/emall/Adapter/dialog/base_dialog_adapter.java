package com.aliosman.emall.Adapter.dialog;

import android.app.Dialog;
import android.content.Context;

public abstract class base_dialog_adapter {

    protected Dialog dialog;
    protected Context c;

    protected base_dialog_adapter(Context c){
        this.c=c;
        CreateDialog(c);
    }

    protected abstract void CreateDialog(Context c);

    public Dialog Show(){
        if (!dialog.isShowing())
            dialog.show();
        return dialog;
    }

    public void Hide(){
        if (dialog.isShowing())
            dialog.dismiss();
    }

    public Dialog getDialog() {
        return dialog;
    }
}