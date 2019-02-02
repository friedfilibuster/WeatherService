package weatherservice;

public interface WeatherDataProvider {
  WeatherData getWeatherDataForZip(String zipCode) throws WeatherDataProviderException;

  class WeatherDataProviderException extends Exception {
    public WeatherDataProviderException(String message, Exception cause) {
      super(message, cause);
    }

    public WeatherDataProviderException(Exception cause) {
      super(cause);
    }

    public WeatherDataProviderException(String message) {
      super(message);
    }
  }
}
