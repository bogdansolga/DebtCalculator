package org.interactive.debts.calculator;

import java.util.HashSet;
import java.util.Set;

/**
 * DebtCalculator class
 *
 * @author bogdan.solga
 *
 * Date: 19.08.2013, time: 16:42
 */
public class DebtCalculator {

    public static void main(String[] args) {
        try {
            Person bogdan = new Person("Bogdan");
            Person cornelia = new Person("Cornelia");
            Person mihai = new Person("Mihai");

            Set<Person> persons = new HashSet<>(3);
            persons.add(bogdan);
            persons.add(cornelia);
            persons.add(mihai);

            PaymentsHandler handler = new PaymentsHandler(persons);

            handler.addPayment(bogdan, new Payment(1, 30.0, "mancare"));
            handler.addPayment(cornelia, new Payment(2, 60.0, "benzina"));
            handler.addPayment(mihai, new Payment(3, 90.0, "altele"));
            handler.addPayment(bogdan, new Payment(4, 60.0, "diverse"));
            handler.addPayment(cornelia, new Payment(5, 57.0, "mai multe"));

            handler.displayPayments();
            System.out.println();
            handler.displayDebts();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
