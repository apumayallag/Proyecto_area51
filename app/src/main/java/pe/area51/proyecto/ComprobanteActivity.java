package pe.area51.proyecto;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.area51.proyecto.database.SentenciaSQL;
import pe.area51.proyecto.modelosbd.DatosLogin;

public class ComprobanteActivity extends AppCompatActivity {

    @BindView(R.id.SpComprobante)
    Spinner SpComprobante;
    private int id;
    private SentenciaSQL sentenciaSQL = new SentenciaSQL(ComprobanteActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprobante);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        id = getIntent().getIntExtra("id", -1);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnComprobante)
    public void onClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ComprobanteActivity.this);
        builder.setTitle(getResources().getString(R.string.tv_comprobante));
        builder.setTitle(getResources().getString(R.string.seleccionar_comprobante));
        builder.setPositiveButton("Boleta", (new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ComprobanteActivity.this,BoletaActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        }));
        builder.setNegativeButton("Factura", (new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(ComprobanteActivity.this,FacturaActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        }));
        builder.setNeutralButton("Cancelar", (new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }));
        builder.create().show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<String> datos = new ArrayList<>();

        final SentenciaSQL sentenciaSQL = new SentenciaSQL(ComprobanteActivity.this);
        datos = sentenciaSQL.obtenerComprobantePorUsuario(id);
        ArrayAdapter arrayAdapter = new ArrayAdapter(ComprobanteActivity.this, R.layout.support_simple_spinner_dropdown_item, datos);
        SpComprobante.setAdapter(arrayAdapter);

        SpComprobante.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int position, long l) {
                if (position != 0) {
                    final String text=SpComprobante.getSelectedItem().toString();
                    final AlertDialog.Builder builder = new AlertDialog.Builder(ComprobanteActivity.this);
                    builder.setTitle(getResources().getString(R.string.seleccione_accion));
                    builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            sentenciaSQL.eliminarComprobante(position);
                            Toast.makeText(ComprobanteActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.create().show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}
