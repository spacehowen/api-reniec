package com.spacehowen.apireniec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText TxtDni, TxtNombres, TxtToken;
    Button BtnConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_main);

        BtnConsultar = (Button) findViewById(R.id.BtnConsultar);

        TxtDni = (EditText) findViewById(R.id.TxtDni);

        TxtNombres = (EditText) findViewById(R.id.TxtNombres);

        TxtToken = (EditText) findViewById(R.id.TxtToken);
        

        BtnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                COnsultarDNi();
            }
        });


    }

    private void COnsultarDNi() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String dni = TxtDni.getText().toString();

        String token = TxtToken.getText().toString();

        String url = "https://apiperu.dev/api/dni/" + dni + "?api_token=" + token;


        try {
            Document document = Jsoup.connect(url)
                    .ignoreHttpErrors(true)
                    .ignoreContentType(true)
                    .get();
            Element element = document.body();

            String resultado = element.text();

            TxtNombres.setText(resultado);

        } catch (Exception e) {
            Toast.makeText(this, "No se encontro el dni"+e, Toast.LENGTH_SHORT).show();
        }

    }
}