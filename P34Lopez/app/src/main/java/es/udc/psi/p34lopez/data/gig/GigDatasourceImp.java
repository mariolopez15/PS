package es.udc.psi.p34lopez.data.gig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.udc.psi.p34lopez.BuildConfig;
import es.udc.psi.p34lopez.data.SetListFmAPI;
import es.udc.psi.p34lopez.data.interceptor.SetListFmApiInterceptor;
import es.udc.psi.p34lopez.domain.gig.Gig;
import es.udc.psi.p34lopez.domain.gig.Gigs;
import es.udc.psi.p34lopez.domain.gig.datasource.GigDatasource;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GigDatasourceImp implements GigDatasource {

    @Override
    public List<Gig> searchGig(String artistId) {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        List<Interceptor> interceptors = okHttpClient.interceptors();
        interceptors.add(loggingInterceptor);
        interceptors.add(new SetListFmApiInterceptor());

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.setlist.fm/rest/1.0/")
                .client(okHttpClient.build()).addConverterFactory(GsonConverterFactory.create()).build();

        SetListFmAPI api = retrofit.create(SetListFmAPI.class);

        Call<Gigs> call = api.searchSetLists(artistId);

        try {

            Response<Gigs> response = call.execute();

            if (response.isSuccessful()) {

                return response.body().getGigs();

            } else {

                return new ArrayList<>();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
