package domain.model;

import domain.model.ladder.Height;
import domain.model.ladder.Ladder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LadderTest {
    @Test
    @DisplayName("출발점으로부터 사다리를 타고 도착점을 반환한다.")
    void findConsequenceTest() {
        Ladder ladder = new Ladder(new Height("2"), 3, () -> true);

        assertThat(ladder.goToConsequence(0, 0)).isEqualTo(0);
        assertThat(ladder.goToConsequence(1, 0)).isEqualTo(1);
        assertThat(ladder.goToConsequence(2, 0)).isEqualTo(2);
    }
}
