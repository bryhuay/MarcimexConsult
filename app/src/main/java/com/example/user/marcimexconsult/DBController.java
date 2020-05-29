package com.example.user.marcimexconsult;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DBController extends SQLiteOpenHelper {
    private static final String LOGCAT = null;



    public DBController(Context applicationcontext) {

        super(applicationcontext, "Data.db", null, 1);  // creating DATABASE

        Log.d(LOGCAT, "Created");

    }



    @Override

    public void onCreate(SQLiteDatabase database) {

        String query;


        query = "CREATE TABLE IF NOT EXISTS cuentas ( Id INTEGER PRIMARY KEY, dni TEXT, nombres TEXT, credito TEXT, tramo TEXT, fechaua TEXT, producto TEXT, gestor TEXT, direccion TEXT, telefono TEXT , calificacion TEXT, alerta TEXT, cuota TEXT, cp TEXT , fecha_vcto TEXT, saldo_vcto TEXT, saldo_actual TEXT , saldo_sd TEXT, morao TEXT, montoa TEXT, provision TEXT , fecha_ultabono TEXT, abonos_total TEXT, seguro_des TEXT)";

        database.execSQL(query);

    }





    @Override

    public void onUpgrade(SQLiteDatabase database, int version_old,

                          int current_version) {

        String query;

        query = "DROP TABLE IF EXISTS cuentas";

        database.execSQL(query);

        onCreate(database);

    }



    public ArrayList<HashMap<String, String>> getAllCuentas() {

        ArrayList<HashMap<String, String>> proList;

        proList = new ArrayList<HashMap<String, String>>();

        String selectQuery = "SELECT  * FROM cuentas";

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                //Id, Company,Name,Price

                HashMap<String, String> map = new HashMap<String, String>();

                map.put("Id", cursor.getString(0));

                map.put("dni", cursor.getString(1));

                map.put("nombres", cursor.getString(2));

                map.put("credito", cursor.getString(3));
                map.put("tramo", cursor.getString(4));
                map.put("fechaua", cursor.getString(5));
                map.put("producto", cursor.getString(6));
                map.put("gestor", cursor.getString(7));
                map.put("direccion", cursor.getString(8));
                map.put("telefono", cursor.getString(9));
                map.put("calificacion", cursor.getString(10));
                map.put("alerta ", cursor.getString(11));
                map.put("cuota", cursor.getString(12));
                map.put("cp", cursor.getString(13));
                map.put("fecha_vcto", cursor.getString(14));
                map.put("saldo_vcto", cursor.getString(15));
                map.put("saldo_actual", cursor.getString(16));
                map.put("saldo_sd", cursor.getString(17));
                map.put("morao", cursor.getString(18));
                map.put("montoa", cursor.getString(19));
                map.put("provision", cursor.getString(20));
                map.put("fecha_ultabono", cursor.getString(21));
                map.put("abonos_total", cursor.getString(22));
                map.put("seguro_des", cursor.getString(23));

                proList.add(map);

            } while (cursor.moveToNext());

        }



        return proList;

    }
    public ArrayList<HashMap<String, String>> getAllCuentasD() {

        ArrayList<HashMap<String, String>> proList;

        proList = new ArrayList<HashMap<String, String>>();

        String selectQuery = "SELECT  * FROM cuentas";

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                //Id, Company,Name,Price

                HashMap<String, String> map = new HashMap<String, String>();

                map.put("dni", cursor.getString(1));

                map.put("nombres", cursor.getString(2));

                map.put("credito", cursor.getString(3));



                proList.add(map);

            } while (cursor.moveToNext());

        }



        return proList;

    }
}
