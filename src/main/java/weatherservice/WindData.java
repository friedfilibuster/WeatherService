package weatherservice;

public class WindData {
  private final String speed;
  private final String direction;

  public WindData(String speed, String direction) {
    this.speed = speed;
    this.direction = direction;
  }

  public String getSpeed() {
    return speed;
  }

  public String getDirection() {
    return direction;
  }
}
