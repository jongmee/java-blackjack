package blackjack.model.result;

import blackjack.view.dto.PlayerEarning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BettingBoardTest {
    @ParameterizedTest
    @MethodSource("provideMatchResultAndExpectedEarning")
    @DisplayName("플레이어의 게임 결과로 수익을 구한다")
    void determineEarningTest(MatchResult matchResult, int expectedEarning) {
        // given
        String playerName = "mia";
        Map<String, BettingMoney> bettingMoneys = Map.of(playerName, new BettingMoney(1000));
        BettingBoard bettingBoard = new BettingBoard(bettingMoneys);

        // when
        PlayerEarning playerEarning = bettingBoard.determineEarning(playerName, matchResult);

        // then
        assertThat(playerEarning.earning()).isEqualTo(expectedEarning);
    }

    private static Stream<Arguments> provideMatchResultAndExpectedEarning() {
        return Stream.of(
                Arguments.of(MatchResult.WIN, 1000),
                Arguments.of(MatchResult.LOSE, -1000),
                Arguments.of(MatchResult.PUSH, 0),
                Arguments.of(MatchResult.BLACKJACK, 1500)
        );
    }
}
