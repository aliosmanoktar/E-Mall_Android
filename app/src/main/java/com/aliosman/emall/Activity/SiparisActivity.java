package com.aliosman.emall.Activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.aliosman.emall.Background.ModelDownloadList;
import com.aliosman.emall.Interface.DownloadInterface;
import com.aliosman.emall.Model.Get.Sepet;
import com.aliosman.emall.Model.Post.Satis;
import com.aliosman.emall.R;
import com.aliosman.emall.degiskenler;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.squareup.picasso.Picasso;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import iammert.com.expandablelib.ExpandCollapseListener;
import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;

public class SiparisActivity extends AppCompatActivity {

    private ExpandableLayout expandableLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siparis);
        expandableLayout=findViewById(R.id.activity_siparis_expandableLayout);
        expandableLayout.setRenderer(renderer);
        expandableLayout.setExpandListener(expandListener);
        expandableLayout.setCollapseListener(collapseListener);
        new ModelDownloadList<Sepet>(Satis[].class,satisDownload).execute(degiskenler.SipariGetUrl +"48");
    }

    DownloadInterface<Satis> satisDownload = new DownloadInterface<Satis>() {
        private Dialog dialog;
        @Override
        public void Start() {
            dialog=new AwesomeProgressDialog(SiparisActivity.this)
                    .setTitle("Lütfen Bekleyiniz")
                    .setMessage("Siparişleriniz Listeleniyor")
                    .setCancelable(false)
                    .show();
        }

        @Override
        public void Complete(List<Satis> items) {
            if (dialog!=null)
                dialog.dismiss();
            SetAdapter(items);
        }
    };

    private ExpandCollapseListener.ExpandListener<Satis> expandListener=(parentIndex, parent, view) -> {
      ImageView arrow = view.findViewById(R.id.item_siparis_parent_arrow);
      arrow.setImageResource(R.drawable.arrow_up);
    };

    private ExpandCollapseListener.CollapseListener<Satis> collapseListener= (parentIndex, parent, view) -> {
        ImageView arrow = view.findViewById(R.id.item_siparis_parent_arrow);
        arrow.setImageResource(R.drawable.arrow_down);
    };

    private ExpandableLayout.Renderer<Satis,Sepet> renderer=new ExpandableLayout.Renderer<Satis, Sepet>() {
        @Override
        public void renderParent(View view, Satis model, boolean isExpanded, int parentPosition) {
            TextView txtTarih = view.findViewById(R.id.item_siparis_parent_tarih);
            TextView txtDurum = view.findViewById(R.id.item_siparis_parent_durum);
            TextView txtToplam = view.findViewById(R.id.item_siparis_parent_toplam);
            TextView kargoKod= view.findViewById(R.id.item_siparis_parent_kargoKod);
            kargoKod.setText(model.getKargoKod());
            try {
                txtTarih.setText(GetDate(model.getZaman()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            txtDurum.setText(model.getDurum());
            float toplam = 0;
            for (Sepet item : model.getItems())
                toplam+=(item.getUrun().getFiyat()*item.getAdet());
            txtToplam.setText(toplam+" ₺");
        }

        @Override
        public void renderChild(View view, Sepet model, int parentPosition, int childPosition) {

            ImageView urunImage=view.findViewById(R.id.item_sepet_urunImage);
            TextView urunAdi=view.findViewById(R.id.item_sepet_urunName);
            TextView urunFiyat=view.findViewById(R.id.item_sepet_urunFiyat);
            TextView urunToplam=view.findViewById(R.id.item_sepet_urunToplam);
            urunAdi.setText(model.getUrun().getAdi());
            urunFiyat.setText(model.getUrun().getFiyat()+" ₺");
            urunToplam.setText(model.getUrun().getFiyat()+"₺ x "+ model.getAdet()+" = "+(model.getUrun().getFiyat()*model.getAdet())+ " ₺");
            Picasso.get()
                    .load(model.getUrun().getResimler().get(0))
                    .centerCrop()
                    .placeholder(R.drawable.urun_hazirlaniyor)
                    .error(R.drawable.urun_hazirlaniyor)
                    .fit().into(urunImage);
        }
    };

    private String GetDate(String Zaman) throws ParseException {
        Date date=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(Zaman);
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

    private void SetAdapter(List<Satis> items){
        expandableLayout.getSections().clear();
        for (Satis item : items)
            expandableLayout.addSection(getSection(item));
    }

    public Section<Satis, Sepet> getSection(Satis item) {
        Section<Satis, Sepet> section = new Section<>();
        section.parent=item;
        for (Sepet sepetItem : item.getItems())
            section.children.add(sepetItem);
        return section;
    }
}
