package com.colabora.soluciones.convocatoriafeyac.Modelos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.colabora.soluciones.convocatoriafeyac.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.support.v7.widget.ShareActionProvider;
import android.widget.Toast;

import java.io.File;

public class VerPDFDiagActivity extends AppCompatActivity {

    private PDFView pdfView;
    private File file;
    private String direccion;

    private Button btnFinalizar;
    private Button btnCompartir;
    private ShareActionProvider shareAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pdfdiag);

        pdfView = (PDFView)findViewById(R.id.pdfViewCotizacionPDF2);

        btnCompartir = (Button)findViewById(R.id.btnFinalizaryCompartirDiagnostico);
        btnFinalizar = (Button)findViewById(R.id.btnFinalizarDiagnostico);

        final Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            file = new File((bundle.getString("path","")));
            direccion = bundle.getString("path","");
        }

        pdfView.fromFile(file)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .enableAntialiasing(true)
                .load();

        btnCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder = new  File(Environment.getExternalStorageDirectory().toString(), "PymeAssitant");
                if(!folder.exists())
                    folder.mkdirs();
                File file = new File(folder, "Diagnostico.pdf");                    //old way
                //Uri uri = Uri.fromFile(file);
                //new way
                Uri pd = FileProvider.getUriForFile(VerPDFDiagActivity.this, "com.colabora.soluciones.convocatoriafeyac.provider", file);
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);

                //intent.setDataAndType(pd,"application/pdf");
                intent.setType("application/pdf");
                //String shareBodyText = "Para la mejora continua de mi empresa/negocio he realizado un diagnóstico el cual se comparto a continuación por medio de la aplicación Pyme Assistant";
                //intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Diágnostico Pyme Assitant");
                intent.putExtra(android.content.Intent.EXTRA_STREAM, pd);

                startActivity(Intent.createChooser(intent, "Escoge un método para compartir"));


            }
        });
    }
}
