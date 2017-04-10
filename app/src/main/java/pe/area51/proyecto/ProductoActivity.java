package pe.area51.proyecto;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.area51.proyecto.adapter.ListadoProductoAdapter;
import pe.area51.proyecto.database.SentenciaSQL;
import pe.area51.proyecto.modelosbd.DatosProducto;

public class ProductoActivity extends AppCompatActivity {

    private int id;

    @BindView(R.id.tvNombreTienda)
    TextView tvNombreTienda;
    @BindView(R.id.tvDireccionTienda)
    TextView tvDireccionTienda;
    @BindView(R.id.tvTelefonoTienda)
    TextView tvTelefonoTienda;
    @BindView(R.id.lvlistaProducto)
    ListView lvlistaProducto;

    private ArrayList<DatosProducto> listaProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        ButterKnife.bind(this);

        id = getIntent().getIntExtra("id", -1);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        SharedPreferences prefe = getSharedPreferences("id_deta", Context.MODE_PRIVATE);

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

        //validamos que el intent traiga datos
        if (getIntent() != null) {
            //obtenemos la posicion que se envio a traves del intent
            int id_tiend = getIntent().getIntExtra("id_tienda", -1);
            String Tienda = getIntent().getStringExtra("nomTienda");
            String Direccion = getIntent().getStringExtra("direcTienda");
            String Telefono = getIntent().getStringExtra("telfTienda");

            tvNombreTienda.setText(Tienda);
            tvDireccionTienda.setText(Direccion);
            tvTelefonoTienda.setText(Telefono);

            SentenciaSQL sentenciaSQL = new SentenciaSQL(ProductoActivity.this);
            listaProduct = sentenciaSQL.obtenerProducto(id_tiend);
            ListadoProductoAdapter listadoProductoAdapter = new ListadoProductoAdapter(ProductoActivity.this, listaProduct);
            lvlistaProducto.setAdapter(listadoProductoAdapter);
        }

        lvlistaProducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Dialog dialog = new Dialog(ProductoActivity.this);
                dialog.setContentView(R.layout.item_precio_producto);
                dialog.setTitle("Ingrese cantidad");

                final TextView tvProducto = (TextView) dialog.findViewById(R.id.txtProducto);
                final TextView tvPrecio = (TextView) dialog.findViewById(R.id.txtPrecio);
                final EditText etCantidad = (EditText) dialog.findViewById(R.id.etCant);
                Button btnAgregar = (Button) dialog.findViewById(R.id.btnAddProduct);

                DatosProducto datosProducto = listaProduct.get(position);
                tvProducto.setText(datosProducto.getProducto());
                tvPrecio.setText(datosProducto.getPrecio());

                btnAgregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (etCantidad.getText().toString().trim().length() > 0) {
                            double total;

                            total = Double.parseDouble(tvPrecio.getText().toString()) * Integer.parseInt(etCantidad.getText().toString());

                            String nombre = tvNombreTienda.getText().toString();
                            String product = tvProducto.getText().toString();
                            String prec = tvPrecio.getText().toString();
                            String can = etCantidad.getText().toString();

                            //creacion del SharedPreferences
                            SharedPreferences preferencias = getSharedPreferences("id_deta", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferencias.edit();
                            //iniciamos id_deta
                            String det = preferencias.getString("id_deta", "-1");
                            //validacion para no reemplazar
                            if (det == "-1") {
                                editor.putString("id_deta", "1");
                                Toast.makeText(ProductoActivity.this, "entro", Toast.LENGTH_SHORT).show();
                                editor.commit();
                            }
                            /*
                            int dato = Integer.parseInt(det);
                            dato += 1;
                            editor.putString("id_deta", "" + dato);
                            editor.commit();
                            Toast.makeText(ProductoActivity.this, "prueba " + dato, Toast.LENGTH_SHORT).show();
                            */
                            Toast.makeText(ProductoActivity.this, "S/. " + total, Toast.LENGTH_SHORT).show();
                            SentenciaSQL sentenciaSQL = new SentenciaSQL(ProductoActivity.this);
                            sentenciaSQL.registrarPreventa(nombre, det, product, prec, can, total + "");
                            Toast.makeText(ProductoActivity.this, "Se almaceno producto", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            etCantidad.setError("Ingrese cantidad");

                        }
                    }
                });
                dialog.show();
            }
        });

    }

    @OnClick(R.id.btnListar)
    public void onViewClicked() {
        Intent intent = new Intent(ProductoActivity.this, PreVentaActivity.class);
        startActivity(intent);
    }
}
