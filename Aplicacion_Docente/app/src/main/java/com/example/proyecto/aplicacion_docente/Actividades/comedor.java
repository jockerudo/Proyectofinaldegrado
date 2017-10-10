package com.example.proyecto.aplicacion_docente.Actividades;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;


import android.widget.TextView;

import com.example.proyecto.aplicacion_docente.FileDownloader;
import com.example.proyecto.aplicacion_docente.R;

import java.util.concurrent.ExecutionException;

import  org.jsoup.Jsoup ;
import  org.jsoup.nodes.Document ;
import  org.jsoup.select.Elements ;


/**
 * Created by Daniel on 27/04/2017.
 */

public class comedor extends Activity {


    String url = "http://mestreacasa.gva.es/web/sanjuanevangelista/25";
    ProgressDialog mProgressDialog;

    String direccion;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comedor);
        final TextView textView = (TextView) findViewById(R.id.url);
        Button menu = (Button) findViewById(R.id.menu);

        // Capture button click
        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Execute Title AsyncTask
                AsyncTask<Void, Void, String> x = new menumes().execute();

                Intent in = new Intent(getApplicationContext(), menu.class);




                try {
                    in.putExtra("url",x.get());

                    //System.out.println("-------------------------"+x.get()+">>>>>>>>>>>>>>>>>>>>>>>");
                  //  startActivity(    new Intent(Intent.ACTION_VIEW, Uri.parse("http://mestreacasa.gva.es"+x.get())));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                startActivity(in);

            }
        });

    }

    // Title AsyncTask
    private class   menumes  extends AsyncTask<Void,Void,String > {


        public String doInBackground (Void ... params )   {
            String pdfHref="" ;
            try   {
                // Connect to the web site
                Document  document   = Jsoup.connect(url).get() ;
                // Using Elements to get the Meta data
                Elements  links   =   document.select("span[id=4600525900_500011984894_1.0] div div div div div div div p a[href]");
                //div[class=middleRight-NORMAL] div[class=contenido-NORMAL] p a[href]");
                 pdfHref = links.attr("href");

                // Locate the content attribute
            }   catch   ( IOException e )   {
                e . printStackTrace ( ) ;
            }

            String fileUrl = "http://mestreacasa.gva.es"+pdfHref;
// -> https://letuscsolutions.files.wordpress.com/2015/07/five-point-someone-chetan-bhagat_ebook.pdf
            String fileName = "menu.pdf";
// ->five-point-someone-chetan-bhagat_ebook.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "PDF DOWNLOAD");
            folder.mkdir();

            File pdfFile = new File(folder, fileName    );

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            System.out.println ("---------------------------"+fileUrl+">>>>>>>>>");
            FileDownloader.downloadFile(fileUrl, pdfFile);
           System.out.println (pdfFile.toString()+ "existe el pdf---------------------------");
            return   pdfFile.getPath();
        }
        @ Override
        protected   void   onPreExecute ( )   {
            super . onPreExecute() ;
            mProgressDialog   =   new   ProgressDialog ( comedor.this ) ;
            mProgressDialog . setTitle ( "Menu del Mes" ) ;
            mProgressDialog . setMessage ( "Loading..." ) ;
            mProgressDialog . setIndeterminate ( false ) ;
            mProgressDialog . show() ;
        }


        protected void onPostExecute(String pdfHref) {
            super.onPostExecute(pdfHref);
            direccion = pdfHref;
          //System.out.println(pdfHref);
            mProgressDialog.dismiss();
            //return direccion;

        }
    }
}