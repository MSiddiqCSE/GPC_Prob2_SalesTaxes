package sales.taxes;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        int quantity, index;
        float price = 0.0f, totalPrice = 0.0f;
        float tax = 0.0f, salesTaxes = 0.0f;
        String description;

        boolean excluded;
        String[] excludedItems = {"chocolate", "book", "pill", "tablet"};

        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);

        String fileName = "INPUT.txt";
        try (Scanner scan = new Scanner(new File(fileName))) {

            while (scan.hasNext()) {

                excluded = false;
                description = scan.nextLine();

                index = description.indexOf(' ');
                quantity = Integer.parseInt(description.substring(0, index));

                index = description.lastIndexOf(' ');
                price = Float.parseFloat(description.substring(index));

                index = description.lastIndexOf(" at ");
                description = description.substring(0, index);

                price = price * quantity;
                tax = 0.0f;

                for(String s: excludedItems) {
                    if(description.contains(s))
                        excluded = true;
                }

                if(!excluded) {
                    tax += price * 0.10f;
                }

                if(description.contains(" imported ")) {
                    tax += price * 0.05f;
                }

                price = (price + tax);
                System.out.println( description +": " + df.format(price) );

                salesTaxes += tax;
                totalPrice += price;
            }
        }

        System.out.println("Sales Taxes: " + df.format(salesTaxes));
        System.out.println("Total: " + df.format(totalPrice));
    }
}
