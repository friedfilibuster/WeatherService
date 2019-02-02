package weatherservice;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WeatherControllerV1Test {

  @Test
  public void testWind() throws Exception {
    WeatherDataProvider weatherDataProvider = Mockito.mock(WeatherDataProvider.class);
    Mockito.when(weatherDataProvider.getWeatherDataForZip(Mockito.anyString()))
        .thenReturn(() -> new WindData("100.2", "123.3"));
    WeatherControllerV1 weatherController = new WeatherControllerV1(weatherDataProvider);

    WindData windData = weatherController.windDataForZip("89074");
    assertNotNull(windData);
    assertEquals("123.3", windData.getDirection());
    assertEquals("100.2", windData.getSpeed());
  }

  @Test(expected = WeatherController.WeatherControllerException.class)
  public void testShortZip() throws Exception {
    WeatherDataProvider weatherDataProvider = Mockito.mock(WeatherDataProvider.class);
    WeatherControllerV1 weatherController = new WeatherControllerV1(weatherDataProvider);

    weatherController.windDataForZip("123");
  }

  @Test(expected = WeatherController.WeatherControllerException.class)
  public void testLongZip() throws Exception {
    WeatherDataProvider weatherDataProvider = Mockito.mock(WeatherDataProvider.class);
    WeatherControllerV1 weatherController = new WeatherControllerV1(weatherDataProvider);

    weatherController.windDataForZip("890745");
  }

  @Test(expected = WeatherController.WeatherControllerException.class)
  public void testNullZip() throws Exception {
    WeatherDataProvider weatherDataProvider = Mockito.mock(WeatherDataProvider.class);
    WeatherControllerV1 weatherController = new WeatherControllerV1(weatherDataProvider);

    weatherController.windDataForZip(null);
  }

  @Test(expected = WeatherController.WeatherControllerException.class)
  public void testExceptionFromProvider() throws Exception {
    WeatherDataProvider weatherDataProvider = Mockito.mock(WeatherDataProvider.class);
    Mockito.when(weatherDataProvider.getWeatherDataForZip(Mockito.anyString()))
        .thenThrow(WeatherDataProvider.WeatherDataProviderException.class);
    WeatherControllerV1 weatherController = new WeatherControllerV1(weatherDataProvider);
    weatherController.windDataForZip("89074");
  }
}
