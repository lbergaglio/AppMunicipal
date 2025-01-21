package RestAPI;

import java.util.List;

import Objects.movimientoDenuncia;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MovimientoDenunciaService {
    public final String API_ROUTE = "/movimientoDenuncia";
    @GET(API_ROUTE)
    Call<List<movimientoDenuncia>> getMovimientosDenuncia();

    @POST(API_ROUTE)
    Call<Void> postMovimientoDenuncia(@Body movimientoDenuncia movDenuncia);
}
