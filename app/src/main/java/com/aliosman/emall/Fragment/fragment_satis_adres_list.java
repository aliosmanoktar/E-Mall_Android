package com.aliosman.emall.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aliosman.emall.Adapter.Swipe.RecylerItemSwipeHelper;
import com.aliosman.emall.Adapter.adapter_adres;
import com.aliosman.emall.Background.ModelDownloadList;
import com.aliosman.emall.Interface.DownloadInterface;
import com.aliosman.emall.Interface.IStepperListener;
import com.aliosman.emall.Interface.RecylerItemClick;
import com.aliosman.emall.Interface.RecylerItemSwipeListener;
import com.aliosman.emall.Model.Get.Adres;
import com.aliosman.emall.R;
import com.aliosman.emall.degiskenler;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;
import java.util.List;

public class fragment_satis_adres_list extends Fragment implements Step {

    private static IStepperListener listener;
    private int AdresID=-1;
    private RecyclerView recyclerView;
    private String TAG = getClass().getName();
    private int KullaniciID=48;

    public static fragment_satis_adres_list newInstance(IStepperListener l){
        listener=l;
        return new fragment_satis_adres_list();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_satis_adres_list,container,false);
        recyclerView=view.findViewById(R.id.layout_satis_adres_list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //KullaniciID=Preferences.GetKullanici(getContext()).getID();
        return view;
    }
    @Nullable
    @Override
    public VerificationError verifyStep() {
        Log.e(TAG, "verifyStep: " );
        if (AdresID==-1) {
            Toast.makeText(getContext(),"Adres Seçimi Yapılması Gereklidir",Toast.LENGTH_LONG).show();
            return new VerificationError("Adres Seçimi Olmalıdır");
        }else {
            listener.OnNext(1,AdresID);
            return null;
        }
    }

    @Override
    public void onSelected() {
        new ModelDownloadList<Adres>(Adres[].class,adresDownload).execute(degiskenler.GetAdressUrl+KullaniciID);
    }
    private RecylerItemClick<Adres> click= item -> {
        if (item.getID()==AdresID)
            AdresID=-1;
        else AdresID=item.getID();
    };
    private DownloadInterface<Adres> adresDownload =new DownloadInterface<Adres>() {
        private Dialog dialog;
        @Override
        public void Start() {
            dialog=new AwesomeProgressDialog(getActivity())
                    .setTitle("Lütfen Bekleyiniz")
                    .setMessage("Adres Listeleniyor")
                    .setCancelable(false)
                    .show();
        }

        @Override
        public void Complete(List<Adres> items) {
            if (dialog!=null)
                dialog.dismiss();
            recyclerView.setAdapter(new adapter_adres(items,click));
        }
    };
    @Override
    public void onError(@NonNull VerificationError error) {

    }

}
