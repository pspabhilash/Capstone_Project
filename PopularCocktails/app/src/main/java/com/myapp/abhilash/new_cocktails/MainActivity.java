package com.myapp.abhilash.new_cocktails;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.myapp.abhilash.new_cocktails.dbcontracts.CocktailContract;
import com.myapp.abhilash.new_cocktails.dbcontracts.DbHelper;



public class MainActivity extends AppCompatActivity {



    private TextView textView;
    private ProgressBar pBar;
    private  TextView pCount;
    private ResponseReceiver receiver;
    private IntentFilter filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        pBar = (ProgressBar) findViewById(R.id.progressBar);
        pCount= (TextView) findViewById(R.id.procount);



        textView = (TextView) findViewById(R.id.textView);
        if(checkDB()) {
            
            Intent msgIntent = new Intent(this, MyIntentService.class);

            startService(msgIntent);

            filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
            filter.addCategory(Intent.CATEGORY_DEFAULT);
            receiver = new ResponseReceiver();
            registerReceiver(receiver, filter);
        }
        else
        {
            Intent intent1 = new Intent(MainActivity.this, MainDrinkActivity.class);
            MainActivity.this.startActivity(intent1);
        }


    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(receiver);
    }

    @Override
    protected void onPostResume() {
        filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        receiver = new ResponseReceiver();
        super.onPostResume();
        registerReceiver(receiver,filter);
    }

    private boolean checkDB() {

            DbHelper mDbHelper = new DbHelper(this);
            SQLiteDatabase db = mDbHelper.getReadableDatabase();

            //Cursor c = db.query(CocktailContract.Drinks.TABLE_NAME,null,"id=?",new String[]{"15395"},null,null,null);
            Cursor c = db.query(CocktailContract.Drinks.TABLE_NAME,null,null,null,null,null,null);
            

            try {
                if (c.getCount() == 0) {
                    return true;
                } else if (c.getCount() != 400) {
                    db.execSQL("DROP TABLE IF EXISTS " + CocktailContract.Drinks.TABLE_NAME);
                    
                    return true;
                }

                return false;
            }finally {
                c.close();
            }

        }


    public class ResponseReceiver extends BroadcastReceiver {

         public static final String ACTION_RESP = "action_response";
         int status;
        @Override
        public void onReceive(Context context, Intent intent) {

            status = intent.getIntExtra("status",0);
            pBar.setProgress(status);
            pCount.setText(Integer.toString(status));
            if(intent.getIntExtra("status",0)==100)
            {
                textView.setText(R.string.SaveinDb);
                pBar.setProgress(0);

            }
            if(intent.hasExtra("done")) {


                Intent intent1 = new Intent(MainActivity.this, MainDrinkActivity.class);
                MainActivity.this.startActivity(intent1);

            }
        }
    }

}


