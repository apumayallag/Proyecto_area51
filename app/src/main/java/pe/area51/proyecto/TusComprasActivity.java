package pe.area51.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.area51.proyecto.adapter.ListadoVentaAdapter;
import pe.area51.proyecto.database.SentenciaSQL;
import pe.area51.proyecto.modelosbd.DatosVenta;

public class TusComprasActivity extends AppCompatActivity {

    @BindView(R.id.lvTusCompras)
    ListView lvTusCompras;

    private ArrayList<DatosVenta> listaVenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tus_compras);
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
        SentenciaSQL sentenciaSQL = new SentenciaSQL(TusComprasActivity.this);

        listaVenta = sentenciaSQL.ListarVentas();
        final ListadoVentaAdapter listadoTiendaAdapter = new ListadoVentaAdapter(TusComprasActivity.this, listaVenta);
        lvTusCompras.setAdapter(listadoTiendaAdapter);

        lvTusCompras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TusComprasActivity.this, listaVenta.get(position).getId_tienda(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TusComprasActivity.this,DetalleVentaActivity.class);
                intent.putExtra("id_prev",listaVenta.get(position).getId_preventa());
                intent.putExtra("tienda",listaVenta.get(position).getId_tienda());
                intent.putExtra("total_pago",listaVenta.get(position).getTotal_pago());
                startActivity(intent);
            }
        });
    }

}
