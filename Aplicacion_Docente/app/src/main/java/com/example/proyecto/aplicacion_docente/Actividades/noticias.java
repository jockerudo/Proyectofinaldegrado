package com.example.proyecto.aplicacion_docente.Actividades;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.aplicacion_docente.Interfaces.AsyncIfc;
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
import java.util.concurrent.ExecutionException;

/**
 * Created by Daniel on 27/04/2017.
 */
public class noticias extends Activity {
    TableLayout tabla;
    TableLayout cabecera;
    TableRow.LayoutParams layoutFila;
    TableRow.LayoutParams valor0;
    TableRow.LayoutParams valor1;
    TableRow.LayoutParams valor2;
    TableRow.LayoutParams valor3;
    TableRow.LayoutParams valor4;
    ArrayList<String> datosusuario ;

    Resources rs;
    JSONArray ja = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticias);
        datosusuario = (ArrayList<String>) getIntent().getSerializableExtra("user");
        //recorrer Arraylist
        for (int x = 0; x < datosusuario.size(); x++) {
            System.out.println(datosusuario.get(x));
        }
        rs = this.getResources();
        tabla = (TableLayout) findViewById(R.id.tabla);
        cabecera = (TableLayout) findViewById(R.id.cabecera);
        layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);

        valor1 = new TableRow.LayoutParams(1300, TableRow.LayoutParams.WRAP_CONTENT);
        valor0 = new TableRow.LayoutParams(650, TableRow.LayoutParams.FILL_PARENT);
        valor2 = new TableRow.LayoutParams(1300, TableRow.LayoutParams.WRAP_CONTENT);


        agregarCabecera();
        agregarFilasTabla();
    }



    public void agregarCabecera() {
        TableRow fila;
        TextView txtvalor1;

        fila = new TableRow(this);
        fila.setLayoutParams(layoutFila);

        txtvalor1 = new TextView(this);


        txtvalor1.setText(rs.getString(R.string.valor1));
        txtvalor1.setGravity(Gravity.CENTER_HORIZONTAL);
        txtvalor1.setTextAppearance(this, R.style.etiqueta);
        txtvalor1.setBackgroundResource(R.drawable.tabla_celda_cabecera);
        txtvalor1.setLayoutParams(valor1);


        fila.addView(txtvalor1);

        cabecera.addView(fila);

    }





    public void agregarFilasTabla() {
    String texto="";
       ConsultarDatos tarea = new ConsultarDatos();
       tarea.execute("http://192.168.1.107/aplicaciondocente/cargarforo.php?id_centro="+datosusuario.get(11));


        tarea.completionCode = new AsyncIfc() {

            @Override
            public void onComplete() {
                // Any logic you want to happen after execution
            }
        };
        String auxMatriz [][] = new String[0][0];
        try {

          //  System.out.println("----->>>>>>>>>>>>>>>>>>>>>>>>>>>>"+tarea.get());

            String resultado[];
            String auxString = "";
            int i;
            for(i=0;i<tarea.get().length();i++) {
                //System.out.println("Substring" +tarea.get().substring(i));
                auxString += tarea.get().substring(i,i+1);

                if(tarea.get().substring(i,i+1).equals("]")){
                    if (!tarea.get().substring(i+1,i+2).equals("[")){
                        break;
                    }
                    else{

                    }
                }

            }

            String auxReplace = "[" + '"';
            auxString = auxString.replace(auxReplace,"");
            String auxSplit = '"' + "]";
            resultado = auxString.split(auxSplit);

            System.out.println("La longitud del resultado es"+resultado.length);

            auxMatriz = new String[resultado.length][8];
            auxSplit = '"' + "," + '"';

            for(i = 0; i < resultado.length;i++ ){

                auxMatriz[i] = resultado[i].split(auxSplit);
                System.out.println(auxMatriz[i].length);


            }
            System.out.println(auxMatriz[3][0]+"  "+auxMatriz[3][1]+" "+auxMatriz[3][2]);





        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //Crear cabecera de comentario





        for(int i=0;i<auxMatriz.length;i++) {
            for (int j=0;j<7;j++) {
                auxMatriz[i][j] = auxMatriz[i][j].replace("\u00e1", "á");
                auxMatriz[i][j] = auxMatriz[i][j].replace("\u00e9", "é");
                auxMatriz[i][j] = auxMatriz[i][j].replace("\u00ed", "í");
                auxMatriz[i][j] = auxMatriz[i][j].replace("\u00f3", "ó");
                auxMatriz[i][j] = auxMatriz[i][j].replace("\u00fa", "ú");
                auxMatriz[i][j] = auxMatriz[i][j].replace("\u00c1", "Á");
                auxMatriz[i][j] = auxMatriz[i][j].replace("\u00c9", "É");
                auxMatriz[i][j] = auxMatriz[i][j].replace("\u00cd", "Í");
                auxMatriz[i][j] = auxMatriz[i][j].replace("\u00d3", "Ó");
                auxMatriz[i][j] = auxMatriz[i][j].replace("\u00da", "Ú");
                auxMatriz[i][j] = auxMatriz[i][j].replace("\u00f1", "ñ");
                auxMatriz[i][j] = auxMatriz[i][j].replace("\u00d1", "Ñ");


            }

    }
        ArrayAdapter adapter;
        String id="";
        //montamos la interfaz
        for(int i = 0; i < auxMatriz.length;i++ ){
           texto= auxMatriz[i][6];

            TableRow fila1;
            id=auxMatriz[i][0];

            if (datosusuario.get(0).equals(auxMatriz[i][2])) {
                String[] mod = {"---", "Modificar", "Borrar"};
                 adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, mod);}else{
                String[] mod = {"---"};
                 adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, mod);
            }


            final Spinner modifica;
            TableRow fila;
            TextView txtvalor1;
            fila = new TableRow(this);
            fila.setLayoutParams(layoutFila);
            modifica = new Spinner(this);
            txtvalor1 = new TextView(this);



            txtvalor1.setText("Usuario: "+auxMatriz[i][7]);
            txtvalor1.setGravity(Gravity.CENTER_HORIZONTAL);
            txtvalor1.setTextAppearance(this, R.style.etiqueta1);
            txtvalor1.setBackgroundResource(R.drawable.tabla_cabecera);
            txtvalor1.setLayoutParams(valor0);

            modifica.setAdapter(adapter);
            modifica.setGravity(Gravity.CENTER);
            //modifica.setTextAppearance(this, R.style.etiqueta1);
            modifica.setBackgroundResource(R.drawable.spinner);
            modifica.setLayoutParams(valor0);

            fila.addView(txtvalor1);
            fila.addView(modifica);

            tabla.addView(fila);



            //fila de texto




                //ActionBar modi;
                TextView txtvalor2;

                fila1 = new TableRow(this);
                fila1.setLayoutParams(layoutFila);
                txtvalor2 = new TextView(this);

                fila1 = new TableRow(this);
                fila1.setLayoutParams(layoutFila);

                texto=texto.replace("\u00f1", "ñ");
               //httpPost.setEntity(new UrlEncodedFormEntity(texto, "iso-8859-1"));
                System.out.println(texto);
                txtvalor2.setText(texto);
                txtvalor2.setGravity(Gravity.CENTER_HORIZONTAL);
                txtvalor2.setTextAppearance(this, R.style.etiqueta);
                txtvalor2.setLayoutParams(valor2);
                fila1.addView(txtvalor2);

                final String finalTexto = texto;
                final String[][] finalAuxMatriz = auxMatriz;
                final String finalTexto1 = texto;
                final String finalId = id;
                final String finalId1 = id;
                modifica.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        // TODO Auto-generated method stub
                        String  mselection=modifica.getSelectedItem().toString();
                        System.out.println(mselection);
                        /**** do your code*****/

                        if(mselection.equals("Modificar")){
                            finish();
                            Intent intent = new Intent(noticias.this, modifica.class);
                            intent.putExtra("texto", finalTexto1);
                            intent.putExtra("user",datosusuario);
                            intent.putExtra("id", finalId);
                            startActivity(intent);



                        }
                        if(mselection.equals("Borrar")){

                            new noticias.borrado().execute("http://192.168.1.107//aplicaciondocente/borrar.php?id=" + finalId1);

                            finish();
                            Intent intent = new Intent(noticias.this, noticias.class);
                            intent.putExtra("user",datosusuario);

                            startActivity(intent);



                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                        //
                    }
                });






            fila1.setBackgroundResource(R.drawable.tabla_celda);
            tabla.addView(fila1);
        }




}

    private class ConsultarDatos extends AsyncTask<String, Void, String> {
        public AsyncIfc completionCode;
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






                } catch (JSONException e) {
                e.printStackTrace();
            }
            completionCode.onComplete();

        }
    }
    private class borrado extends AsyncTask<String, Void, String> {
        public AsyncIfc completionCode;
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

            Toast.makeText(noticias.this, "mensaje borrado", Toast.LENGTH_SHORT).show();


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

