package blackjack.model.referee;

import blackjack.model.card.Score;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.view.dto.PlayerMatchResult;

import java.util.List;

public class Referee {
    private final Players players;
    private final Dealer dealer;

    public Referee(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public List<PlayerMatchResult> determinePlayersMatchResult() {
        return players.getPlayers().stream()
                .map(this::determinePlayerResult)
                .toList();
    }

    private PlayerMatchResult determinePlayerResult(final Player player) {
        return new PlayerMatchResult(player.getName(), determineMatchResult(player));
    }

    private MatchResult determineMatchResult(final Player player) {
        Score playerTotalScore = player.calculateCardsTotalScore();
        Score dealerTotalScore = dealer.calculateCardsTotalScore();

        if (dealer.isBlackJack() && player.isBlackJack()) {
            return MatchResult.TIE;
        }
        if (dealer.isBust() || playerTotalScore.equalToOrGreaterThan(dealerTotalScore)) {
            return MatchResult.WIN;
        }
        return MatchResult.LOSE;
    }
}
