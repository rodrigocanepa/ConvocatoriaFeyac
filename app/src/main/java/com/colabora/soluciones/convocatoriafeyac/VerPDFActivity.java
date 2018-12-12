package com.colabora.soluciones.convocatoriafeyac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;

public class VerPDFActivity extends AppCompatActivity {

    private PDFView pdfView;
    private File file;
    private String direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pdf);

        pdfView = (PDFView)findViewById(R.id.pdfViewCotizacionPDF);

        final Bundle bundle = getIntent().getExtras();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //FirebaseAuth.getInstance().getCurrentUser;
        final String UUIDUser = user.getUid();

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
    }
}
