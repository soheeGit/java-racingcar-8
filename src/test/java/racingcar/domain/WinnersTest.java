package racingcar.domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class WinnersTest {

    @Test
    @DisplayName("단독 우승자를 문자열로 표시한다")
    void displaySingleWinner() {
        Winners winners = new Winners(List.of("pobi"));
        assertThat(winners.toString()).isEqualTo("pobi");
    }

    @Test
    @DisplayName("공동 우승자를 쉼표로 구분하여 표시한다")
    void displayMultipleWinners() {
        Winners winners = new Winners(List.of("pobi", "woni", "jun"));
        assertThat(winners.toString()).isEqualTo("pobi, woni, jun");
    }

    @Test
    @DisplayName("우승자 목록을 반환한다")
    void getWinnerNames() {
        Winners winners = new Winners(List.of("pobi", "woni"));
        assertThat(winners.names()).containsExactly("pobi", "woni");
    }
}