package RestAPI;

import java.util.List;

import Objects.reclamos;
import Objects.rubros;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RubroService {
    public final String API_ROUTE = "/rubro";
    @GET(API_ROUTE)
    Call<List<rubros>> getRubro();
    @GET(API_ROUTE+"/{categoria}")
    Call<List<rubros>> getRubroPorCategoria(String categoria);
    @GET(API_ROUTE+"/{esProfesional}")
    Call<List<rubros>> getRubroPorProfesional(boolean esProfesional);
    @GET(API_ROUTE+"/{promociones}")
    Call<List<rubros>> getRubroPorPromociones(String promociones);
    @POST(API_ROUTE)
    Call<List<reclamos>> postRubro();
    @DELETE(API_ROUTE)
    Call<List<reclamos>> deleteRubro();

}
