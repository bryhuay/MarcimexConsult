package com.example.user.marcimexconsult;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class nreport extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nreport);
        Spinner spinner = findViewById(R.id.motivos);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Fugado");
        arrayList.add("Operativo");
        arrayList.add("Incautar");
        arrayList.add("Fallecido");
        arrayList.add("Nota Credito");
        arrayList.add("Inubicable");
        arrayList.add("Horario Extremo");
        arrayList.add("Estafa");
        arrayList.add("Servicio Tecnico");
        arrayList.add("CP");
        arrayList.add("Incautado");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,                         android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);


        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        TextView mes = (TextView)findViewById(R.id.textMonth);

        Locale spanishLocale=new Locale("es", "ES");
        String dateInSpanish=date.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy",spanishLocale));
        System.out.println("'2016-01-01' in Spanish: "+dateInSpanish);
        mes.setText(dateFormat.format(date).toString());

    }
}
