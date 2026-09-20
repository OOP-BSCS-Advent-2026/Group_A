public class Main {
    public static void main(String[] args) {
        Item[] items = {
            new PercentDiscountItem("Sugar", 555.66, 5, 5),
            new NoDiscountItem("Salt", 6666.00),
            new FlatDiscountItem("Porridge", 44444.00, 3, 5000),
            new PercentDiscountItem("Bread", 444.00, 2, 10)
        };

        int[] quantities = {4, 2, 2, 2};
        double total = 0;

        for (int i = 0; i < items.length; i++) {
            double lineTotal = items[i].calculateTotal(quantities[i]);
            System.out.println(items[i].getName() + " x" + quantities[i]
                    + " = UGX " + lineTotal);
            total += lineTotal;
        }

        System.out.println("TOTAL = UGX " + total);
    }
}