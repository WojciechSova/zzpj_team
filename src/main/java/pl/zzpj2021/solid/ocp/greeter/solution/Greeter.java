package pl.zzpj2021.solid.ocp.greeter.solution;


public class Greeter {
    private Personality personality;

    public Greeter(Personality personality) {
        this.personality = personality;
        }

    public String greet() {
        return this.personality.greet();
    }
}
