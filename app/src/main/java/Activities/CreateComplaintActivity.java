package Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmunicipal.R;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import Objects.cuentas;
import Objects.denuncias;
import Objects.movimientoDenuncia;
import RestAPI.DenunciaService;
import RestAPI.MovimientoDenunciaService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateComplaintActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_complaint_new);

        Intent intent = getIntent();
        cuentas cuenta = (cuentas) intent.getSerializableExtra("cuenta");

        EditText editTextVecinoComercio = findViewById(R.id.editTextTextVecinoComercio);
        EditText editTextDireccion = findViewById(R.id.editTextDireccion);
        EditText editTextDescripcion = findViewById(R.id.editTextDescripcion);
        CheckBox checkBoxDeclaracionJurada = findViewById(R.id.checkBoxDeclaracionJurada);
        Button buttonGenerarDenuncia = findViewById(R.id.buttonGenerarDenuncia);
        Button buttonVolver = findViewById(R.id.buttonVolverComplaint);

        buttonGenerarDenuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vecinoComercio = editTextVecinoComercio.getText().toString();
                String direccion = editTextDireccion.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();
                Boolean declaracionAceptada = checkBoxDeclaracionJurada.isChecked();

                if (validarDenuncia(vecinoComercio, direccion, descripcion, declaracionAceptada) && cuenta != null) {
                    denuncias denuncia = new denuncias();
                    denuncia.setAceptaResponsabilidad(1);
                    denuncia.setDocumento(cuenta.getDocumento());
                    denuncia.setIdSitio(1234);
                    denuncia.setEstado("Enviado");
                    denuncia.setDescripcion("Vecino o Comercio: " + vecinoComercio + ", Direccion: " + direccion + ", Descripcion: " + descripcion);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2:3000")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    DenunciaService denunciaService = retrofit.create(DenunciaService.class);
                    Call<Void> callPostDenuncia = denunciaService.postDenuncia(denuncia);
                    callPostDenuncia.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(CreateComplaintActivity.this, "Denuncia generada con éxito", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CreateComplaintActivity.this, CreatedComplaintActivity.class);
                                intent.putExtra("cuenta", cuenta);
                                //intent.putExtra("idDenuncia",getNuevoNumeroDenuncia());
                                startActivity(intent);
                            } else {
                                Toast.makeText(CreateComplaintActivity.this, "Error al generar la denuncia", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(CreateComplaintActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateComplaintActivity.this, ComplaintActivity.class);
                startActivity(intent);
            }
        });
    }

    private Boolean validarDenuncia(String vecinoComercio, String direccion, String descripcion, Boolean declaracionAceptada) {
        if (declaracionAceptada) {
            if (!vecinoComercio.isEmpty()) {
                if (!direccion.isEmpty()) {
                    if (!descripcion.isEmpty()) return true;
                    else
                        Toast.makeText(CreateComplaintActivity.this, "Causa no ingresada", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(CreateComplaintActivity.this, "Direccion no ingresada", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(CreateComplaintActivity.this, "Nombre del Vecino o Comercio no fue ingresado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CreateComplaintActivity.this, "Acepte Declaracion Jurada", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void generarMovimientoDeDenuncia(denuncias denuncia, int nroDenuncia){
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());//POSIBLE FALLA DE ANDROID
        movimientoDenuncia movDenuncia = new movimientoDenuncia();
        movDenuncia.setIdDenuncia(nroDenuncia);
        movDenuncia.setCausa(denuncia.getDescripcion());
        movDenuncia.setResponsable(denuncia.getDocumento());
        movDenuncia.setFecha(timestamp);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovimientoDenunciaService movimientoDenunciaService = retrofit.create(MovimientoDenunciaService.class);
        Call<Void> callMovimientoDenuncia = movimientoDenunciaService.postMovimientoDenuncia(movDenuncia);

        callMovimientoDenuncia.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(CreateComplaintActivity.this,"Movimiento Generado",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CreateComplaintActivity.this,"No se generó movimiento",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getNuevoNumeroDenuncia() {
        int[] nroDenuncia = {1}; // Usamos un array para poder modificar su valor dentro del Callback

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DenunciaService denunciaService = retrofit.create(DenunciaService.class);
        Call<List<denuncias>> call = denunciaService.getDenuncias();

        call.enqueue(new Callback<List<denuncias>>() {
            @Override
            public void onResponse(Call<List<denuncias>> call, Response<List<denuncias>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<denuncias> data = response.body();
                    nroDenuncia[0] = data.get(data.size() - 1).getIdDenuncias();
                }
            }

            @Override
            public void onFailure(Call<List<denuncias>> call, Throwable t) {
                Toast.makeText(CreateComplaintActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return nroDenuncia[0];
    }
}
