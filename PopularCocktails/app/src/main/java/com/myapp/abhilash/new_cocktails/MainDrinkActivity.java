package com.myapp.abhilash.new_cocktails;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainDrinkActivity extends AppCompatActivity  {

    String myUri = "google.com";


    public static final Integer[] imageIDs = {
            R.drawable.cocktail_1,
            R.drawable.cocktail_2,
            R.drawable.cocktail_3,
            R.drawable.cocktail_4,
            R.drawable.cocktail_5,
            R.drawable.cocktail_6,
            R.drawable.cocktail_7,
            R.drawable.cocktail_8,
            R.drawable.cocktail_9,
            R.drawable.cocktail_10

    };

    private android.support.v4.app.FragmentTransaction fragmentTransaction;
    private final static String MENU_SELECTED = "selected";
    private static int selected = R.id.action_showall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drink);
        Toolbar toolbar = null;
        /*if (findViewById(R.id.drink_fragment) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                return;
            }
        }*/
        //Set DrinksGalleryFragment to display

        // Default Toolbar and NavigationView Initialization
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle  = getIntent().getExtras();
        if(bundle!=null)
        {
            Intent intent = new Intent(this, DrinkDetailActivity.class);
            intent.putExtra("cocktail_id", bundle.getString("cocktail_id"));
            startActivity(intent);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        closeOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_drink, menu);
        MenuItem item;
        Bundle bundle = new Bundle();

        DrinksGalleryFragment drinksGalleryFragment= new DrinksGalleryFragment();

        switch (selected){
            case R.id.action_showall:
                item = menu.findItem(R.id.action_showall);
                item.setChecked(true);

                bundle.putString("url", "content://drinkscontentprovider.drinks/drinks");
                 drinksGalleryFragment.setArguments(bundle);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.drink_fragment,drinksGalleryFragment,"showall");
                fragmentTransaction.commit();

                break;

            case R.id.action_alcoholic:
                item = menu.findItem(R.id.action_alcoholic);
                item.setChecked(true);
                bundle.putString("url", "content://drinkscontentprovider.drinks/cat_drinks/1");
                drinksGalleryFragment.setArguments(bundle);
                getSupportFragmentManager().popBackStack();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.drink_fragment,drinksGalleryFragment,"alcoholic");
                fragmentTransaction.commit();

                break;

            case R.id.action_non_alcoholic:
                item = menu.findItem(R.id.action_non_alcoholic);
                item.setChecked(true);
                bundle.putString("url", "content://drinkscontentprovider.drinks/cat_drinks/2");
                drinksGalleryFragment.setArguments(bundle);
                getSupportFragmentManager().popBackStack();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.drink_fragment,drinksGalleryFragment,"non_alcoholic");
                fragmentTransaction.commit();
                break;

            case R.id.favourite_drinks:
                item = menu.findItem(R.id.favourite_drinks);
                item.setChecked(true);
                bundle.putString("url", "content://drinkscontentprovider.drinks/save_drinks");
                drinksGalleryFragment.setArguments(bundle);
                getSupportFragmentManager().popBackStack();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.drink_fragment,drinksGalleryFragment,"fav_drinks");
                fragmentTransaction.commit();
                break;
        }
        MenuItem menuItem = menu.findItem(R.id.action_share);
        // Get the provider and hold onto it to set/change the share intent.
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        // Set share Intent.
        // Note: You can set the share Intent afterwords if you don't want to set it right now.
        mShareActionProvider.setShareIntent(createShareIntent());
        return true;
    }
    // Create and return the Share Intent
    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_intent));
        return shareIntent;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(MENU_SELECTED, selected);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        DrinksGalleryFragment drinksGalleryFragment= new DrinksGalleryFragment();
        //noinspection SimplifiableIfStatement
        selected = id;

        switch(id)
        {
            case R.id.action_showall:
                item.setChecked(true);
                bundle.putString("url", "content://drinkscontentprovider.drinks/drinks");
                drinksGalleryFragment.setArguments(bundle);
                getSupportFragmentManager().popBackStack();
                fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.drink_fragment,drinksGalleryFragment,"all");
                fragmentTransaction.commit();
                Toast.makeText(this, "Selected Show All", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_alcoholic:
                item.setChecked(true);
                bundle.putString("url", "content://drinkscontentprovider.drinks/cat_drinks/1");
                drinksGalleryFragment.setArguments(bundle);
                getSupportFragmentManager().popBackStack();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.drink_fragment,drinksGalleryFragment,"all");
                Toast.makeText(this, "Selected Alcoholic Drinks", Toast.LENGTH_SHORT).show();
                fragmentTransaction.commit();
                break;

            case R.id.action_non_alcoholic:
                item.setChecked(true);
                bundle.putString("url", "content://drinkscontentprovider.drinks/cat_drinks/2");
                drinksGalleryFragment.setArguments(bundle);
                getSupportFragmentManager().popBackStack();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.drink_fragment,drinksGalleryFragment,"all");
                Toast.makeText(this, "Selected Non Alcoholic Drinks", Toast.LENGTH_SHORT).show();
                fragmentTransaction.commit();
                break;

            case R.id.favourite_drinks:
                item.setChecked(true);
                bundle.putString("url", "content://drinkscontentprovider.drinks/save_drinks");
                drinksGalleryFragment.setArguments(bundle);
                getSupportFragmentManager().popBackStack();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.drink_fragment,drinksGalleryFragment,"all");
                Toast.makeText(this, "Selected Favourite Drinks", Toast.LENGTH_SHORT).show();
                fragmentTransaction.commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        closeOptionsMenu();
    }





}

