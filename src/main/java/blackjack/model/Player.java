package blackjack.model;

public class Player {
    private static final String INVALID_NAME_LENGTH = "참여자 이름은 한 글자 이상이다";
    private static final int HIT_CONDITION = 21;

    private final String name;
    private final Hand hand;

    public Player(final String name, final NumberGenerator numberGenerator) {
        validateName(name);
        this.name = name;
        this.hand = new Hand(numberGenerator);
    }

    private void validateName(final String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH);
        }
    }

    public boolean canHit() {
        int cardsTotal = hand.calculateCardsTotal();
        return cardsTotal <= 21;
    }
}
