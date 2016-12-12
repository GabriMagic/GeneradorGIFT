package pgl.ejercicios.generadorgift;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button crear = (Button) findViewById(R.id.crear);
        Button exportar = (Button) findViewById(R.id.exportar);

        PreguntasHelper preguntasHelper = new PreguntasHelper(this, "PreguntasDB", null, 1);

        SQLiteDatabase db = preguntasHelper.getWritableDatabase();


        TextView label = (TextView) findViewById(R.id.label);

        int pregunta = 1;

        db.execSQL("INSERT INTO preguntas (titulo, pregunta) VALUES ('Animales', 'Relacionalos')");
        db.execSQL("INSERT INTO relaciones VALUES ("+pregunta+",'oso','pardo')");
        pregunta++;

        Cursor c = db.rawQuery("SELECT * FROM preguntas", null);
        c.moveToFirst();
        label.setText(c.getString(1));



    }
}
