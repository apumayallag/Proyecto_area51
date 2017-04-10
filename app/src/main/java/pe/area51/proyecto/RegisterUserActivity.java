package pe.area51.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.area51.proyecto.database.SentenciaSQL;

public class RegisterUserActivity extends AppCompatActivity {

    @BindView(R.id.etRegisNombre)
    EditText etRegisNombre;
    @BindView(R.id.etRegisApe)
    EditText etRegisApe;
    @BindView(R.id.etRegisCorreo)
    EditText etRegisCorreo;
    @BindView(R.id.etRegisMovil)
    EditText etRegisMovil;
    @BindView(R.id.etRegisUser)
    EditText etRegisUser;
    @BindView(R.id.etRegisPass)
    EditText etRegisPass;
    @BindView(R.id.etRegisRePass)
    EditText etRegisRePass;
    @BindView(R.id.activity_register_user)
    LinearLayout activityRegisterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnRegistrar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegistrar:
                //Obtenemos los datos de los EditText
                String nombre = etRegisNombre.getText().toString();
                String apellid = etRegisApe.getText().toString();
                String correo = etRegisCorreo.getText().toString();
                String telfo = etRegisMovil.getText().toString();
                String user = etRegisUser.getText().toString();
                String pass = etRegisPass.getText().toString();
                String repass = etRegisRePass.getText().toString();
                //Validaciones
                if (nombre.equals("")) {
                    etRegisNombre.setError("Ingrese Nombre");
                    etRegisNombre.requestFocus();
                    return;
                } else if (apellid.equals("")) {
                    etRegisApe.setError("Ingrese Apellido");
                    etRegisApe.requestFocus();
                    return;
                } else if (correo.equals("")) {
                    etRegisCorreo.setError("Ingrese Correo");
                    etRegisCorreo.requestFocus();
                    return;
                } else if (telfo.equals("")) {
                    etRegisMovil.setError("Ingrese Telefono o celular");
                    etRegisMovil.requestFocus();
                    return;
                } else if (user.equals("")) {
                    etRegisUser.setError("Ingrese Usuario");
                    etRegisUser.requestFocus();
                    return;
                } else if (pass.equals("")) {
                    etRegisPass.setError("Ingrese Contraseña");
                    etRegisPass.requestFocus();
                    return;
                }
                //Validamos la contraseña
                if (!pass.equals(repass)) {
                    Toast.makeText(this, "Las contraseñas no son correctas", Toast.LENGTH_SHORT).show();
                    etRegisPass.setText("");
                    etRegisRePass.setText("");
                    etRegisPass.requestFocus();
                    return;
                }

                SentenciaSQL sentenciaSQL = new SentenciaSQL(RegisterUserActivity.this);

                //Validamos si esta registrado
                String uservald = sentenciaSQL.seencuentra(user);
                if (uservald == "SI") {
                    Toast.makeText(this, "El usuario ya se encuentra registrado", Toast.LENGTH_SHORT).show();
                    etRegisUser.setError("Usuario en uso");
                    etRegisUser.requestFocus();
                    return;
                }

                sentenciaSQL.registrarUser(nombre, apellid, correo, telfo, user, pass);

                Toast.makeText(this, "Se registro correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(RegisterUserActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
