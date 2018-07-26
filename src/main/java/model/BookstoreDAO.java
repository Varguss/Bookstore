package model;

import entity.BookstoreEntity;

import java.util.List;

public interface BookstoreDAO {
    void createBookstore(BookstoreEntity bookstore) throws DAOException;
    void deleteBookstore(BookstoreEntity bookstore) throws DAOException;
    void updateBookstore(BookstoreEntity bookstore) throws DAOException;
    BookstoreEntity getBookstoreById(int id) throws DAOException;
    List<BookstoreEntity> getAllBookstores() throws DAOException;
}
