package pgl.ejercicios.generadorgift;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by GabriMagic on 13/12/2016.
 */
public class EditarActivity extends AppCompatActivity {

    SQLiteDatabase db;
    int id;
    String dato11, dato12, dato21, dato22, dato31, dato32, dato41, dato42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_pregunta);

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");


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


        PreguntasHelper helper = new PreguntasHelper(this, "PreguntasDB", null, 1);
        db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM preguntas WHERE _id = " + id, null);
        Cursor c2 = db.rawQuery("SELECT * FROM relaciones WHERE _id = " + id, null);

        if (c.moveToFirst()) {
            titulo.setText(c.getString(1));
            pregunta.setText(c.getString(2));
            if (c2.moveToFirst()) {
                rel11.setText(c2.getString(1));
                rel12.setText(c2.getString(2));
                c2.moveToNext();
                rel21.setText(c2.getString(1));
                rel22.setText(c2.getString(2));
                c2.moveToNext();
                rel31.setText(c2.getString(1));
                rel32.setText(c2.getString(2));
                c2.moveToNext();
                rel41.setText(c2.getString(1));
                rel42.setText(c2.getString(2));
            }
        }

        dato11 = rel11.getText().toString();
        dato12 = rel12.getText().toString();

        dato21 = rel21.getText().toString();
        dato22 = rel22.getText().toString();

        dato31 = rel31.getText().toString();
        dato32 = rel32.getText().toString();

        dato41 = rel41.getText().toString();
        dato42 = rel42.getText().toString();
    }

    public void crearPregunta(View v) {

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


        db.execSQL("UPDATE preguntas SET titulo= '" + titulo.getText() + "' WHERE _id = " + id);
        db.execSQL("UPDATE preguntas SET pregunta= '" + pregunta.getText() + "' WHERE _id = " + id);

        db.execSQL("UPDATE relaciones SET relacion1='" + rel11.getText() + "' WHERE _id =" + id + " AND relacion1='" + dato11 + "'");
        db.execSQL("UPDATE relaciones SET relacion2='" + rel12.getText() + "' WHERE _id =" + id + " AND relacion2='" + dato12 + "'");

        db.execSQL("UPDATE relaciones SET relacion1='" + rel21.getText() + "' WHERE _id =" + id + " AND relacion1='" + dato21 + "'");
        db.execSQL("UPDATE relaciones SET relacion2='" + rel22.getText() + "' WHERE _id =" + id + " AND relacion2='" + dato22 + "'");

        db.execSQL("UPDATE relaciones SET relacion1='" + rel31.getText() + "' WHERE _id =" + id + " AND relacion1='" + dato31 + "'");
        db.execSQL("UPDATE relaciones SET relacion2='" + rel32.getText() + "' WHERE _id =" + id + " AND relacion2='" + dato32 + "'");

        db.execSQL("UPDATE relaciones SET relacion1='" + rel41.getText() + "' WHERE _id =" + id + " AND relacion1='" + dato41 + "'");
        db.execSQL("UPDATE relaciones SET relacion2='" + rel42.getText() + "' WHERE _id =" + id + " AND relacion2='" + dato42 + "'");

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        cancel(v);
    }

    public void cancel(View v) {
        finish();
    }
}
