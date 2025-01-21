package Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmunicipal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

//import Adapters.AdapterComplaint;
import Objects.cuentas;
import Objects.denuncias;
import RestAPI.DenunciaService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComplaintActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_complaint_list);
        Intent intent = getIntent();
        cuentas cuenta = (cuentas) intent.getSerializableExtra("cuenta");
        /*
        RecyclerView recyclerView;
        AdapterComplaint adapterComplaint;
        FloatingActionButton buttonAddComplaint = findViewById(R.id.floatingActionButtonComplaint);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<denuncias> listaDenuncias = new ArrayList<>() ;
        adapterComplaint = new AdapterComplaint(this,listaDenuncias);
        recyclerView.setAdapter(adapterComplaint);

        getListaDenuncias(listaDenuncias,adapterComplaint);

        buttonAddComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComplaintActivity.this,CreateComplaintActivity.class);
                intent.putExtra("cuenta",cuenta);
                startActivity(intent);
            }
        });
*/
    }
    /*private void getListaDenuncias(List<denuncias> lista, AdapterComplaint adapter){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DenunciaService denunciaService = retrofit.create(DenunciaService.class);
        Call<List<denuncias>> callListaDenuncias = denunciaService.getDenuncias();

        callListaDenuncias.enqueue(new Callback<List<denuncias>>() {
            @Override
            public void onResponse(Call<List<denuncias>> call, Response<List<denuncias>> response) {
                Toast.makeText(ComplaintActivity.this, "Conexion establecida", Toast.LENGTH_SHORT).show();
                if(response.isSuccessful() && response.body()!=null){
                    lista.clear();
                    lista.addAll(response.body());
                    //adapter.notifyDataSetChanged();
                    Toast.makeText(ComplaintActivity.this, "Se otuvieron " + lista.size() + " denuncias", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(ComplaintActivity.this, "No se obtuvieron datos", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<denuncias>> call, Throwable t) {
                Toast.makeText(ComplaintActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
