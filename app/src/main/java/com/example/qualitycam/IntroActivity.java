package com.example.qualitycam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    ViewPager viewPager;
    Button button;
    TabLayout tabLayout;

    IntroItemAdapter introItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        viewPager = findViewById(R.id.viewPager);
        button = findViewById(R.id.btn);
        tabLayout = findViewById(R.id.tabLayout);

        List<IntroItem> data = new ArrayList<>();

        IntroItem introItem = new IntroItem(R.drawable.walkthrough_1,
                "Order Your Camera Online",
                " Browse through the best camera\n" +
                        " and get your camera delivered to\n" +
                        " you at home! "
                );

        IntroItem introItem2 = new IntroItem(R.drawable.walkthrough_2,
                "Record High Quality Video",
                "Our app shows, high \n" +
                        "quality cameras in the market,\n" +
                        "which you can buy and can create\n" +
                        "high quality videos "
        );

        IntroItem introItem3 = new IntroItem(R.drawable.walkthrough_3,
                "Search And Get Best Camera",
                "The app has special features,\n" +
                        "from which you can search cameras,\n" +
                        "according your requirement and \n" +
                        "can purchase it."
        );

        data.add(introItem);
        data.add(introItem2);
        data.add(introItem3);


        introItemAdapter = new IntroItemAdapter(IntroActivity.this, data);

        viewPager.setAdapter(introItemAdapter);
        tabLayout.setupWithViewPager(viewPager);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}