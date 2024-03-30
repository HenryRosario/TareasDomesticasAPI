package network;

import data.Tarea;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;

import java.util.List;

public interface tareaInterface {

    @GET("api/Tareas")
    Call<List<Tarea>> getTodasTareas();

    @POST("api/Tareas")
    Call<Void> enviarTarea(@Body Tarea tarea);


}
