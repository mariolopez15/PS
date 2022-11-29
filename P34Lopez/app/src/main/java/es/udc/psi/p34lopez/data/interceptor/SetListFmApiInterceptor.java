package es.udc.psi.p34lopez.data.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class SetListFmApiInterceptor implements Interceptor {

    private static final String API_KEY_HEADER = "x-api-key";
    private static final String ACCEPT_HEADER = "Accept";

    private static final String API_KEY = "HHQghTl1wVmNY_9z17UDOdJeIYl5PC6zkmuv";
    private static final String JSON_RESPONSE = "application/json";

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl.Builder builder = originalHttpUrl.newBuilder();
        HttpUrl url = builder.build();

        Request.Builder requestBuilder = original.newBuilder().url(url);

        requestBuilder.addHeader(API_KEY_HEADER, API_KEY);
        requestBuilder.addHeader(ACCEPT_HEADER, JSON_RESPONSE);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
