package com.example.aitor.aplicacionesficheros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    private Button btFicheros1, btFicheros2, btFicheros3, btSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btFicheros1 = (Button) findViewById(R.id.btFicheros1);
        btFicheros2 = (Button) findViewById(R.id.btFicheros2);
        btFicheros3 = (Button) findViewById(R.id.btFicheros3);
        btSalir = (Button) findViewById(R.id.btSalir);

        btFicheros1.setOnClickListener(this);
        btFicheros2.setOnClickListener(this);
        btFicheros3.setOnClickListener(this);
        btSalir.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if (v.getId() == btFicheros1.getId()) {
            Intent intent = new Intent(MainActivity.this, Ficheros1.class);
            startActivity(intent);
        } else {
            if (v.getId() == btFicheros2.getId()) {
                Intent intent = new Intent(MainActivity.this, Ficheros2.class);
                startActivity(intent);
            } else {
                if (v.getId() == btFicheros3.getId()) {
                    Intent intent = new Intent(MainActivity.this, Ficheros3.class);
                    startActivity(intent);
                } else {
                    if (btSalir.getId() == v.getId()) {
                        finish();
                    }
                }
            }
        }
    }

}
