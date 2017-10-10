package com.example.proyecto.aplicacion_docente.Actividades;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.example.proyecto.aplicacion_docente.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnConectar,btnregistro;
    EditText dni,pass;
    JSONArray ja = null;
    ArrayList<String>  user = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

          dni = (EditText)findViewById(R.id.editText1);
          pass = (EditText)findViewById(R.id.editText2);
         btnConectar = (Button)findViewById(R.id.button1);
         btnregistro =(Button)findViewById(R.id.button2);



        btnConectar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        if(dni.getText().toString().equals("")){
            Toast.makeText(MainActivity.this, "debes rellenar el campo DNI  ", Toast.LENGTH_SHORT).show();

        }else {

            //192.168.1.107
            new MainActivity.ConsultarDatos().execute("http://192.168.1.102/aplicaciondocente/consulta.php?dni=" + dni.getText().toString());//+"&password="+pass.getText().toString());
        }

            }
        });




        btnregistro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent in = new Intent(getApplicationContext(), registro.class);


                startActivity(in);

            }
        });

    }



    private class ConsultarDatos extends AsyncTask<String, Void, String> {
        String password;
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {



                try {

                    ja = new JSONArray(result);

                    if(ja.getString(0).equals("null")){
                        Toast.makeText(MainActivity.this, "DNI no registrado", Toast.LENGTH_SHORT).show();}
                    else{
                    password = ja.getString(10);


                        for (int i=0;i<ja.length();i++){

                            user.add( ja.getString(i));

                        }


                    if (password.equals(pass.getText().toString())) {
                        finish();
                        Intent intent = new Intent(MainActivity.this,aplimenu.class);
                        intent.putExtra("user",user);
                        startActivity(intent);


                    }else{Toast.makeText(MainActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();}


                } }catch (JSONException e) {
                    e.printStackTrace();
                }

        }
    }




    private String downloadUrl(String myurl) throws IOException {
        Log.i("URL",""+myurl);
        myurl = myurl.replace(" ","%20");
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}






















       /* setContentView(R.layout.activity_main);
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
                Intent in = new Intent(getApplicationContext(),colegio.class);



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
                Intent in = new Intent(getApplicationContext(),noticias.class);


                startActivity(in);
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
*/