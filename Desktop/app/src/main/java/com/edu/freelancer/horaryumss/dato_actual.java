package com.edu.freelancer.horaryumss;

public final class dato_actual {
    public static String dia_actual="";
    public static String ma_ho_au="";
    public static int posicionModificacion=0;
    public static String getDia_actual() {
        return dia_actual;
    }
    public static String[] get_materia_hora_aula(){
        if(!ma_ho_au.isEmpty()){
            return ma_ho_au.split(";");
        }
        String[]g=new String[3];g[0]="";g[1]="";g[2]="";
        return g;
    }
    public static int getPosicionModificacion(){
        return posicionModificacion;
    }
}
