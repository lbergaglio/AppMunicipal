package Objects;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface reclamoService {
    public final String API_ROUTE = "/reclamo";
    @GET(API_ROUTE)
    Call<List<reclamos>> getReclamo();

    @POST(API_ROUTE)
    Call<List<reclamos>> postReclamo();

    @DELETE(API_ROUTE)
    Call<List<reclamos>> deleteReclamo();

}
