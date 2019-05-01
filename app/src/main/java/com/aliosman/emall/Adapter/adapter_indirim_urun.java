package com.aliosman.emall.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aliosman.emall.Interface.RecylerItemClick;
import com.aliosman.emall.Model.Get.Urun;
import com.aliosman.emall.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.shts.android.library.TriangleLabelView;

public class adapter_indirim_urun extends RecyclerView.Adapter<adapter_indirim_urun.ViewHolder> {
    private List<Urun> items;
    private RecylerItemClick click;
    public adapter_indirim_urun(List<Urun> items,RecylerItemClick click) {
        this.items = items;
        this.click=click;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_indirim_urun,viewGroup,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Urun item = items.get(i);
        viewHolder.txt_indirim_oran.setPrimaryText(" % "+item.getOran());
        viewHolder.txt_eski_fiyat.setText(item.getEskiFiyat()+" ₺");
        viewHolder.txt_urunFiyat.setText(item.getFiyat()+"");
        viewHolder.txt_urunAdi.setText(item.getAdi());
        viewHolder.layout.setOnClickListener( v -> {
            click.onclick(item);
        });
        Picasso.get()
                .load(item.getResimler().get(0))
                .centerCrop()
                .error(R.drawable.urun_hazirlaniyor)
                .fit().into(viewHolder.urunImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TriangleLabelView txt_indirim_oran;
        private RelativeLayout layout;
        private TextView txt_urunAdi,txt_urunFiyat,txt_eski_fiyat;
        private ImageView urunImage;

        public ViewHolder(View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.item_indirim_urun_layout);
            txt_urunAdi=itemView.findViewById(R.id.item_indirim_urun_txt_urunAdi);
            txt_urunFiyat=itemView.findViewById(R.id.item_indirim_urun_txt_urunFiyat);
            urunImage=itemView.findViewById(R.id.item_indirim_urun_image);
            txt_eski_fiyat=itemView.findViewById(R.id.item_indirim_urun_txt_eski);
            txt_indirim_oran=itemView.findViewById(R.id.item_indirim_urun_indirim_oran);
        }
    }
}