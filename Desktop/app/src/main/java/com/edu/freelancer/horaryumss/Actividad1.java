package com.edu.freelancer.horaryumss;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

/**
 * Created by ASUS on 6/7/2018.
 */

public class Actividad1 extends AppCompatActivity{//dia especifico de la semana

    registro datos;
    private FloatingActionButton buttonAdd;
    private Bundle parametro;
    private String diaEspecifico;
    @Override
    protected void onCreate(Bundle instancia){
        super.onCreate(instancia);
        setContentView(R.layout.activity_menu_dias);
        datos=new registro(this);
        parametro=getIntent().getExtras();
        diaEspecifico=parametro.getString("AcConSecDia2134");
        buttonAdd=findViewById(R.id.agregarNuevo);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                accionButtonCrear();
            }
        });
        cargarListaElementos();
    }
    private void accionButtonCrear(){
        //Actividad1.this.startActivity(new Intent(Actividad1.this,Actividad2.class));
        Intent actividad=new Intent(Actividad1.this,Actividad2.class);
        actividad.putExtra("AcConSecDia2134",diaEspecifico);
        startActivity(actividad);

    }
    private void cargarListaElementos(){
        datos.cargar_datos_por_dia(diaEspecifico);
        ListView listaELementos=findViewById(R.id.Lista_de_Datos_dia);
        Lista_Elementos LE=new Lista_Elementos(this,datos.getlista_materias(),datos.getlista_horas(),datos.getlista_imagenes(),datos.getlista_aulas(),datos.getlista_dias());
        listaELementos.setAdapter(LE);

    }
}
