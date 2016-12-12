package pgl.ejercicios.generadorgift;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GabriMagic on 12/12/2016.
 */
public class PreguntasHelper extends SQLiteOpenHelper{

    private String createTable = "CREATE TABLE preguntas (id_pregunta INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, pregunta TEXT)";
    private String createTable2 = "CREATE TABLE relaciones (id_pregunta INTEGER, relacion1 TEXT , relacion2 TEXT)";

    public PreguntasHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version){
    super(contexto,nombre,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
        db.execSQL(createTable2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS preguntas");
        db.execSQL("DROP TABLE IF EXISTS relaciones");

        onCreate(db);
    }
}