package RestAPI;

import java.util.List;

import Objects.reclamos;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ReclamoService {
    public final String API_ROUTE = "/reclamo";
    @GET(API_ROUTE)
    Call<List<reclamos>> getReclamo();

    @GET(API_ROUTE+"{documento}")
    Call<List<reclamos>> getReclamosPorDocumento(int documento);

    @GET(API_ROUTE+"{idReclamo}")
    Call<List<reclamos>> getReclamosPorIdReclamo(int idReclamo);

    @POST(API_ROUTE)
    Call<List<reclamos>> postReclamo();

    @DELETE(API_ROUTE+"{idReclamo}")
    Call<List<reclamos>> deleteReclamo(int idReclamo);

}
