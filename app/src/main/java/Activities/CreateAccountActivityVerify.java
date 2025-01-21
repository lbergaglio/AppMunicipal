package Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmunicipal.R;

import java.util.List;

import Objects.vecinos;
import RestAPI.VecinosService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateAccountActivityVerify extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_create_account_verify);

        EditText editTextDocumento = findViewById(R.id.editTextDocumentoVerify);
        EditText editTextCorreo = findViewById(R.id.editTextCorreo);
        CheckBox checkBoxAceptoTerminos = findViewById(R.id.checkBox);
        Button buttonCrearCuenta = findViewById(R.id.idForgotPasswordButton);
        Button buttonVolver = findViewById(R.id.idBackButton);

        buttonVolver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccountActivityVerify.this, LoginCreateActivity.class);
                startActivity(intent);
            }
        });

        buttonCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String documento = editTextDocumento.getText().toString();
                String correo = editTextCorreo.getText().toString();
                Boolean checkBox = checkBoxAceptoTerminos.isChecked();
                if(checkBox){
                    vecinos vecino = obtenerVecinoPorDocumento(documento);
                    Intent intent = new Intent(CreateAccountActivityVerify.this, AccountCreatedActivity.class);
                    if (vecino != null) {
                        intent.putExtra("documento", vecino.getDocumento());
                        intent.putExtra("nombre", vecino.getApellido());
                        intent.putExtra("apellido", vecino.getNombre());
                        intent.putExtra("direccion", vecino.getDireccion());
                        intent.putExtra("codBarrio", vecino.getCodigoBarrio());
                        intent.putExtra("email", correo);
                        startActivity(intent);
                    } else startActivity(intent);
                }
                else Toast.makeText(CreateAccountActivityVerify.this, "No has aceptado los terminos", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private vecinos obtenerVecinoPorDocumento(String documento) {
        vecinos vecino = new vecinos();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VecinosService vecinoPorDocumentoService = retrofit.create(VecinosService.class);
        Call<List<vecinos>> vecinoCall = vecinoPorDocumentoService.getVecinoPorDocumento(documento);

        vecinoCall.enqueue(new Callback<List<vecinos>>() {
            @Override
            public void onResponse(Call<List<vecinos>> call, Response<List<vecinos>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<vecinos> data = response.body();
                    vecinos dataUnica= data.get(0);
                    //Toast.makeText(CreateAccountActivityVerify.this, data.getApellido(), Toast.LENGTH_SHORT).show();
                    vecino.setDocumento(dataUnica.getDocumento());
                    vecino.setNombre(dataUnica.getNombre());
                    vecino.setApellido(dataUnica.getApellido());
                    vecino.setCodigoBarrio(dataUnica.getCodigoBarrio());
                    vecino.setDireccion(dataUnica.getDireccion());
                    Toast.makeText(CreateAccountActivityVerify.this,vecino.getApellido(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(CreateAccountActivityVerify.this, "No se obtuvieron datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<vecinos>> call, Throwable t) {
                //Toast.makeText(CreateAccountActivityVerify.this,"hola", Toast.LENGTH_SHORT).show();
                Toast.makeText(CreateAccountActivityVerify.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return vecino;
    }
}
