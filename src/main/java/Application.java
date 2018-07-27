import model.DAOFactory;
import org.apache.log4j.Logger;
import service.BookstoreService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Application {
    private static final Logger logger = Logger.getLogger(Application.class);
    private static BookstoreService service = new BookstoreService(DAOFactory.DAOType.DATABASE);

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        help();

        String operation[] = parse(reader.readLine());

        while (!operation[0].equalsIgnoreCase("-exit")) {
            logger.info("Input: " + Arrays.toString(operation));
            try {
                switch (operation[0].toLowerCase()) {
                    case "-use": {
                        if (operation.length != 2)
                            throw new WrongNumberOfArgumentsException();

                        try {
                            int storeId = Integer.parseInt(operation[1]);
                            service.use(storeId);
                        } catch (NumberFormatException e) {
                            System.out.println("Cannot parse " + operation[1] + " to number.");
                        }
                    }
                    break;

                    case "-drop": {
                        if (operation.length != 1)
                            throw new WrongNumberOfArgumentsException();

                        service.drop();
                    }
                    break;

                    case "-createstore": {
                        if (operation.length != 3)
                            throw new WrongNumberOfArgumentsException();

                        service.createStore(operation[1], operation[2]);
                    }
                    break;

                    case "-addbook": {
                        if (operation.length != 6)
                            throw new WrongNumberOfArgumentsException();

                        try {
                            int printedYear = Integer.parseInt(operation[5]);
                            service.addBook(operation[1], operation[2], operation[3], operation[4], printedYear);
                        } catch (NumberFormatException e) {
                            System.out.println("Cannot parse " + operation[5] + " to number.");
                        }
                    }
                    break;

                    case "-removebook": {
                        if (operation.length != 2)
                            throw new WrongNumberOfArgumentsException();

                        try {
                            int bookId = Integer.parseInt(operation[1]);
                            service.removeBookIfExists(bookId);
                        } catch (NumberFormatException e) {
                            System.out.println("Cannot parse " + operation[1] + " to number.");
                        }
                    }
                    break;

                    case "-show": {
                        if (operation.length != 1)
                            throw new WrongNumberOfArgumentsException();

                        service.show();
                    }
                    break;

                    case "-showall": {
                        if (operation.length != 1)
                            throw new WrongNumberOfArgumentsException();

                        service.showAll();
                    }
                    break;

                    case "-help": {
                        if (operation.length != 1)
                            throw new WrongNumberOfArgumentsException();

                        help();
                    }
                    break;

                    default: {
                        System.out.println("Command is not found. Use -help to look at commands' list.");
                    }
                }
            } catch (WrongNumberOfArgumentsException e) {
                System.out.println("Wrong count of arguments. Please, don't add any extra arguments to commands.");
            }

            System.out.println("#####################################################################");
            operation = parse(reader.readLine());
        }

        onClosing();
        logger.info("Application successfully closed.");
    }

    private static void help() {
        System.out.println("#####################################################################");
        System.out.println("                    ### COMMANDS LIST ###                   ");
        System.out.println("-use bookstoreId - use this command to choose bookstore you need in.");
        System.out.println("-drop - use this command to delete current bookstore at all.");
        System.out.println("-createStore storeName storeOwner - use this command to create new bookstore.");
        System.out.println("-addBook title description author isbn printedYear - use this command to add a book to the current bookstore.");
        System.out.println("-removeBook id - use this command to remove a book from the current bookstore (only current).");
        System.out.println("-show - use this command to show the all information about current bookstore.");
        System.out.println("-showAll - use this command to show the all information about all bookstores.");
        System.out.println("-exit - close the application.");
        System.out.println("-help - show this instruction again.");
        System.out.println("\nTip: use \"text\" syntax to input an one argument with escapes.");
        System.out.println("#####################################################################\n\n");
    }

    private static void onClosing() {
        System.out.println("\n\nThank you for using this application! Goodbye!");
    }

    private static String[] parse(String operation) {
        List<String> parts = new ArrayList<>(Arrays.asList(operation.split(" ")));

        List<String> accumulator = new ArrayList<>(Collections.singletonList(parts.get(0)));

        for (int i = 1; i < parts.size(); i++) {
            if (parts.get(i).startsWith("\"")) {
                StringJoiner joiner = new StringJoiner(" ");

                for (int k = i + 1; k < parts.size(); k++) {
                    if (parts.get(k).endsWith("\"")) {
                        joiner.add(parts.get(i).substring(1));

                        for (int j = i + 1; j < k; j++)
                            joiner.add(parts.get(j));

                        joiner.add(parts.get(k).substring(0, parts.get(k).length() - 1));

                        accumulator.add(joiner.toString());

                        i = k;
                        break;
                    }
                }
            } else {
                accumulator.add(parts.get(i));
            }
        }

        return accumulator.toArray(new String[0]);
    }
}