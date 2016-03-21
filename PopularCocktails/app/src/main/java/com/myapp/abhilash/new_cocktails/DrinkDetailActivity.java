package com.myapp.abhilash.new_cocktails;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class DrinkDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);

        Bundle bundle = getIntent().getExtras();


        DrinkDetailFragment drinkDetailFragment = new DrinkDetailFragment();
        drinkDetailFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.drink_detail_fragment, drinkDetailFragment);
        fragmentTransaction.commit();

        //Banner ads
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

}
