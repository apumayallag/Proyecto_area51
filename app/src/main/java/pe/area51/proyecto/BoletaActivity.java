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

public class BoletaActivity extends AppCompatActivity {

    private int id;

    @BindView(R.id.etComprobNombre)
    EditText etComprobNombre;
    @BindView(R.id.etComprobDNI)
    EditText etComprobDNI;
    @BindView(R.id.etComprobDireccion)
    EditText etComprobDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boleta);
        ButterKnife.bind(this);

        id = getIntent().getIntExtra("id", -1);

        if (getSupportActionBar()!= null){
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
        String Nombre = etComprobNombre.getText().toString();
        String Dni = etComprobDNI.getText().toString();
        String Direccion = etComprobDireccion.getText().toString();
        if (Nombre.equals("")) {
            etComprobNombre.setError("Ingrese Nombre");
            return;
        } else if (Dni.equals("")) {
            etComprobDNI.setError("Ingrese DNI");
            return;
        } else if (Direccion.equals("")) {
            etComprobDireccion.setError("Ingrese Direccion");
            return;
        } else {
            SentenciaSQL sentenciaSQL = new SentenciaSQL(BoletaActivity.this);
            String tipo = "Boleta";
            sentenciaSQL.registrarComprobante(id, tipo, Nombre, Direccion, Dni);
            Toast.makeText(this, "Se registro correctamente", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
