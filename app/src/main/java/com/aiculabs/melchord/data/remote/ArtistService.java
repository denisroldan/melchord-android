package com.aiculabs.melchord.data.remote;

import com.aiculabs.melchord.data.model.Artist;
import com.aiculabs.melchord.data.model.ArtistSearch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import timber.log.Timber;


public interface ArtistService {
    String ENDPOINT = APIConfig.BASE_URL;

    @GET("artist/{mbid}")
    Observable<Artist> getArtist(@Path("mbid") String mbid);

    @GET("artist/search")
    Observable<List<ArtistSearch>> getArtistSearchResults(@Query("term") String term);

    @GET("artists/featured")
    Observable<List<Artist>> getFeaturedArtists();


    /******** Helper class that sets up a new services *******/
    class Creator {

        public static ArtistService newArtistsService() {


            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override public void log(String message) {
                    Timber.tag("OkHttp").d(message);
                }
            });

            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);  // <-- this is the important line!


            Gson gson = new GsonBuilder()
                    .setDateFormat(APIConfig.DATE_FORMAT)
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ArtistService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                    .client(httpClient.build())


                    .build();
            return retrofit.create(ArtistService.class);
        }

    }
}
