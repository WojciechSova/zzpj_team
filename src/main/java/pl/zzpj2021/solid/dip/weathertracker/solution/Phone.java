package pl.zzpj2021.solid.dip.weathertracker.solution;

public class Phone implements Alerter {
    public String generateWeatherAlert(String weatherConditions) {
        String alert = "It is " + weatherConditions;
        return alert;
    }
}
