package com.edu.freelancer.horaryumss;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class  CodeSearch {
    HashMap<String,Integer>mapeoMateria;//nombre inicial de la materia
    HashMap<Integer,String>mapeoDias;
    HashMap<String,String>mapeoDiasIngles;
    String abecedario="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String[]diasSemana;
    Calendar calendario;
    String dia_actual,hora_min;
    public CodeSearch(){
        mapeoMateria=new HashMap<>();
        mapeoDias=new HashMap<>();
        mapeoDiasIngles=new HashMap<>();
        diasSemana=new String[6];
        try {
            for (int i=0;i<abecedario.length();i++){
                mapeoMateria.put(abecedario.charAt(i)+"",new Integer("R.drawable."+i+""));
            }
        }
        catch (Exception e){

        }
        mapeoDias.put(0,"lunes");mapeoDias.put(1,"martes");
        mapeoDias.put(4,"viernes");mapeoDias.put(2,"miercoles");
        mapeoDias.put(5,"sabado");mapeoDias.put(3,"jueves");
        diasSemana[0]="Lunes";diasSemana[1]="Martes";diasSemana[2]="Miercoles";diasSemana[3]="Jueves";diasSemana[4]="Viernes";
        diasSemana[5]="Sabado";

        mapeoDiasIngles.put("Mon","Lunes");
        mapeoDiasIngles.put("Tue","Martes");
        mapeoDiasIngles.put("Wed","Miercoles");
        mapeoDiasIngles.put("Thu","Jueves");
        mapeoDiasIngles.put("Fri","Viernes");
        mapeoDiasIngles.put("Sat","Sabado");
        calendario=Calendar.getInstance();
        formatearCalendario();
    }
    public void formatearCalendario(){
        String []datos=(""+calendario.getTime()).split(" ");
        // Web Dec 07 00:00:00 CET 2018
        dia_actual=mapeoDiasIngles.get(datos[0]);
        datos=datos[3].split(":");
        hora_min=datos[0]+":"+datos[1];
    }
    public Integer getImagen_materia(String nombreMateria){//obtiene un caracter de una imagen
        String dato=nombreMateria.charAt(0)+"";
        return mapeoMateria.get(dato);
    }
    public String Mayuscula_guardar(String nombreMateria){//mayuscula y minuscula la palabra(antes de guardar)
        String mayuscula=nombreMateria.charAt(0)+"";
        String minuscula=nombreMateria.substring(1,nombreMateria.length());
        mayuscula=mayuscula.toUpperCase();
        return mayuscula+minuscula;
    }
    public String getDia_porValor(int clave){//menu dias actividad 0 a actividad 1
        return mapeoDias.get(clave);
    }
    public ArrayList getFin_del_dia(){//obtener el dia despues de 5 dias
        ArrayList<String> diasRecogidos=new ArrayList<>();
        String salida="error";
        int indice=0;
        for(int i=0;i<diasSemana.length;i++){
            if(dia_actual.equals(diasSemana[i])){
                indice=i+1;
                diasRecogidos.add(diasSemana[i]);
                break;
            }
        }
        int contador=5;
        while(!salida.equals("error")){
            for(int i=indice;i<diasSemana.length;i++){
                if(contador==-1){
                    salida="correcto";
                    break;
                }else{
                    contador--;
                }
                diasRecogidos.add(diasSemana[i]);
            }
            indice=0;
        }
        return diasRecogidos;
    }
    public String getestadoSegunDia(){
        String dato="Am";
        String[]datos=hora_min.split(":");
        if(Integer.parseInt(datos[0])>12){
            dato="Pm";
        }
        return dato;
    }
}
