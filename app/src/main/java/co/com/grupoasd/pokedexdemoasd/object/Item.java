package co.com.grupoasd.pokedexdemoasd.object;

import android.content.ClipData;

/**
 * Created by ASD on 28/12/2016.
 */

public class Item {

    private  String titulo;
    private String subTitulo;

    public Item(String titulo, String subTitulo){
        this.titulo = titulo;
        this.subTitulo = subTitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }


}
