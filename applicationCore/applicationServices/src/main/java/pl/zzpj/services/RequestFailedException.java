package pl.zzpj.services;

public class RequestFailedException extends Exception {

    public RequestFailedException(String message) {
        super(message);
    }
}
