package service;

import entity.BookstoreEntity;
import model.BookstoreDAO;
import model.DAOFactory;

public class BookstoreService {
    private BookstoreDAO dao;
    private BookstoreEntity currentBookstore;

    public BookstoreService(DAOFactory.DAOType daoType) {
        dao = DAOFactory.getBookstoreDAO(daoType);
    }

    public void use(int storeId) {

    }

    public void drop() {

    }

    public void createStore(String storeName, String owner) {

    }

    public void updateStore() {

    }

    public static void addBook(String title, String description, String author, String isbn, int printedYear) {

    }

    public static void removeBook(int bookId) {

    }

    public static void showAll() {
        // TODO: Показать магазины и все их записи.
    }

    public static void show() {
        // TODO: Показать информацию о текущем магазине.
    }
}
