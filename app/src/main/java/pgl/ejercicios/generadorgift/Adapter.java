package pgl.ejercicios.generadorgift;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by GabriMagic on 13/12/2016.
 */
public class Adapter extends CursorAdapter {

    Adapter(Context context, Cursor cursor) {
        super(context, cursor, 0);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.lista_preguntas, parent, false);
    }

    @Override
    public void bindView(View v, Context context, Cursor cursor) {

        TextView titulo = (TextView) v.findViewById(R.id.tituloText);
        TextView pregunta = (TextView) v.findViewById(R.id.preguntaText);

        Button mostrarButton = (Button) v.findViewById(R.id.mostrarButton);
        mostrarButton.setTag(cursor.getString(cursor.getColumnIndexOrThrow("_id")));

        Button editarButton = (Button) v.findViewById(R.id.editarButton);
        editarButton.setTag(cursor.getString(cursor.getColumnIndexOrThrow("_id")));

        titulo.setText(cursor.getString(cursor.getColumnIndexOrThrow("titulo")));
        pregunta.setText(cursor.getString(cursor.getColumnIndexOrThrow("pregunta")));
    }
}
