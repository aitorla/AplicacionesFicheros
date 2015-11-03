package com.example.aitor.aplicacionesficheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;

public class Ficheros2 extends AppCompatActivity {


    private Spinner spinner;

    //// Lo hacemos con un array list y añadimos con add en lugar de utilizar Array!!!!
    ArrayList<String>provincias =new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficheros2);

        spinner = (Spinner)findViewById(R.id.spinner);

        //// Añadimos las provincias del fichero provincias.txt
        try
        {
            InputStream fraw = getResources().openRawResource(R.raw.provincias);

            BufferedReader brin = new BufferedReader(new InputStreamReader(fraw));
            String texto = brin.readLine();
            int i =0;
            while (texto != null) {
                provincias.add(texto);
                texto = brin.readLine();
            }
            fraw.close();
        }
        catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde recurso raw");
        }
        ////////////

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, provincias);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ficheros2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
