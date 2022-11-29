package es.udc.psi.p34lopez.data.interceptor;

import android.util.Log;

import java.io.IOException;

import es.udc.psi.p34lopez.BuildConfig;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class MusicBrainzApiInterceptor implements Interceptor{

    private static final String TAG = MusicBrainzApiInterceptor.class.getSimpleName();

    private static final String USER_AGENT_HEADER = "User-Agent";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl.Builder builder = originalHttpUrl.newBuilder();
        HttpUrl url = builder.build();

        Request.Builder requestBuilder = original.newBuilder().url(url);

        String userAgentHeaderValue = "PSiCleanArch/" + BuildConfig.VERSION_CODE + " ( juan.porta@udc.es )";
        requestBuilder.addHeader(USER_AGENT_HEADER, userAgentHeaderValue);

        Log.d(TAG, userAgentHeaderValue);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }


}
