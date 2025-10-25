package racingcar.controller;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import camp.nextstep.edu.missionutils.Console;
import racingcar.domain.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class RacingGameControllerTest {
    
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    private InputView inputView;
    private OutputView outputView;
    private RandomNumberGenerator generator;
    
    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        inputView = new InputView();
        outputView = new OutputView();
        generator = () -> 4;
    }
    
    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        Console.close();
    }
    
    @Test
    @DisplayName("컨트롤러를 생성한다")
    void createController() {
        assertThatCode(() -> new RacingGameController(inputView, outputView, generator))
                .doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("정상적인 입력으로 게임을 실행한다")
    void runGameWithValidInput() {
        String input = "pobi,woni,jun\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        RacingGameController controller = new RacingGameController(inputView, outputView, generator);
        
        assertThatCode(() -> controller.run())
                .doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("게임 실행 결과에 '실행 결과' 문구가 포함된다")
    void runGamePrintsExecutionResult() {
        String input = "pobi,woni\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        RacingGameController controller = new RacingGameController(inputView, outputView, generator);
        controller.run();
        
        String output = outputStream.toString();
        assertThat(output).contains("실행 결과");
    }
    
    @Test
    @DisplayName("게임 실행 결과에 '최종 우승자' 문구가 포함된다")
    void runGamePrintsWinner() {
        String input = "pobi,woni\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        RacingGameController controller = new RacingGameController(inputView, outputView, generator);
        controller.run();
        
        String output = outputStream.toString();
        assertThat(output).contains("최종 우승자");
    }
    
    @Test
    @DisplayName("게임 실행 시 자동차 이름이 출력된다")
    void runGamePrintsCarNames() {
        String input = "pobi,woni,jun\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        RacingGameController controller = new RacingGameController(inputView, outputView, generator);
        controller.run();
        
        String output = outputStream.toString();
        assertThat(output).contains("pobi");
        assertThat(output).contains("woni");
        assertThat(output).contains("jun");
    }
    
    @Test
    @DisplayName("잘못된 자동차 이름 입력 시 예외 발생")
    void throwExceptionWhenInvalidCarName() {
        String input = "pobi,toolongname\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        RacingGameController controller = new RacingGameController(inputView, outputView, generator);
        
        assertThatThrownBy(() -> controller.run())
                .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("잘못된 시도 횟수 입력 시 예외 발생")
    void throwExceptionWhenInvalidAttemptCount() {
        String input = "pobi,woni\nabc\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        RacingGameController controller = new RacingGameController(inputView, outputView, generator);
        
        assertThatThrownBy(() -> controller.run())
                .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("0 이하의 시도 횟수 입력 시 예외 발생")
    void throwExceptionWhenAttemptCountIsZero() {
        String input = "pobi,woni\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        RacingGameController controller = new RacingGameController(inputView, outputView, generator);
        
        assertThatThrownBy(() -> controller.run())
                .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("중복된 자동차 이름 입력 시 예외 발생")
    void throwExceptionWhenDuplicateCarNames() {
        String input = "pobi,woni,pobi\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        RacingGameController controller = new RacingGameController(inputView, outputView, generator);
        
        assertThatThrownBy(() -> controller.run())
                .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("빈 자동차 이름 입력 시 예외 발생")
    void throwExceptionWhenEmptyCarName() {
        String input = "pobi,,woni\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        RacingGameController controller = new RacingGameController(inputView, outputView, generator);
        
        assertThatThrownBy(() -> controller.run())
                .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("단일 자동차로 게임을 실행한다")
    void runGameWithSingleCar() {
        String input = "pobi\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        RacingGameController controller = new RacingGameController(inputView, outputView, generator);
        
        assertThatCode(() -> controller.run())
                .doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("공백이 포함된 자동차 이름을 처리한다")
    void runGameWithSpacesInCarNames() {
        String input = "pobi , woni , jun\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        RacingGameController controller = new RacingGameController(inputView, outputView, generator);
        
        assertThatCode(() -> controller.run())
                .doesNotThrowAnyException();
    }
}
