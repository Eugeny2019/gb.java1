package java1.dz.dz7.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private WeatherModel weatherModel = new AccuweatherModel();
    private Map<Integer, Period> variants = new HashMap<>();

    public Controller() {
        variants.put(1, Period.NOW);
        variants.put(5, Period.FIVE_DAYS);
        variants.put(2, Period.DB);
        variants.put(3, Period.DB_BY_CITY);
    }

    public void getWeather(String userInput, String selectedCity) throws IOException {
        Integer userIntegerInput = Integer.parseInt(userInput);

        switch (variants.get(userIntegerInput)) {
            case NOW:
                weatherModel.getWeather(selectedCity, Period.NOW);
                break;
            case FIVE_DAYS:
                weatherModel.getWeather(selectedCity, Period.FIVE_DAYS);
                break;
            case DB:
                weatherModel.getSavedToDBWeather();
                break;
            case DB_BY_CITY:
                if (weatherModel.getSavedToDBWeatherByCity(selectedCity).size() == 0) {
                    System.out.println("Нет данных о городе.");
                }
                break;
        }
    }
}
