package com.debashis.io.material.demo;
 
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.debashis.io.material.tablayout.MagicIndicator;
import com.debashis.io.material.tablayout.ViewPagerHelper;
import com.debashis.io.material.tablayout.buildins.commonnavigator.CommonNavigator;
import com.debashis.io.material.tablayout.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.debashis.io.material.tablayout.buildins.commonnavigator.abs.IPagerIndicator;
import com.debashis.io.material.tablayout.buildins.commonnavigator.abs.IPagerTitleView;
import com.debashis.io.material.tablayout.buildins.commonnavigator.titles.ClipPagerTitleView;
import com.debashis.io.material.demo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class DynamicTabActivity extends AppCompatActivity {
    private static final String[] CHANNELS = new String[]{"CUPCAKE", "DONUT", "ECLAIR", "GINGERBREAD", "HONEYCOMB", "ICE_CREAM_SANDWICH", "JELLY_BEAN", "KITKAT", "LOLLIPOP", "M", "NOUGAT"};
    private List<String> mDataList = new ArrayList<String>(Arrays.asList(CHANNELS));
    private ViewPagerAdapter mViewPagerAdapter;

    private ViewPager mViewPager;
    private MagicIndicator mMagicIndicator;
    private CommonNavigator mCommonNavigator;

    private Toast mToast; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamictab);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
		mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);

        mMagicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator1);
        mMagicIndicator.setBackgroundColor(Color.parseColor("#d43d3d"));
        mCommonNavigator = new CommonNavigator(this);
        mCommonNavigator.setSkimOver(true);
        mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {

				@Override
				public int getCount() {
					return mDataList.size();
				}

				@Override
				public IPagerTitleView getTitleView(Context context, final int index) {
					ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
					clipPagerTitleView.setText(mDataList.get(index));
					clipPagerTitleView.setTextColor(Color.parseColor("#f2c4c4"));
					clipPagerTitleView.setClipColor(Color.WHITE);
					clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								mViewPager.setCurrentItem(index);
							}
						});
					return clipPagerTitleView;
				}

				@Override
				public IPagerIndicator getIndicator(Context context) {
					return null;
				}
			});
        mMagicIndicator.setNavigator(mCommonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);

        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
    }

    public void randomPage(View view) {
        mDataList.clear();
        int total = new Random().nextInt(CHANNELS.length);
        for (int i = 0; i <= total; i++) {
            mDataList.add(CHANNELS[i]);
        }

        mCommonNavigator.notifyDataSetChanged();    // must call firstly
        mViewPagerAdapter.notifyDataSetChanged();

        mToast.setText("" + mDataList.size() + " page");
        mToast.show();
    }
}

