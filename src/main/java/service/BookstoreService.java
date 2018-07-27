package service;

import entity.BookEntity;
import entity.BookstoreEntity;
import model.BookstoreDAO;
import model.BookstoreIsNotFoundException;
import model.DAOException;
import model.DAOFactory;
import org.apache.log4j.Logger;

import javax.persistence.EntityExistsException;
import java.util.List;

public class BookstoreService {
    private static final Logger logger = Logger.getLogger(BookstoreService.class);
    private BookstoreDAO dao;
    private BookstoreEntity currentBookstore;

    public BookstoreService(DAOFactory.DAOType daoType) {
        dao = DAOFactory.getBookstoreDAO(daoType);
    }

    public void use(int storeId) {
        try {
            System.out.println("### Searching... ###");
            currentBookstore = dao.getBookstoreById(storeId);
            System.out.println("Bookstore has been found. Current bookstore: " + currentBookstore);
            logger.info("Store successfully has been chosen.");
        } catch (BookstoreIsNotFoundException e) {
            System.out.println("Bookstore has not been found.");
            logger.warn("Store wasn't found.");
        }
    }

    public void drop() {
        System.out.println("### Dropping... ###");
        if (currentBookstore != null) {
            dao.deleteBookstore(currentBookstore);
            System.out.println("Bookstore has been successfully dropped.");
            logger.info("Successfully droped.");
        } else {
            System.out.println("Current bookstore isn't chosen.");
        }
    }

    public void createStore(String storeName, String owner) {
        System.out.println("### Creating... ###");
        BookstoreEntity bookstoreEntity = new BookstoreEntity();
        bookstoreEntity.setName(storeName);
        bookstoreEntity.setOwner(owner);

        try {
            dao.createBookstore(bookstoreEntity);
            logger.info("Successfully created.");
            System.out.println("Bookstore has been created.");
        } catch (EntityExistsException e) {
            System.out.println("Bookstore exists.");
        } catch (DAOException e) {
            logger.error("Oops, something is happened. Creation has been failed.");
            System.out.println("Bookstore has not been created.");
        }
    }

    public void updateStore(String storeName, String owner) {
        System.out.println("### Updating... ###");
        if (currentBookstore != null) {
            currentBookstore.setName(storeName);
            currentBookstore.setOwner(owner);
            dao.updateBookstore(currentBookstore);
            logger.info("Current store is successfully updated.");
            System.out.println("Bookstore has been updated.");
        } else {
            System.out.println("Current bookstore isn't chosen.");
        }
    }

    public void addBook(String title, String description, String author, String isbn, int printedYear) {
        System.out.println("### Adding a new book... ###");
        if (currentBookstore != null) {
            BookEntity bookEntity = new BookEntity();
            bookEntity.setTitle(title);
            bookEntity.setDescription(description);
            bookEntity.setAuthor(author);
            bookEntity.setIsbn(isbn);
            bookEntity.setPrintYear(printedYear);

            currentBookstore.addBook(bookEntity);
            dao.updateBookstore(currentBookstore);
            System.out.println("The new book has been added to current bookstore.");
        } else {
            System.out.println("Current bookstore isn't chosen.");
        }
    }

    public void removeBookIfExists(int bookId) {
        System.out.println("### Removing a book... ###");
        if (currentBookstore != null) {
            currentBookstore.removeBook(bookId);
            dao.updateBookstore(currentBookstore);
            logger.info("Operation is done successfully.");
            System.out.println("Operation is done.");
        } else {
            System.out.println("Current bookstore isn't chosen.");
        }
    }

    public void showAll() {
        System.out.println("### Showing bookstores... ###");
        List<BookstoreEntity> bookstores = dao.getAllBookstores();

        if (bookstores.size() == 0)
            System.out.println("You have not added any bookstores yet.");
        else
            bookstores.forEach(System.out::println);
    }

    public void show() {
        System.out.println("### Showing the current bookstore... ###");
        if (currentBookstore != null) {
            System.out.println(currentBookstore);
        } else {
            System.out.println("Current bookstore isn't chosen.");
        }
    }

    public void exit() throws ServiceException {
        try {
            if (currentBookstore != null)
                dao.updateBookstore(currentBookstore);

            dao.close();
        } catch (Exception e) {
            logger.error("DAO closing has been failed.", e);
            throw new ServiceException(e);
        }
    }
}
