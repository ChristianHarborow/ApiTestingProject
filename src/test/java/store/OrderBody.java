package store;

public record OrderBody(
        int id, int petId, int quantity, String shipDate, String status, boolean complete
) {
    public OrderBody(int id, int petId, int quantity, String shipDate, String status) {
        this(id, petId, quantity, shipDate, status, true);
    }

    public OrderBody(int id, int petId, int quantity, String shipDate) {
        this(id, petId, quantity, shipDate, "approved");
    }
}
