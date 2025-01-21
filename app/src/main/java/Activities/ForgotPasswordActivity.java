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

public class ForgotPasswordActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_forgot_username_password);

        EditText editTextDocumento = findViewById(R.id.editTextDocumentoForgot);
        Button buttonForgotPassword = findViewById(R.id.idForgotPasswordButton);
        Button buttonVolver = findViewById(R.id.idBackButton);

        buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String documento = editTextDocumento.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:3000")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                CuentasService cuentasService = retrofit.create(CuentasService.class);
                Call<List<cuentas>> callCuentas = cuentasService.getCuentasPorDocumento(documento);

                callCuentas.enqueue(new Callback<List<cuentas>>() {
                    @Override
                    public void onResponse(Call<List<cuentas>> call, Response<List<cuentas>> response) {
                        if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                            List<cuentas> data = response.body();
                            if (data != null && !data.isEmpty()) {
                                Intent intent = new Intent(ForgotPasswordActivity.this, ForgotPasswordPasswordActivity.class);
                                intent.putExtra("documento", data.get(0).getDocumento());
                                startActivity(intent);
                            }

                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, "No se encontró el correo o DNI ingresado", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<cuentas>> call, Throwable t) {
                        Toast.makeText(ForgotPasswordActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
