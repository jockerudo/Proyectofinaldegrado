package com.example.proyecto.aplicacion_docente.Actividades;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;

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


/**
 * Created by Usuario on 26/08/2017.
 */

public class registro extends AppCompatActivity {
    Button btnregistro;
    EditText usuario,pass,repass,nombre,apellidos,dni,email,telf,pais,ciudad;
    Spinner tipo,centro;
    int tip,cent;
    ArrayList<String> user=null;
    JSONArray ja = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        //layout = (ViewGroup) findViewById(R.id.content);	//layout sera la variable donde metamos la imagen y el nombre de la categoria.
        //int id = R.layout.layout_registro;	 				//id es el identificador de layout_category (para poder llamarlo luego).

//        LayoutInflater inflater = LayoutInflater.from(this);
  //      final RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(id, null, false);

      //  layout.addView(relativeLayout);

        usuario = (EditText) findViewById(R.id.editText1);
        pass = (EditText) findViewById(R.id.editText2);
        repass = (EditText) findViewById(R.id.EditText3);
        nombre = (EditText) findViewById(R.id.editText3);
        apellidos = (EditText) findViewById(R.id.EditText03);
        dni = (EditText) findViewById(R.id.editText4);
        email = (EditText) findViewById(R.id.editText10);
        telf = (EditText) findViewById(R.id.editText12);
        pais = (EditText) findViewById(R.id.editText11);
        ciudad = (EditText) findViewById(R.id.EditText02);
        btnregistro = (Button) findViewById(R.id.button2);
        tipo = (Spinner) findViewById(R.id.spinner1);

        centro = (Spinner) findViewById(R.id.spinner2);



        btnregistro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pass.getText().toString().equals(repass.getText().toString())) {
                    if(String.valueOf(tipo.getSelectedItem()).equals("profesor")){
                        tip=1;
                    }else if(String.valueOf(tipo.getSelectedItem()).equals("alumno")){
                        tip=2;
                    }else if(String.valueOf(tipo.getSelectedItem()).equals("padre")){
                        tip=3;
                    }

                    if(String.valueOf(centro.getSelectedItem()).equals("Colegio San Roque")){
                        cent=1;
                    }else if(String.valueOf(centro.getSelectedItem()).equals("Colegio San Juan Evangelista")){
                        cent=2;
                    }else if(String.valueOf(centro.getSelectedItem()).equals("Colegio Juan Comenius")){
                        cent=3;
                    }else if(String.valueOf(centro.getSelectedItem()).equals("Colegio EL CLOT")){
                        cent=4;
                    }




                    if (usuario.getText().toString().equals("")) {
                        Toast.makeText(registro.this, "debes rellenar el campo usuario  ", Toast.LENGTH_SHORT).show();

                    } else if (pass.getText().toString().equals("")) {
                        Toast.makeText(registro.this, "debes rellenar el campo password  ", Toast.LENGTH_SHORT).show();

                    } else if (repass.getText().toString().equals("")) {
                        Toast.makeText(registro.this, "debes rellenar el campo repite password  ", Toast.LENGTH_SHORT).show();

                    } else if (nombre.getText().toString().equals("")) {
                        Toast.makeText(registro.this, "debes rellenar el campo nombre  ", Toast.LENGTH_SHORT).show();

                    } else if (apellidos.getText().toString().equals("")) {
                        Toast.makeText(registro.this, "debes rellenar el campo apellidos  ", Toast.LENGTH_SHORT).show();

                    } else if (dni.getText().toString().equals("")) {
                        Toast.makeText(registro.this, "debes rellenar el campo DNI  ", Toast.LENGTH_SHORT).show();

                    } else if (telf.getText().toString().equals("")) {
                        Toast.makeText(registro.this, "debes rellenar el campo telf  ", Toast.LENGTH_SHORT).show();


                    } else {


                        //
                            new registro.ConsultarDatosdni().execute("http://192.168.1.107/aplicaciondocente/consulta.php?dni=" + dni.getText().toString());








                    }
                }else{ Toast.makeText(registro.this, "las password no coinciden", Toast.LENGTH_SHORT).show();}
            }

        });
    }

    class insregistro extends AsyncTask<String,Void,String> {

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

            Toast.makeText(getApplicationContext(), "Se almacenaron los datos correctamente", Toast.LENGTH_LONG).show();
            finish();

            Intent intent = new Intent(registro.this,aplimenu.class);
            intent.putExtra("user", user);
            startActivity(intent);



        }
    }

    private class ConsultarDatosdni extends AsyncTask<String, Void, String> {
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

                    for (int i=0;i<ja.length();i++){
                        user.set(i, ja.getString(i));

                    }

                    new registro.ConsultarDatosusuario().execute("http://192.168.1.107/aplicaciondocente/consultaregistro.php?usuario=" + usuario.getText().toString());


                }
                else{

                Toast.makeText(getApplicationContext(), "DNI ya registrado", Toast.LENGTH_LONG).show();






            } }catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private class ConsultarDatosusuario extends AsyncTask<String, Void, String> {
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


                    new registro.insregistro().execute(("http://192.168.1.107/aplicaciondocente/registro.php?" + "dni=" + dni.getText().toString() + "&tipo=" + tip + "&correo=" + email.getText().toString() + "&nombre=" + nombre.getText().toString() + "&apellidos=" + apellidos.getText().toString() + "&telf=" + telf.getText().toString() + "&ciudad=" + ciudad.getText().toString() + "&pais=" + pais.getText().toString() + "&usuario=" + usuario.getText().toString() + "&password=" + pass.getText().toString() + "&id_centro="+cent + "&activo=1"));


                }
                else{
                    Toast.makeText(getApplicationContext(), "Nombre de usuario no disponible", Toast.LENGTH_LONG).show();



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
