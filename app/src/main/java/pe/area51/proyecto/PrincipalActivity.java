package pe.area51.proyecto;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.area51.proyecto.adapter.ListadoTiendaAdapter;
import pe.area51.proyecto.adapter.SlidingMenuAdapter;
import pe.area51.proyecto.database.SentenciaSQL;
import pe.area51.proyecto.model.ItemSlideMenu;
import pe.area51.proyecto.modelosbd.DatosTienda;

public class PrincipalActivity extends AppCompatActivity {

    @BindView(R.id.lvlista)
    ListView lvlista;
    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private int ide;

    private ArrayList<DatosTienda> listaTienda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        ButterKnife.bind(this);

        //Init component
        listViewSliding = (ListView) findViewById(R.id.lv_sliding_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listSliding = new ArrayList<>();
        //Add item for sliding list
        listSliding.add(new ItemSlideMenu(R.mipmap.ic_credit_card_black_24dp, "Comprobante de Pago"));
        listSliding.add(new ItemSlideMenu(R.mipmap.ic_shopping_basket_black_24dp, "Tus Compras"));
        listSliding.add(new ItemSlideMenu(R.mipmap.ic_error_outline_black_24dp, "Acerca"));
        adapter = new SlidingMenuAdapter(this, listSliding);
        listViewSliding.setAdapter(adapter);

        //Display icon to open/ close sliding list
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set title
        //setTitle(listSliding.get(0).getTitle());
        //item selected
        //listViewSliding.setItemChecked(0, true);
        //Close menu
        drawerLayout.closeDrawer(listViewSliding);

        //Display fragment 1 when start
        //Hanlde on item click

        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Set title
                //setTitle(listSliding.get(position).getTitle());
                //item selected
                listViewSliding.setItemChecked(position, true);
                //Replace fragment
                replaceFragment(position);
                //Close menu
                drawerLayout.closeDrawer(listViewSliding);
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int men = item.getItemId();

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (men == R.id.menu_perfil) {
            Intent intent = new Intent(PrincipalActivity.this, PerfilActivity.class);
            intent.putExtra("id", ide);
            startActivity(intent);
            overridePendingTransition(R.anim.left_in, R.anim.left_out);
        }else if (men == R.id.menu_carrito) {
            Intent intent = new Intent(PrincipalActivity.this, PreVentaActivity.class);
            intent.putExtra("id", ide);
            startActivity(intent);
            overridePendingTransition(R.anim.left_in, R.anim.left_out);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    //Create method replace fragment

    private void replaceFragment(int pos) {
        switch (pos) {
            case 0:
                Intent intent = new Intent(PrincipalActivity.this, ComprobanteActivity.class);
                intent.putExtra("id", ide);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                break;
            case 1:
                Intent intent1 = new Intent(PrincipalActivity.this, TusComprasActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                break;
            case 2:
                AlertDialog.Builder builder = new AlertDialog.Builder(PrincipalActivity.this);
                builder.setTitle(getResources().getString(R.string.lateral_acerca_de));//Seleccione opción
                builder.setMessage(getResources().getString(R.string.acerca));//Acción a realizar
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        ide = getIntent().getIntExtra("id", -1);

        final SentenciaSQL sentenciaSQL = new SentenciaSQL(PrincipalActivity.this);
        listaTienda = sentenciaSQL.obtenerTienda();
        final ListadoTiendaAdapter listadoTiendaAdapter = new ListadoTiendaAdapter(PrincipalActivity.this, listaTienda);
        lvlista.setAdapter(listadoTiendaAdapter);

        lvlista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PrincipalActivity.this, listaTienda.get(position).getNomTienda(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PrincipalActivity.this, ProductoActivity.class);
                intent.putExtra("id", ide);
                intent.putExtra("id_tienda", listaTienda.get(position).getId_tiend());
                intent.putExtra("nomTienda", listaTienda.get(position).getNomTienda());
                intent.putExtra("direcTienda", listaTienda.get(position).getDirecTienda());
                intent.putExtra("telfTienda", listaTienda.get(position).getTelefTienda());

                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.MiUbicacion, R.id.btnVerMapa})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.MiUbicacion:
                Intent intent = new Intent(PrincipalActivity.this,MapaActivity.class);
                intent.putExtra("codMap",1);
                startActivity(intent);
                break;
            case R.id.btnVerMapa:
                Intent intent1=new Intent(PrincipalActivity.this,MapaActivity.class);
                intent1.putExtra("codMap",0);
                startActivity(intent1);
                break;
        }
    }
}
