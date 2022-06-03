package java1.dz.dz6;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AccuweatherMoscow {
    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment("294021")
                .addQueryParameter("apikey", "oxL4BsrOv9rNNFlXJwKrIJox86MCvGtn")
                .addQueryParameter("language", "en-us")
                .addQueryParameter("details","false")
                .addQueryParameter("metric","false")
                .build();

        Request requestAuth = new Request.Builder()
                .url(httpUrl)
                .addHeader("Content-Type", "application/json")
                .build();

        Response responseAuth = okHttpClient.newCall(requestAuth).execute();

        String responseBodyWithToken = responseAuth.body().string();

        System.out.println(responseBodyWithToken);
    }
}
