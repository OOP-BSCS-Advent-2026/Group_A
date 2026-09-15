import java.util.Scanner;

public class GroupA_BusinessSimulator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("===== KABS SUPERMARKET =====");

        // Each position in these arrays describes the same product: its name, price, and quantity.
        String[] items = {"Sugar", "Salt", "Porridge", "Bread"};
        double[] prices = {555.66, 6666.00, 44444.00, 444.00};
        int[] quantities = new int[items.length];

        displayInventory(items, prices);

        // Collect and validate the quantity for each product before calculating totals.
        for (int i = 0; i < items.length; i++) {
            System.out.print("Enter quantity for " + items[i] + ": ");

            // Keep asking until the user enters a whole number that is zero or greater.
            while (true) {
                if (input.hasNextInt()) {
                    quantities[i] = input.nextInt();
                    if (quantities[i] >= 0) {
                        break;
                    }
                    System.out.print("Quantity cannot be negative. Please enter a valid quantity: ");
                } else {
                    System.out.print("Invalid input. Please enter a whole number: ");
                    input.next();
                }
            }
        }

        System.out.println();
        double total = 0;
        double[] afterDiscount = new double[items.length];
        String[] discountMsg = new String[items.length];

        // Apply each product's discount rule and add the discounted subtotals.
        for (int i = 0; i < items.length; i++) {
            // Calculate and store the final price for this product after any discount.
            afterDiscount[i] = calculateSubtotal(i, prices[i], quantities[i]);

            // Create a message that tells the customer which discount rule was used.
            if (i == 0) {
                if (quantities[i] >= 5) {
                    discountMsg[i] = "(5% discount applied)";
                } else {
                    discountMsg[i] = "(no discount — fewer than 5)";
                }
            } else if (i == 1) {
                discountMsg[i] = "(no discount)";
            } else if (i == 2) {
                if (quantities[i] >= 3) {
                    discountMsg[i] = "(UGX 5000 discount applied)";
                } else {
                    discountMsg[i] = "(no discount — fewer than 3)";
                }
            } else if (i == 3) {
                if (quantities[i] >= 2) {
                    discountMsg[i] = "(10% discount applied)";
                } else {
                    discountMsg[i] = "(no discount — fewer than 2)";
                }
            }

            // Add this product's final price to the running order total.
            total += afterDiscount[i];
        }

        // Send all calculated order details to the receipt-printing method.
        printReceipt(items, quantities, afterDiscount, discountMsg, total);
        input.close();
    }

    public static void displayInventory(String[] items, double[] prices) {
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i] + " - UGX " + formatMoney(prices[i]));
        }
        System.out.println();
    }

    public static double calculateSubtotal(int index, double price, int quantity) {
        // Start with the regular cost before checking whether a discount applies.
        double sub = price * quantity;
        double finalPrice = sub;

        // Discounts are determined by the product index and the purchased quantity.
        if (index == 0 && quantity >= 5) {
            // Sugar receives 5% off when the customer buys at least five units.
            finalPrice = sub - (sub * 0.05);
        } else if (index == 2 && quantity >= 3) {
            // Porridge receives a fixed UGX 5000 discount when at least three are bought.
            finalPrice = sub - 5000;
        } else if (index == 3 && quantity >= 2) {
            // Bread receives 10% off when the customer buys at least two units.
            finalPrice = sub - (sub * 0.10);
        }

        // Salt has no discount, and all products keep the regular price if their threshold is not met.
        return finalPrice;
    }

    public static void printReceipt(String[] items, int[] quantities, double[] afterDiscount,
                                    String[] discountMsg, double total) {
        System.out.println("\n===== RECEIPT =====");

        // Display each item's discounted amount together with its discount status.
        for (int i = 0; i < items.length; i++) {
            // Use the same array index to print the product name, quantity, amount, and message together.
            System.out.println(items[i] + " x " + quantities[i] + " = UGX "
                    + formatMoney(afterDiscount[i]) + "     " + discountMsg[i]);
        }

        // Print the total after every product's discount has been included.
        System.out.println("------------------------------------------");
        System.out.println("TOTAL = UGX " + formatMoney(total));
    }

    public static String formatMoney(double value) {
        // Avoid decimal places for whole amounts while retaining two for fractional values.
        if (value == (long) value) {
            // Whole-number prices are easier to read without an unnecessary .00 suffix.
            return String.format("%d", (long) value);
        } else {
            // Fractional prices are displayed with exactly two decimal places.
            return String.format("%.2f", value);
        }
    }
}