package com.colabora.soluciones.convocatoriafeyac;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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

    private SharedPreferences sharedPreferences;
    private LoginButton loginButton;
    private CallbackManager mCallbackManager;


    private String profileFoto;
    private String email;
    private String firstName;
    private String lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        btnRegistrar = (Button)findViewById(R.id.btnSignUpLogin);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLoginFace = (Button)findViewById(R.id.btnFaceBack);
        editCorreo = (EditText)findViewById(R.id.editEmailLogin);
        editContrasena = (EditText)findViewById(R.id.editPasswordLogin);
        txtOlvideContrasena = (TextView)findViewById(R.id.txtForgotPassword);

        loginButton = (LoginButton)findViewById(R.id.login_buttonFace);

        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.colabora.soluciones.convocatoriafeyac",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String key = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        progressDialog = new ProgressDialog(LoginActivity.this);

        progressDialog.setTitle("Validando");
        progressDialog.setMessage("Espere un momento mientras el sistema inicia sesión");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat
                    .requestPermissions(LoginActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_LOCATION);
        }

        mAuth = FirebaseAuth.getInstance();
        // ***************************** FACEBOOK *************************************
        mCallbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email", "public_profile");

        btnLoginFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.performClick();
            }
        });


        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                System.out.println("onSuccess");

                String accessToken = loginResult.getAccessToken().getToken();
                Log.i("accessToken", accessToken);

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("LoginActivity", response.toString());
                        // Get facebook data from login
                        Bundle bFacebookData = getFacebookData(object);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();

                Log.d("FACEBOOK", "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("FACEBOOK", "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("FACEBOOK", "facebook:onError", error);
                // ...
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser(); //FirebaseAuth.getInstance().getCurrentUser;

                if(user != null){
                    Log.i("SESION", "Sesión iniciada con email: " + user.getEmail());
                    sharedPreferences = getSharedPreferences("misDatos", 0);
                    boolean suscripcion = sharedPreferences.getBoolean("suscripcion", false);
                    if(suscripcion){
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else{

                        /*Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();*/

                        Intent i = new Intent(LoginActivity.this, SuscripcionesActivity.class);
                        startActivity(i);
                        finish();
                    }


                }else{
                    Log.i("SESION", "Sesión cerrada");
                }
            }
        };


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


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("FACEBOOK", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("FACEBOOK", "signInWithCredential:success");

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FACEBOOK", "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
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

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                    new AlertDialog.Builder(this)
                            .setTitle("Aviso")
                            .setMessage("Si no concede los permisos la app se cerrará")
                            .setPositiveButton("Cerrar aplicación", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            })
                            .create()
                            .show();

                }
                return;
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=150&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());
                profileFoto = profile_pic.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name")){
                bundle.putString("first_name", object.getString("first_name"));
                firstName = object.getString("first_name");
            }
            if (object.has("last_name")){
                bundle.putString("last_name", object.getString("last_name"));
                lastName = object.getString("last_name");
            }
            if (object.has("email")){
                bundle.putString("email", object.getString("email"));
                email = object.getString("email");
            }
            if (object.has("gender")){
                bundle.putString("gender", object.getString("gender"));
            }
            if (object.has("birthday")){
                bundle.putString("birthday", object.getString("birthday"));
            }
            if (object.has("location")){
                bundle.putString("location", object.getJSONObject("location").getString("name"));
            }

            return bundle;
        }
        catch(JSONException e) {
            Log.d("JSON","Error parsing JSON");
        }
        return null;
    }

}
