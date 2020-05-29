package com.example.user.marcimexconsult;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class importar extends ListActivity {
    TextView lbl;
    DBController controller = new DBController(this);
    Button btnimport;
    ListView lv;
    final Context context = this;
    ListAdapter adapter;
    ArrayList<HashMap<String, String>> myList;
    public static final int requestcode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importar);

        lbl = (TextView) findViewById(R.id.txtresulttext);
        btnimport = (Button) findViewById(R.id.btnupload);
        lv = getListView();

        btnimport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
                fileintent.setType("gagt/sdf");
                try {
                    startActivityForResult(fileintent, requestcode);
                } catch (ActivityNotFoundException e) {
                    lbl.setText("No activity can handle picking a file. Showing alternatives.");
                }

            }
        });
        myList= controller.getAllCuentasD();
        if (myList.size() != 0) {
            ListView lv = getListView();
            ListAdapter adapter = new SimpleAdapter(importar.this, myList,
                    R.layout.v, new String[]{"dni", "nombres", "credito"}, new int[]{
                    R.id.txtcodigo, R.id.txtnombre, R.id.txttramo});
            setListAdapter(adapter);
            lbl.setText("");
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;
        switch (requestCode) {
            case requestcode:
                String filepath = data.getData().getPath();
                controller = new DBController(getApplicationContext());
                SQLiteDatabase db = controller.getWritableDatabase();
                String tableName = "cuentas";
                db.execSQL("delete from " + tableName);
                try {
                    if (resultCode == RESULT_OK) {
                        try {

                            FileReader file = new FileReader(filepath);

                            BufferedReader buffer = new BufferedReader(file);
                            ContentValues contentValues = new ContentValues();
                            String line = "";
                            db.beginTransaction();


                            while ((line = buffer.readLine()) != null) {

                                String[] str = line.split(";", 23);  // defining 3 columns with null or blank field //values acceptance


                                String dni = str[0].toString();
                                String nombres = str[1].toString();
                                String credito = str[2].toString();
                                String tramo = str[3].toString();
                                String fechaua = str[4].toString();
                                String producto = str[5].toString();
                                String gestor = str[6].toString();
                                String direccion = str[7].toString();
                                String telefono = str[8].toString();
                                String calificacion = str[9].toString();
                                String alerta = str[10].toString();
                                String cuota = str[11].toString();
                                String cp = str[12].toString();
                                String fecha_vcto = str[13].toString();
                                String saldo_vcto = str[14].toString();
                                String saldo_actual = str[15].toString();
                                String saldo_sd = str[16].toString();
                                String morao = str[17].toString();
                                String montoa = str[18].toString();
                                String provision = str[19].toString();
                                String fecha_ultabono = str[20].toString();
                                String abonos_total = str[21].toString();
                                String seguro_des = str[22].toString();


                                contentValues.put("dni", dni);
                                contentValues.put("nombres", nombres);
                                contentValues.put("credito", credito);
                                contentValues.put("tramo", tramo);
                                contentValues.put("fechaua", fechaua);
                                contentValues.put("producto", producto);
                                contentValues.put("gestor", gestor);
                                contentValues.put("direccion", direccion);
                                contentValues.put("telefono", telefono);
                                contentValues.put("calificacion", calificacion);
                                contentValues.put("alerta", alerta);
                                contentValues.put("cuota", cuota);
                                contentValues.put("cp", cp);
                                contentValues.put("fecha_vcto", fecha_vcto);
                                contentValues.put("saldo_vcto", saldo_vcto);
                                contentValues.put("saldo_actual", saldo_actual);
                                contentValues.put("saldo_sd", saldo_sd);
                                contentValues.put("morao", morao);
                                contentValues.put("montoa", montoa);
                                contentValues.put("provision", provision);
                                contentValues.put("fecha_ultabono",fecha_ultabono);
                                contentValues.put("abonos_total", abonos_total);
                                contentValues.put("seguro_des", seguro_des);
                                db.insert(tableName, null, contentValues);
                                //lbl.setBackgroundResource(R.color.orange);
                                //lbl.setText("Exito , datos actualizados.");
                               // Toast.makeText(context, "Datos actualizados con exito", Toast.LENGTH_SHORT).show();

                            }

                            db.setTransactionSuccessful();
                            db.endTransaction();
                            //lbl.setText("Data importada");
                        } catch (IOException e) {
                            if (db.inTransaction())
                                db.endTransaction();
                            Dialog d = new Dialog(this);
                            d.setTitle(e.getMessage().toString() + "first");
                            d.show();
                            // db.endTransaction();
                        }
                    } else {
                        if (db.inTransaction())
                            db.endTransaction();
                        Dialog d = new Dialog(this);
                        d.setTitle("Solo se permite CSV");
                        d.show();
                    }
                } catch (Exception ex) {
                    if (db.inTransaction())
                        db.endTransaction();

                    Dialog d = new Dialog(this);
                    d.setTitle(ex.getMessage().toString() + "second");
                    //d.show();
                    //lbl.setBackgroundResource();
                    //lbl.setText(ex.getMessage().toString() + "second");
                    Toast.makeText(context, "Error, contactar a soporte", Toast.LENGTH_SHORT).show();
                    // db.endTransaction();
                }
        }
        myList= controller.getAllCuentasD();

        if (myList.size() != 0) {
            ListView lv = getListView();
            ListAdapter adapter = new SimpleAdapter(importar.this, myList,
                    R.layout.v, new String[]{"dni", "nombres", "credito"}, new int[]{
                    R.id.txtcodigo, R.id.txtnombre, R.id.txttramo});
            setListAdapter(adapter);
            //lbl.setText("Datos importados");
            Toast.makeText(context, "Datos actualizados con exito", Toast.LENGTH_SHORT).show();
        }
    }
    public void regresar (View v){
        Intent dn=new Intent(importar.this,MainActivity.class);
        startActivity(dn);
    }
}
