package com.example.user.marcimexconsult;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class detalle extends AppCompatActivity {
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle recibido = this.getIntent().getExtras();
        if(recibido != null){
            id = getIntent().getExtras().getString("id");
            int idn = Integer.parseInt(id);
            TextView persona = (TextView)findViewById(R.id.lblNombre);
            ListView listado = (ListView)findViewById(R.id.lstDatos);
            DBController dbc = new DBController(this);
            SQLiteDatabase db = dbc.getWritableDatabase();
            String query = "Select * from cuentas Where Id = "+idn+"";
            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();


            ArrayList<String> lista = new ArrayList<>();
            String Id = cursor.getString(0);
            String dni = cursor.getString(1);
            String nombres = cursor.getString(2);
            String credito = cursor.getString(3);
            String tramo = cursor.getString(4);
            String fechaua = cursor.getString(5);
            String producto = cursor.getString(6);
            String gestor = cursor.getString(7);
            String direccion = cursor.getString(8);
            String telefono = cursor.getString(9);
            String calificacion = cursor.getString(10);
            String alerta = cursor.getString(11);
            String cuota = cursor.getString(12);
            String cp = cursor.getString(13);
            String fecha_vcto = cursor.getString(14);
            String saldo_vcto = cursor.getString(15);
            String saldo_actual = cursor.getString(16);
            String saldo_sd = cursor.getString(17);
            String morao  = cursor.getString(18);
            String montoa = cursor.getString(19);
            String provision = cursor.getString(20);
            String fecha_ultabono = cursor.getString(21);
            String abonos_total = cursor.getString(22);
            String seguro_des = cursor.getString(23);


            cursor.close();







            int dni_int = Integer.parseInt(dni);
            String query2 = "Select count(*) from cuentas Where dni = "+dni_int+"";
            Cursor cursor2 = db.rawQuery(query2, null);
            cursor2.moveToFirst();

            int nroc = cursor2.getInt(0);

            cursor2.close();




            String query3 = "Select producto from cuentas Where dni = "+dni_int+"";
            ArrayList<String> listapro = new ArrayList<>();
            Cursor cursor3 = db.rawQuery(query3, null);
            cursor3.moveToFirst();
            do{

                String xproddd = cursor3.getString(0);

                listapro.add(xproddd);

            } while (cursor3.moveToNext());

            cursor3.close();



            String xdir= "\nDIRECCION :\n"+direccion;
            String xtel = "\nTELEFONO :\n"+telefono;
            String xprestamos = "\nPRESTAMOS : \n"+String.valueOf(nroc);
           // String xret = "RETAIL : ";
            String xcuop = "\nCREDITO : \n"+credito;
            String venc = "\nGESTOR : \n" + gestor;
            String xdesem = "\nCALIFICACION : \n"+ calificacion;
            String xtramo = "\nTRAMO : \n" + tramo;
            String xplazo = "\nALERTA : \n "+ alerta;
            String xgestor = "\nVENCIMIENTO : \n" + fecha_vcto;
            String xplaza = "\nCUOTA/PLAZO : \n"+ cp;
            String xdistrito = "\nCUOTA + SEG \n"+saldo_sd;
            String xurban = "\nSALDO + SEG : \n"+ saldo_sd;
            String xulpag = "\nSALDO VCDO : \n" + cuota;
            String xulpagm = "\nPENALIDAD : \n" + morao;
            String xdeuc = "\nMONTO A COBRAR : \n" + montoa;
            String xsaldoki = "\nSALDO ACTUAL : \n"+ saldo_actual;
            String xcuota = "\nPROVISION : \n" + provision;

            lista.add(xdir);
            lista.add(xtel);
            lista.add(xprestamos);
            //lista.add(xret);
            lista.add(xcuop);
            lista.add(venc);
            lista.add(xdesem);
            lista.add(xtramo);
            lista.add(xplazo);
            lista.add(xgestor);
            lista.add(xplaza);
            lista.add(xdistrito);
            lista.add(xurban);
            lista.add(xulpag);
            lista.add(xulpagm);
            lista.add(xdeuc);
            lista.add(xsaldoki);
            lista.add(xcuota);
            //for(int i = 0 ; i<=listapro.size(); i++){
            //  lista.add("PRODUCTOS : \n" + listapro.get(i).toString());
            //}


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, lista);
            listado.setAdapter(adapter);
            registerForContextMenu(listado);
            persona.setText("Datos de: " + nombres+"\n"+dni);









        }
    }
    public void regresar (View v){
        Intent dn=new Intent(detalle.this,nreport.class);
        startActivity(dn);
    }
}
