package com.myapp.abhilash.new_cocktails.contentproviders;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;


import com.myapp.abhilash.new_cocktails.dbcontracts.CocktailContract;
import com.myapp.abhilash.new_cocktails.dbcontracts.DbHelper;


public class DrinksProvider extends ContentProvider{

    private static final String PROVIDER_NAME = "drinkscontentprovider.drinks";

    private static final int DRINKS = 1;
    private static final int DRINK_ID = 2;
    private static final int SAVE_DRINKS = 3;
    private static final int SAVE_DRINK_ID = 4;
    private static final int CAT_DRINKS = 4;
    private static final UriMatcher uriMatcher = getUriMatcher();
    private static UriMatcher getUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "drinks", DRINKS);
        uriMatcher.addURI(PROVIDER_NAME, "drinks/#", DRINK_ID);
        uriMatcher.addURI(PROVIDER_NAME, "save_drinks", SAVE_DRINKS);
        uriMatcher.addURI(PROVIDER_NAME, "save_drinks/#", SAVE_DRINK_ID);
        uriMatcher.addURI(PROVIDER_NAME, "cat_drinks/#", CAT_DRINKS);

        return uriMatcher;
    }

    private DbHelper mDbHelper;
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case DRINKS:
                return "vnd.android.cursor.dir/vnd.com.drinkscontentprovider.drinks";
            case DRINK_ID:
                return "vnd.android.cursor.item/vnd.com.drinkscontentprovider.drinks";

        }
        return "";
    }

    @Override
    public boolean onCreate() {
         mDbHelper = new DbHelper(getContext().getApplicationContext());
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor c = null;
        
        String id = null;
        switch (uriMatcher.match(uri)) {
            case DRINK_ID: //Query is for one single image. Get the ID from the URI.
                id = uri.getPathSegments().get(1);
                c = mDbHelper.getDrinks(id, projection, selection, selectionArgs, sortOrder);
                break;
            case DRINKS:
                c = mDbHelper.getDrinks(null, projection, selection, selectionArgs, sortOrder);
                break;
            case SAVE_DRINKS:
                
                c = mDbHelper.getSavedDrinks(projection, CocktailContract.Drinks.COLUMN_FAVOURITE+"= ?", new String[]{"YES"}, sortOrder);
                break;
            case CAT_DRINKS:
                id = uri.getPathSegments().get(1);
                
                int i = Integer.parseInt(id);
                if(i==1)
                    c = mDbHelper.getDrinks(null,projection,CocktailContract.Drinks.COLUMN_ALCOHOLIC+"=?",new String[]{"Alcoholic"}, sortOrder);
                else
                    c = mDbHelper.getDrinks(null,projection,CocktailContract.Drinks.COLUMN_ALCOHOLIC+"=?",new String[]{"Non alcoholic"}, sortOrder);

                break;
        }
        return c;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {


        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if(uriMatcher.match(uri) == SAVE_DRINK_ID) {
            //Query is for one single image. Get the ID from the URI.
            String id = uri.getPathSegments().get(1);
            selection = CocktailContract.Drinks.COLUMN_DRINKID+"=?";
            selectionArgs = new String[]{id};
            
            mDbHelper.handleFav(values,selection,selectionArgs);

        }
        return 0;
    }
}


