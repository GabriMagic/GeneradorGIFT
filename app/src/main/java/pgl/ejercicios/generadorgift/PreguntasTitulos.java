package pgl.ejercicios.generadorgift;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

class ListaPreguntas extends CursorAdapter{

    public ListaPreguntas(Context context, Cursor c) {
        super(context, c);
    }

    public ListaPreguntas(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    public ListaPreguntas(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
