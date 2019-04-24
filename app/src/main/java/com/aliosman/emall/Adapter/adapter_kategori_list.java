package com.aliosman.emall.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aliosman.emall.Interface.RecylerItemClick;
import com.aliosman.emall.Model.Get.Kategori;
import com.aliosman.emall.R;
import java.util.List;

public class adapter_kategori_list extends RecyclerView.Adapter<adapter_kategori_list.ViewHolder> {
    private List<Kategori> items;
    private RecylerItemClick click;
    public adapter_kategori_list(List<Kategori> items, RecylerItemClick click) {
        this.items = items;
        this.click=click;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_kategori_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Kategori item = items.get(i);
        viewHolder.Adi.setText(item.getAdi());
        viewHolder.layout.setOnClickListener(l->{
            click.onclick(item);
        });
        viewHolder.icon.setVisibility((item.isAltKategori())? View.VISIBLE : View.GONE);
        if (item.getID()==-1)
            viewHolder.back.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout layout;
        private TextView Adi;
        private ImageView icon,back;
        public ViewHolder(View itemView) {
            super(itemView);
            Adi=itemView.findViewById(R.id.item_kategori_list_Adi);
            icon=itemView.findViewById(R.id.item_kategori_list_Alt);
            layout=itemView.findViewById(R.id.item_kategori_list_layout);
            back=itemView.findViewById(R.id.item_kategori_list_back);
        }
    }
}