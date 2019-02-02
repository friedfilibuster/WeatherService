package weatherservice.openweathermap;

/** Binding from OpenWeatherMap json to pojo. Not all fields are defined for brevity. */
class OpenWeatherMapResponse {

  private OpenWeatherMapWindData wind;

  OpenWeatherMapWindData getWind() {
    return wind;
  }

  public void setWind(OpenWeatherMapWindData wind) {
    this.wind = wind;
  }

  static class OpenWeatherMapWindData {
    private String speed;
    private String deg;

    String getSpeed() {
      return speed;
    }

    public void setSpeed(String speed) {
      this.speed = speed;
    }

    String getDeg() {
      return deg;
    }

    public void setDeg(String deg) {
      this.deg = deg;
    }
  }
}
