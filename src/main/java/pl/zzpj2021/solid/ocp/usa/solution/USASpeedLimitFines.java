package pl.zzpj2021.solid.ocp.usa.solution;

public class USASpeedLimitFines {

	public double calculateSpeedLimitFine(State state, int speed) {
		return state.calculateFine(speed);
	}

}
