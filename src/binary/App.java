// App.java

package binary;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String nextAction = "add"; // Possible actions: add, search, print, quit (anything else defaults to quit)
        People ppl = new People();

        while (!nextAction.toLowerCase().equals("quit")) {
            switch (nextAction.toLowerCase()) {
            case "add":
                String names, ages;
                System.out.println("Enter names and ages in the form: a, b, c, d");
                System.out.print("Names: ");
                names = in.nextLine() + ", ";
                System.out.print("Ages: ");
                ages = in.nextLine() + ", ";

                int count = 0;
                for (int i = 0; i < names.length(); i++) {
                    if (names.substring(i, i + 1).equals(",")) {
                        count++;
                    }
                }

                try {
                    for (int i = 0; i < count; i++) {
                        String name = names.substring(0, names.indexOf(","));
                        names = names.substring(names.indexOf(",") + 2);
                        int age = Integer.parseInt(ages.substring(0, ages.indexOf(",")));
                        ages = ages.substring(ages.indexOf(",") + 2);
                        ppl.addPerson(new Person(name, age));
                    }
                    ppl.sortByName();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input");
                }
                break;

            case "search":
                System.out.print("Input Name: ");
                String name = in.next();
                System.out.print("Search type: ");
                String search = in.next();
                int i;
                if (search.equals("linear")) {
                    i = ppl.linearSearch(name);
                } else {
                    i = ppl.binarySearch(name);
                }
                System.out.println("This Search took " + ppl.getComparisons() + " comparisons");

                if (i == -1) {
                    System.out.println("There are no persons with the name \"" + name + "\"");
                } else {
                    System.out.println("Name: " + ppl.getPerson(i).getName());
                    System.out.println("Age: " + ppl.getPerson(i).getAge() + "\n");
                    System.out.print("Next Action (edit or delete or quit): ");
                    String updatePerson = in.next();
                    if (updatePerson.equals("edit")) {
                        System.out.print("New Name: ");
                        ppl.getPerson(i).setName(in.next());
                        in.nextLine();
                        System.out.print("New Age: ");
                        ppl.getPerson(i).setAge(in.nextInt());
                    } else if (updatePerson.equals("delete")) {
                        ppl.deletePerson(i);
                    }
                }
                break;

            case "print":
                ppl.sortByName();
                System.out.println(ppl.print());
                break;
            }

            System.out.print("Next Action? (add, search, print, or quit): ");
            nextAction = in.next();
            in.nextLine();
        }

        in.close();
    }
}
