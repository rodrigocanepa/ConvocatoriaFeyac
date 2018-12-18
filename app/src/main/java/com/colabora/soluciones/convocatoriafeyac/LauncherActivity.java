package com.colabora.soluciones.convocatoriafeyac;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LauncherActivity extends Activity {

    private RelativeLayout linearAliados;
    private RelativeLayout relativeLayoutDesarrollado;

    FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

    private boolean sesion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        linearAliados = (RelativeLayout) findViewById(R.id.linealLauncherAliados);
        relativeLayoutDesarrollado = (RelativeLayout)findViewById(R.id.relativeLauncherDesarrollado);

        linearAliados.setVisibility(View.INVISIBLE);
        relativeLayoutDesarrollado.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser(); //FirebaseAuth.getInstance().getCurrentUser;

                if(user != null){
                    Log.i("SESION", "Sesión iniciada con email: " + user.getEmail());
                    sesion = true;

                }else{
                    Log.i("SESION", "Sesión cerrada");
                    sesion = false;
                }
            }
        };


        Animation animation = AnimationUtils.loadAnimation(LauncherActivity.this, R.anim.transparentar);
        final Animation animation1 = AnimationUtils.loadAnimation(LauncherActivity.this, R.anim.transparentar);
        relativeLayoutDesarrollado.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                relativeLayoutDesarrollado.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                linearAliados.setAnimation(animation1);
                relativeLayoutDesarrollado.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                linearAliados.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
               if (sesion){
                   // LANZAMOS SEGUNDA ACTIVITY, ESTO SE QUEDA GUARDADO
                   //final String UUIDUser = user.getUid();
                   Intent i = new Intent(LauncherActivity.this, MainActivity.class);
                   finish();
                   startActivity(i);
               }
               else{
                   Intent i = new Intent(LauncherActivity.this, LoginActivity.class);
                   finish();
                   startActivity(i);
               }


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

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
}
