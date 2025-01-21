package com.example.Main;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import Objects.barrios;
import Objects.denuncias;
import Objects.desperfectos;
import Objects.movimientoDenuncia;
import Objects.movimientoReclamo;
import Objects.personal;
import Objects.reclamos;
import Objects.rubros;
import Objects.sitios;
import Objects.vecinos;
import RestAPI.DenunciaService;
import RestAPI.DesperfectosService;
import RestAPI.MovimientoDenunciaService;
import RestAPI.MovimientoReclamoService;
import RestAPI.PersonalService;
import RestAPI.ReclamoService;
import RestAPI.RubroService;
import RestAPI.BarriosService;
import RestAPI.SitiosService;
import RestAPI.VecinosService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    ArrayList<denuncias> listaDenunciasRegistradas;
    ArrayAdapter<denuncias> adapterDenuncias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDatos();




    }

    public void getDatos(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BarriosService barriosService = retrofit.create(BarriosService.class);
        DenunciaService denunciaService =retrofit.create(DenunciaService.class);
        DesperfectosService desperfectosService = retrofit.create(DesperfectosService.class);
        MovimientoDenunciaService movimientoDenunciaService = retrofit.create(MovimientoDenunciaService.class);
        MovimientoReclamoService movimientoReclamoService = retrofit.create(MovimientoReclamoService.class);
        PersonalService personalService = retrofit.create(PersonalService.class);
        ReclamoService reclamoService = retrofit.create(ReclamoService.class);
        RubroService rubroService = retrofit.create(RubroService.class);
        SitiosService sitiosService = retrofit.create(SitiosService.class);
        VecinosService vecinosService = retrofit.create(VecinosService.class);



        int documento=0;
        boolean esProfesional=true;
        String promocion="";

        //DEFINIMOS METODOS

        //BARRIOS
        Call<List<barrios>> listaBarrios = barriosService.getBarrios();

        //DENUNCIAS
        Call<List<denuncias>> listaDenuncias = denunciaService.getDenuncia();
        Call<List<denuncias>> denunciasPorDocumento = denunciaService.getDenunciaPorDocumento(documento);

        //DESPERFECTOS
        Call<List<desperfectos>> listaDesperfectos = desperfectosService.getDesperfectos();

        //MOVIMIENTOS DENUNCIA
        Call<List<movimientoDenuncia>> listaMovimientoDenuncia = movimientoDenunciaService.getMovimientosDenuncia();

        //MOVIMIENTO RECLAMO
        Call<List<movimientoReclamo>> listaMovimientoReclamo = movimientoReclamoService.getMovimientosReclamo();

        //PERSONAL
        Call<List<personal>> listaPersonal = personalService.getPersonal();

        //RECLAMOS
        Call<List<reclamos>> listaReclamos = reclamoService.getReclamo();

        //RUBROS
        Call<List<rubros>> listaRubros = rubroService.getRubro();
        Call<List<rubros>> rubrosPorProfesional = rubroService.getRubroPorProfesional(esProfesional);
        Call<List<rubros>> rubrosPorPromociones = rubroService.getRubroPorPromociones(promocion);

        //SITIOS
        Call<List<sitios>> listaSitios = sitiosService.getSitios();

        //VECINOS
        Call<List<vecinos>> listaVecinos = vecinosService.getVecinos();

        listaDenuncias.enqueue(new Callback<List<denuncias>>() {
            @Override
            public void onResponse(Call<List<denuncias>> call, Response<List<denuncias>> response) {
                int cantidad = 0;
                if(response.body()!=null){
                    for(denuncias denuncia : response.body()){
                        listaDenunciasRegistradas.add(denuncia);
                        cantidad++;
                    }
                    adapterDenuncias.notifyDataSetChanged();
                }
                else{

                }
                return cantidad;
            }
            @Override
            public void onFailure(Call<List<denuncias>> call, Throwable t) {

            }
        });





    }
}