package com.example.basis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.basis.Model.Main;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {


    private List<Main> contents;
    private Context context;

    public ViewPagerAdapter(List<Main> contents, Context context) {
        this.contents = contents;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item, container, false);
        container.addView(view);

        TextView cardCount = view.findViewById(R.id.cardCount);
        TextView content = view.findViewById(R.id.helloText);

        cardCount.setText(position + 1 + " out of " + contents.size());
        content.setText(contents.get(position).getText());

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
