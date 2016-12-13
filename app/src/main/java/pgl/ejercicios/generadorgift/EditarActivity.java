package pgl.ejercicios.generadorgift;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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

    }
}
