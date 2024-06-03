package com.example.Main;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import Objects.denunciaService;
import Objects.denuncias;
import Objects.reclamoService;
import Objects.reclamos;
import Objects.rubroService;
import Objects.rubros;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<denuncias> listDenuncias;
    ArrayAdapter<denuncias> adapterDenuncias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        listDenuncias = new ArrayList<>();
    }

    public void getDatos(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addCallAdapterFactory(GsonConverterFactory.create())
                .build();

        denunciaService denunciaService =retrofit.create(denunciaService.class);
        reclamoService reclamoService = retrofit.create(reclamoService.class);
        rubroService rubroService = retrofit.create(rubroService.class);
        int documento=0;
        boolean esProfesional=true;
        String promocion="";

        //DENUNCIAS
        Call<List<denuncias>> listaDenuncias = denunciaService.getDenuncia();
        Call<List<denuncias>> denunciasPorDocumento = denunciaService.getDenunciaDocumento(documento);
        //RECLAMOS
        Call<List<reclamos>> listaReclamos = reclamoService.getReclamo();
        //RUBROS
        Call<List<rubros>> listaRubros = rubroService.getRubro();
        Call<List<rubros>> rubrosPorProfesional = rubroService.getRubroPorProfesional(esProfesional);
        Call<List<rubros>> rubrosPorPromociones = rubroService.getRubroPorPromociones(promocion);

        listaDenuncias.enqueue(new Callback<List<denuncias>>() {
            @Override
            public void onResponse(Call<List<denuncias>> call, Response<List<denuncias>> response) {
                int cantidad = 0;
                if(response.body()!=null){
                    for(denuncias denuncia : response.body()){
                        listDenuncias.add(denuncia);
                        cantidad++;
                    }
                    adapterDenuncias.notifyDataSetChanged();
                }
                else{

                }
            }
            @Override
            public void onFailure(Call<List<denuncias>> call, Throwable t) {

            }
        });



    }
}