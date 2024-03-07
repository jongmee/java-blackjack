package blackjack.model.dealer;

import blackjack.model.card.Hand;
import blackjack.model.cardgenerator.CardGenerator;

public class Dealer {
    private static final int ACTION_CONDITION = 17;

    private final Hand hand;

    public Dealer(final CardGenerator cardGenerator) {
        this.hand = new Hand(cardGenerator);
    }

    public void doAction(final CardGenerator cardGenerator) {
        while (hand.calculateCardsTotal() < ACTION_CONDITION) {
            hand.addCard(cardGenerator);
        }
    }

    public int calculateCardsTotal() {
        return hand.calculateCardsTotal();
    }

    public boolean isBurst() {
        return hand.isBurst();
    }

    public Hand getHand() {
        return hand;
    }
}
