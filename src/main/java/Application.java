import entity.BookEntity;
import entity.BookstoreEntity;

import javax.persistence.*;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("standard");
        EntityManager manager = factory.createEntityManager();

        BookstoreEntity bookstoreEntity = new BookstoreEntity();
        BookEntity book1 = new BookEntity(2000, "Hello!", "It's really nice book!", "Michael Bernard", "isbn_example", false, bookstoreEntity);
        BookEntity book2 = new BookEntity(1996, "Clean Code!", "It's really nice book!", "Michael Bernard", "isbn_example", false, bookstoreEntity);
        BookEntity book3 = new BookEntity(2003, "Java For Professionals", "It's really nice book!", "Michael Bernard", "isbn_example", false, bookstoreEntity);

        bookstoreEntity.setName("Store #1");
        bookstoreEntity.setOwner("Anton");

        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();

        manager.persist(book1);
        manager.persist(book2);
        manager.persist(book3);
        manager.persist(bookstoreEntity);

        transaction.commit();

        TypedQuery<BookEntity> query = manager.createQuery("select book from BookEntity book WHERE title = 'Hello!'", BookEntity.class);
        query.getResultList().forEach(System.out::println);
        factory.close();
        manager.close();
    }
}