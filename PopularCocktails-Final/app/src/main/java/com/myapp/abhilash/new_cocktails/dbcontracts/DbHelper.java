package com.myapp.abhilash.new_cocktails.dbcontracts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import com.myapp.abhilash.new_cocktails.MainActivity;
import com.myapp.abhilash.new_cocktails.MyIntentService;

public class DbHelper extends SQLiteOpenHelper{
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "create table if not exists " + CocktailContract.Drinks.TABLE_NAME + " (" +
                    CocktailContract.Drinks._ID + " INTEGER PRIMARY KEY," +

   CocktailContract.Drinks.COLUMN_DRINKID +TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_DRINK_NAME +TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_CATEGORY +TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_ALCOHOLIC +TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_GLASS +TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_INSRUCTIONS +TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_DRINK_THUMB +TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_ING1+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_ING2+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_ING3+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_ING4+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_ING5+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_ING6+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_ING7+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_ING8+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_ING9+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_ING10+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_ING11+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_ING12+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_ING13+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_ING14+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_ING15+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_MEASURE1+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_MEASURE2+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_MEASURE3+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_MEASURE4+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_MEASURE5+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_MEASURE6+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_MEASURE7+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_MEASURE8+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_MEASURE9+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_MEASURE10+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_MEASURE11+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_MEASURE12+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_MEASURE13+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_MEASURE14+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_MEASURE15+TEXT_TYPE +COMMA_SEP+
   CocktailContract.Drinks.COLUMN_FAVOURITE+TEXT_TYPE +")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CocktailContract.Drinks.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "Cocktail.db";

    public DbHelper(MyIntentService  context) {


        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        

    }
    public DbHelper(Context context) {


        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        

    }
    public DbHelper(MainActivity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public Cursor getDrinks(String id, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        SQLiteQueryBuilder sqliteQueryBuilder = new SQLiteQueryBuilder();
        sqliteQueryBuilder.setTables(CocktailContract.Drinks.TABLE_NAME);

        if(id != null) {
            sqliteQueryBuilder.appendWhere(CocktailContract.Drinks.COLUMN_DRINKID + " = " + id);
        }


      ///  String query = sqliteQueryBuilder.buildQuery(projection,selection,null,null,sortOrder,null);
       //

        return sqliteQueryBuilder.query(getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

    }

    public Cursor getSavedDrinks( String[] projection, String selection, String[] selectionArgs, String sortOrder){
        SQLiteQueryBuilder sqliteQueryBuilder = new SQLiteQueryBuilder();
        sqliteQueryBuilder.setTables(CocktailContract.Drinks.TABLE_NAME);


        return sqliteQueryBuilder.query(getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

    }
    public void handleFav(ContentValues values, String where, String[] whereArgs)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(
                CocktailContract.Drinks.TABLE_NAME,values,where,whereArgs);
        
        return;
    }


}
