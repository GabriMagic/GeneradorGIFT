package pgl.ejercicios.generadorgift;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    PreguntasHelper preguntasHelper;
    SQLiteDatabase db;
    ListView listaPreguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button crear = (Button) findViewById(R.id.crear);
        Button exportar = (Button) findViewById(R.id.exportar);
        Button editar = (Button) findViewById(R.id.editarButton);

        preguntasHelper = new PreguntasHelper(this, "PreguntasDB", null, 1);
        db = preguntasHelper.getWritableDatabase();
        listaPreguntas = (ListView) findViewById(R.id.listaPreguntas);

//        preguntasHelper.onUpgrade(db, 1, 1);

        Cursor c = db.rawQuery("SELECT * FROM preguntas", null);
        Adapter cursorAdapter = new Adapter(this, c);
        listaPreguntas.setAdapter(cursorAdapter);



    }

    public void crearPregunta(View v) {
        Intent intent = new Intent(MainActivity.this, CrearActivity.class);
        startActivity(intent);
    }

    public void exportarPregunta(View v) {

    }
}
