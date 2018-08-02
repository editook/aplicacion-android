package com.edu.freelancer.horaryumss;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static com.edu.freelancer.horaryumss.R.layout.vista_horaria;
/**
 * Created by edu on 6/7/2018.
 */

public class Lista_Elementos extends ArrayAdapter<String>{
    String[]listaMaterias,listaHoras,listaDias,listaAulas;
    Integer[]listaImagen;
    Context contexto;
    Activity actividad;
    public Lista_Elementos(Activity actividad, String []listaMaterias, String[]listaHoras, Integer[]listaImagen,String[]listaAulas, String[]listaDias){
        super(actividad,vista_horaria,listaMaterias);
        this.listaMaterias=listaMaterias;
        this.listaHoras=listaHoras;
        this.listaImagen=listaImagen;
        this.listaDias=listaDias;
        this.listaAulas=listaAulas;
        this.actividad=actividad;
        contexto=actividad.getBaseContext();
    }

    @Override
    public View getView(int pos, View vista, ViewGroup grupo){
        View salida=vista;
        folder_vista folder=null;
        if(salida==null){
            LayoutInflater inf=actividad.getLayoutInflater();
            salida=inf.inflate(vista_horaria,null,true);
            folder=new folder_vista(salida);
            salida.setTag(folder);
        }
        else{
            folder =(folder_vista) salida.getTag();
        }
        folder.imagen.setImageResource(listaImagen[pos]);
        folder.materia.setText(listaMaterias[pos]);
        folder.aula.setText(listaAulas[pos]);
        folder.hora.setText(listaHoras[pos]);
        folder.dia.setText(listaDias[pos]);

        return salida;
    }
    class folder_vista{
        TextView materia,hora,aula,dia;
        ImageView imagen;
        folder_vista(View vista){
            materia= vista.findViewById(R.id.materia);
            hora= vista.findViewById(R.id.hora);
            aula= vista.findViewById(R.id.aula);
            dia=vista.findViewById(R.id.dia);
            imagen= vista.findViewById(R.id.imagen);
        }
    }
}
