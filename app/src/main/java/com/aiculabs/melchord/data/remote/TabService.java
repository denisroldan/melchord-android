package com.aiculabs.melchord.data.remote;

import com.aiculabs.melchord.data.model.Tab;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;


public interface TabService {
    String ENDPOINT = APIConfig.BASE_URL;

    @GET("tab/{id}")
    Observable<Tab> getTab(@Path("id") Integer id);

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static TabService newTabServices() {
            Gson gson = new GsonBuilder()
                    .setDateFormat(APIConfig.DATE_FORMAT)
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(TabService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(TabService.class);
        }

    }
}
