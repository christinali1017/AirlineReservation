/**
 * Transaction type Enum.
 *
 * Transaction type:
 * <ul>
 * <li>BookPassenger</li>
 * <li>ChangePrice</li>
 * <li>CancelPassenger</li>
 * </ul>
 */
public enum TransactionTypeEnum {
    BOOK_PASSENGER("BookPassenger"),
    CHANGE_PRICE("ChangePrice"),
    CANCEL_PASSENGER("CancelPassenger");

    /**
     * Transaction type.
     */
    private final String transactionType;

    /**
     * Constructor.
     * @param transactionType Transaction type.
     */
    private TransactionTypeEnum(final String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Get transaction type.
     * @return transaction type.
     */
    public String getTransactionType() {
        return transactionType;
    }
}
