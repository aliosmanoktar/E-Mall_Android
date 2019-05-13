package com.aliosman.emall.Adapter;

import android.support.annotation.NonNull;
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

public class adapter_populer_urun extends RecyclerView.Adapter<adapter_populer_urun.ViewHolder> {
    private List<Urun> uruns;
    private String TAG = getClass().getName();
    private RecylerItemClick click;
    public adapter_populer_urun(List<Urun> uruns,RecylerItemClick click){
        this.uruns=uruns;
        this.click=click;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_populer_urun,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Urun item = uruns.get(i);
        viewHolder.txt_urunAdi.setText(item.getAdi());
        viewHolder.txt_urunFiyat.setText(item.getFiyat()+" ₺");
        viewHolder.layout.setOnClickListener(v -> {
            click.onclick(item);
        });
        Picasso.get()
                .load(item.getResimler().get(0))
                .centerCrop()
                .placeholder(R.drawable.urun_hazirlaniyor)
                .error(R.drawable.urun_hazirlaniyor)
                .fit().into(viewHolder.urunImage);
        viewHolder.layout.setTag(item);
    }

    @Override
    public int getItemCount() {
        return uruns.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout layout;
        private TextView txt_urunAdi,txt_urunFiyat;
        private ImageView urunImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.item_populer_urun_layout);
            txt_urunAdi=itemView.findViewById(R.id.item_populer_urun_txt_urunAdi);
            txt_urunFiyat=itemView.findViewById(R.id.item_populer_urun_txt_urunFiyat);
            urunImage=itemView.findViewById(R.id.item_populer_urun_image);
        }
    }
}
