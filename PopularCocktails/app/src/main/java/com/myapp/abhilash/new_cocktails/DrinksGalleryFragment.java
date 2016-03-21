package com.myapp.abhilash.new_cocktails;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.myapp.abhilash.new_cocktails.dbcontracts.CocktailContract;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import java.util.ArrayList;

import static com.myapp.abhilash.new_cocktails.R.id.empty_gridview;


/**
 * A simple {@link Fragment} subclass.
 */
public class
        DrinksGalleryFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {

    private static final int LOADER_ID=1;
    private SimpleCursorAdapter adapter;
    private GridView gridView;
    private ImageView imageView;
    private final ArrayList<String> drinkIds = new ArrayList<>();
    private  TextView textView;


    public DrinksGalleryFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drinks_gallery, container, false);
        Bundle bundle = getArguments();
        getLoaderManager().initLoader(LOADER_ID,bundle, this);
        gridView = (GridView) view.findViewById(R.id.gridDrinks);
        textView = (TextView) view.findViewById(R.id.empty_gridview);
        String[] columns = new String[] { CocktailContract.Drinks.COLUMN_DRINK_NAME,CocktailContract.Drinks.COLUMN_DRINK_THUMB};
        // THE XML DEFINED VIEWS WHICH THE DATA WILL BE BOUND TO
        int[] to = new int[] { R.id.cocktailName, R.id.cocktailImage };

        adapter = new SimpleCursorAdapter(
                            getActivity(),
                            R.layout.grid_view_item,
                            null,
                            columns,to,0);

        gridView.setOnItemClickListener(this);

        // Inflate the layout for this fragment
        return view;

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse(args.getString("url","content://drinkscontentprovider.drinks/drinks"));
        return (new CursorLoader(
                getActivity(),
                uri,
                new String[]{CocktailContract.Drinks.COLUMN_ALCOHOLIC,CocktailContract.Drinks._ID,CocktailContract.Drinks.COLUMN_DRINKID,CocktailContract.Drinks.COLUMN_DRINK_NAME,CocktailContract.Drinks.COLUMN_DRINK_THUMB},
                null,
                null,
                null));

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if (data != null) {
            if(!data.moveToFirst()){
                gridView.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
                return;
            }
        }
        adapter.swapCursor(data);
        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (view.getId() == R.id.cocktailImage)
                {

                    imageView = null;
                    String imageID = cursor.getString(cursor.getColumnIndex(CocktailContract.Drinks.COLUMN_DRINK_THUMB));
                    int id = Integer.parseInt(imageID);
                    id--;
                    imageView = (ImageView) view;
                    Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(),MainDrinkActivity.imageIDs[id]);
                    bitmap = Bitmap.createScaledBitmap(bitmap,200,200,true);
                    imageView.setImageBitmap(bitmap);

                    return true;

                }
                return false;}

        });
        if (data != null) {
            data.moveToFirst();
        }
        for(int i = 0; i< (data != null ? data.getCount() : 0); i++)
        {
            drinkIds.add(data.getString(data.getColumnIndex(CocktailContract.Drinks.COLUMN_DRINKID)));
            data.moveToNext();
        }

        gridView.setAdapter(adapter);
        //data.close();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Bundle bundle = getArguments();
        getLoaderManager().initLoader(LOADER_ID,bundle, this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), DrinkDetailActivity.class);
        intent.putExtra("cocktail_id", drinkIds.get(position));
        startActivity(intent);


    }
}