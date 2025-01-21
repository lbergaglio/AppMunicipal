package RestAPI;

import java.util.List;

import Objects.barrios;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BarriosService {
    public final String API_ROUTE= "/barrios";
    @GET(API_ROUTE)
    Call<List<barrios>> getBarrios();

}
