package model;

public class Environment {

    private Weather weather;
    private House house;

    /**
     * Constructors
     */
    public Environment() {
        this.weather = new Weather();
        this.house = new House();
    }

    public Environment(Weather weather, House house) {
        this.weather = weather;
        this.house = house;
    }

    public void onNewDay(int dayNumber) {
        this.house.onNewDay();
        this.weather.update(dayNumber);
    }

    public Weather getWeather() {
        return weather;
    }

    public House getHouse() {
        return house;
    }
}
