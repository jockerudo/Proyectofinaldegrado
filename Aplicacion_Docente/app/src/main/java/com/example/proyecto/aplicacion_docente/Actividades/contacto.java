package com.example.proyecto.aplicacion_docente.Actividades;

/**
 * Created by Daniel on 27/04/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.proyecto.aplicacion_docente.R;


/** Hay que depurar este código para el envio de correo.
 * Created by David on 12/09/2015.
 */
public class contacto extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacto);


        final ImageButton mail = (ImageButton) findViewById(R.id.imageButton);
        final ImageButton llamada = (ImageButton) findViewById(R.id.imageButton1);


        mail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


               // Intent in = new Intent(getApplicationContext(), mail.class);


                //startActivity(in);
            }


        });

        llamada.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:666-666-666"));
                //startActivity(intent);


            }


        });

    }
    /**  protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.contacto);
     // WebView myWebView = (WebView) this.findViewById(R.id.contacto);
     final Button envio = (Button) findViewById(R.id.button);


     envio.setOnClickListener(new View.OnClickListener(){





     envio.setOnClickListener(new OnClickListener() {

     }
     public void onClick(View v) {
     //obtenemos los datos para el envío del correo
     String etEmail = "46005259@edu.gva.es ";


     //es necesario un intent que levante la actividad deseada
     Intent itSend = new Intent(android.content.Intent.ACTION_SEND);

     //vamos a enviar texto plano a menos que el checkbox esté marcado
     itSend.setType("plain/text");

     //colocamos los datos para el envío
     itSend.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ etEmail});


     //revisamos si el checkbox está marcado enviamos el ícono de la aplicación como adjunto


     //iniciamos la actividad
     startActivity(itSend);
     }

     });



     }**/
}
