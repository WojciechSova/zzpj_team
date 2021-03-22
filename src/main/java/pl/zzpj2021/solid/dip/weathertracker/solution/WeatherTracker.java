package pl.zzpj2021.solid.dip.weathertracker.solution;

public class WeatherTracker {

    private Alerter alerter;

    public WeatherTracker(Alerter alerter) {
        this.alerter = alerter;
    }

    public void setCurrentConditions(String weatherDescription) {
        String alert = alerter.generateWeatherAlert(weatherDescription);
        System.out.print(alert);
    }
}
