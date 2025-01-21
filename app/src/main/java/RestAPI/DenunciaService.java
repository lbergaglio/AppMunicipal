package RestAPI;

import java.util.List;

import Objects.denuncias;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DenunciaService {

    public final String API_ROUTE = "/denuncias";

    @GET(API_ROUTE)
    Call<List<denuncias>> getDenuncias();

    @GET(API_ROUTE+"/{documento}")
    Call<List<denuncias>> getDenunciaPorDocumento(int documento);

    @POST(API_ROUTE)
    Call<Void> postDenuncia(@Body denuncias denuncia);



}
