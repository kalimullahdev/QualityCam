package com.example.qualitycam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class IntroItemAdapter extends PagerAdapter {

    Context context;
    List<IntroItem> data;

    public IntroItemAdapter(Context context, List<IntroItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.intro_item, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView title = view.findViewById(R.id.tv_title);
        TextView description = view.findViewById(R.id.tv_decription);

        imageView.setImageResource(data.get(position).getImage());
        title.setText(data.get(position).getTitle());
        description.setText(data.get(position).getDescription());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }
}
