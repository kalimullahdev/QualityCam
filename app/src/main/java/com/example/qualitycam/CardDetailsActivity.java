package com.example.qualitycam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class CardDetailsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem description_tab, specification_tab, rating_tab;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        tabLayout = findViewById(R.id.cardTabLayout);
        description_tab = findViewById(R.id.description_tab);
        specification_tab = findViewById(R.id.specification_tab);
        rating_tab = findViewById(R.id.rating_tab);
        viewPager = findViewById(R.id.cardDetailviewPager);


        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(tabFragmentAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0 | tab.getPosition()==1 | tab.getPosition()==2){
                    tabFragmentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}