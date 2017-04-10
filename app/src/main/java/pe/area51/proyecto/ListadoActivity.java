package pe.area51.proyecto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.area51.proyecto.adapter.DatoAdapter;
import pe.area51.proyecto.database.SentenciaSQL;
import pe.area51.proyecto.modelosbd.DatosLogin;

public class ListadoActivity extends AppCompatActivity {

    @BindView(R.id.lvLista)
    ListView lvLista;
    private ArrayList<DatosLogin> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final SentenciaSQL sentenciaSQL = new SentenciaSQL(ListadoActivity.this);
        lista=sentenciaSQL.obtener();
        final DatoAdapter datoAdapter = new DatoAdapter(ListadoActivity.this, lista);
        lvLista.setAdapter(datoAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SentenciaSQL sentenciaSQL = new SentenciaSQL(ListadoActivity.this);
        lista = sentenciaSQL.obtener();
        DatoAdapter datoAdapter = new DatoAdapter(ListadoActivity.this, lista);
        lvLista.setAdapter(datoAdapter);
    }
}
