package com.example.user.marcimexconsult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class dni extends AppCompatActivity {
    EditText dni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dni);
        dni=(EditText)findViewById(R.id.txtdni);
    }
    public void buscar (View v){
        Intent buscar = new Intent(dni.this,resultados.class);
        dni=(EditText)findViewById(R.id.txtdni);
        buscar.putExtra("dni",dni.getText().toString());
        //Toast.makeText(this, dni.getText().toString(), Toast.LENGTH_SHORT).show();
        startActivity(buscar);

    }
}
