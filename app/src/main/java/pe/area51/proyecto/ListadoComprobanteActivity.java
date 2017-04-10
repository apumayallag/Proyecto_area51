package pe.area51.proyecto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.area51.proyecto.adapter.ComprobanteAdapter;
import pe.area51.proyecto.database.SentenciaSQL;
import pe.area51.proyecto.modelosbd.DatosComprobante;

public class ListadoComprobanteActivity extends AppCompatActivity {

    @BindView(R.id.lvListaCompro)
    ListView lvListaCompro;
    private ArrayList<DatosComprobante> listaCompro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_comprobante);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final SentenciaSQL sentenciaSQL=new SentenciaSQL(ListadoComprobanteActivity.this);
        listaCompro=sentenciaSQL.obtenerComprobante();
        final ComprobanteAdapter comprobanteAdapter=new ComprobanteAdapter(ListadoComprobanteActivity.this,listaCompro);
        lvListaCompro.setAdapter(comprobanteAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        SentenciaSQL sentenciaSQL = new SentenciaSQL(ListadoComprobanteActivity.this);
        listaCompro=sentenciaSQL.obtenerComprobante();
        ComprobanteAdapter comprobanteAdapter=new ComprobanteAdapter(ListadoComprobanteActivity.this,listaCompro);
        lvListaCompro.setAdapter(comprobanteAdapter);
    }
}
