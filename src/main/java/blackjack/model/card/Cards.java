package blackjack.model.card;

import blackjack.model.cardgenerator.CardGenerator;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Cards {
    private static final int MAX_CARDS_TOTAL = 21;
    private static final int ACE_ADJUSTMENT = 10;

    private final List<Card> cards;

    Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Cards(final CardGenerator cardGenerator) {
        this.cards = new ArrayList<>(deal(cardGenerator));
    }

    private List<Card> deal(final CardGenerator cardGenerator) {
        return List.of(cardGenerator.pick(), cardGenerator.pick());
    }

    public void addCard(final CardGenerator cardGenerator) {
        cards.add(cardGenerator.pick());
    }

    public int calculateCardsTotalScore() {
        int total = cards.stream()
                .map(Card::getDenomination)
                .mapToInt(Denomination::getScore)
                .sum();

        if (hasAce() && canBeAdjusted(total)) {
            return adjustTotalForAce(total);
        }
        return total;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.getDenomination().isAce());
    }

    private boolean canBeAdjusted(int total) {
        return total + ACE_ADJUSTMENT <= MAX_CARDS_TOTAL;
    }

    private int adjustTotalForAce(int total) {
        return total + ACE_ADJUSTMENT;
    }

    public boolean isBlackJack() {
        return calculateCardsTotalScore() == MAX_CARDS_TOTAL;
    }

    public boolean isBust() {
        return calculateCardsTotalScore() > MAX_CARDS_TOTAL;
    }

    public int getSize() {
        return cards.size();
    }

    public Card get(int index) {
        return cards.get(index);
    }

    public List<Card> getCards() {
        return unmodifiableList(cards);
    }
}
