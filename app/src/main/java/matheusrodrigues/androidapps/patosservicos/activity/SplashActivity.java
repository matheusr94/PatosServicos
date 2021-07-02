package matheusrodrigues.androidapps.patosservicos.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import matheusrodrigues.androidapps.patosservicos.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);
        getSupportActionBar().hide();

        //duração da splashScreen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                abrirTelaAnuncios();
            }
        }, 3000);
    }


    private void abrirTelaAnuncios(){
        Intent i = new Intent(SplashActivity.this, AnunciosActivity.class);
        startActivity(i);
        finish();
    }
    private void abrirTelaAutenticacao(){
        Intent i = new Intent(SplashActivity.this, AutenticacaoActivity.class);
        startActivity(i);
        finish();
    }
}