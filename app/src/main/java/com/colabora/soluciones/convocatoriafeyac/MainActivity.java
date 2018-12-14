package com.colabora.soluciones.convocatoriafeyac;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import com.colabora.soluciones.convocatoriafeyac.Modelos.VerPDFDiagActivity;
import com.github.barteksc.pdfviewer.util.FileUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView imgTarjetas;
    private ImageView imgCotizaciones;
    private ImageView imgFinanciero;
    private ImageView imgClientes;
    private ImageView imgProveedores;
    private ImageView imgRH;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Pyme Assistant");

        imgClientes = (ImageView)findViewById(R.id.imgMainClientes);
        imgCotizaciones = (ImageView)findViewById(R.id.imgMainCotizaciones);
        imgFinanciero = (ImageView)findViewById(R.id.imgMainFinanciero);
        imgProveedores = (ImageView)findViewById(R.id.imgMainProveedor);
        imgRH = (ImageView)findViewById(R.id.imgMainRH);
        imgTarjetas = (ImageView)findViewById(R.id.imgMainTarjetas);

        imgTarjetas.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgCotizaciones.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgFinanciero.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgProveedores.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgRH.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgClientes.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);

        imgCotizaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CotizacionesActivity.class);
                startActivity(i);
            }
        });

        imgTarjetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TarjetasActivity.class);
                startActivity(i);
            }
        });

        imgFinanciero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ***************************** GUARDAMOS LA IMAGEN ***********************
                File folder = new File(Environment.getExternalStorageDirectory().toString(), "Documents");
                if(!folder.exists())
                    folder.mkdirs();
                File file = new File(folder, "finanzas_pyme.xlsx");

                if (!file.exists ()){
                    StorageReference storageRef = storage.getReferenceFromUrl("gs://pyme-assistant.appspot.com").child("Corrida financiera IYEM - METODOS.xlsx");
                    final long ONE_MEGABYTE = 1024 * 1024;
                    storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                            // ***************************** GUARDAMOS LA IMAGEN ***********************
                            File folder = new File(Environment.getExternalStorageDirectory().toString(), "Documents");

                            if(!folder.exists())
                                folder.mkdirs();
                            File file = new File(folder, "finanzas_pyme.xlsx");

                            if (file.exists ()) file.delete ();
                            try{
                                FileOutputStream out = new FileOutputStream(file);
                                out.write(bytes);
                                out.flush();
                                out.close();
/*
                            Uri pd = FileProvider.getUriForFile(MainActivity.this, "com.colabora.soluciones.convocatoriafeyac.provider", file);
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(pd, "application/vnd.ms-excel");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            try {
                                getApplicationContext().startActivity(intent);
                            } catch (ActivityNotFoundException e) {
                                Toast.makeText(getApplicationContext(), "No Application available to view XLS", Toast.LENGTH_SHORT).show();
                            }
*/
                            }catch (Exception e){
                                e.printStackTrace();

                            }
                            Toast.makeText(getApplicationContext(), "Descargado con exito", Toast.LENGTH_SHORT).show();
                            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.microsoft.office.excel");
                            if (launchIntent != null) {
                                startActivity(launchIntent);//null pointer check in case package name was not found
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Debes descargar Microsoft Excel en la Playstore para avanzar", Toast.LENGTH_LONG).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                        }
                    });

                }
                else{
                    //Uri uri = Uri.fromFile(file);
                    //new way
                    /*
                    Uri pd = FileProvider.getUriForFile(MainActivity.this, "com.colabora.soluciones.convocatoriafeyac.provider", file);

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.putExtra(android.content.Intent.EXTRA_STREAM, pd);
                    intent.setType("application/vnd.ms-excel");
                    //intent.setDataAndType(pd, "application/vnd.ms-excel");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        getApplicationContext().startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "Tu dispositivo no cuenta con algún lector de archivos de excel", Toast.LENGTH_SHORT).show();
                    }*/
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.microsoft.office.excel");
                    if (launchIntent != null) {
                        startActivity(launchIntent);//null pointer check in case package name was not found
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Debes descargar Microsoft Excel en la Playstore para avanzar", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        imgClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ClientesActivity.class);
                startActivity(i);
            }
        });

        imgProveedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NominasActivity.class);
                startActivity(i);
            }
        });

        imgRH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DiagnosticoActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Perfil) {
            return true;
        }
        else if(id == R.id.action_Cerrar){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Cerrar sesión");
            builder.setMessage("¿Estas seguro de querer cerrar sesión?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            FirebaseAuth.getInstance().signOut();
                            Intent i = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            // Create the AlertDialog object and return it
            builder.create();
            builder.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
