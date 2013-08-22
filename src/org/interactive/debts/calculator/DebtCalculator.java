package org.interactive.debts.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * DebtCalculator class
 *
 * @author bogdan.solga
 *
 * Date: 19.08.2013, time: 16:42
 */
public class DebtCalculator {

    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        try {
            Integer numberOfPersons = readNumber("Numarul de persoane: ").intValue();

            Set<Person> persons = readPersons(numberOfPersons);

            Map<Person, LinkedHashSet<Payment>> payments = readPayments(persons);

            PaymentsHandler handler = new PaymentsHandler(persons);

            addPayments(handler, payments);

            handler.displayPayments();
            handler.displayDebts();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static Set<Person> readPersons(Integer numberOfPersons) throws IOException {
        Set<Person> persons = new HashSet<>(numberOfPersons);

        for (int i = 0; i < numberOfPersons; i++) {
            System.out.print("Numele persoanei numarul " + (i + 1) + ": ");
            persons.add(new Person(BUFFERED_READER.readLine()));
        }

        return persons;
    }

    private static Map<Person, LinkedHashSet<Payment>> readPayments(Set<Person> persons) throws IOException {
        Map<Person, LinkedHashSet<Payment>> payments = new LinkedHashMap<>();

        Number number;
        Double amount;
        String description;
        int paymentId = 0;
        LinkedHashSet<Payment> perPerson;
        for (Person person : persons) {
            number = readNumber("Numarul de plati facute de " + person + ": ");

            for (int i = 0; i < number.intValue(); i++) {
                amount = readNumber("Suma platii numarul " + (i + 1) + " facuta de " + person + ": ").doubleValue();

                System.out.print("Descrierea platii numarul " + (i + 1) + " facuta de " + person + ": ");
                description = BUFFERED_READER.readLine();

                if (payments.containsKey(person)) {
                    perPerson = payments.get(person);
                } else {
                    perPerson = new LinkedHashSet<>(number.intValue());
                }

                perPerson.add(new Payment(++paymentId, amount, description));
                payments.put(person, perPerson);
            }
        }

        return payments;
    }

    private static void addPayments(PaymentsHandler handler, Map<Person, LinkedHashSet<Payment>> payments) {
        LinkedHashSet<Payment> perPerson;
        for (Person person : payments.keySet()) {
            perPerson = payments.get(person);

            for (Payment payment : perPerson) {
                handler.addPayment(person, payment);
            }
        }
    }

    private static Number readNumber(String message) throws IOException {
        boolean correct = false;
        Integer number = 0;
        do {
            System.out.print(message);
            try {
                number = Integer.parseInt(BUFFERED_READER.readLine());
                correct = true;
            } catch (NumberFormatException nfe) {
                System.err.println("Introduceti un numar corect [0-999]");
            }
        } while (!correct);

        return number;
    }
}
