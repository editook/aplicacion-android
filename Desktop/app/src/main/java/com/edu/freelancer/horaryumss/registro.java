package com.edu.freelancer.horaryumss;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
/**
 * Created by Eduard on 6/7/2018.
 */

public class registro {
    ArrayList <String>listaMaterias,listahoras,listaAulas,listaDias;
    ArrayList<Integer>listaImagenes;
    InputStreamReader archivo;
    OutputStreamWriter escritura;
    private int tamanio;
    public CodeSearch solucion;
    Activity Actividad;
    public registro(Activity a){
        Actividad=a;
        listaMaterias =new ArrayList<>();
        listahoras=new ArrayList<>();
        listaAulas=new ArrayList<>();
        listaDias=new ArrayList<>();
        listaImagenes=new ArrayList<>();
        tamanio=listaAulas.size();
        solucion=new CodeSearch();
    }
    public void cargar_datos_por_dia(){//solo de un dia es especial
        String nombre_dia=solucion.dia_actual;
        String dato_lectura;
        try {
            InputStreamReader archivo=new InputStreamReader(Actividad.openFileInput(nombre_dia));
            BufferedReader leer=new BufferedReader(archivo);
            if(archivo!=null){

                while((dato_lectura=leer.readLine())!=null){
                    String []grupo=dato_lectura.split(";");// materia,hora,aula,imagen,dia
                    listaMaterias.add(grupo[0]);
                    listahoras.add(grupo[1]);
                    listaAulas.add(grupo[2]);
                    listaImagenes.add(solucion.getImagen_materia(grupo[0]));
                }
                archivo.close();
            }
        }catch (Exception e){
            try {
                OutputStreamWriter escritura=new OutputStreamWriter(Actividad.openFileOutput(nombre_dia, Context.MODE_PRIVATE));
                escritura.write("");
                escritura.close();
            }
            catch (Exception ex){

            }

        }
        tamanio=listaAulas.size();
    }
    public void cargar_datos_por_dia(String dia){//solo de un dia es especial
        String nombre_dia=dia;
        String dato_lectura;
        try {
            InputStreamReader archivo=new InputStreamReader(Actividad.openFileInput(nombre_dia));
            BufferedReader leer=new BufferedReader(archivo);
            if(archivo!=null){

                while((dato_lectura=leer.readLine())!=null){
                    String []grupo=dato_lectura.split(";");// materia,hora,aula,imagen,dia
                    listaMaterias.add(grupo[0]);
                    listahoras.add(grupo[1]);
                    listaAulas.add(grupo[2]);
                    listaImagenes.add(solucion.getImagen_materia(grupo[0]));
                }
                archivo.close();
            }
        }catch (Exception e){
            try {
                OutputStreamWriter escritura=new OutputStreamWriter(Actividad.openFileOutput(nombre_dia, Context.MODE_PRIVATE));
                escritura.write("");
                escritura.close();
            }
            catch (Exception ex){

            }

        }
        tamanio=listaAulas.size();
    }
    public void cargarDatos_por_semana1(){//datos hasta 1 semana-> 6 dias
        String dato_lectura;
        ArrayList<String> diasRecogidos=solucion.getFin_del_dia();
            for(String dia:diasRecogidos) {
                try {
                    archivo=new InputStreamReader(Actividad.openFileInput(dia));
                    if (archivo != null) {
                        BufferedReader leer = new BufferedReader(archivo);
                        while((dato_lectura=leer.readLine())!=null){
                            String []grupo=dato_lectura.split(";");// materia,hora,aula,dia
                            if(dia.equals(grupo[3])){
                                listaMaterias.add(grupo[0]);
                                listahoras.add(grupo[1]+" "+solucion.getestadoSegunDia());
                                listaAulas.add("Aula: "+grupo[2]);
                                listaImagenes.add(solucion.getImagen_materia(grupo[0]));
                                listaDias.add(grupo[3]);
                            }
                        }
                    }
                    archivo.close();
                } catch (Exception e) {
                    try {
                    escritura = new OutputStreamWriter(Actividad.openFileOutput(dia, Context.MODE_PRIVATE));
                    escritura.write("");
                    escritura.close();
                    cargar_datos_por_dia();
                    } catch (IOException e1) {

                    }
                }

            }
        tamanio=listaAulas.size();
    }
    public String[]getlista_aulas(){
        return listaAulas.toArray(new String[tamanio]);
    }
    public String[]getlista_horas(){
        return listahoras.toArray(new String[tamanio]);
    }
    public String[]getlista_materias(){
        return listaMaterias.toArray(new String[tamanio]);
    }
    public String[]getlista_dias(){
        return listaDias.toArray(new String[tamanio]);
    }
    public Integer[]getlista_imagenes(){
        return listaImagenes.toArray(new Integer[tamanio]);
    }

    public void registrarClases(ArrayList<String> listaElementos,String dia) {
        try {
            OutputStreamWriter archivo=new OutputStreamWriter(Actividad.openFileOutput(dia,Context.MODE_PRIVATE));
            String []datos;
            String formateo;
            if(archivo!=null){
                for (int i=0;i<listaElementos.size();i++){
                    //guardar con Mayuscula
                    datos=listaElementos.get(i).split(";");
                    formateo=solucion.Mayuscula_guardar(datos);
                    archivo.write(formateo+"\n");
                }
                archivo.close();
            }
        }
        catch (Exception e){

        }

    }

    public boolean expresionDatos(String m, String h, String a) {

        return true;
    }
}
