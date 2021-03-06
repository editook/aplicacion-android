package com.edu.freelancer.horaryumss;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Eduard on 6/7/2018.
 */

public class registro {
    ArrayList <String>listaMaterias,listahoras,listaAulas,listaDias;
    ArrayList<Integer>listaImagenes;
    InputStreamReader lectura;
    OutputStreamWriter escritura;
    public int tamanio;
    private String error;
    public CodeSearch solucion;
    Activity Actividad;
    public registro(Activity a){
        Actividad=a;
        listaMaterias =new ArrayList<>();
        listahoras=new ArrayList<>();
        listaAulas=new ArrayList<>();
        listaDias=new ArrayList<>();
        listaImagenes=new ArrayList<>();
        tamanio=0;
        solucion=new CodeSearch();
    }
    public void cargar_datos_por_dia(String dia){//solo de un dia es especial
        String nombre_dia=dia;
        String dato_lectura;
        clear();
        //Log.println(Log.DEBUG,"dia ",nombre_dia);
        try {
            InputStreamReader archivo=new InputStreamReader(Actividad.openFileInput(nombre_dia));
            BufferedReader leer=new BufferedReader(archivo);
            if(archivo!=null){

                while((dato_lectura=leer.readLine())!=null){
                    String []grupo=dato_lectura.split(";");// materia,hora,aula,imagen,dia
                    listaMaterias.add(grupo[0]);
                    listahoras.add("Hora : "+grupo[1]); //+solucion.getestadoSegunDia(grupo[1]));
                    listaAulas.add("Aula : "+grupo[2]);
                    listaImagenes.add(solucion.getImagen_materia(grupo[0]));
                    listaDias.add(" ");
                }
                tamanio=listaAulas.size();
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

    }
    public void cargarDatos_por_semana(){//datos hasta 1 semana-> 6 dias
        String dato_lectura;
        clear();
        ArrayList<String> diasRecogidos=solucion.getFin_del_dia();
            for(String dia:diasRecogidos) {

                try {
                    InputStreamReader archivo=new InputStreamReader(Actividad.openFileInput(dia));
                    BufferedReader leer=new BufferedReader(archivo);
                    if(archivo!=null){
                        while((dato_lectura=leer.readLine())!=null){
                            String []grupo=dato_lectura.split(";");// materia,hora,aula,imagen,dia
                            if(dia.equals(solucion.dia_actual)){
                                if(solucion.Zona_Horaria(grupo[1])){
                                    listaMaterias.add(grupo[0]);
                                    listahoras.add("Hora : "+grupo[1]);//+solucion.getestadoSegunDia(grupo[1]));
                                    listaAulas.add("Aula : "+grupo[2]);
                                    listaImagenes.add(solucion.getImagen_materia(grupo[0]));
                                    listaDias.add("Dia : "+dia);
                                }
                            }
                            else{
                                listaMaterias.add(grupo[0]);
                                listahoras.add("Hora : "+grupo[1]);//+solucion.getestadoSegunDia(grupo[1]));
                                listaAulas.add("Aula : "+grupo[2]);
                                listaImagenes.add(solucion.getImagen_materia(grupo[0]));
                                listaDias.add("Dia : "+dia);
                            }

                        }
                        tamanio=listaMaterias.size();
                        archivo.close();
                    }
                }catch (Exception e){

                }

            }
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
        if(!listaElementos.isEmpty()){
        ArrayList<String>nuevaLista=new ArrayList<>();
        cargar_datos_por_dia(dia);
        for(int i=0;i<listaMaterias.size();i++){
            nuevaLista.add(listaMaterias.get(i)+";"+listahoras.get(i)+";"+listaAulas.get(i));
        }
            try {
                OutputStreamWriter archivo=new OutputStreamWriter(Actividad.openFileOutput(dia,Context.MODE_PRIVATE));
                String []datos;
                String formateo;
                if(!nuevaLista.isEmpty()){//copiando datos existentes
                    for(int i=0;i<nuevaLista.size();i++){
                        datos=nuevaLista.get(i).split(";");
                        formateo=solucion.Mayuscula_guardar(datos);
                        archivo.write(formateo+"\n");
                    }
                }
                if(archivo!=null){
                    for (int i=0;i<listaElementos.size();i++){
                        //guardar con Mayuscula
                        datos=listaElementos.get(i).split(";");
                        formateo=solucion.Mayuscula_guardar(datos);
                        archivo.write(formateo+"\n");
                    }
                }
                archivo.close();
            }
            catch (Exception e){

            }
        }
    }

    public boolean expresionDatos(String m, String h, String a) {
        Pattern patternMateria = Pattern.compile("[A-Za-z0-9_ ]+");
        Pattern patternHora = Pattern.compile("[0-9:]");
        Pattern patternAula = Pattern.compile("[A-Za-z0-9_ ]+");
        Matcher matcher=patternMateria.matcher(m);
        if(matcher.find()){
            matcher=patternHora.matcher(h);
            if(matcher.find()){
                matcher=patternAula.matcher(a);
                if (matcher.find()){
                    return true;
                }
                else{
                    error="AULA";
                }
            }
            else{
                error="HORA";
            }
        }
        else{
            error="MATERIA";
        }
        return false;
    }

    public String getError() {

        return error;
    }

    public void eliminarDato(String diaEspecifico, int posicionSeleccionado) {

        if(posicionSeleccionado!=-1){

            cargar_datos_por_dia(diaEspecifico);
            for (int i=0;i<listaMaterias.size();i++){
                if(i==posicionSeleccionado){
                    listaMaterias.remove(i);
                    listaAulas.remove(i);
                    listahoras.remove(i);
                    listaDias.remove(i);
                    ArrayList<String>nuevaLista=new ArrayList<>();
                    for (int j=0;j<listaMaterias.size();j++){
                        nuevaLista.add(listaMaterias.get(j)+";"+listahoras.get(j)+";"+listaAulas.get(j));
                    }
                    try {
                        OutputStreamWriter archivo=new OutputStreamWriter(Actividad.openFileOutput(diaEspecifico,Context.MODE_PRIVATE));
                        String []datos;
                        String formateo;
                        if(!nuevaLista.isEmpty()){//copiando datos existentes
                            for(int k=0;k<nuevaLista.size();k++){
                                datos=nuevaLista.get(k).split(";");
                                formateo=solucion.Mayuscula_guardar(datos);
                                archivo.write(formateo+"\n");
                            }
                        }
                        archivo.close();
                    }
                    catch (Exception e){
                    }
                    Toast.makeText(Actividad,"Eliminado", Toast.LENGTH_LONG).show();
                    break;
                }

            }
        }

        cargar_datos_por_dia(diaEspecifico);
    }
    public void clear(){
        listaAulas.clear();
        listahoras.clear();
        listaMaterias.clear();
        listaAulas.clear();
        listaImagenes.clear();
    }
    public void modificarElemento(String diaEspecifico,int posicion) {
        if (posicion!=-1) {
            cargar_datos_por_dia(diaEspecifico);
            for (int i=0;i<listaMaterias.size();i++){
                if(posicion==i){
                    dato_actual.ma_ho_au=listaMaterias.get(i)+";"+listahoras.get(i).split(": ")[1]+";"+listaAulas.get(i).split(": ")[1];
                    dato_actual.posicionModificacion=posicion;
                    break;
                }
            }

        }
    }
    public void modificarElemento(String elemento,String dia) {
        String[]element=elemento.split(";");
        if(element!=null){
            ArrayList<String>nuevaLista=new ArrayList<>();
            cargar_datos_por_dia(dia);
            for(int i=0;i<listaMaterias.size();i++){
                if(dato_actual.posicionModificacion==i){
                    listaMaterias.set(i,element[0]);
                    listahoras.set(i,element[1]);
                    listaAulas.set(i,element[2]);
                    break;
                }
            }
            for(int i=0;i<listaMaterias.size();i++){
                nuevaLista.add(listaMaterias.get(i)+";"+listahoras.get(i)+";"+listaAulas.get(i));
            }
            try {
                OutputStreamWriter archivo=new OutputStreamWriter(Actividad.openFileOutput(dia,Context.MODE_PRIVATE));
                String []datos;
                String formateo;
                if(!nuevaLista.isEmpty()){//copiando datos existentes
                    for(int i=0;i<nuevaLista.size();i++){
                        datos=nuevaLista.get(i).split(";");
                        formateo=solucion.Mayuscula_guardar(datos);
                        archivo.write(formateo+"\n");
                    }
                }
                archivo.close();
            }
            catch (Exception e){

            }
        }
    }
}
