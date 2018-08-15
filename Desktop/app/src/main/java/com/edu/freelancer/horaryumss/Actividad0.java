package com.edu.freelancer.horaryumss;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import java.io.IOException;

public class Actividad0 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    registro datos;
    private CircleMenu circleMenu;
    ListView listaELementos;
    Actividad0 actividad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actividad=this;
        //configuraciones de esta actividad
        setContentView(R.layout.activity_actividad0);
        Toolbar toolbar = findViewById(R.id.toolbar);
        listaELementos=findViewById(R.id.Lista_de_Datos_Principal);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView =  findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);
        //configuracion Aplicacion
        datos=new registro(actividad);

        //configuraciones de panel izquierdo

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(actividad);
        cargarListaElementos();
        menu_opciones();


    }
    private void menu_opciones(){

        circleMenu = findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.BLACK,R.drawable.menu3,R.drawable.menu4)
                .addSubMenu(Color.WHITE,R.drawable.lunes)
                .addSubMenu(Color.WHITE,R.drawable.martes)
                .addSubMenu(Color.WHITE,R.drawable.miercoles)
                .addSubMenu(Color.WHITE,R.drawable.jueves)
                .addSubMenu(Color.WHITE,R.drawable.viernes)
                .addSubMenu(Color.WHITE,R.drawable.sabado)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        Log.println(Log.DEBUG,"dia actual ",datos.solucion.getDia_porValor(index));
                        Intent actividad=new Intent(Actividad0.this,Actividad1.class);
                        actividad.putExtra("AcConSecDia2134",datos.solucion.getDia_porValor(index));

                        startActivity(actividad);

                    }

                });
    }
    private void cargarListaElementos(){
        datos.cargarDatos_por_semana();

        Lista_Elementos LE=new Lista_Elementos(this,datos.getlista_materias(),datos.getlista_horas(),datos.getlista_imagenes(),datos.getlista_aulas(),datos.getlista_dias());
        listaELementos.setAdapter(LE);

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actividad0, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Actividad0.this.startActivity(new Intent(Actividad0.this,Actividadcamara.class));
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
