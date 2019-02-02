package weatherservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/** Provides weather information to a client */
@RestController
public class WeatherControllerV1 implements WeatherController {

  private final WeatherDataProvider weatherDataProvider;

  public WeatherControllerV1(@Autowired WeatherDataProvider weatherDataProvider) {
    this.weatherDataProvider = Objects.requireNonNull(weatherDataProvider);
  }

  /**
   * @param zipCode - The US Zip Code to get wind data for
   * @return An object containing the speed and direction of the wind currently for the given Zip
   *     Code
   * @throws WeatherControllerException Thrown when an invalid response is received from the weather
   *     data provider
   */
  @Override
  @RequestMapping(
      path = "/v1/wind/{zipCode}",
      method = RequestMethod.GET,
      produces = "application/json")
  public WindData windDataForZip(@PathVariable("zipCode") String zipCode)
      throws WeatherControllerException {
    try {
      if (zipCode == null || zipCode.length() == 0) {
        throw new WeatherControllerIllegalArgumentException("zipCode is required");
      }
      if (!zipCode.matches("\\d{5}")) {
        throw new WeatherControllerIllegalArgumentException("Invalid zip code");
      }

      final WeatherData weatherDataForZip = weatherDataProvider.getWeatherDataForZip(zipCode);
      return weatherDataForZip.getWindData();
    } catch (WeatherDataProvider.WeatherDataProviderException e) {
      throw new WeatherControllerException(e);
    }
  }
}
