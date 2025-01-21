package Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmunicipal.R;

import java.util.List;

import Objects.cuentas;
import RestAPI.CuentasService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgotPasswordPasswordActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_forgot_password_password);
        Intent intent = getIntent();
        String documento = intent.getStringExtra("documento");

        EditText editTextContrasenia = findViewById(R.id.editTextContrasenia);
        EditText editTextConfirmarContrasenia = findViewById(R.id.editTextConfirmarContrasenia);
        Button buttonCambiarContrasenia = findViewById(R.id.idForgotPasswordButton);
        Button buttonVolver = findViewById(R.id.idBackButton);

        buttonCambiarContrasenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contrasenia = editTextContrasenia.getText().toString();
                String confirmarContrsenia = editTextConfirmarContrasenia.getText().toString();

                if(contrasenia.equals(confirmarContrsenia)){
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2:3000")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    CuentasService cuentasService = retrofit.create(CuentasService.class);
                    Call<List<cuentas>> callCuentas = cuentasService.getCuentasPorDocumento(documento);

                    callCuentas.enqueue(new Callback<List<cuentas>>() {
                        @Override
                        public void onResponse(Call<List<cuentas>> call, Response<List<cuentas>> response) {
                            if(response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                                cuentas dataUnica = response.body().get(0);
                                dataUnica.setContrasenia(contrasenia);
                                updateContraseniaEnServidor(documento, dataUnica);
                            } else {
                                Toast.makeText(ForgotPasswordPasswordActivity.this, "No se encontró la cuenta para el documento proporcionado", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<cuentas>> call, Throwable t) {
                            Toast.makeText(ForgotPasswordPasswordActivity.this, "Error al obtener la cuenta: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(ForgotPasswordPasswordActivity.this, "Las Contrasenas son distintas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordPasswordActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    private void updateContraseniaEnServidor(String documento, cuentas cuenta) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CuentasService cuentasService = retrofit.create(CuentasService.class);
        Call<Void> updateContraseniaPorDocumento = cuentasService.updateContraseniaCuenta(documento, cuenta);
        updateContraseniaPorDocumento.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ForgotPasswordPasswordActivity.this, "Contraseña actualizada correctamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ForgotPasswordPasswordActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ForgotPasswordPasswordActivity.this, "Error al actualizar contraseña", Toast.LENGTH_LONG).show();
                }
                return 0;
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ForgotPasswordPasswordActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
