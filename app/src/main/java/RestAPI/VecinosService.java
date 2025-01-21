package RestAPI;

import java.util.List;

import Objects.vecinos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VecinosService {

    String API_ROUTE = "/vecinos";

    @GET(API_ROUTE)
    Call<List<vecinos>> getVecinos();

    @GET(API_ROUTE + "/{documento}")
    Call<List<vecinos>> getVecinoPorDocumento(@Path("documento") String documento);

}
