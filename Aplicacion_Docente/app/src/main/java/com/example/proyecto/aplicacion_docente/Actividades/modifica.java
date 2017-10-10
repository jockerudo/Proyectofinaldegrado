package com.example.proyecto.aplicacion_docente.Actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.proyecto.aplicacion_docente.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Usuario on 31/08/2017.
 */

public class modifica extends Activity {
    ArrayList<String> datosusuario ;
    String id,texto;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar);
        String texto= getIntent().getExtras().getString("texto");
        datosusuario= (ArrayList<String>) getIntent().getSerializableExtra("user");
        final String id= getIntent().getExtras().getString("id");
        final EditText foro = (EditText) findViewById(R.id.etBody);

        final EditText asunto = (EditText) findViewById(R.id.etSubject);

        Button btnmodificar = (Button)findViewById(R.id.btnSend);

        foro.setText(texto);

        btnmodificar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                        String texforo =""+foro.getText();

                    //192.168.1.107
                try {
                    texforo= URLEncoder.encode(texforo,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                new modifica.ModificaDatos().execute("http://192.168.1.107/aplicaciondocente/modifica.php?id=" + id+"&foro="+texforo+" ");//+"&password="+pass.getText().toString());


            }
        });



    }
    private class ModificaDatos extends AsyncTask<String, Void, String> {

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

            finish();
            Intent intent = new Intent(modifica.this, noticias.class);
            intent.putExtra("user",datosusuario);
            startActivity(intent);


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
