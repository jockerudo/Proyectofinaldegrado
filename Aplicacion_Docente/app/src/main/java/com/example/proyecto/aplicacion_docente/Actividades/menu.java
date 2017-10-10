package com.example.proyecto.aplicacion_docente.Actividades;

/**
 * Created by Usuario on 18/05/2017.
 */
import android.app.Activity;

import android.os.Bundle;

import android.util.Log;


import com.example.proyecto.aplicacion_docente.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.util.List;


public class menu extends Activity implements OnPageChangeListener, OnLoadCompleteListener {
    private static final String TAG = menu.class.getSimpleName();
    String SAMPLE_FILE;
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.menu);





        Bundle bundle=getIntent().getExtras();
        String path = bundle.getString("url");
      // path="http://mestreacasa.gva.es"+path;



//System.out.println(path);
         SAMPLE_FILE =path ;
        pdfView= (PDFView)findViewById(R.id.pdfView);
        displayFromAsset(SAMPLE_FILE);

    }
    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;

        pdfView.fromAsset(SAMPLE_FILE)
                .defaultPage(pageNumber)
                .enableSwipe(true)

                .swipeHorizontal(false)
                .onPageChange( this)
                .enableAnnotationRendering(true)
                .onLoad( this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }


    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }



    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

}
