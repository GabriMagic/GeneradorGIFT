package pgl.ejercicios.generadorgift;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    PreguntasHelper preguntasHelper;
    SQLiteDatabase db;
    ListView listaPreguntas;
    Button mostrar;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }

        Button crear = (Button) findViewById(R.id.crear);
        Button exportar = (Button) findViewById(R.id.exportar);
        Button editar = (Button) findViewById(R.id.editarButton);
        mostrar = (Button) findViewById(R.id.mostrarButton);

        preguntasHelper = new PreguntasHelper(this, "PreguntasDB", null, 1);
        db = preguntasHelper.getWritableDatabase();
        listaPreguntas = (ListView) findViewById(R.id.listaPreguntas);
//
//      preguntasHelper.onUpgrade(db, 1, 1);
//
        cargarLista();


    }

    private void cargarLista() {
        Cursor c = db.rawQuery("SELECT * FROM preguntas", null);
        Adapter cursorAdapter = new Adapter(this, c);
        listaPreguntas.setAdapter(cursorAdapter);
    }

    public void editarPregunta(View v) {
        Intent intent = new Intent(MainActivity.this, EditarActivity.class);
        intent.putExtra("id", Integer.parseInt((String) v.getTag()));
        startActivity(intent);
    }

    public void mostrarPregunta(View v) {
        Intent intent = new Intent(MainActivity.this, MostrarActivity.class);
        intent.putExtra("id", Integer.parseInt((String) v.getTag()));
        startActivity(intent);
    }

    public void crearPregunta(View v) {
        Intent intent = new Intent(MainActivity.this, CrearActivity.class);
        startActivity(intent);
    }

    public void exportarPregunta(View v) {

        boolean sdDisponible = false;
        boolean sdAccesoEscritura = false;

        //Comprobamos el estado de la memoria externa (tarjeta SD)
        String estado = Environment.getExternalStorageState();

        if (estado.equals(Environment.MEDIA_MOUNTED)) {
            sdDisponible = true;
            sdAccesoEscritura = true;
        } else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            sdDisponible = true;
            sdAccesoEscritura = false;
        } else {
            sdDisponible = false;
            sdAccesoEscritura = false;
        }


        try {
            File ruta = Environment.getExternalStorageDirectory();

            File f = new File(ruta.getAbsolutePath(), "preguntas.txt");

            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            new FileOutputStream(f));

            Cursor c = db.rawQuery("SELECT * FROM preguntas", null);
            Cursor c2 = db.rawQuery("SELECT * FROM relaciones", null);


            fout.write("Texto de prueba.");
            fout.close();
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
        }
    }

}
