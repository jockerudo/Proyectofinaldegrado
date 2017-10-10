package com.example.proyecto.aplicacion_docente.Fragments;

/**
 * Created by Usuario on 03/09/2017.
 */
import android.content.res.Resources;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.proyecto.aplicacion_docente.R;

public class tablafragment extends Fragment {
    public tablafragment(){
        // constructor;
    }

    private ViewGroup layout;

    TableLayout tabla;
    TableLayout cabecera;
    TableRow.LayoutParams layoutFila;
    TableRow.LayoutParams valor1;
    TableRow.LayoutParams valor2;
    TableRow.LayoutParams valor3;
    TableRow.LayoutParams valor4;
    Resources rs;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View castView = inflater.inflate(R.layout.tablafragment, container,false);

        castView.findViewById(R.id.contenedor_fragment);

        rs = this.getResources();
        tabla = (TableLayout)castView.findViewById(R.id.tabla);
        cabecera = (TableLayout)castView.findViewById(R.id.cabecera);
        layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);

        valor1 = new TableRow.LayoutParams(1300,TableRow.LayoutParams.WRAP_CONTENT);
        valor2 = new TableRow.LayoutParams(1020,TableRow.LayoutParams.WRAP_CONTENT);
        valor3 = new TableRow.LayoutParams(200,TableRow.LayoutParams.FILL_PARENT);
        valor4 = new TableRow.LayoutParams(50,TableRow.LayoutParams.FILL_PARENT);


        //agregarCabecera();
        //agregarCabeceratabla();




        return castView;
    }





}
