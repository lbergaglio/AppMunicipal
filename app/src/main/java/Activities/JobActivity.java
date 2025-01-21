package Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmunicipal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Objects.rubros;
import RestAPI.RubroService;
import kotlinx.coroutines.JobNode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_job_list);

        ArrayList<rubros> listaJobs = getListaRubros();


        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButtonJob);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobActivity.this, CreateJobActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<rubros> getListaRubros(){

        ArrayList<rubros> lista = new ArrayList<rubros>();

        rubros rubro = new rubros();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RubroService rubroService = retrofit.create(RubroService.class);
        Call<List<rubros>> listaRubros = rubroService.getRubro();

        listaRubros.enqueue(new Callback<List<rubros>>() {
            @Override
            public void onResponse(Call<List<rubros>> call, Response<List<rubros>> response) {
                Toast.makeText(JobActivity.this, "Conexion establecida", Toast.LENGTH_SHORT).show();
                if(response.isSuccessful() && response.body()!=null){
                    List<rubros> data = response.body();
                    for(rubros item : data){
                        rubro.setIdRubro(item.getIdRubro());
                        rubro.setCategoria(item.getCategoria());
                        rubro.setDescripcion(item.getDescripcion());
                        rubro.setEsProfesional(item.esProfesional());
                        rubro.setPromociones(item.getPromociones());
                        lista.add(rubro);
                    }
                    Toast.makeText(JobActivity.this, "Se obtuvieron " + lista.size() + " trabajos", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(JobActivity.this, "No se obtuvieron datos", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<rubros>> call, Throwable t) {
                Toast.makeText(JobActivity.this, t.getMessage() , Toast.LENGTH_LONG).show();
            }
        });
        return lista;
    }
}
