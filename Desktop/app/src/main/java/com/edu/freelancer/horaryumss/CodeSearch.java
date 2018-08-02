package com.edu.freelancer.horaryumss;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class  CodeSearch {
    HashMap<String,Integer>mapeoMateria;//nombre inicial de la materia
    HashMap<Integer,String>mapeoDias;
    HashMap<String,String>mapeoDiasIngles;
    String[]diasSemana;
    Calendar calendario;
    String dia_actual,hora_min;
    public CodeSearch(){
        mapeoMateria=new HashMap<>();
        mapeoDias=new HashMap<>();
        mapeoDiasIngles=new HashMap<>();
        diasSemana=new String[6];
        //cargando imagenes
        mapeoMateria.put("A" , R.drawable.a);mapeoMateria.put("I" , R.drawable.i);
        mapeoMateria.put("B" , R.drawable.b);mapeoMateria.put("J", R.drawable.j);
        mapeoMateria.put("C" , R.drawable.c);mapeoMateria.put("K" , R.drawable.k);
        mapeoMateria.put("D" , R.drawable.d);mapeoMateria.put("L", R.drawable.l);
        mapeoMateria.put("E" , R.drawable.e);mapeoMateria.put("M", R.drawable.m);
        mapeoMateria.put("F" , R.drawable.f);mapeoMateria.put("N", R.drawable.n);
        mapeoMateria.put("G" , R.drawable.g);mapeoMateria.put("O" , R.drawable.o);
        mapeoMateria.put("H" , R.drawable.h);mapeoMateria.put("P" , R.drawable.p);
        mapeoMateria.put("Q" , R.drawable.q);mapeoMateria.put("U" , R.drawable.u);
        mapeoMateria.put("R" , R.drawable.r);mapeoMateria.put("V" , R.drawable.v);
        mapeoMateria.put("S" , R.drawable.s);mapeoMateria.put("W" , R.drawable.q);
        mapeoMateria.put("T" , R.drawable.t);mapeoMateria.put("X" , R.drawable.x);
        mapeoMateria.put("Z" , R.drawable.z);mapeoMateria.put("Y" , R.drawable.y);
        mapeoDias.put(0,"Lunes");mapeoDias.put(1,"Martes");
        mapeoDias.put(4,"Viernes");mapeoDias.put(2,"Miercoles");
        mapeoDias.put(5,"Sabado");mapeoDias.put(3,"Jueves");
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
    public String Mayuscula_guardar(String[] datos){//mayuscula y minuscula la palabra(antes de guardar)
        String nombreMateria=datos[0];
        String nombrehora=datos[1];
        String nombreAula=datos[2];
        //materia
        String mayuscula=nombreMateria.charAt(0)+"";
        String minuscula=nombreMateria.substring(1,nombreMateria.length());
        mayuscula=mayuscula.toUpperCase();
        mayuscula=mayuscula+minuscula;
        //hora
        mayuscula=mayuscula+";";
        for (int i=0;i<nombrehora.length();i++){
            if(nombrehora.charAt(i)!=' '){//mejorar
                mayuscula=mayuscula+nombrehora.charAt(i);
            }
        }
        //aula
        mayuscula=mayuscula+";";
        for (int i=0;i<nombreAula.length();i++){
            if(nombreAula.charAt(i)!=' '){//mejorar
                mayuscula=mayuscula+nombreAula.charAt(i);
            }
        }

        return mayuscula;
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
                diasRecogidos.add(diasSemana[i]);
                indice=i+1;
                break;
            }
        }
        int contador=3;
        while(!salida.equals("correcto")){
            for(int i=indice;i<diasSemana.length;i++){
                if(contador==-1){
                    salida="correcto";
                    break;
                }else{
                    contador--;
                    diasRecogidos.add(diasSemana[i]);
                }
            }
            indice=0;
        }
        return diasRecogidos;
    }
    public String getestadoSegunDia(String entrada_hora){
        String dato=" Am";
        String[]datos=entrada_hora.split(":");
        if(Integer.parseInt(datos[0])>12){
            dato=" Pm";
        }
        return dato;
    }

    public boolean Zona_Horaria(String s) {
        String[]hora_actual=hora_min.split(":");
        String[]hora_entrada=s.split(":");
        int valor1=Integer.parseInt(hora_actual[0]+""+hora_actual[1]);
        int valor2=Integer.parseInt(hora_entrada[0]+""+hora_entrada[1]);

        return valor2>valor1;
    }
}
