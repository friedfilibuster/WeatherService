package weatherservice.openweathermap;

import org.junit.Test;
import org.mockito.Mockito;
import weatherservice.WeatherData;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OpenWeatherMapDataProviderTest {

  private static final String openWeatherMapResponse =
      "{\n"
          + "    \"coord\": {\n"
          + "        \"lon\": -115.04,\n"
          + "        \"lat\": 36.09\n"
          + "    },\n"
          + "    \"weather\": [\n"
          + "        {\n"
          + "            \"id\": 500,\n"
          + "            \"main\": \"Rain\",\n"
          + "            \"description\": \"light rain\",\n"
          + "            \"icon\": \"10d\"\n"
          + "        }\n"
          + "    ],\n"
          + "    \"base\": \"stations\",\n"
          + "    \"main\": {\n"
          + "        \"temp\": 285.95,\n"
          + "        \"pressure\": 1013,\n"
          + "        \"humidity\": 76,\n"
          + "        \"temp_min\": 285.35,\n"
          + "        \"temp_max\": 286.45\n"
          + "    },\n"
          + "    \"visibility\": 16093,\n"
          + "    \"wind\": {\n"
          + "        \"speed\": 7.2,\n"
          + "        \"deg\": 150\n"
          + "    },\n"
          + "    \"rain\": {\n"
          + "        \"1h\": 0.25\n"
          + "    },\n"
          + "    \"clouds\": {\n"
          + "        \"all\": 90\n"
          + "    },\n"
          + "    \"dt\": 1549131300,\n"
          + "    \"sys\": {\n"
          + "        \"type\": 1,\n"
          + "        \"id\": 3527,\n"
          + "        \"message\": 0.0036,\n"
          + "        \"country\": \"US\",\n"
          + "        \"sunrise\": 1549118375,\n"
          + "        \"sunset\": 1549156125\n"
          + "    },\n"
          + "    \"id\": 420025171,\n"
          + "    \"name\": \"Henderson\",\n"
          + "    \"cod\": 200\n"
          + "}";

  @Test
  public void testGetWindData() throws Exception {
    OpenWeatherMapClient client = Mockito.mock(OpenWeatherMapClient.class);
    Mockito.when(client.getWeatherDataForZip(Mockito.anyString()))
        .thenAnswer(
            invocation -> {
              final byte[] bodyBytes = openWeatherMapResponse.getBytes();
              ByteArrayInputStream body = new ByteArrayInputStream(bodyBytes);
              return new OpenWeatherMapClientImpl.OpenWeatherClientResponse(200, "", body);
            });

    OpenWeatherMapDataProvider dataProvider = new OpenWeatherMapDataProvider(client);
    WeatherData weatherData = dataProvider.getWeatherDataForZip("89074");
    assertNotNull(weatherData);
    assertNotNull(weatherData.getWindData());
    assertEquals("150", weatherData.getWindData().getDirection());
    assertEquals("7.2", weatherData.getWindData().getSpeed());
  }
}
