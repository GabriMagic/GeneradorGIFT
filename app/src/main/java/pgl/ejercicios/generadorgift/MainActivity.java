package pgl.ejercicios.generadorgift;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView label;
    PreguntasHelper preguntasHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button crear = (Button) findViewById(R.id.crear);
        Button exportar = (Button) findViewById(R.id.exportar);

        label = (TextView) findViewById(R.id.label);
        preguntasHelper = new PreguntasHelper(this, "PreguntasDB", null, 1);
        db = preguntasHelper.getWritableDatabase();


        preguntasHelper.onUpgrade(db, 1, 1);
        load(db, label);


    }

    private void load(SQLiteDatabase db, TextView label) {
        Cursor c = db.rawQuery("SELECT * FROM relaciones", null);
        label.setText("");
        if (c.moveToFirst()) {
            do {
                label.setText(label.getText() + "" + c.getInt(0) + c.getString(1) + c.getString(2) + "\n");
            } while (c.moveToNext());
        }
    }

    public void crearPregunta(View v) {
        Intent intent = new Intent(MainActivity.this, CrearActivity.class);
        startActivity(intent);
    }

    public void exportarPregunta(View v) {
        load(db, label);
    }
}
