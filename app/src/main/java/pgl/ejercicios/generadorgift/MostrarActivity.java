package pgl.ejercicios.generadorgift;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by GabriMagic on 13/12/2016.
 */
public class MostrarActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formato_pregunta);

        int id = 0;

        TextView label = (TextView) findViewById(R.id.pregunta);

        PreguntasHelper preguntasHelper = new PreguntasHelper(this, "PreguntasDB", null, 1);
        SQLiteDatabase db = preguntasHelper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT pregunta FROM preguntas WHERE _id = " + id, null);
        Cursor c2 = db.rawQuery("SELECT * FROM relaciones WHERE _id = " + id, null);

        label.setText("");
        if (c.moveToFirst() && c2.moveToFirst()) {
            label.setText("" + c.getString(0) + " {\n");
            do {
                label.setText("  " + label.getText() + "=" + c2.getString(1) + " -> " + c2.getString(2) + "\n");
            } while (c2.moveToNext());
        }
        label.setText(label.getText() + "}");

    }
}
