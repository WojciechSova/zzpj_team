package pl.lodz.p.zzpj.testing.assertj;


public class FellowTestFixture {

    public static Fellow frodo() {
        return FellowBuilder.aFellow().withName("Frodo").withRace(Fellow.Race.HOBBIT).build();
    }

    public static Fellow sam() {
        return FellowBuilder.aFellow().withName("Sam").withRace(Fellow.Race.HOBBIT).build();
    }

    public static Fellow merry() {
        return FellowBuilder.aFellow().withName("Merry").withRace(Fellow.Race.HOBBIT).build();
    }

    public static Fellow pippin() {
        return FellowBuilder.aFellow().withName("Pippin").withRace(Fellow.Race.HOBBIT).build();
    }

    public static Fellow gandalf() {
        return FellowBuilder.aFellow().withName("Gandalf").withRace(Fellow.Race.MAIAR).build();
    }

    public static Fellow legolas() {
        return FellowBuilder.aFellow().withName("Legolas").withRace(Fellow.Race.ELF).build();
    }

    public static Fellow gimli() {
        return FellowBuilder.aFellow().withName("Gimli").withRace(Fellow.Race.DWARF).build();
    }

    public static Fellow aragorn() {
        return FellowBuilder.aFellow().withName("Aragorn").withRace(Fellow.Race.MAN).build();
    }

    public static Fellow boromir() {
        return FellowBuilder.aFellow().withName("Boromir").withRace(Fellow.Race.MAN).build();
    }

    public static Fellow sauron() {
        return FellowBuilder.aFellow().withName("Sauron").withRace(Fellow.Race.AINUR).build();
    }
}
