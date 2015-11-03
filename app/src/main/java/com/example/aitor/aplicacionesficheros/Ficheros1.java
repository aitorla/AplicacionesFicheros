package com.example.aitor.aplicacionesficheros;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Ficheros1 extends AppCompatActivity implements Button.OnClickListener {

    private Button button, button2, button3, button4, button5, button6, button7;
    private EditText nuevoContenido;
    private TextView tvContenidoFichero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficheros1);


        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);

        nuevoContenido = (EditText) findViewById(R.id.etNuevoContenido);
        tvContenidoFichero = (TextView) findViewById(R.id.tvContenidoFichero);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ficheros1, menu);
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

    @Override
    public void onClick(View v) {

        if (v.getId() == button.getId()) {
            ////// Escritura interna
            try {

                OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("ficherointerno.txt", Context.MODE_APPEND));
                fout.write(nuevoContenido.getText().toString());
                fout.close();
            } catch (Exception ex) {
                Log.e("Ficheros", "Error al escribir fichero a memoria interna");
            }

        } else {
            if (v.getId() == button2.getId()) {
                /// Escritura Externa
                try
                {
                    File ruta_sd = Environment.getExternalStorageDirectory();
                    File f = new File(ruta_sd.getAbsolutePath(), "ficheroexterno.txt");
                    OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f));
                    fout.write(nuevoContenido.getText().toString());
                    fout.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
                }
            } else {
                    if (button3.getId() == v.getId()) {
                        ///// Lectura Memoria Interna
                        tvContenidoFichero.setText("");
                        try {
                            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("ficherointerno.txt")));
                            String texto = fin.readLine();
                            while (texto != null) {
                                tvContenidoFichero.setText(tvContenidoFichero.getText()+texto);
                                texto = fin.readLine();
                            }
                            fin.close();
                        } catch (Exception ex) {
                            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
                        }
                        ////////////////////////////

                    } else {
                        if (button4.getId() == v.getId()) {
                            /// Lectura SD
                            tvContenidoFichero.setText("");
                            try
                            {
                                File ruta_sd = Environment.getExternalStorageDirectory();
                                File f = new File(ruta_sd.getAbsolutePath(), "ficheroexterno.txt");
                                BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                                String texto = fin.readLine();
                                while (texto != null) {
                                    tvContenidoFichero.setText(tvContenidoFichero.getText()+texto);
                                    texto = fin.readLine();
                                }
                                fin.close();
                            }
                            catch (Exception ex)
                            {
                                Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
                            }


                            ///////////////
                        }  else {
                            if (v.getId() == button5.getId()) {
                                /// Lectura Rercurso fichero
                                tvContenidoFichero.setText("");
                                try
                                {
                                    InputStream fraw = getResources().openRawResource(R.raw.ficheroraw);
                                    BufferedReader brin = new BufferedReader(new InputStreamReader(fraw));
                                    String texto = brin.readLine();
                                    while (texto != null) {
                                        tvContenidoFichero.setText(tvContenidoFichero.getText()+texto);
                                        texto = brin.readLine();
                                    }
                                    fraw.close();
                                }
                                catch (Exception ex) {
                                    Log.e("Ficheros", "Error al leer fichero desde recurso raw");
                                }
                                //////////////////////////////

                            } else {
                                if (v.getId() == button6.getId()) {
                                    /// Borrar Fichero interno

                                    File dir = getFilesDir();
                                    File file = new File(dir, "ficherointerno.txt");
                                    file.delete();

                                }
                                else {
                                    /// Borrar Fichero externo
                                    File dir= Environment.getExternalStorageDirectory();
                                    File file = new File(dir, "ficheroexterno.txt");
                                    file.delete();

                                }

                            }
                        }

                    }
                }
            }

        }
}
