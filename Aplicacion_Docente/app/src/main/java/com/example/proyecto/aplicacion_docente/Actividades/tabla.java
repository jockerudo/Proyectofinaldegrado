package com.example.proyecto.aplicacion_docente.Actividades;

/**
 * Created by Usuario on 29/08/2017.
 */



import android.app.ActionBar;
import android.content.res.Resources;

import android.os.Bundle;
import android.app.Activity;


import android.view.Gravity;

import android.view.ViewGroup;

import android.widget.ArrayAdapter;


import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.proyecto.aplicacion_docente.R;


public class tabla extends Activity {


    private ViewGroup layout;

    TableLayout tabla;
    TableLayout cabecera;
    TableRow.LayoutParams layoutFila;
    TableRow.LayoutParams valor1;
    TableRow.LayoutParams valor2;
    TableRow.LayoutParams valor3;
    TableRow.LayoutParams valor4;

    //ArrayList <Product> prods = new ArrayList <Product>();
    Resources rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tablaforodinamica);
        rs = this.getResources();
        tabla = (TableLayout)findViewById(R.id.tabla);
        cabecera = (TableLayout)findViewById(R.id.cabecera);
        layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);

            valor1 = new TableRow.LayoutParams(1300,TableRow.LayoutParams.WRAP_CONTENT);
            valor2 = new TableRow.LayoutParams(1020,TableRow.LayoutParams.WRAP_CONTENT);
            valor3 = new TableRow.LayoutParams(200,TableRow.LayoutParams.FILL_PARENT);
            valor4 = new TableRow.LayoutParams(50,TableRow.LayoutParams.FILL_PARENT);
        agregarCabecera();
        agregarCabeceratabla();

    }
    public void agregarCabecera(){
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

    public void agregarCabeceratabla(){
        Spinner modifica;
        TableRow fila;
        TextView txtvalor1;
        fila = new TableRow(this);
        fila.setLayoutParams(layoutFila);
        modifica= new Spinner(this);
        txtvalor1 = new TextView(this);

        String[] mod = { "---", "Modificar", "Borrar" ,"Denunciar"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, mod);

        txtvalor1.setText("Usuario:");
        txtvalor1.setGravity(Gravity.CENTER_HORIZONTAL);
        txtvalor1.setTextAppearance(this, R.style.etiqueta1);
        txtvalor1.setBackgroundResource(R.drawable.tabla_cabecera);
        txtvalor1.setLayoutParams(valor4);

        modifica.setAdapter(adapter);
        modifica.setGravity(Gravity.CENTER);
        //modifica.setTextAppearance(this, R.style.etiqueta1);
        modifica.setBackgroundResource(R.drawable.spinner);
        modifica.setLayoutParams(valor4);

        fila.addView(txtvalor1);
        fila.addView(modifica);

        tabla.addView(fila);
        agregarFilasTabla();
    }

    public void agregarFilasTabla() {

        //Agregar cabecera de mensaje
        Spinner modifica;
        TableRow fila;
        //ActionBar modi;
        TextView txtvalor1;


        fila = new TableRow(this);
        fila.setLayoutParams(layoutFila);
        txtvalor1 = new TextView(this);
        modifica= new Spinner(this);
        txtvalor1.setText("usuario: ");

        String prueba=
                "Puede empezar con su propio color escribiendo su código de color en el campo de entrada superior.";
        txtvalor1.setText(prueba);


        modifica.setGravity(Gravity.CENTER_HORIZONTAL);
       modifica.setLayoutParams(valor3);


        txtvalor1.setGravity(Gravity.CENTER_HORIZONTAL);
        txtvalor1.setTextAppearance(this, R.style.etiqueta);
        txtvalor1.setLayoutParams(valor2);

        fila.setBackgroundResource(R.drawable.tabla_celda);
        fila.addView(txtvalor1);
        fila.addView(modifica);

        tabla.addView(fila);}



        //conectar con la base de datos para cargar informacion;
        /*MyDataBase MDB = new MyDataBase(getApplicationContext());
        Cart[] cart = MDB.Todosloscarros();
        if (MDB.esvaciaCESTA() > 0) {
            for (int i = 0; i < cart.length; i++) {
                nombre_producto=cart[i].getNombre_produto();
                precio=cart[i].getPrecio();
                cantidad=cart[i].getCantidad();


                fila = new TableRow(this);
                fila.setLayoutParams(layoutFila);

                txtvalor1 = new TextView(this);
                txtvalor2 = new TextView(this);
                txtvalor3 = new TextView(this);
                txtvalor4 = new TextView(this);

                txtvalor1.setText(nombre_producto);
                txtvalor1.setGravity(Gravity.CENTER_HORIZONTAL);
                txtvalor1.setTextAppearance(this, R.style.etiqueta);
                txtvalor1.setBackgroundResource(R.drawable.tabla_celda);
                txtvalor1.setLayoutParams(valor1);

                txtvalor2.setText(String.valueOf(precio) + "\u20AC" );
                txtvalor2.setGravity(Gravity.CENTER);
                txtvalor2.setTextAppearance(this, R.style.etiqueta);
                txtvalor2.setBackgroundResource(R.drawable.tabla_celda);
                txtvalor2.setLayoutParams(valor2);

                txtvalor3.setText(String.valueOf(cantidad) );
                txtvalor3.setGravity(Gravity.CENTER);
                txtvalor3.setTextAppearance(this, R.style.etiqueta);
                txtvalor3.setBackgroundResource(R.drawable.tabla_celda);
                txtvalor3.setLayoutParams(valor3);

                txtvalor4.setText(String.valueOf(cantidad * precio)+ "\u20AC" );
                txtvalor4.setGravity(Gravity.CENTER);
                txtvalor4.setTextAppearance(this, R.style.etiqueta);
                txtvalor4.setBackgroundResource(R.drawable.tabla_celda);
                txtvalor4.setLayoutParams(valor4);

                fila.addView(txtvalor1);
                fila.addView(txtvalor2);
                fila.addView(txtvalor3);
                fila.addView(txtvalor4);
                tabla.addView(fila);
                double Total=precio*cantidad;
                Cantidadtotal+=cantidad;
                Totales+=Total;
                if(i==cart.length-1){
                    fila = new TableRow(this);
                    fila.setLayoutParams(layoutFila);

                    txtvalor1 = new TextView(this);
                    txtvalor2 = new TextView(this);
                    txtvalor3 = new TextView(this);
                    txtvalor0 = new ImageView(this);
                    //Metiendo imagen
                    Bitmap loadedImage = null;
                    ImageOwn img = new ImageOwn();
                    String imgUrl="http://turrones.web-dvl.com/sites/all/themes/stylevintage/logo.png";
                    try {

                        loadedImage = img.downloadFile(imgUrl);

                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    txtvalor0.setImageBitmap(loadedImage);
                    //  txtvalor0.setGravity(Gravity.CENTER_HORIZONTAL);
                    //    txtvalor0.setTextAppearance(this, R.style.etiqueta);
                    txtvalor0.setBackgroundResource(R.drawable.tabla_celda);
                    txtvalor0.setLayoutParams(valor1);

                    txtvalor1.setText("TOTAL");
                    txtvalor1.setGravity(Gravity.CENTER);
                    txtvalor1.setTextAppearance(this, R.style.etiqueta);
                    txtvalor1.setBackgroundResource(R.drawable.tabla_celda);
                    txtvalor1.setLayoutParams(valor2);

                    txtvalor2.setText(String.valueOf(Totales) + "\u20AC");
                    txtvalor2.setGravity(Gravity.CENTER);
                    txtvalor2.setTextAppearance(this, R.style.etiqueta);
                    txtvalor2.setBackgroundResource(R.drawable.tabla_celda);
                    txtvalor2.setLayoutParams(valor3);

                    txtvalor3.setText(String.valueOf(Cantidadtotal) + " Unidades");
                    txtvalor3.setGravity(Gravity.CENTER);
                    txtvalor3.setTextAppearance(this, R.style.etiqueta);
                    txtvalor3.setBackgroundResource(R.drawable.tabla_celda);
                    txtvalor3.setLayoutParams(valor4);

                    fila.addView(txtvalor0);
                    fila.addView(txtvalor1);
                    fila.addView(txtvalor3);
                    fila.addView(txtvalor2);

                    tabla.addView(fila);


                }
            }*/


    }
