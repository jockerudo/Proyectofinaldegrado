package com.example.proyecto.aplicacion_docente.Actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.proyecto.aplicacion_docente.R;

import java.util.ArrayList;

/**
 * Created by Usuario on 25/08/2017.
 */

public class aplimenu extends Activity {
//

ArrayList<String> user ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user= (ArrayList<String>) getIntent().getSerializableExtra("user");


        ImageButton colegio = (ImageButton) findViewById(R.id.colegio);
    ImageButton comedor = (ImageButton) findViewById(R.id.comedor);
    ImageButton noticias = (ImageButton) findViewById(R.id.noticias);
    ImageButton ubicacion = (ImageButton) findViewById(R.id.ubicacion);
    ImageButton contacto = (ImageButton) findViewById(R.id.contacto);


        colegio.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //finalizada

            //TODO Auto-generated method stub
            Intent in = new Intent(getApplicationContext(),tabla.class);//colegio.class);



            startActivity(in);
        }


    });
        comedor.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            // webservice
            //TODO Auto-generated method stub
            Intent in = new Intent(getApplicationContext(), comedor.class);


            startActivity(in);
        }


    });
        noticias.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // webservice
            //TODO Auto-generated method stub

            Intent intent = new Intent(aplimenu.this,noticias.class);
            System.out.println("no puede estar vacio"+user.get(2));
            intent.putExtra("user", user);
            startActivity(intent);

        }


    });

        ubicacion.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //TODO Auto-generated method stub
            Intent in = new Intent(getApplicationContext(), ubicacion.class);


            startActivity(in);
        }


    });
        contacto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //TODO Auto-generated method stub
            Intent in = new Intent(getApplicationContext(), contacto.class);


            startActivity(in);
        }


    });
}




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


