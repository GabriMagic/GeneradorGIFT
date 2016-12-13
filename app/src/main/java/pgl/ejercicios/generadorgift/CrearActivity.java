package pgl.ejercicios.generadorgift;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by GabriMagic on 12/12/2016.
 */
public class CrearActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_pregunta);

        PreguntasHelper preguntasHelper = new PreguntasHelper(this, "PreguntasDB", null, 1);
        db = preguntasHelper.getWritableDatabase();
    }

    public void crearPregunta(View v) {

        Cursor c = db.rawQuery("SELECT * FROM preguntas ORDER BY _id DESC LIMIT 1", null);
        int i = 0;
        if (c.moveToFirst()) {
             i = c.getInt(0);
            do {
                i++;
            } while (c.moveToNext());
        };

        TextView titulo = (TextView) findViewById(R.id.tituloText);
        TextView pregunta = (TextView) findViewById(R.id.preuntaText);

        TextView rel11 = (TextView) findViewById(R.id.rel1_1);
        TextView rel12 = (TextView) findViewById(R.id.rel1_2);

        TextView rel21 = (TextView) findViewById(R.id.rel2_1);
        TextView rel22 = (TextView) findViewById(R.id.rel2_2);

        TextView rel31 = (TextView) findViewById(R.id.rel3_1);
        TextView rel32 = (TextView) findViewById(R.id.rel3_2);

        TextView rel41 = (TextView) findViewById(R.id.rel4_1);
        TextView rel42 = (TextView) findViewById(R.id.rel4_2);

        db.execSQL("INSERT INTO preguntas (titulo, pregunta) VALUES ('" + titulo.getText() + "','" + pregunta.getText() + "')");
        db.execSQL("INSERT INTO relaciones VALUES ('"+i+"','"+rel11.getText()+"','"+rel12.getText()+"')");
        db.execSQL("INSERT INTO relaciones VALUES ('"+i+"','"+rel21.getText()+"','"+rel22.getText()+"')");
        db.execSQL("INSERT INTO relaciones VALUES ('"+i+"','"+rel31.getText()+"','"+rel32.getText()+"')");
        db.execSQL("INSERT INTO relaciones VALUES ('"+i+"','"+rel41.getText()+"','"+rel42.getText()+"')");

        finish();
    }

    public void cancel(View v) {
        finish();
    }

}
