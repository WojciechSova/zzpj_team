package pl.lodz.p.zzpj.testing.assertj;

import org.assertj.core.groups.Tuple;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static pl.lodz.p.zzpj.testing.assertj.FellowTestFixture.*;

public class FellowshipAssertionTest {

    private Fellowship fellowship = formTheFellowshipOfTheRing();

    @Test
    public void frodoShouldBeIn() {
        assertThat(fellowship).contains(frodo());
    }

    @Test
    public void sauronShouldNotBeIn() {
        assertThat(fellowship).doesNotContain(sauron());
    }

    @Test
    public void shouldFrodoNameBeCorrectAndNotBeASauronAndBeInFellowship() {
        assertThat(fellowship).extracting("name").contains("Frodo").doesNotContain("Sauron");
    }

    @Test
    public void aragornShouldBeBeforeBoromir() {
        assertThat(fellowship).containsSequence(aragorn(), boromir());
    }

    @Test
    public void shouldNotContainDuplicatedFellows() {
        assertThat(fellowship).doesNotHaveDuplicates();
    }

    @Test
    public void shouldContainOnlyFellowsWithExpectedNamesInProperOrder() {
        assertThat(fellowship).containsSequence(frodo(), sam(), merry(), pippin(), gandalf(), legolas(), gimli(), aragorn(), boromir());
    }

    @Test
    public void shouldContainNineFellowsButNoneGiant() {
        assertThat(fellowship).hasSize(9).extracting("race").doesNotContain(Fellow.Race.GIANT);
    }

    @Test
    public void shouldContainTupleForBoromirSamAndLegolas() {
        assertThat(fellowship).extracting(Fellow::getName, Fellow::getRace)
                .contains(
                        tuple( "Boromir", Fellow.Race.MAN),
                        tuple("Sam", Fellow.Race.HOBBIT),
                        tuple("Legolas", Fellow.Race.ELF));
    }


    @Test
    public void shouldContainsFourHobbits() {
        assertThat(fellowship).filteredOn(e -> e.getRace() == Fellow.Race.HOBBIT).hasSize(4);
    }


    private Fellowship formTheFellowshipOfTheRing() {
        return new Fellowship(
                frodo(),
                FellowTestFixture.sam(),
                FellowTestFixture.merry(),
                FellowTestFixture.pippin(),
                FellowTestFixture.gandalf(),
                FellowTestFixture.legolas(),
                FellowTestFixture.gimli(),
                aragorn(),
                FellowTestFixture.boromir());
    }
}
