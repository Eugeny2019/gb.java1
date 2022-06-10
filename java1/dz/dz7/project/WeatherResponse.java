package java1.dz.dz7.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java1.dz.dz7.project.entity.Weather;
import okhttp3.Response;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeatherResponse {
    private String weatherResponse;
    private String selectedCity;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public  WeatherResponse(String selectedCity, String weatherResponse) {
        this.selectedCity = selectedCity;
        this.weatherResponse = weatherResponse;
    }

    public void printWeatherResponse() throws IOException {
        JsonNode response = objectMapper.readTree(weatherResponse);
        String aboutDayRemark = "";
        String date = "";
        Double temperature = 0.0;
        List<Weather> weatherList = new ArrayList<>();
        for (JsonNode node : response) {
            String text = node.findValues("Text").toString();
            aboutDayRemark = (text.equals("[]") ? aboutDayRemark : text.replaceAll("[\\[\"\\]]", ""));
            for (JsonNode inNode : node) {
                String dateResponse = inNode.findValues("Date").toString();
                if (dateResponse.length() > 10) {
                    date = dateResponse.replaceAll("[\\[\"\\]]", "").substring(0, 10);
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
            for (Weather w : weatherList) {
                System.out.println("В городе " + selectedCity + " на дату " + w.getLocalDate() + " ожидается " + aboutDayRemark + ", температура " + w.getTemperature() + ".");

            }
            //System.out.println("Погода сегодня: Температура " + weatherList.get(0).getTemperature() + " градусов по цельсию. " + aboutDayRemark + ".");
            //В городе CITY на дату DATE ожидается WEATHER_TEXT, температура - TEMPERATURE |
            //
            //где CITY, DATE, WEATHER_TEXT и TEMPERATURE - уникальные значения для каждого дня.
            try {
                new DataBaseRepository().saveWeatherToDataBase(weatherList);
            } catch (SQLException e) {
                System.out.println("Ошибка при записи в БД.");
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Нет данных о погоде");
        }
    }
}
