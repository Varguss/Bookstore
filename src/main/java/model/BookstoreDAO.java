package model;

import entity.BookstoreEntity;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BookstoreDAO {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("standard");

    public void save(BookstoreEntity bookstore) {

    }

    public void update(BookstoreEntity bookstore) {

    }

    public void delete(int bookstore_id) {

    }

    public List<BookstoreEntity> get() {
        return null;
    }
}
