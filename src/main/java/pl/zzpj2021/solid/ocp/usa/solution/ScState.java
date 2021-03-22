package pl.zzpj2021.solid.ocp.usa.solution;

public class ScState implements State {
    private final static int MAX_SPEED = 0;

    @Override
    public int calculateFine(int speed) {
        int fine = 0;
        if (speed > MAX_SPEED) {
            // calculate...
        }
        return fine;
    }
}
