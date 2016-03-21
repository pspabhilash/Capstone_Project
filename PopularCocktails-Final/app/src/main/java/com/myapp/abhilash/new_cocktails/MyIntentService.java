package com.myapp.abhilash.new_cocktails;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.GsonBuilder;
import com.myapp.abhilash.new_cocktails.dbcontracts.CocktailContract;
import com.myapp.abhilash.new_cocktails.dbcontracts.DbHelper;
import com.myapp.abhilash.new_cocktails.json_models.DrinkID;
import com.myapp.abhilash.new_cocktails.json_models.Drinks;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    private final String[] idUrls = {"http://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic",
            "http://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Non%20alcoholic"
    };
    private HttpURLConnection urlConnection;
    private final List<Drinks.DrinksEntity> drinks_list= new ArrayList<>();
    private final List<DrinkID.DrinksEntity> drink_IDs= new ArrayList<>();
    private DbHelper dbHelper = null;
    private final Intent msgIntent = new Intent();
    @Override
    protected void onHandleIntent(Intent intent) {
        dbHelper = new DbHelper(this);

        if (intent != null) {

            String jsonString;

            for (String idUrl : idUrls) {
                jsonString = getJsonReader(idUrl);

                DrinkID drinkID = new GsonBuilder().create().fromJson(jsonString, DrinkID.class);


                //temp_drink_IDs = drinkID.getDrinks();
                drink_IDs.addAll(drinkID.getDrinks());

                //   
            }
            for(int i=0;i<drink_IDs.size();i++)//drink_IDs.size()
            {

                jsonString = getJsonReader("http://www.thecocktaildb.com/api/json/v1/1/lookup.php?i="+ drink_IDs.get(i).getIdDrink());
                Drinks drinks = new GsonBuilder().create().fromJson(jsonString, Drinks.class);
                drinks_list.addAll(drinks.getDrinks());
                if(i%4==0)
                {

                    msgIntent.setAction(MainActivity.ResponseReceiver.ACTION_RESP);
                    msgIntent.putExtra("status", i/4);
                    sendBroadcast(msgIntent);
                }
            }
            msgIntent.setAction(MainActivity.ResponseReceiver.ACTION_RESP);
            msgIntent.putExtra("status", 100);
            sendBroadcast(msgIntent);
            saveInDb(drinks_list);



        }
    }

    private String getJsonReader(String base){
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(base);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;

            while ((line = reader.readLine()) != null) {
                   
                result.append(line);
            }

        }catch( Exception e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }
        return result.toString();

    }

    private void saveInDb(List<Drinks.DrinksEntity> drinks_list) {

           


        // Gets the data repository in write mode

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int i=0;
        for (Drinks.DrinksEntity drink : drinks_list)
        {
            i++;
               


            // Create a new map of values, where column names are the keys

            ContentValues values = new ContentValues();
            int imgID= (i%10)+1;
            values.put(CocktailContract.Drinks.COLUMN_DRINKID ,drink.idDrink );
            values.put(CocktailContract.Drinks.COLUMN_DRINK_NAME ,drink.strDrink);
            values.put(CocktailContract.Drinks.COLUMN_CATEGORY ,drink.strCategory);
            values.put(CocktailContract.Drinks.COLUMN_ALCOHOLIC , drink.strAlcoholic);
            values.put(CocktailContract.Drinks.COLUMN_GLASS ,drink.strGlass);
            values.put(CocktailContract.Drinks.COLUMN_INSRUCTIONS , drink.strInstructions);
            values.put(CocktailContract.Drinks.COLUMN_DRINK_THUMB ,Integer.toString(imgID));
            values.put(CocktailContract.Drinks.COLUMN_ING1, drink.strIngredient1);
            values.put(CocktailContract.Drinks.COLUMN_ING2, drink.strIngredient2);
            values.put(CocktailContract.Drinks.COLUMN_ING3, drink.strIngredient3);
            values.put(CocktailContract.Drinks.COLUMN_ING4, drink.strIngredient4);
            values.put(CocktailContract.Drinks.COLUMN_ING5, drink.strIngredient5);
            values.put(CocktailContract.Drinks.COLUMN_ING6, drink.strIngredient6);
            values.put(CocktailContract.Drinks.COLUMN_ING7, drink.strIngredient7);
            values.put(CocktailContract.Drinks.COLUMN_ING8, drink.strIngredient8);
            values.put(CocktailContract.Drinks.COLUMN_ING9, drink.strIngredient9);
            values.put(CocktailContract.Drinks.COLUMN_ING10, drink.strIngredient10);
            values.put(CocktailContract.Drinks.COLUMN_ING11, drink.strIngredient11);
            values.put(CocktailContract.Drinks.COLUMN_ING12, drink.strIngredient12);
            values.put(CocktailContract.Drinks.COLUMN_ING13, drink.strIngredient13);
            values.put(CocktailContract.Drinks.COLUMN_ING14, drink.strIngredient14);
            values.put(CocktailContract.Drinks.COLUMN_ING15, drink.strIngredient15);
            values.put(CocktailContract.Drinks.COLUMN_MEASURE1, drink.strMeasure1);
            values.put(CocktailContract.Drinks.COLUMN_MEASURE2, drink.strMeasure2);
            values.put(CocktailContract.Drinks.COLUMN_MEASURE3, drink.strMeasure3);
            values.put(CocktailContract.Drinks.COLUMN_MEASURE4, drink.strMeasure4);
            values.put(CocktailContract.Drinks.COLUMN_MEASURE5, drink.strMeasure5);
            values.put(CocktailContract.Drinks.COLUMN_MEASURE6, drink.strMeasure6);
            values.put(CocktailContract.Drinks.COLUMN_MEASURE7,  drink.strMeasure7);
            values.put(CocktailContract.Drinks.COLUMN_MEASURE8, drink.strMeasure8);
            values.put(CocktailContract.Drinks.COLUMN_MEASURE9, drink.strMeasure9);
            values.put(CocktailContract.Drinks.COLUMN_MEASURE10,  drink.strMeasure10);
            values.put(CocktailContract.Drinks.COLUMN_MEASURE11,  drink.strMeasure11);
            values.put(CocktailContract.Drinks.COLUMN_MEASURE12,  drink.strMeasure12);
            values.put(CocktailContract.Drinks.COLUMN_MEASURE13,  drink.strMeasure13);
            values.put(CocktailContract.Drinks.COLUMN_MEASURE14,  drink.strMeasure14);
            values.put(CocktailContract.Drinks.COLUMN_MEASURE15,  drink.strMeasure15);
            values.put(CocktailContract.Drinks.COLUMN_FAVOURITE,  "NO");
               
            // Insert the new row, returning the primary key value of the new row
            long newRowId;
            newRowId = db.insert(
                    CocktailContract.Drinks.TABLE_NAME,null,
                    values);
               
            if(newRowId%4==0) {
                msgIntent.setAction(MainActivity.ResponseReceiver.ACTION_RESP);
                msgIntent.putExtra("status", i / 4);
                   
                sendBroadcast(msgIntent);
            }
               

        }
        msgIntent.setAction(MainActivity.ResponseReceiver.ACTION_RESP);
        msgIntent.putExtra("done", "");
        sendBroadcast(msgIntent);
    }


}
