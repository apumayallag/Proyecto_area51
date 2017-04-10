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
import pe.area51.proyecto.modelosbd.DatosLogin;

public class PerfilActivity extends AppCompatActivity {

    @BindView(R.id.etPerfilnombre)
    EditText etPerfilnombre;
    @BindView(R.id.etPerfilApellido)
    EditText etPerfilApellido;
    @BindView(R.id.etPerfilTelefono)
    EditText etPerfilTelefono;
    @BindView(R.id.etPerfilCorreo)
    EditText etPerfilCorreo;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        SentenciaSQL sentenciaSQL = new SentenciaSQL(PerfilActivity.this);
        id = getIntent().getIntExtra("id",-1);
        DatosLogin datos = sentenciaSQL.ListarporId(id);
        etPerfilnombre.setText(datos.getNomb());
        etPerfilApellido.setText(datos.getApel());
        etPerfilTelefono.setText(datos.getTelf());
        etPerfilCorreo.setText(datos.getCorreo());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnPerfilModificar)
    public void onClick() {
        SentenciaSQL sentenciaSQL = new SentenciaSQL(PerfilActivity.this);
        sentenciaSQL.actualizarUser(
                etPerfilnombre.getText().toString(),
                etPerfilApellido.getText().toString(),
                etPerfilTelefono.getText().toString(),
                etPerfilCorreo.getText().toString(),
                id);
        Toast.makeText(this, "Se actualizo correctamente", Toast.LENGTH_SHORT).show();

    }
}
