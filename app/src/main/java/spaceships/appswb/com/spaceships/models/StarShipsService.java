package spaceships.appswb.com.spaceships.models;

import retrofit2.Call;
import retrofit2.http.GET;

//classe que cria um contrato para acessar os dados da api
public interface StarShipsService {

    public static final String BASE_URL = "https://swapi.co/api/";

    @GET("starships")
    Call<StarshipList> starShipList();



}
