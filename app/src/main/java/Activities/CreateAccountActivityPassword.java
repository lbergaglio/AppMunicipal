package Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmunicipal.R;

import java.util.ArrayList;
import java.util.List;

import Objects.cuentas;
import RestAPI.CuentasService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateAccountActivityPassword extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_create_account_password);
        EditText editTextCorreoElectronico = findViewById(R.id.editTextCorreoElectronico);
        EditText editTextContrasenia = findViewById(R.id.editTextContrasenia);
        EditText editTextConfirmarContrasenia = findViewById(R.id.editTextConfirmarContrasenia);
        Button buttonCrearCuenta = findViewById(R.id.idForgotPasswordButton);
        Button buttonVolver = findViewById(R.id.idBackButton);

        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccountActivityPassword.this,CreateAccountActivityVerify.class);
                startActivity(intent);
            }
        });
        buttonCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String nombre = intent.getStringExtra("nombre");
                String apellido = intent.getStringExtra("apellido");
                String documento = intent.getStringExtra("documento");
                String correo = editTextCorreoElectronico.getText().toString();
                String direccion = intent.getStringExtra("direccion");
                Integer codBarrio = intent.getIntExtra("codBarrio",0);
                Number esVecino = intent.getByteExtra("esVecino", (byte) 1);
                String contrasenia = editTextContrasenia.getText().toString();
                String confirmarContrasenia = editTextConfirmarContrasenia.getText().toString();

                if(contrasenia.equals(confirmarContrasenia)){
                    cuentas cuenta = new cuentas();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2:3000")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    cuenta.setNombre(nombre);
                    cuenta.setApellido(apellido);
                    cuenta.setDocumento(documento);
                    cuenta.setCorreo(correo);
                    cuenta.setContrasenia(contrasenia);
                    cuenta.setDireccion(direccion);
                    cuenta.setCodBarrio(codBarrio);
                    cuenta.setEsVecino(esVecino);

                    CuentasService cuentasService = retrofit.create(CuentasService.class);
                    Call<Void> postCuentas = cuentasService.postCuenta(cuenta);

                    postCuentas.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()){
                                Intent intent = new Intent(CreateAccountActivityPassword.this,LoginActivity.class);
                                Toast.makeText(CreateAccountActivityPassword.this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            } else {
                                Toast.makeText(CreateAccountActivityPassword.this, response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(CreateAccountActivityPassword.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
