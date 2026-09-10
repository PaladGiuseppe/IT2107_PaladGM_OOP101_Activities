import java.util.Scanner;

public class Canteen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menu items
        String[] itemNames = {"Burger", "Pizza", "Pasta", "Sandwich", "Milk Tea",
                              "Mash Potato", "Ilocos Empanada", "Malunggay Pandesal"};
        double[] itemPrices = {80.00, 120.00, 100.00, 70.00, 90.00,
                               60.00, 85.00, 45.00};

        // Accumulators for summary
        int totalQuantity = 0;
        double totalBeforeDiscount = 0.0;
        double totalDiscount = 0.0;

        boolean ordering = true;

        System.out.println("===== GIUSEPPE CANTEEN =====");

        while (ordering) {
            // Display menu
            System.out.println("\n=====  M E N U  =====");
            for (int i = 0; i < itemNames.length; i++) {
                System.out.printf("%d. %-20s - PHP %.2f%n", (i + 1), itemNames[i], itemPrices[i]);
            }

            // Get item number
            System.out.print("Enter item number: ");
            int itemNumber = scanner.nextInt();

            // Get quantity
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();

            // Check if student
            System.out.print("Are you a student? (Y/N): ");
            char studentStatus = scanner.next().charAt(0);
            boolean isStudent = (studentStatus == 'Y' || studentStatus == 'y');

            // Validate order
            boolean validItem = (itemNumber >= 1 && itemNumber <= itemNames.length);
            boolean validQuantity = (quantity >= 1 && quantity <= 10);

            if (!validItem || !validQuantity) {
                System.out.println("\nInvalid order! Please enter a valid item and quantity.");
                // Skip processing for this order and continue to next attempt
                continue;
            }

            // Process valid order
            double price = itemPrices[itemNumber - 1];
            double subtotal = price * quantity;
            double discount = 0.0;

            // Calculate discount based on status and amount
            if (isStudent && subtotal >= 500.0) {
                discount = subtotal * 0.15; // 15% discount
            } else if (isStudent) {
                discount = subtotal * 0.10; // 10% discount
            } else if (subtotal >= 500.0) {
                discount = subtotal * 0.05; // 5% discount
            }

            double orderTotal = subtotal - discount;

            // Update accumulators
            totalQuantity += quantity;
            totalBeforeDiscount += subtotal;
            totalDiscount += discount;

            // Display order summary for this item
            System.out.printf("%nSubtotal: PHP %.2f%n", subtotal);
            System.out.printf("Discount: PHP %.2f%n", discount);
            System.out.printf("Order total: PHP %.2f%n", orderTotal);

            // Ask if customer wants to order again
            System.out.print("\nDo you want to order again? (Y/N): ");
            char again = scanner.next().charAt(0);
            if (again == 'N' || again == 'n') {
                ordering = false;
            }
        }

        // Display final summary
        double finalAmount = totalBeforeDiscount - totalDiscount;
        System.out.println("\n==== ORDER SUMMARY ====");
        System.out.printf("Total items: %d%n", totalQuantity);
        System.out.printf("Total before discount: PHP %.2f%n", totalBeforeDiscount);
        System.out.printf("Total discount: PHP %.2f%n", totalDiscount);
        System.out.printf("Final amount: PHP %.2f%n", finalAmount);
        System.out.println("Thank you for ordering at Giuseppe Canteen!");

        scanner.close();
    }
}