package com.aliosman.emall.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aliosman.emall.Interface.RecylerItemClick;
import com.aliosman.emall.Model.Get.Urun;
import com.aliosman.emall.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.shts.android.library.TriangleLabelView;

public class adapter_benzer_urunler extends RecyclerView.Adapter<adapter_benzer_urunler.ViewHolder> {

    private List<Urun> items;
    private RecylerItemClick<Urun> click;
    private RecylerItemClick<Urun> longClick;
    private int height;
    public adapter_benzer_urunler(List<Urun> items, RecylerItemClick<Urun> click,RecylerItemClick<Urun> longClick,int height) {
        this.items = items;
        this.click = click;
        this.longClick=longClick;
        this.height=height;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_urun_listeleme,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {
        Urun item = items.get(i);
        viewHolder.Fiyat.setText(item.getFiyat()+ " ₺");
        viewHolder.UrunAdi.setText(item.getAdi());
        if (item.getOran()>0 && item.getEskiFiyat()!=0){
            viewHolder.IndirimOran.setVisibility(View.VISIBLE);
            viewHolder.EskiFiyat.setVisibility(View.VISIBLE);
            viewHolder.EskiFiyat.setText(item.getEskiFiyat()+ " ₺");
            viewHolder.IndirimOran.setPrimaryText("%"+item.getOran());
        }
        if (click!=null)
            viewHolder.layout.setOnClickListener(v -> {
                click.onclick(item);
            });
        if (longClick!=null)
            viewHolder.layout.setOnLongClickListener(v -> {
                longClick.onclick(item);
                return true;
            });
        if (height!=-1)
            viewHolder.layout.getLayoutParams().height=height;
        Picasso.get()
                .load(item.getResimler().get(0))
                .centerCrop().fit()
                .placeholder(R.drawable.urun_hazirlaniyor)
                .error(R.drawable.urun_hazirlaniyor)
                .into(viewHolder.UrunImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TriangleLabelView IndirimOran;
        private TextView UrunAdi,EskiFiyat,Fiyat;
        private ImageView UrunImage;
        private RelativeLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
            IndirimOran=itemView.findViewById(R.id.item_urun_list_indirimOran);
            UrunAdi=itemView.findViewById(R.id.item_urun_list_urunAdi);
            EskiFiyat=itemView.findViewById(R.id.item_urun_list_urunEskiFiyat);
            Fiyat=itemView.findViewById(R.id.item_urun_list_urunFiyat);
            UrunImage=itemView.findViewById(R.id.item_urun_list_urunImage);
            layout=itemView.findViewById(R.id.item_urun_list_layout);
        }
    }
}
