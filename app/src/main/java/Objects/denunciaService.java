package Objects;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface denunciaService {

    public final String API_ROUTE = "/denuncias";

    @GET(API_ROUTE)
    Call<List<denuncias>> getDenuncia();
    @GET(API_ROUTE+"/{documento}")
    Call<List<denuncias>> getDenunciaDocumento(int documento);
    @POST(API_ROUTE)
    Call<List<denuncias>> postDenuncia();



}
