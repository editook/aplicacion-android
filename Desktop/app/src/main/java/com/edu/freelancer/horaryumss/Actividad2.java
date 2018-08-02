package com.edu.freelancer.horaryumss;

import android.app.TimePickerDialog;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Actividad2 extends AppCompatActivity {
    EditText materia_entrada;
    EditText aula_entrada;
    EditText hora_entrada;
    Button aceptar,agregar;

    ArrayList<String>listaElementos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_agregar_materia2);
        materia_entrada=findViewById(R.id.materia_input);
        aula_entrada=findViewById(R.id.aula_input);
        hora_entrada=findViewById(R.id.hora_input);
        agregar=findViewById(R.id.agregar);
        aceptar=findViewById(R.id.aceptar);
        aceptarBoton();
        agregarBoton();
        seleccionarHora();
        listaElementos=new ArrayList<>();
    }

    private void aceptarBoton(){
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void agregarBoton(){//agrega un registro
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaElementos.add(materia_entrada.getText()+";"+hora_entrada.getText()+";"+aula_entrada.getText());
                materia_entrada.setText("");
                hora_entrada.setText("");
                aula_entrada.setText("");
                
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

