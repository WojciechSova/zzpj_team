package pl.lodz.p.zzpj.testing.assertj;

public class FellowBuilder {
    private String name;
    private Fellow.Race race;

    private FellowBuilder() {
    }

    public static FellowBuilder aFellow() {
        return new FellowBuilder();
    }

    public FellowBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public FellowBuilder withRace(Fellow.Race race) {
        this.race = race;
        return this;
    }

    public Fellow build() {
        Fellow fellow = new Fellow(name, race);
        return fellow;
    }
}
