package pgl.ejercicios.generadorgift;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by GabriMagic on 13/12/2016.
 */
public class EditarActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_pregunta);

        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("id");

    }
}
