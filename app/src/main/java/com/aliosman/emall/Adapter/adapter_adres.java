package com.aliosman.emall.Adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.aliosman.emall.Interface.RecylerItemClick;
import com.aliosman.emall.Model.Get.Adres;
import com.aliosman.emall.R;
import java.util.List;

public class adapter_adres extends RecyclerView.Adapter<adapter_adres.ViewHolder> {

    private List<Adres> adres;
    private RecylerItemClick click;
    private int select=-1;

    public adapter_adres(List<Adres> adres, RecylerItemClick click) {
        this.adres = adres;
        this.click=click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_adres,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Adres item = adres.get(i);
        viewHolder.AdresAdi.setText(item.getAdi());
        viewHolder.AcikAdres.setText(item.getAcikAdres());
        if (i==select)
            viewHolder.back.setBackgroundColor(viewHolder.back.getResources().getColor(R.color.colorReyclerItemSelect));
        else
            viewHolder.back.setBackgroundColor(Color.TRANSPARENT);
        viewHolder.back.setOnClickListener(v -> {
            Select(i);
            click.onclick(item);
        });
    }

    public void Select(int i){
        if (i==select)
            select=-1;
        else select=i;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return adres.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView AcikAdres,AdresAdi;
        private LinearLayout back;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            back=itemView.findViewById(R.id.item_adres_background);
            AcikAdres=itemView.findViewById(R.id.item_adres_acikAdres);
            AdresAdi=itemView.findViewById(R.id.item_adres_adresAdi);
        }
    }
}
