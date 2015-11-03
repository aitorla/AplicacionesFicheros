package com.example.aitor.aplicacionesficheros;

import android.graphics.drawable.Drawable;

/**
 * Created by aitor on 2/11/15.
 */
public class Webs {
    protected String nombre;
    protected String enlace;
    protected Drawable logotipo;
    protected long idweb;
    public Webs (String nom, String url, Drawable logo) {
        super();
        this.nombre=nom;
        this.enlace=url;
        this.logotipo=logo;
    }
    public Webs (String nom, String url, Drawable logo, long id) {
        super();
        this.nombre=nom;
        this.enlace=url;
        this.logotipo=logo;
        this.idweb=id;
    }
    public Drawable getLogo (){
        return logotipo;
    }
    public void setLogo(Drawable logo){
        this.logotipo=logo;
    }
    public String getNombre (){
        return nombre;
    }
    public void setNombre (String nom){
        this.nombre=nom;
    }
    public String getEnlace (){
        return enlace;
    }
    public void setEnlace(String url){
        this.enlace=url;
    }
    public long getId (){
        return idweb;
    }
    public void setId(int id){
        this.idweb=id;
    }
}