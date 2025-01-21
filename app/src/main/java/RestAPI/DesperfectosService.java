package RestAPI;

import java.util.List;

import Objects.desperfectos;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DesperfectosService {
    public final String API_ROUTE = "/desperfectos";
    @GET(API_ROUTE)
    Call<List<desperfectos>> getDesperfectos();
}
