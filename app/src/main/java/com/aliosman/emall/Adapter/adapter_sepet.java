package com.aliosman.emall.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.aliosman.emall.Adapter.Swipe.SwipeViewHolderBase;
import com.aliosman.emall.Interface.RecylerItemClick;
import com.aliosman.emall.Model.Get.Sepet;
import com.aliosman.emall.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapter_sepet extends RecyclerView.Adapter<adapter_sepet.ViewHolder> {

    private List<Sepet> items;
    private RecylerItemClick click;
    public adapter_sepet(List<Sepet> items, RecylerItemClick click) {
        this.items = items;
        this.click=click;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sepet_urun,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Sepet item = items.get(i);
        viewHolder.itemView.setTag(item);
        viewHolder.urunAdi.setText(item.getUrun().getAdi());
        viewHolder.urunFiyat.setText(item.getUrun().getFiyat()+" ₺");
        viewHolder.urunToplam.setText(item.getUrun().getFiyat()+"₺ x "+ item.getAdet()+" = "+(item.getUrun().getFiyat()*item.getAdet())+ " ₺");
        viewHolder.frg_view.setOnClickListener(v -> {
            if (click!=null)
                click.onclick(item);
        });
        Picasso.get()
                .load(item.getUrun().getResimler().get(0))
                .centerCrop()
                .error(R.drawable.urun_hazirlaniyor)
                .fit().into(viewHolder.urunImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void removeItem(int index){
        items.remove(index);
        notifyItemRemoved(index);
    }

    public void restoreItem(Sepet data, int index){
        items.add(index,data);
        notifyItemInserted(index);
    }
    class ViewHolder extends SwipeViewHolderBase{
        private ImageView urunImage;
        private TextView urunAdi,urunFiyat,urunToplam;
        public ViewHolder(View itemView) {
            super(itemView);
            urunImage=itemView.findViewById(R.id.item_sepet_urunImage);
            urunAdi=itemView.findViewById(R.id.item_sepet_urunName);
            urunFiyat=itemView.findViewById(R.id.item_sepet_urunFiyat);
            urunToplam=itemView.findViewById(R.id.item_sepet_urunToplam);
        }
    }
}
