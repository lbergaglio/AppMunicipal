package Activities;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Objects.reclamos;
import RestAPI.ReclamoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.appmunicipal.R;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_claim_list);
        ImageButton buttonAddReport = findViewById(R.id.floatingActionButtonReport);
        ImageButton buttonHome = findViewById(R.id.homeButtonReport);
        ImageButton buttonJob = findViewById(R.id.workButtonReport);

        ArrayList<reclamos> listaReclamos= getDatos();

        buttonAddReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this,CreateClaimActivity.class);
                startActivity(intent);
            }
        });


    }

    private ArrayList<reclamos> getDatos(){
        ArrayList<reclamos> lista = new ArrayList<reclamos>();
        reclamos reclamo = new reclamos();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ReclamoService reclamoService = retrofit.create(ReclamoService.class);
        Call<List<reclamos>> listaReclamos = reclamoService.getReclamo();

        listaReclamos.enqueue(new Callback<List<reclamos>>() {
            @Override
            public void onResponse(Call<List<reclamos>> call, Response<List<reclamos>> response) {
                if(response.isSuccessful() && response.body()!=null){
                    List<reclamos> data = response.body();
                    int cantidad = 0;
                    for(reclamos item : data){
                        reclamo.setIdReclamo(item.getIdReclamo());
                        reclamo.setDocumento(item.getDocumento());
                        reclamo.setIdSitio(item.getIdSitio());
                        reclamo.setIdDesperfecto(item.getIdDesperfecto());
                        reclamo.setDescripcion(item.getDescripcion());
                        reclamo.setDescripcion(item.getDescripcion());
                        reclamo.setEstado(item.getEstado());
                        reclamo.setIdReclamoUnificado(item.getIdReclamoUnificado());

                        lista.add(reclamo);
                        cantidad++;
                    }
                    Toast.makeText(ReportActivity.this, "Se obtuvieron " + cantidad + "reclamos", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ReportActivity.this, "No se pudo obtener datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<reclamos>> call, Throwable t) {
                Toast.makeText(ReportActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

        return lista;
    }


}
