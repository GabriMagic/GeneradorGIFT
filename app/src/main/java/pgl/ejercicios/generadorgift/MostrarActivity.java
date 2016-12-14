package pgl.ejercicios.generadorgift;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by GabriMagic on 13/12/2016.
 */
public class MostrarActivity extends AppCompatActivity {

    PreguntasHelper preguntasHelper;
    SQLiteDatabase db;
    int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formato_pregunta);

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        TextView label = (TextView) findViewById(R.id.pregunta);

        preguntasHelper = new PreguntasHelper(this, "PreguntasDB", null, 1);
        db = preguntasHelper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT pregunta FROM preguntas WHERE _id = " + id, null);
        Cursor c2 = db.rawQuery("SELECT relacion1, relacion2 FROM relaciones WHERE _id = " + id, null);

        if (c.moveToFirst()) {
            label.setText(c.getString(0) + " {\n");
            if (c2.moveToFirst()) {
                do {
                    label.setText("\t" + label.getText() + "=" + c2.getString(0) + " -> " + c2.getString(1) + "\n");

                } while (c2.moveToNext());
            }
        }
        label.setText(label.getText() + "}");
    }

    public void eliminar(View v) {
        Log.i("PENE",""+id);
        db.execSQL("DELETE FROM preguntas WHERE _id = " + id);
        db.execSQL("DELETE FROM relaciones WHERE _id = " + id);
        finish();
    }

    public void volver(View V) {
        finish();
    }

}

