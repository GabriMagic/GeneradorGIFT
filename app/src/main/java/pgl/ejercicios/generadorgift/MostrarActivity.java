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

        TextView label = (TextView) findViewById(R.id.pregunta);

        PreguntasHelper preguntasHelper = new PreguntasHelper(this, "PreguntasDB", null, 1);
        SQLiteDatabase db = preguntasHelper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT titulo, pregunta, relacion1, relacion2 FROM preguntas INNER JOIN relaciones ON preguntas._id = relaciones._id",null);

        if (c.moveToFirst()){
            label.setText(c.getString(0)+"\n"+c.getString(1)+"\n"+c.getString(2)+"\n"+c.getString(3));
        }

    }
}
