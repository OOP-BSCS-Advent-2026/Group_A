public class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double calculateTotal(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        return price * quantity;
    }
}