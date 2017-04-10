package pe.area51.proyecto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.area51.proyecto.database.SentenciaSQL;

public class FacturaActivity extends AppCompatActivity {

    private int id;

    @BindView(R.id.etComprobRazon)
    EditText etComprobRazon;
    @BindView(R.id.etComprobRUC)
    EditText etComprobRUC;
    @BindView(R.id.etComprobDireccion)
    EditText etComprobDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);
        ButterKnife.bind(this);

        id = getIntent().getIntExtra("id", -1);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnAgregarBoleta)
    public void onViewClicked() {
        String tipo = "Factura";
        String razon = etComprobRazon.getText().toString();
        String Ruc = etComprobRUC.getText().toString();
        String Direccion = etComprobDireccion.getText().toString();
        if (razon.equals("")) {
            etComprobRazon.setError("Ingrese Razon Social");
            return;
        } else if (Ruc.equals("")) {
            etComprobRUC.setError("Ingrese RUC");
            return;
        } else if (Direccion.equals("")) {
            etComprobDireccion.setError("Ingrese Direccion");
            return;
        } else {

            SentenciaSQL sentenciaSQL = new SentenciaSQL(FacturaActivity.this);
            sentenciaSQL.registrarComprobante(id, tipo, razon, Direccion, Ruc);
            Toast.makeText(this, "Se registro correctamente", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
