package com.myapp.abhilash.new_cocktails;


import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.myapp.abhilash.new_cocktails.dbcontracts.CocktailContract;
import com.myapp.abhilash.new_cocktails.widget.CocktailWidget;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrinkDetailFragment extends Fragment {

    private String cocktailId;


    private final String  LOG_TAG = DrinkDetailFragment.class.getSimpleName();

    private TextView tvIngredient1;
    private TextView tvIngredient2;
    private TextView tvIngredient3;
    private TextView tvIngredient4;
    private TextView tvIngredient5;
    private TextView tvIngredient6;
    private TextView tvIngredient7;
    private TextView tvIngredient8;
    private TextView tvIngredient9;
    private TextView tvIngredient10;

    private TextView tvMeasure1;
    private TextView tvMeasure2;
    private TextView tvMeasure3;
    private TextView tvMeasure4;
    private TextView tvMeasure5;
    private TextView tvMeasure6;
    private TextView tvMeasure7;
    private TextView tvMeasure8;
    private TextView tvMeasure9;
    private TextView tvMeasure10;

    private TextView tvDash1;
    private TextView tvDash2;
    private TextView tvDash3;
    private TextView tvDash4;
    private TextView tvDash5;
    private TextView tvDash6;
    private TextView tvDash7;
    private TextView tvDash8;
    private TextView tvDash9;
    private TextView tvDash10;

    private String favStatus;
    public DrinkDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drink_detail, container, false);

        TextView tvName = (TextView) rootView.findViewById(R.id.cocktail_name);
        TextView tvByline = (TextView) rootView.findViewById(R.id.cocktail_byline);
        ImageView ivCocktailIcon = (ImageView) rootView.findViewById(R.id.cocktail_glass_icon);
        TextView tvMethod = (TextView) rootView.findViewById(R.id.cocktail_method);
        ImageView ivPhotoBackground = (ImageView) rootView.findViewById(R.id.photo);

        tvIngredient1 = (TextView) rootView.findViewById(R.id.ingredient_1);
        tvIngredient2 = (TextView) rootView.findViewById(R.id.ingredient_2);
        tvIngredient3 = (TextView) rootView.findViewById(R.id.ingredient_3);
        tvIngredient4 = (TextView) rootView.findViewById(R.id.ingredient_4);
        tvIngredient5 = (TextView) rootView.findViewById(R.id.ingredient_5);
        tvIngredient6 = (TextView) rootView.findViewById(R.id.ingredient_6);
        tvIngredient7 = (TextView) rootView.findViewById(R.id.ingredient_7);
        tvIngredient8 = (TextView) rootView.findViewById(R.id.ingredient_8);
        tvIngredient9 = (TextView) rootView.findViewById(R.id.ingredient_9);
        tvIngredient10 = (TextView) rootView.findViewById(R.id.ingredient_10);

        tvMeasure1 = (TextView) rootView.findViewById(R.id.measure_1);
        tvMeasure2 = (TextView) rootView.findViewById(R.id.measure_2);
        tvMeasure3 = (TextView) rootView.findViewById(R.id.measure_3);
        tvMeasure4 = (TextView) rootView.findViewById(R.id.measure_4);
        tvMeasure5 = (TextView) rootView.findViewById(R.id.measure_5);
        tvMeasure6 = (TextView) rootView.findViewById(R.id.measure_6);
        tvMeasure7 = (TextView) rootView.findViewById(R.id.measure_7);
        tvMeasure8 = (TextView) rootView.findViewById(R.id.measure_8);
        tvMeasure9 = (TextView) rootView.findViewById(R.id.measure_9);
        tvMeasure10 = (TextView) rootView.findViewById(R.id.measure_10);

        tvDash1 = (TextView) rootView.findViewById(R.id.dash_1);
        tvDash2 = (TextView) rootView.findViewById(R.id.dash_2);
        tvDash3 = (TextView) rootView.findViewById(R.id.dash_3);
        tvDash4 = (TextView) rootView.findViewById(R.id.dash_4);
        tvDash5 = (TextView) rootView.findViewById(R.id.dash_5);
        tvDash6 = (TextView) rootView.findViewById(R.id.dash_6);
        tvDash7 = (TextView) rootView.findViewById(R.id.dash_7);
        tvDash8 = (TextView) rootView.findViewById(R.id.dash_8);
        tvDash9 = (TextView) rootView.findViewById(R.id.dash_9);
        tvDash10 = (TextView) rootView.findViewById(R.id.dash_10);
        // Inflate the layout for this fragment

        if (getArguments() == null) {
             
            Toast.makeText(getActivity(), "Error getting cocktail details.", Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();
        } else {
            cocktailId = getArguments().getString("cocktail_id");

            Uri uri = Uri.parse("content://drinkscontentprovider.drinks/drinks/"+cocktailId);
            Cursor c =getContext().getContentResolver().query(uri, null, null, null, null, null);
            String dash = " - ";
            if(c.moveToFirst()){
                favStatus = c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_FAVOURITE));
                tvName.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_DRINK_NAME)));
                String text = c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ALCOHOLIC))+ dash + c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_CATEGORY));
                tvByline.setText(text);

                String imageID = c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_DRINK_THUMB));
                int id = Integer.parseInt(imageID);
                id--;

                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(),MainDrinkActivity.imageIDs[id]);
                bitmap = Bitmap.createScaledBitmap(bitmap,200,200,true);
                ivCocktailIcon.setImageBitmap(bitmap);
                ivPhotoBackground.setImageBitmap(bitmap);

                tvMethod.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_INSRUCTIONS)));


                tvIngredient1.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING1)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING1)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING1)), "")) {
                    setHidden(1);
                }

                tvIngredient2.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING2)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING2)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING2)), "")) {
                     
                    setHidden(2);
                }

                tvIngredient3.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING3)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING3)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING3)), "")) {
                    setHidden(3);
                }

                tvIngredient4.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING4)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING4)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING4)), "")) {
                    setHidden(4);
                }

                tvIngredient5.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING5)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING5)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING5)), "")) {
                    setHidden(5); 
                }

                tvIngredient6.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING6)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING6)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING6)), "")) {
                    setHidden(6);
                }

                tvIngredient7.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING7)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING7)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING7)), "")) {
                    setHidden(7);
                }

                tvIngredient8.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING8)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING8)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING8)), "")) {
                    setHidden(8);
                }

                tvIngredient9.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING9)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING9)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING9)), "")) {
                    setHidden(9);
                }

                tvIngredient10.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING10)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING10)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_ING10)), "")) {
                    setHidden(10);
                }

                tvMeasure1.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE1)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE1)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE1)), "")) {
                    setHidden(1);
                }

                tvMeasure2.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE2)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE2)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE2)), "")) {
                    setHidden(2);
                }

                tvMeasure3.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE3)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE3)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE3)), "")) {
                    setHidden(3);
                }

                tvMeasure4.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE4)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE4)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE4)), "")) {
                    setHidden(4);
                }

                tvMeasure5.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE5)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE5)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE5)), "")) {
                    setHidden(5);
                }

                tvMeasure6.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE6)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE6)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE6)), "")) {
                    setHidden(6);
                }

                tvMeasure7.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE7)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE7)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE7)), "")) {
                    setHidden(7);
                }

                tvMeasure8.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE8)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE8)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE8)), "")) {
                    setHidden(8);
                }

                tvMeasure9.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE9)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE9)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE9)), "")) {
                    setHidden(9);
                }

                tvMeasure10.setText(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE10)));
                if (c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE10)) == null || Objects.equals(c.getString(c.getColumnIndex(CocktailContract.Drinks.COLUMN_MEASURE10)), "")) {
                    setHidden(10);
                }
            }
            c.close();
        }
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        //((AppCompatActivity) getActivity()).getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        final ImageView ivShareFab = (ImageView) rootView.findViewById(R.id.share_fab);
         
        if(favStatus.equals("NO")) {

            ivShareFab.setImageResource(R.drawable.ic_favorite_border_white_18dp);
        }
        else
        {

            ivShareFab.setImageResource(R.drawable.ic_favorite_white_18dp);
        }
        ivShareFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().onBackPressed();
                //Utilities.saveCocktailId(getContext(), cocktailId);
                 

                if(favStatus.equals("NO")) {
                     
                    ContentValues values = new ContentValues();
                    values.put(CocktailContract.Drinks.COLUMN_FAVOURITE, "YES");
                    Uri uri = Uri.parse("content://drinkscontentprovider.drinks/save_drinks/"+cocktailId);
                    getContext().getContentResolver().update(uri, values, null, null);
                    Toast.makeText(getActivity(), "Cocktail added to favourite list.", Toast.LENGTH_SHORT).show();
                    ivShareFab.setImageResource(R.drawable.ic_favorite_white_18dp);
                    favStatus="YES";
                }
                else
                {
                     
                    ContentValues values = new ContentValues();
                    values.put(CocktailContract.Drinks.COLUMN_FAVOURITE, "NO");
                    Uri uri = Uri.parse("content://drinkscontentprovider.drinks/save_drinks/"+cocktailId);
                    getContext().getContentResolver().update(uri, values,null,null);
                    Toast.makeText(getActivity(), "Cocktail removed from favourite list.", Toast.LENGTH_SHORT).show();
                    ivShareFab.setImageResource(R.drawable.ic_favorite_border_white_18dp);
                    favStatus="NO";
                }
                Context context = getContext();
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.cocktail_widget);
                ComponentName thisWidget = new ComponentName(context, CocktailWidget.class);
               appWidgetManager.updateAppWidget(thisWidget, remoteViews);

            }

        });

        /*AdView mAdView = (AdView)rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/

        return rootView;
    }
    private void setHidden(int aHidden) {
         
        switch (aHidden) {
            case 1:
                tvIngredient1.setVisibility(View.GONE);
                tvDash1.setVisibility(View.GONE);
                tvMeasure1.setVisibility(View.GONE);
                break;
            case 2:
                tvIngredient2.setVisibility(View.GONE);
                tvDash2.setVisibility(View.GONE);
                tvMeasure2.setVisibility(View.GONE);
                break;
            case 3:
                tvIngredient3.setVisibility(View.GONE);
                tvDash3.setVisibility(View.GONE);
                tvMeasure3.setVisibility(View.GONE);
                break;
            case 4:
                tvIngredient4.setVisibility(View.GONE);
                tvDash4.setVisibility(View.GONE);
                tvMeasure4.setVisibility(View.GONE);
                break;
            case 5:
                tvIngredient5.setVisibility(View.GONE);
                tvDash5.setVisibility(View.GONE);
                tvMeasure5.setVisibility(View.GONE);
                break;
            case 6:
                tvIngredient6.setVisibility(View.GONE);
                tvDash6.setVisibility(View.GONE);
                tvMeasure6.setVisibility(View.GONE);
                break;
            case 7:
                tvIngredient7.setVisibility(View.GONE);
                tvDash7.setVisibility(View.GONE);
                tvMeasure7.setVisibility(View.GONE);
                break;
            case 8:
                tvIngredient8.setVisibility(View.GONE);
                tvDash8.setVisibility(View.GONE);
                tvMeasure8.setVisibility(View.GONE);
                break;
            case 9:
                tvIngredient9.setVisibility(View.GONE);
                tvDash9.setVisibility(View.GONE);
                tvMeasure9.setVisibility(View.GONE);
                break;
            case 10:
                tvIngredient10.setVisibility(View.GONE);
                tvDash10.setVisibility(View.GONE);
                tvMeasure10.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

}
