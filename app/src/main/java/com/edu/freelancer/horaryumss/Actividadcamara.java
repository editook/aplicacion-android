package com.edu.freelancer.horaryumss;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class Actividadcamara extends AppCompatActivity {
    private final String ruta_de_imagen="/storage/extSdCard/DCIM/Camera";
    ImageView imagen;
    static final int cod_seleccion=10;
    static final int cod_foto=20;
    Button cargar;
    String path="";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_camara);
        imagen=findViewById(R.id.imagen);
        cargar=findViewById(R.id.cargarImagen);

        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[]opciones={"tomar foto","cargar foto"};
                final AlertDialog.Builder alert=new AlertDialog.Builder(Actividadcamara.this);
                alert.setTitle("selecciona");
                alert.setItems(opciones,(DialogInterface, i)->{
                    if(opciones[i].equals("tomar foto")){
                        tomarFotografia();
                    }
                    else{
                        Intent intent =new Intent(Intent.ACTION_GET_CONTENT,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                        intent.setType("image/*");
                        startActivityForResult(intent.createChooser(intent,"selecciona una imagen"),cod_seleccion);
                    }
                });
                alert.show();
            }
        });
        Bitmap bitmap1= BitmapFactory.decodeFile(ruta_de_imagen+"/20180801_123227.jpg");
        imagen.setImageBitmap(bitmap1);
    }
    public void tomarFotografia(){
        String nombre=".jpg";
        File archivo_imagen=new File(Environment.getExternalStorageDirectory(),ruta_de_imagen);

        boolean creada=archivo_imagen.exists();
        if(creada){
            creada=archivo_imagen.mkdir();

        }
        if(creada){
            nombre=(System.currentTimeMillis()/100)+".jpg";
        }
        path=Environment.getExternalStorageDirectory()+File.separator+nombre;

        File archivoimagen=new File(path);
        Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, archivoimagen);
        startActivityForResult(intent,cod_foto);
    }
    @Override
    protected void onActivityResult(int code,int resultadoCode,Intent data){
        super.onActivityResult(code,resultadoCode,data);
        Log.println(Log.DEBUG,"estado ",code+" "+data.getExtras());
        if(resultadoCode==Activity.RESULT_OK){
            switch (code){
                case 10:
                    Uri Mipath=data.getData();
                    imagen.setImageURI(Mipath);
                    Log.println(Log.DEBUG,"cargo bien: ",Mipath.toString());
                    break;
                case 20:
                    Bundle extras=data.getExtras();
                    //Bitmap bitmap= (Bitmap) extras.get("20");
                    Bitmap bitmap1= BitmapFactory.decodeFile(path);
                    imagen.setImageBitmap(bitmap1);
                    break;
            }
        }
    }
}
