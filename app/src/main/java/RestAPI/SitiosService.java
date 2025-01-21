package RestAPI;

import java.util.List;

import Objects.sitios;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SitiosService {
    public final String API_ROUTE = "/sitios";
    @GET(API_ROUTE)
    Call<List<sitios>> getSitios();
}
