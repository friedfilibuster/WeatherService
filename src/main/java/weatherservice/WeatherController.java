package weatherservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface WeatherController {
  WindData windDataForZip(String zipCode) throws WeatherControllerException;

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  class WeatherControllerIllegalArgumentException extends WeatherControllerException {
    WeatherControllerIllegalArgumentException(Exception cause) {
      super(cause);
    }

    WeatherControllerIllegalArgumentException(String message) {
      super(message);
    }
  }

  class WeatherControllerException extends Exception {
    WeatherControllerException(Exception cause) {
      super(cause);
    }

    WeatherControllerException(String message) {
      super(message);
    }
  }
}
