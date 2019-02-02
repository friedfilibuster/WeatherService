package weatherservice.openweathermap;

import weatherservice.WeatherData;
import weatherservice.WindData;

import java.util.Objects;

/** Transforms an OpenWeatherMapResponse to WeatherData */
class OpenWeatherMapData implements WeatherData {

  private final WindData windData;

  OpenWeatherMapData(OpenWeatherMapResponse openWeatherMapResponse) {
    final OpenWeatherMapResponse.OpenWeatherMapWindData wind =
        Objects.requireNonNull(openWeatherMapResponse).getWind();
    if (wind != null) {
      String windSpeed = wind.getSpeed();
      String windDirection = wind.getDeg();
      this.windData = new WindData(windSpeed, windDirection);
    } else {
      this.windData = new WindData("", "");
    }
  }

  @Override
  public WindData getWindData() {
    return windData;
  }
}
