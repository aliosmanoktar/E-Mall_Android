package com.aliosman.emall.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.aliosman.emall.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class adapter_image_swipe extends PagerAdapter {
    private List<String> resimler;
    private Context c;
    private LayoutInflater inflater;
    private String TAG = getClass().getName();
    public adapter_image_swipe(List<String> resimler, Context c) {
        this.resimler = resimler;
        this.c = c;
        inflater=LayoutInflater.from(c);
    }

    @Override
    public Object instantiateItem( ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_urun_image,container,false);
        ImageView ig = view.findViewById(R.id.item_urun_image_ImageView);
        (container).addView(view);
        Picasso.get()
                .load(resimler.get(position))
                .centerCrop()
                .fit()
                .error(R.drawable.urun_hazirlaniyor)
                .into(ig);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView((View)object);
    }

    @Override
    public int getCount() {
        return resimler.size();
    }

    @Override
    public boolean isViewFromObject(View view,Object o) {
        return view==((View)o);
    }
}
