package com.edu.freelancer.horaryumss;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Actividad2 extends AppCompatActivity {
    EditText materia_entrada;
    TextView agregados;
    EditText aula_entrada;
    EditText hora_entrada;
    Button aceptar,agregar;
    registro data;
    ArrayList<String>listaElementos;
    Bundle parametro;
    String diaEspecifico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_agregar_materia2);
        materia_entrada=findViewById(R.id.materia_input);
        aula_entrada=findViewById(R.id.aula_input);
        hora_entrada=findViewById(R.id.hora_input);
        agregados=findViewById(R.id.agregados_input);
        agregar=findViewById(R.id.agregar);
        aceptar=findViewById(R.id.aceptar);
        data=new registro(this);
        parametro=getIntent().getExtras();
        diaEspecifico=parametro.getString("AcConSecDia2134");
        aceptarBoton();
        agregarBoton();
        seleccionarHora();
        listaElementos=new ArrayList<>();
    }

    private void aceptarBoton(){
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!listaElementos.isEmpty()){
                    data.registrarClases(listaElementos,diaEspecifico);
                    Intent act=new Intent(Actividad2.this,Actividad1.class);
                    act.putExtra("AcConSecDia2134",diaEspecifico);
                    startActivity(act);
                }
            }
        });
    }
    private void agregarBoton(){//agrega un registro
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m,h,a;
                m=materia_entrada.getText().toString();
                h=hora_entrada.getText().toString();
                a=aula_entrada.getText().toString();
                if(!m.equals("") && !h.equals("") && !a.equals("")){
                    if(data.expresionDatos(m,h,a)){
                        listaElementos.add(m+";"+h+";"+a);
                        materia_entrada.setText("");
                        hora_entrada.setText("");
                        aula_entrada.setText("");
                        agregados.setText("Agregados : "+listaElementos.size());
                        Toast.makeText(Actividad2.this,"Se agrego una nueva materia", Toast.LENGTH_SHORT).show();
                    }
                    else{//machine learning
                        //notificacion de modificacion
                    }
                }else{
                    Toast.makeText(Actividad2.this,"llene los datos correctamente", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private void seleccionarHora() {
        hora_entrada.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    int hora,min;
                    final Calendar calendario=Calendar.getInstance();
                    hora=calendario.get(Calendar.DAY_OF_MONTH);
                    min=calendario.get(Calendar.MINUTE);
                    TimePickerDialog alert=new TimePickerDialog(Actividad2.this, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            hora_entrada.setText(hourOfDay+":"+minute);
                        }
                    },hora,min,false);
                    alert.show();
                }
            }
        });
    }
}

