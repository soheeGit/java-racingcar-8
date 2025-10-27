package racingcar.view;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Winners;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

class OutputViewTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private OutputView outputView;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        outputView = new OutputView();
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("실행 결과 헤더를 출력한다")
    void printResultHeader() {
        outputView.printResultHeader();

        String output = outputStream.toString();
        assertThat(output).contains("실행 결과");
    }

    @Test
    @DisplayName("실행 결과 헤더 앞에 빈 줄이 있다")
    void printResultHeaderWithEmptyLine() {
        outputView.printResultHeader();

        String output = outputStream.toString();
        assertThat(output).startsWith("\n");
    }

    @Test
    @DisplayName("자동차 상태를 출력한다")
    void printRoundResult() {
        Cars cars = new Cars(List.of("pobi", "woni"));
        cars.getCars().get(0).move(4);
        cars.getCars().get(0).move(5);
        cars.getCars().get(1).move(4);

        outputView.printRoundResult(cars);

        String output = outputStream.toString();
        assertThat(output).contains("pobi : --");
        assertThat(output).contains("woni : -");
    }

    @Test
    @DisplayName("자동차가 이동하지 않았을 때 빈 문자열을 출력한다")
    void printRoundResultWithNoMovement() {
        Cars cars = new Cars(List.of("pobi"));

        outputView.printRoundResult(cars);

        String output = outputStream.toString();
        assertThat(output).contains("pobi : ");
        assertThat(output).doesNotContain("pobi : -");
    }

    @Test
    @DisplayName("여러 자동차의 상태를 출력한다")
    void printRoundResultWithMultipleCars() {
        Cars cars = new Cars(List.of("pobi", "woni", "jun"));

        outputView.printRoundResult(cars);

        String output = outputStream.toString();
        assertThat(output).contains("pobi");
        assertThat(output).contains("woni");
        assertThat(output).contains("jun");
    }

    @Test
    @DisplayName("라운드 결과 출력 후 빈 줄을 출력한다")
    void printRoundResultWithEmptyLineAtEnd() {
        Cars cars = new Cars(List.of("pobi"));

        outputView.printRoundResult(cars);

        String output = outputStream.toString();
        assertThat(output).endsWith("\n\n");
    }

    @Test
    @DisplayName("자동차 이름과 위치를 구분자로 구분하여 출력한다")
    void printRoundResultWithSeparator() {
        Cars cars = new Cars(List.of("pobi"));
        cars.getCars().get(0).move(4);

        outputView.printRoundResult(cars);

        String output = outputStream.toString();
        assertThat(output).contains(" : ");
    }

    @Test
    @DisplayName("단독 우승자를 출력한다")
    void printSingleWinner() {
        Winners winners = new Winners(List.of("pobi"));

        outputView.printWinners(winners);

        String output = outputStream.toString();
        assertThat(output).contains("최종 우승자 : pobi");
    }

    @Test
    @DisplayName("공동 우승자를 쉼표로 구분하여 출력한다")
    void printMultipleWinners() {
        Winners winners = new Winners(List.of("pobi", "woni"));

        outputView.printWinners(winners);

        String output = outputStream.toString();
        assertThat(output).contains("최종 우승자 : pobi, woni");
    }

    @Test
    @DisplayName("세 명의 공동 우승자를 출력한다")
    void printThreeWinners() {
        Winners winners = new Winners(List.of("pobi", "woni", "jun"));

        outputView.printWinners(winners);

        String output = outputStream.toString();
        assertThat(output).contains("최종 우승자 : pobi, woni, jun");
    }

    @Test
    @DisplayName("우승자 출력 문구가 올바른 형식이다")
    void printWinnersFormat() {
        Winners winners = new Winners(List.of("pobi"));

        outputView.printWinners(winners);

        String output = outputStream.toString();
        assertThat(output).contains("최종 우승자 : ");
        assertThat(output).contains("pobi");
    }

    @Test
    @DisplayName("많은 자동차의 상태를 출력한다")
    void printRoundResultWithManyCars() {
        Cars cars = new Cars(List.of("pobi", "woni", "jun", "brown", "sally"));
        
        for (Car car : cars.getCars()) {
            car.move(4);
        }

        outputView.printRoundResult(cars);

        String output = outputStream.toString();
        assertThat(output).contains("pobi : -");
        assertThat(output).contains("woni : -");
        assertThat(output).contains("jun : -");
        assertThat(output).contains("brown : -");
        assertThat(output).contains("sally : -");
    }

    @Test
    @DisplayName("자동차가 최대로 이동했을 때 위치를 출력한다")
    void printRoundResultWithMaxPosition() {
        Cars cars = new Cars(List.of("pobi"));
        
        for (int i = 0; i < 10; i++) {
            cars.getCars().get(0).move(4);
        }

        outputView.printRoundResult(cars);

        String output = outputStream.toString();
        assertThat(output).contains("pobi : ----------");
    }
}
