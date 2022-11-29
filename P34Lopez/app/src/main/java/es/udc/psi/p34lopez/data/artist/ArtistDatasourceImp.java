package es.udc.psi.p34lopez.data.artist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.udc.psi.p34lopez.BuildConfig;
import es.udc.psi.p34lopez.data.MusicBrainzAPI;
import es.udc.psi.p34lopez.domain.artist.Artist;
import es.udc.psi.p34lopez.domain.artist.Artists;
import es.udc.psi.p34lopez.domain.artist.datasource.ArtistDatasource;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistDatasourceImp implements ArtistDatasource {

    @Override
    public List<Artist> searchArtists(String textToSearch) {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        List<Interceptor> interceptors = okHttpClient.interceptors();
        interceptors.add(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://musicbrainz.org/ws/2/").client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create()).build();

        MusicBrainzAPI api = retrofit.create(MusicBrainzAPI.class);

        String query = "artist:" + textToSearch;
        String format = "json";
        Call<Artists> call = api.searchArtistByName(query, format);

        try {

            return call.execute().body().getArtists();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();

    }
}
