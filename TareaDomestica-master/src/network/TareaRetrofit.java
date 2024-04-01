package network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Tarea;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;


public class TareaRetrofit {
    private static final String API_URI = "https://gestordomestico.somee.com/";

    private static final Gson gson = new GsonBuilder()
            .create();

    private static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(API_URI)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private static final tareaInterface SERVICE = RETROFIT.create(tareaInterface.class);

    public TareaRetrofit() {
    }

    public List<Tarea> listaCitas() {
        List<Tarea> citas = new ArrayList<>() { };
        try {
            citas = SERVICE.getTodasTareas().execute().body();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return citas;
    }
    public boolean enviarTarea(Tarea tarea) {
        boolean listo = false;
        try {
            listo = SERVICE.enviarTarea(tarea).execute().isSuccessful();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return listo;
    }
}
