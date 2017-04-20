package pe.area51.proyecto;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.area51.proyecto.adapter.ListadoPreventaAdapter;
import pe.area51.proyecto.adapter.ListadoVentaAdapter;
import pe.area51.proyecto.database.SentenciaSQL;
import pe.area51.proyecto.modelosbd.DatosPreventa;
import pe.area51.proyecto.modelosbd.DatosTienda;

public class DetalleVentaActivity extends AppCompatActivity {

    @BindView(R.id.Dt_Tienda)
    TextView DtTienda;
    @BindView(R.id.Dt_Tienda_Direccion)
    TextView DtTiendaDireccion;
    @BindView(R.id.Dt_Tienda_Telef)
    TextView DtTiendaTelef;
    @BindView(R.id.lvDetalleVenta)
    ListView lvDetalleVenta;
    @BindView(R.id.tvTotalPrecio)
    TextView tvTotalPrecio;
    private ArrayList<DatosPreventa> listaDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_venta);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String id_prev = getIntent().getStringExtra("id_prev");
        String tienda = getIntent().getStringExtra("tienda");
        String tota_pago = getIntent().getStringExtra("total_pago");
        tvTotalPrecio.setText(tota_pago);

        DtTienda.setText(tienda);

        SentenciaSQL sentenciaSQL= new SentenciaSQL(DetalleVentaActivity.this);
        DatosTienda datosTienda = sentenciaSQL.obtenerDatoporTienda(tienda);
        DtTiendaDireccion.setText(datosTienda.getDirecTienda());
        DtTiendaTelef.setText(datosTienda.getTelefTienda());
        listaDetalle = sentenciaSQL.listarPreVentaporID(id_prev);
        final ListadoPreventaAdapter listadoPreventaAdapter = new ListadoPreventaAdapter(DetalleVentaActivity.this, listaDetalle);
        lvDetalleVenta.setAdapter(listadoPreventaAdapter);
    }
}
