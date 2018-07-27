package model;

import entity.BookstoreEntity;

import javax.persistence.EntityExistsException;
import java.util.List;

public interface BookstoreDAO extends AutoCloseable {
    void createBookstore(BookstoreEntity bookstore) throws DAOException, EntityExistsException;
    void deleteBookstore(BookstoreEntity bookstore) throws DAOException;
    void updateBookstore(BookstoreEntity bookstore) throws DAOException;
    BookstoreEntity getBookstoreById(int id) throws DAOException, BookstoreIsNotFoundException;
    List<BookstoreEntity> getAllBookstores() throws DAOException;
}
