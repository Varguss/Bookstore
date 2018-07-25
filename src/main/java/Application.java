import entity.BookstoreEntity;

import java.util.HashMap;
import java.util.Map;

public class Application {
    private static Map<String, BookstoreEntity> stores = new HashMap<>();
    private static BookstoreEntity currentStore;

    public static void main(String[] args) {

    }

    private static void use(String storeName) {
       // TODO: Смена текущего магазина.
    }

    private static void drop(String storeName) {
        // TODO: Удаление текущего магазина.
    }

    private static void createStore(String storeName, String owner) {
        // TODO: Создание нового магазина.
    }

    private static void addBook(String title, String description, String author, String isbn, int printedYear) {
        // TODO: Добавление книги в текущий магазин.
    }

    private static void removeBook() {
        // TODO: Удаление книги из текущего магазина.
    }

    private static void showAll() {
        // TODO: Показать магазины и все их записи.
    }

    private static void show() {
        // TODO: Показать информацию о текущем магазине.
    }

    static {

    }
}