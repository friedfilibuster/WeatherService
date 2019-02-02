package weatherservice.openweathermap;

import java.io.IOException;
import java.io.InputStream;

public interface OpenWeatherMapClient {
  OpenWeatherClientResponse getWeatherDataForZip(String zipCode) throws IOException;

  final class OpenWeatherClientResponse {
    private final int code;
    private final String message;
    private final InputStream body;

    OpenWeatherClientResponse(int code, String message, InputStream body) {
      this.code = code;
      this.message = message;
      this.body = body;
    }

    int getCode() {
      return code;
    }

    boolean isSuccessful() {
      return code >= 200 && code < 300;
    }

    String getMessage() {
      return message;
    }

    InputStream getBody() {
      return body;
    }
  }
}
