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
import Objects.vecinos;
import RestAPI.CuentasService;
import RestAPI.VecinosService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_login);

        EditText textdocumento = findViewById(R.id.idTextInputUsername);
        EditText textPassword = findViewById(R.id.idTextInputPassword);

        Button buttonLogin = findViewById(R.id.idSingInButton);
        Button buttonPasswordForgot = findViewById(R.id.idPasswordForgot);
        Button buttonAccountCreate = findViewById(R.id.idForgotPasswordButton);
        Button buttonBack = findViewById(R.id.idBackButton);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String documento = textdocumento.getText().toString();
                String password = textPassword.getText().toString();
                buscarCuenta(documento, password);
            }
        });

        buttonPasswordForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        buttonAccountCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivityVerify.class);
                startActivity(intent);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, LoginCreateActivity.class);
                startActivity(intent);
            }
        });
    }

    private void buscarCuenta(String documento, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CuentasService cuentasService = retrofit.create(CuentasService.class);
        Call<List<cuentas>> cuentasCall = cuentasService.getCuentasPorDocumento(documento);

        cuentasCall.enqueue(new Callback<List<cuentas>>() {
            @Override
            public void onResponse(Call<List<cuentas>> call, Response<List<cuentas>> response) {
                List<cuentas> data = response.body();
                if (data != null && !data.isEmpty()) {
                    cuentas dataUnica = data.get(0);
                    if (dataUnica.getContrasenia().equals(password)) {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        //intent.putExtra("id", dataUnica.getId());
                        intent.putExtra("cuenta",dataUnica);
                        intent.putExtra("nombre", dataUnica.getNombre());
                        intent.putExtra("apellido", dataUnica.getApellido());
                        intent.putExtra("documento", dataUnica.getDocumento());
                        intent.putExtra("direccion", dataUnica.getDireccion());
                        intent.putExtra("codigoBarrio", dataUnica.getCodBarrio());
                        intent.putExtra("correo", dataUnica.getCorreo());
                        intent.putExtra("contrasenia", dataUnica.getContrasenia());
                        intent.putExtra("esVecino", dataUnica.getEsVecino());
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "La contraseña ingresada es incorrecta", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    obtenerVecinoPorDocumento(documento, new VecinoCallback() {
                        @Override
                        public void onVecinoFound(vecinos vecino) {
                            if (vecino != null) {
                                Intent intent = new Intent(LoginActivity.this, CreateAccountActivityPassword.class);
                                intent.putExtra("documento", vecino.getDocumento());
                                intent.putExtra("nombre", vecino.getApellido());
                                intent.putExtra("apellido", vecino.getNombre());
                                intent.putExtra("direccion", vecino.getDireccion());
                                intent.putExtra("codBarrio", vecino.getCodigoBarrio());
                                intent.putExtra("esVecino", true);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "No se encontró un vecino con ese documento", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                return 0;
            }

            @Override
            public void onFailure(Call<List<cuentas>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void obtenerVecinoPorDocumento(String documento, VecinoCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VecinosService vecinoPorDocumentoService = retrofit.create(VecinosService.class);
        Call<List<vecinos>> vecinoCall = vecinoPorDocumentoService.getVecinoPorDocumento(documento);

        vecinoCall.enqueue(new Callback<List<vecinos>>() {
            @Override
            public void onResponse(Call<List<vecinos>> call, Response<List<vecinos>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    vecinos vecino = response.body().get(0);
                    callback.onVecinoFound(vecino);
                } else {
                    callback.onVecinoFound(null);
                }
                return 0;
            }

            @Override
            public void onFailure(Call<List<vecinos>> call, Throwable t) {
                callback.onVecinoFound(null);
            }
        });
    }

    interface VecinoCallback {
        void onVecinoFound(vecinos vecino);
    }
}
