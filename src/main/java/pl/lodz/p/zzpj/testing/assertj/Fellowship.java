package pl.lodz.p.zzpj.testing.assertj;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Fellowship implements Iterable<Fellow> {

    private final List<Fellow> fellows;

    public Fellowship(Fellow... fellows) {
        this.fellows = Arrays.asList(fellows);
    }

    @Override
    public Iterator<Fellow> iterator() {
        return fellows.iterator();
    }

    public Fellow get(int index) {
        return fellows.get(index);
    }

    public List<Fellow> hobbits() {
        return filterByRace(Fellow.Race.HOBBIT);
    }

    public List<Fellow> men() {
        return filterByRace(Fellow.Race.MAN);
    }

    public List<Fellow> elfs() {
        return filterByRace(Fellow.Race.ELF);
    }

    private List<Fellow> filterByRace(Fellow.Race race) {
        return fellows.stream()
                .filter(f -> f.getRace() == race)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
