package weatherservice.openweathermap;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import weatherservice.WeatherData;
import weatherservice.WeatherDataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Reads weather data from OpenWeatherMap and transforms responses into common WeatherData objects
 */
@Service
@CacheConfig(cacheNames = {"weatherData"})
public class OpenWeatherMapDataProvider implements WeatherDataProvider {

  private final OpenWeatherMapClient client;

  private final ObjectReader objectReader =
      new ObjectMapper()
          // we don't have all OpenWeatherMap fields defined, so don't fail
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
          .reader();

  public OpenWeatherMapDataProvider(@Autowired OpenWeatherMapClient client) {
    this.client = Objects.requireNonNull(client);
  }

  @Cacheable
  public WeatherData getWeatherDataForZip(String zipCode) throws WeatherDataProviderException {
    try {
      OpenWeatherMapClientImpl.OpenWeatherClientResponse response =
          client.getWeatherDataForZip(zipCode);
      if (response.isSuccessful() && response.getBody() != null) {
        try (InputStream responseBody = response.getBody()) {
          OpenWeatherMapResponse openWeatherResponse =
              objectReader.forType(OpenWeatherMapResponse.class).readValue(responseBody);
          return new OpenWeatherMapData(openWeatherResponse);
        }
      } else {
        throw new WeatherDataProviderException(
            "Invalid response received: " + response.getCode() + " " + response.getMessage());
      }
    } catch (IOException e) {
      throw new WeatherDataProviderException("Error getting weather for zip " + zipCode, e);
    }
  }
}
