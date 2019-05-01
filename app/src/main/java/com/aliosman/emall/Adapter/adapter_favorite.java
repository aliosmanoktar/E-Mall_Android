package com.aliosman.emall.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.aliosman.emall.Adapter.Swipe.SwipeViewHolderBase;
import com.aliosman.emall.Interface.RecylerItemClick;
import com.aliosman.emall.Model.Get.Favorite;
import com.aliosman.emall.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class adapter_favorite extends RecyclerView.Adapter<adapter_favorite.ViewHolder> {

    private String TAG=getClass().getName();
    private List<Favorite> items;
    private RecylerItemClick click;
    public adapter_favorite(List<Favorite> items,RecylerItemClick click) {
        this.items = items;
        this.click=click;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_favori_urun,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Favorite item = items.get(i);
        viewHolder.itemView.setTag(item);
        viewHolder.urunAdi.setText(item.getUrun().getAdi());
        viewHolder.urunFiyat.setText(item.getUrun().getFiyat()+" ₺");
        viewHolder.frg_view.setOnClickListener(v -> {
            click.onclick(item);
        });
        Picasso.get()
                .load(item.getUrun().getResimler().get(0))
                .centerCrop()
                .fit().into(viewHolder.urunImage);
    }

    public void removeItem(int index){
        items.remove(index);
        notifyItemRemoved(index);
    }

    public void restoreItem(Favorite data, int index){
        items.add(index,data);
        notifyItemInserted(index);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends SwipeViewHolderBase{
        private ImageView urunImage;
        private TextView urunAdi,urunFiyat;
        public ViewHolder(View itemView) {
            super(itemView);
            urunImage=itemView.findViewById(R.id.item_favoriler_urunImage);
            urunAdi=itemView.findViewById(R.id.item_favoriler_urunName);
            urunFiyat=itemView.findViewById(R.id.item_favoriler_urunFiyat);
        }
    }
}