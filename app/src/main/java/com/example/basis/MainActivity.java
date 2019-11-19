package com.example.basis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.basis.Model.Main;
import com.example.basis.Model.MainDataModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FetchData.GetDataOnPostExecute {

    private static final String TAG = "MainActivity";
    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;

    private ArrayList<Main> mContents;

    private TextView previous, next;
    private ImageView reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FetchData data = new FetchData(this);
        data.execute();


        mViewPager = findViewById(R.id.viewPager);

        mContents = new ArrayList<>();


        previous = findViewById(R.id.previousBtn);
        next = findViewById(R.id.nextBtn);

        reset = findViewById(R.id.reset);


    }


    @Override
    public void onGetData(final MainDataModel mainDataModel) {

        if (mainDataModel != null) {
            Log.d(TAG, "onGetData: " + mainDataModel.getData().size());

            for (int i = 0; i < mainDataModel.getData().size(); i++) {
                String text = mainDataModel.getData().get(i).getText();
                String id = mainDataModel.getData().get(i).getId();
                Main main = new Main(id, text);
                mContents.add(main);
            }


            mAdapter = new ViewPagerAdapter(mContents, this);

            mViewPager.setPageTransformer(true, new ViewPagerStack());
            mViewPager.setOffscreenPageLimit(4);

            mViewPager.setAdapter(mAdapter);
        }

    }

    private class ViewPagerStack implements ViewPager.PageTransformer {

        @Override
        public void transformPage(@NonNull final View page, final float position) {
            if (position >= 0) {
                page.setScaleX(0.9f - 0.04f * position);
                page.setScaleY(0.8f);

                page.setTranslationX(-page.getWidth() * position);
                page.setTranslationY(10 * position);


                if (mViewPager.getCurrentItem() > 0) {
                    previous.setVisibility(View.VISIBLE);
                } else if (mViewPager.getCurrentItem() == 0) {
                    previous.setVisibility(View.GONE);
                }

                previous.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewPager.arrowScroll(View.FOCUS_LEFT);

                    }
                });


                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewPager.arrowScroll(View.FOCUS_RIGHT);
                    }
                });


                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG, "onClick: reset clicked");
                        mViewPager.setCurrentItem(0, true);
                    }
                });

            }
        }
    }


}
