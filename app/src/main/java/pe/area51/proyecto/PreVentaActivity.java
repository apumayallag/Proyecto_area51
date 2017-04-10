package pe.area51.proyecto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.area51.proyecto.adapter.ListadoPreventaAdapter;
import pe.area51.proyecto.database.SentenciaSQL;
import pe.area51.proyecto.modelosbd.DatosPreventa;
import pe.area51.proyecto.modelosbd.DatosTienda;

public class PreVentaActivity extends AppCompatActivity {

    @BindView(R.id.Pv_Tienda)
    TextView PvTienda;
    @BindView(R.id.Pv_Tienda_Direccion)
    TextView PvTiendaDireccion;
    @BindView(R.id.Pv_Tienda_Telef)
    TextView PvTiendaTelef;
    @BindView(R.id.lvPreVenta)
    ListView lvPreVenta;
    @BindView(R.id.btnCompPreventa)
    Button btnCompPreventa;

    private ArrayList<DatosPreventa> listPreventa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_venta);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final SentenciaSQL sentenciaSQL = new SentenciaSQL(PreVentaActivity.this);
        listPreventa=sentenciaSQL.listarPreVenta();
        final ListadoPreventaAdapter listadoPreventaAdapter=new ListadoPreventaAdapter(PreVentaActivity.this,listPreventa);
        lvPreVenta.setAdapter(listadoPreventaAdapter);

        String Tienda = sentenciaSQL.obtenerTiendaconId("1");
        PvTienda.setText(Tienda);

        DatosTienda datosTienda= sentenciaSQL.obtenerDatoporTienda(Tienda);
        PvTiendaDireccion.setText(datosTienda.getDirecTienda());
        PvTiendaTelef.setText(datosTienda.getTelefTienda());

    }
}
