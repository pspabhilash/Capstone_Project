package com.myapp.abhilash.new_cocktails.widget;


import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.myapp.abhilash.new_cocktails.MainDrinkActivity;
import com.myapp.abhilash.new_cocktails.R;
import com.myapp.abhilash.new_cocktails.dbcontracts.CocktailContract;

import java.util.ArrayList;


class WidgetViewFactory implements RemoteViewsService.RemoteViewsFactory
{
    private Context context = null;
    private static final String[] WIDGET_COLUMNS = {
            CocktailContract.Drinks.COLUMN_DRINK_NAME,
            CocktailContract.Drinks.COLUMN_DRINK_THUMB,
            CocktailContract.Drinks.COLUMN_CATEGORY,
            CocktailContract.Drinks.COLUMN_DRINKID

    };
    private ArrayList<String> names;
    private ArrayList<String> cats;
    private int[] imageids;
    private int[] ids;

    public WidgetViewFactory(Context applicationContext,Intent intent) {
        this.context = applicationContext;
        int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        getData();
    }

    private void getData() {
        Uri myUri = Uri.parse("content://drinkscontentprovider.drinks/save_drinks");
        Cursor data = context.getContentResolver().query(myUri, WIDGET_COLUMNS, null, null, null);
        assert data != null;
        imageids = new int[data.getCount()];
        ids = new int[data.getCount()];
        names = new ArrayList<>();
        cats = new ArrayList<>();
        if(!data.moveToFirst())
        {
            return;
        }
        int i =0;
        do{
            imageids[i] = data.getInt(data.getColumnIndex(CocktailContract.Drinks.COLUMN_DRINK_THUMB));
            ids[i]= data.getInt(data.getColumnIndex(CocktailContract.Drinks.COLUMN_DRINKID));
            names.add(i, data.getString(data.getColumnIndex(CocktailContract.Drinks.COLUMN_DRINK_NAME)));
            cats.add(i, data.getString(data.getColumnIndex(CocktailContract.Drinks.COLUMN_CATEGORY)));
            i++;
        }while(data.moveToNext());
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews row = new RemoteViews(context.getPackageName(), R.layout.widget_detail_list_item);
        
        row.setTextViewText(R.id.widget_cocktail_name,names.get(position));
        row.setTextViewText(R.id.widget_cocktail_type,cats.get(position));
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), MainDrinkActivity.imageIDs[imageids[position]]);
        bitmap = Bitmap.createScaledBitmap(bitmap,200,200,true);
        row.setImageViewBitmap(R.id.widget_image,bitmap);
        Intent intent = new Intent();
        intent.putExtra("cocktail_id", Integer.toString(ids[position]));
        row.setOnClickFillInIntent(R.id.list_item_layout, intent);
        return row;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {
    }
}
