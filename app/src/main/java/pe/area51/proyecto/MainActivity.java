package pe.area51.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.area51.proyecto.database.SentenciaSQL;
import pe.area51.proyecto.modelosbd.DatosLogin;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etUser)
    EditText etUser;
    @BindView(R.id.etPass)
    EditText etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tvRegistro, R.id.btnIngresar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvRegistro:
                Intent intent = new Intent(MainActivity.this, RegisterUserActivity.class);
                startActivity(intent);
                break;
            case R.id.btnIngresar:

                SentenciaSQL sentenciaSQL = new SentenciaSQL(MainActivity.this);

                String user = etUser.getText().toString();
                String pass = etPass.getText().toString();
                String paswBd = sentenciaSQL.obtenerPassconUser(user);
                String nombre = sentenciaSQL.obtenerNombreconUser(user);
                String id = sentenciaSQL.obtenerIdconUser(user);

                if (pass.equals(paswBd)) {
                    Toast.makeText(this, id +" Bienvenido " + nombre, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, PrincipalActivity.class);
                    i.putExtra("id", Integer.parseInt(id));
                    startActivity(i);
                    overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
                    finish();
                }else
                Toast.makeText(this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
