package com.colabora.soluciones.convocatoriafeyac;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LauncherActivity extends Activity {

    private LinearLayout linearAliados;
    private RelativeLayout relativeLayoutDesarrollado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        linearAliados = (LinearLayout)findViewById(R.id.linealLauncherAliados);
        relativeLayoutDesarrollado = (RelativeLayout)findViewById(R.id.relativeLauncherDesarrollado);

        linearAliados.setVisibility(View.INVISIBLE);
        relativeLayoutDesarrollado.setVisibility(View.INVISIBLE);

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
                Intent i = new Intent(LauncherActivity.this, LoginActivity.class);
                finish();
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
}
