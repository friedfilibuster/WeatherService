package weatherservice.openweathermap;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

/** Handles the http requests to OpenWeatherMap */
@Component
public class OpenWeatherMapClientImpl implements OpenWeatherMapClient {

  private final OkHttpClient client = new OkHttpClient.Builder().build();

  @Value("${OpenWeatherMap.ApiKey}")
  private String apiKey;

  @Override
  public OpenWeatherClientResponse getWeatherDataForZip(String zipCode) throws IOException {
    URL requestUrl =
        new DefaultUriBuilderFactory()
            .builder()
            .scheme("https")
            .host("api.openweathermap.org")
            .path("/data/2.5/weather")
            .queryParam("zip", zipCode)
            .queryParam("APPID", apiKey)
            .build()
            .toURL();

    Request request = new Request.Builder().get().url(requestUrl).build();
    Call call = client.newCall(request);
    Response response = call.execute();
    return new OpenWeatherClientResponse(
        response.code(),
        response.message(),
        Optional.ofNullable(response.body()).map(ResponseBody::byteStream).orElse(null));
  }
}
