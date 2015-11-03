package com.example.aitor.aplicacionesficheros;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ficheros3 extends AppCompatActivity {

    public ListView lvDirecciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficheros3);


        lvDirecciones=(ListView) findViewById(R.id.lvDirecciones);
        // Creamos el Array
        ArrayList<Webs>  arraywebs= new ArrayList<Webs> ();
        Webs web;
        // Cargamos los datos

        //// Añadimos las provincias del fichero webs.txt
        try
        {
            InputStream fraw = getResources().openRawResource(R.raw.webs);

            BufferedReader brin = new BufferedReader(new InputStreamReader(fraw));
            String texto = brin.readLine();
            int i =0;
            while (texto != null) {
                String[] arraytexto=texto.split(";");
                Resources res = this.getResources();

                int imagenlogo = res.getIdentifier(arraytexto[2], "drawable", this.getPackageName());
                web= new Webs (arraytexto[0],arraytexto[1], ContextCompat.getDrawable(this,imagenlogo) ,Integer.parseInt(arraytexto[3]));
                arraywebs.add(web);
                // web= new Webs ("Yahoo","http://www.yahoo.com", ContextCompat.getDrawable(this,R.drawable.yahoo),2);
                // arraywebs.add(web);
                texto = brin.readLine();
            }
            fraw.close();
        }
        catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde recurso raw");
        }
        ////////////

  //
  //      web= new Webs ("Yahoo","http://www.yahoo.com", ContextCompat.getDrawable(this,R.drawable.yahoo),2);
  //      arraywebs.add(web);
  //      web= new Webs ("Google","http://www.google.com", ContextCompat.getDrawable(this, R.drawable.google),3);
  //      arraywebs.add(web);
  //      web= new Webs ("Bing-es","http://www.bing.es", ContextCompat.getDrawable(this,R.drawable.bing),4);
  //      arraywebs.add(web);
  //      web= new Webs ("Yahoo-es","http://www.yahoo.es", ContextCompat.getDrawable(this,R.drawable.yahoo),5);
  //      arraywebs.add(web);
  //      web= new Webs ("Google-es","http://www.google.es", ContextCompat.getDrawable(this,R.drawable.google),6);
  //      arraywebs.add(web);

        // Creamos el adaptador personalizado
        AdaptadorWebs adapter = new AdaptadorWebs (this, arraywebs);

        // Aplico el adaptador a la lista
        lvDirecciones.setAdapter(adapter);

        // Creamos el listener para que cuando clickemos sobre la lista enlace con la url mediante un intend
        lvDirecciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                String urldestino =
                        ((TextView) v.findViewById(R.id.tvUrl)).getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urldestino));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ficheros3, menu);
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

    public class AdaptadorWebs extends BaseAdapter {
        protected Activity activity;
        protected ArrayList<Webs> items;
        // Creamos el constructor
        public AdaptadorWebs(Activity activity, ArrayList<Webs> items) {
            this.activity = activity;
            this.items = items;
        }
        @Override
        public int getCount() {
            return items.size();
        }
        @Override
        public Object getItem(int position) {
            return items.get(position);
        }
        @Override
        public long getItemId(int position) {
            return items.get(position).getId();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Cremos el objeto HolderView Optimizacion 2 ****
            ViewHolder holder;

            //
            // Generamos una convertView por motivos de eficiencia **** Optimización 1
            View item = convertView;
            // Asociamos el Layout con la listView
            if (item == null) {
                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                item = inflater.inflate(R.layout.elementoitem, null);
                ////****** Añadido Optimización 2 lo sustituimos por lo de abajo
                holder = new ViewHolder();
                holder.nombre = (TextView) item.findViewById(R.id.tvNombre);
                holder.url = (TextView) item.findViewById(R.id.tvUrl);
                holder.logotipo = (ImageView) item.findViewById(R.id.ivLogo);
                item.setTag (holder);
            }
            else
            {
                holder = (ViewHolder)item.getTag();
            }
            //Creamos un objeto web y rellenamos
            Webs web = items.get(position);
            holder = (ViewHolder) item.getTag();
            holder.nombre.setText(web.getNombre());
            holder.url.setText(web.getEnlace());
            holder.logotipo.setImageDrawable(web.getLogo());

            return item;
        }
    }

    // Creamos su clase
    public class ViewHolder {
        TextView nombre;
        TextView url;
        ImageView logotipo;
    }

}




