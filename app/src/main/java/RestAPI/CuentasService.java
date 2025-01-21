package RestAPI;

import java.util.List;

import Objects.cuentas;
import Objects.denuncias;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CuentasService {

    public final String API_ROUTE= "/cuentas";

    @GET(API_ROUTE)
    Call<List<cuentas>> getCuentas();

    @GET(API_ROUTE+"/{documento}")
    Call<List<cuentas>> getCuentasPorDocumento(@Path("documento") String documento);

    @POST(API_ROUTE)
    Call<Void> postCuenta(@Body cuentas cuenta);

    @PUT(API_ROUTE + "/{documento}")
    Call<Void> updateContraseniaCuenta(@Path("documento") String documento,@Body cuentas cuenta);
}
