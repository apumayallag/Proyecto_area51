package pe.area51.proyecto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.tvPago)
    TextView tvPago;

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

        SharedPreferences preferences=getSharedPreferences("id_deta",Context.MODE_PRIVATE);
        String det = preferences.getString("id_deta", "-1");
        final SentenciaSQL sentenciaSQL = new SentenciaSQL(PreVentaActivity.this);
        listPreventa = sentenciaSQL.listarPreVentaporID(det);
        final ListadoPreventaAdapter listadoPreventaAdapter = new ListadoPreventaAdapter(PreVentaActivity.this, listPreventa);
        lvPreVenta.setAdapter(listadoPreventaAdapter);


        String Tienda = sentenciaSQL.obtenerTiendaconId(det);
        PvTienda.setText(Tienda);

        DatosTienda datosTienda = sentenciaSQL.obtenerDatoporTienda(Tienda);
        PvTiendaDireccion.setText(datosTienda.getDirecTienda());
        PvTiendaTelef.setText(datosTienda.getTelefTienda());

        double total=0;
        for(DatosPreventa item:listPreventa){
            total+=Double.parseDouble(item.getPrecio_tot());
        }

        tvPago.setText(""+total);

    }

    @OnClick(R.id.btnCompPreventa)
    public void onViewClicked() {
        SentenciaSQL sentenciaSQL = new SentenciaSQL(PreVentaActivity.this);
        SharedPreferences preferences = getSharedPreferences("id_deta", Context.MODE_PRIVATE);
        String det = preferences.getString("id_deta", "-1");
        SharedPreferences.Editor editor = preferences.edit();
        int dato = Integer.parseInt(det);
        String tienda =PvTienda.getText().toString(),pago=tvPago.getText().toString();
        sentenciaSQL.registrarVenta(""+dato, tienda,pago);
        dato += 1;
        editor.putString("id_deta", "" + dato);
        editor.commit();
        Toast.makeText(this, "Se realizo la compra satisfactoriamente "+dato, Toast.LENGTH_SHORT).show();
        finish();
        Intent intent =new Intent(PreVentaActivity.this,PrincipalActivity.class);
        startActivity(intent);
    }
}
