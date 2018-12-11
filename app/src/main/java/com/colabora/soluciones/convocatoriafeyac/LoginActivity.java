package com.colabora.soluciones.convocatoriafeyac;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends Activity {

    private Button btnRegistrar;
    private EditText editCorreo;
    private EditText editContrasena;
    private Button btnLoginFace;
    private Button btnLogin;
    private TextView txtOlvideContrasena;

    FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnRegistrar = (Button)findViewById(R.id.btnSignUpLogin);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLoginFace = (Button)findViewById(R.id.btnFaceBack);
        editCorreo = (EditText)findViewById(R.id.editEmailLogin);
        editContrasena = (EditText)findViewById(R.id.editPasswordLogin);
        txtOlvideContrasena = (TextView)findViewById(R.id.txtForgotPassword);

        progressDialog = new ProgressDialog(LoginActivity.this);

        progressDialog.setTitle("Validando");
        progressDialog.setMessage("Espere un momento mientras el sistema inicia sesión");

        mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser(); //FirebaseAuth.getInstance().getCurrentUser;

                if(user != null){
                    Log.i("SESION", "Sesión iniciada con email: " + user.getEmail());
                    // LANZAMOS SEGUNDA ACTIVITY, ESTO SE QUEDA GUARDADO
                    //final String UUIDUser = user.getUid();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    finish();
                    startActivity(i);

                }else{
                    Log.i("SESION", "Sesión cerrada");
                }
            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat
                    .requestPermissions(LoginActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_LOCATION);
        }
        mAuth = FirebaseAuth.getInstance();


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                checkBlanckSpaces();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void checkBlanckSpaces(){
        boolean error = false;

        if(editCorreo.getText().toString().isEmpty()){
            error = true;
            editCorreo.setError("No se permiten campos vacíos");
        }

        else if(editContrasena.getText().toString().isEmpty()){
            error = true;
            editContrasena.setError("No se permiten campos vacíos");
        }


        if(error == false){
            progressDialog.show();
            IniciarSesion(editCorreo.getText().toString(), editContrasena.getText().toString());
        }

    }

    private void IniciarSesion(String email, String password){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.i("SESION", "Sesión iniciada correctamente");
                } else{
                    Log.e("SESION", task.getException().getMessage() + "");
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
