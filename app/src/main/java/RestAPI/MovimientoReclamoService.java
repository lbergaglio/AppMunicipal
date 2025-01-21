package RestAPI;

import java.util.List;

import Objects.movimientoReclamo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MovimientoReclamoService {
    public final String API_ROUTE = "/movimientosReclamo";
    @GET(API_ROUTE)
    Call<List<movimientoReclamo>> getMovimientosReclamo();
}
