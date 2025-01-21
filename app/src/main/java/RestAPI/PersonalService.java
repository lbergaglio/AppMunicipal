package RestAPI;

import java.util.List;

import Objects.personal;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PersonalService {
    public final String API_ROUTE = "/personal";

    @GET(API_ROUTE)
    Call<List<personal>> getPersonal();
}
