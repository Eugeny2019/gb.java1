package java1.dz.dz7.project;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java1.dz.dz7.project.entity.Weather;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccuweatherModel implements WeatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/349727
    private static final String PROTOKOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "oxL4BsrOv9rNNFlXJwKrIJox86MCvGtn";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LANGUAGE_KEY = "language";
    private static final String LANGUAGE = "en-us";
    private static final String METRIC_KEY = "metric";
    private static final String METRIC = "true";

    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private DataBaseRepository dataBaseRepository = new DataBaseRepository();

    public void getWeather(String selectedCity, Period period) throws IOException {
        String weatherResponse;
        HttpUrl httpUrl;
        Request request;
        JsonNode response;
        String date = "";
        Double temperature = 0.0;
        String aboutDayRemark = "";
        switch (period) {
            case NOW:

                httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter(LANGUAGE_KEY, LANGUAGE)
                        .addQueryParameter(METRIC_KEY, METRIC)
                        .build();

                request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
                weatherResponse = oneDayForecastResponse.body().string();
//                System.out.println(weatherResponse + "/n/n");
                //TODO: сделать человекочитаемый вывод погоды. Выбрать параметры для вывода на свое усмотрение
                //Например: Погода в городе Москва - 5 градусов по цельсию Expect showers late Monday night
                //dataBaseRepository.saveWeatherToDataBase(new Weather()) - тут после парсинга добавляем данные в БД

                response = objectMapper.readTree(weatherResponse);
                Weather weather = null;
                for (JsonNode node : response) {
                    String text = node.findValues("Text").toString();
                    aboutDayRemark = (text.equals("[]") ? aboutDayRemark : text.replaceAll("[\\[\"\\]]", ""));
                    for (JsonNode inNode : node) {
                        String dateResponse = inNode.findValues("Date").toString();
                        if (dateResponse.length() != 0) {
                            date = dateResponse.replaceAll("[\\[\"\\]]", "");
                        }

                        JsonNode t = inNode.findParent("Temperature");
                        if (t != null) {
                            String temperatureMinResponse = t.findParent("Minimum").findValue("Value").toString();
                            String temperatureMaxResponse = t.findParent("Maximum").findValue("Value").toString();
                            if (temperatureMinResponse.length() != 0 && temperatureMaxResponse.length() != 0) {
                                Double tMin = Double.parseDouble(temperatureMinResponse);
                                Double tMax = Double.parseDouble(temperatureMaxResponse);
                                temperature = (tMax + tMin) / 2.0;
                                weather = new Weather(selectedCity, date, temperature);
                            }
                        }
                    }
                }

                if (Objects.nonNull((weather))) {
                    System.out.println("Погода сегодня в городе " + selectedCity + ": Температура " + weather.getTemperature() + " градусов по цельсию. " + aboutDayRemark + ".");
                    try {
                        new DataBaseRepository().saveWeatherToDataBase(weather);
                    } catch (SQLException e) {
                        System.out.println("Ошибка при записи в БД.");
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("Нет данных о погоде");
                }
                break;
            case FIVE_DAYS:
                //TODO*: реализовать вывод погоды на 5 дней
                httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAYS)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter(LANGUAGE_KEY, LANGUAGE)
                        .addQueryParameter(METRIC_KEY, METRIC)
                        .build();

                request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response fiveDaysForecastResponse = okHttpClient.newCall(request).execute();
                weatherResponse = fiveDaysForecastResponse.body().string();
                System.out.println(weatherResponse);
                //TODO: сделать человекочитаемый вывод погоды. Выбрать параметры для вывода на свое усмотрение
                //Например: Погода в городе Москва - 5 градусов по цельсию Expect showers late Monday night
                //dataBaseRepository.saveWeatherToDataBase(new Weather()) - тут после парсинга добавляем данные в БД

                response = objectMapper.readTree(weatherResponse);
                List<Weather> weatherList = new ArrayList<>();
                for (JsonNode node : response) {
                    String text = node.findValues("Text").toString();
                    aboutDayRemark = (text.equals("[]") ? aboutDayRemark : text.replaceAll("[\\[\"\\]]", ""));
                    for (JsonNode inNode : node) {
                        String dateResponse = inNode.findValues("Date").toString();
                        if (dateResponse.length() != 0) {
                            date = dateResponse.replaceAll("[\\[\"\\]]", "");
                        }

                        JsonNode t = inNode.findParent("Temperature");
                        if (t != null) {
                            String temperatureMinResponse = t.findParent("Minimum").findValue("Value").toString();
                            String temperatureMaxResponse = t.findParent("Maximum").findValue("Value").toString();
                            if (temperatureMinResponse.length() != 0 && temperatureMaxResponse.length() != 0) {
                                Double tMin = Double.parseDouble(temperatureMinResponse);
                                Double tMax = Double.parseDouble(temperatureMaxResponse);
                                temperature = (tMax + tMin) / 2.0;
                                weatherList.add(new Weather(selectedCity, date, temperature));
                            }
                        }
                    }
                }


                if (weatherList.size() != 0) {
                    System.out.println("Погода на 5 дней в городе " + selectedCity + ":");
                    for (Weather w : weatherList) {
                        System.out.println("Дата: " + w.getLocalDate() + " Средняя температура: " + w.getTemperature());
                    }
                    System.out.println("Погода сегодня: Температура " + weatherList.get(0).getTemperature() + " градусов по цельсию. " + aboutDayRemark + ".");
                    try {
                        new DataBaseRepository().saveWeatherToDataBase(weatherList);
                    } catch (SQLException e) {
                        System.out.println("Ошибка при записи в БД.");
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("Нет данных о погоде");
                }
                break;
        }
    }

    @Override
    public List<Weather> getSavedToDBWeather() {
        return dataBaseRepository.getSavedToDBWeather();
    }

    private String detectCityKey(String selectCity) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        return cityKey;
    }
}
