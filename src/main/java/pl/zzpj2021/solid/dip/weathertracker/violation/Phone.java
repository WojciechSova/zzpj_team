package pl.zzpj2021.solid.dip.weathertracker.violation;

public class Phone {
    public String generateWeatherAlert(String weatherConditions) {
        String alert = "It is " + weatherConditions;
        return alert;
    }
}
