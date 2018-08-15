package com.edu.freelancer.horaryumss;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import static com.edu.freelancer.horaryumss.R.*;

/**
 * Created by ASUS on 6/7/2018.
 */

public class Actividad1 extends AppCompatActivity{//dia especifico de la semana

    registro datos;
    private FloatingActionButton buttonAdd;
    private Bundle parametro;
    private String diaEspecifico;
    ListView listaELementos;

    private int posicionSeleccionado;
    @Override
    protected void onCreate(Bundle instancia){
        super.onCreate(instancia);
        setContentView(layout.activity_menu_dias);
        agregarActionBar();

        datos=new registro(this);
        try {
            parametro=getIntent().getExtras();
            diaEspecifico=parametro.getString("AcConSecDia2134");
        }
        catch (Exception e) {
            diaEspecifico=dato_actual.getDia_actual();
        }
        buttonAdd=findViewById(id.agregarNuevo);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                accionButtonCrear();
            }
        });
        listaELementos=findViewById(id.Lista_de_Datos_dia);
        cargarListaElementos();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0){
            event.setSource(0);
            return true;
        }
        else{
            return super.onKeyDown(keyCode,event);
        }
    }
    private void agregarActionBar() {
        ActionBar accion=getSupportActionBar();
        accion.setDisplayHomeAsUpEnabled(true);

    }

    private void accionButtonCrear(){
        //Actividad1.this.startActivity(new Intent(Actividad1.this,Actividad2.class));
        Intent actividad=new Intent(Actividad1.this,Actividad2.class);
        actividad.putExtra("AcConSecDia2134",diaEspecifico);

        startActivity(actividad);

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.eliminar_modificar,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id=menuItem.getItemId();

        if(id==R.id.eliminar){
            Toast.makeText(Actividad1.this,"eliminar", Toast.LENGTH_LONG).show();
            return true;
        }
        if(id==R.id.modificar){
            Toast.makeText(Actividad1.this,"modificar", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
    private void cargarListaElementos(){
        datos.cargar_datos_por_dia(diaEspecifico);
        Lista_Elementos LE=new Lista_Elementos(this,datos.getlista_materias(),datos.getlista_horas(),datos.getlista_imagenes(),datos.getlista_aulas(),datos.getlista_dias());
        listaELementos.setAdapter(LE);
        listaELementos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listaELementos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(posicionSeleccionado==position){
                    listaELementos.getChildAt(position).setBackgroundColor(Color.WHITE);
                }
                else{
                    posicionSeleccionado=position;
                }
                listaELementos.getChildAt(position).setBackgroundColor(Color.LTGRAY);
            }
        });

    }
}
