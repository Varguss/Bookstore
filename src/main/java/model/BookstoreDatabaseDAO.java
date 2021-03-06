package model;

import entity.BookstoreEntity;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.List;

public class BookstoreDatabaseDAO implements BookstoreDAO, AutoCloseable {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("standard");
    private static BookstoreDatabaseDAO dao;
    private static final Logger logger = Logger.getLogger(BookstoreDatabaseDAO.class);

    @Override
    public void createBookstore(BookstoreEntity bookstore) throws DAOException, EntityExistsException {
        try {
            logger.info("Saving " + bookstore + " in database...");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            logger.debug("Transaction has been opened.");

            entityManager.persist(bookstore);

            entityManager.getTransaction().commit();
            logger.debug("Transaction has been commited.");

            logger.info(bookstore + " saved.");
            entityManager.close();
        } catch (EntityExistsException e) {
            logger.info("Bookstore is already created.", e);
            throw e;
        } catch (Exception e) {
            logger.error("Creating " + bookstore + " has been failed.", e);
            throw new DAOException(e);
        }
    }

    @Override
    public BookstoreEntity getBookstoreById(int id) throws DAOException, BookstoreIsNotFoundException {
        try {
            logger.info("Searching for bookstore with id " + id + "...");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            TypedQuery<BookstoreEntity> query = entityManager.createQuery("SELECT bookstore FROM BookstoreEntity bookstore LEFT JOIN FETCH bookstore.books WHERE bookstore.id = " + id, BookstoreEntity.class);

            BookstoreEntity bookstoreEntity;
            try {
                bookstoreEntity = query.getSingleResult();
            } catch (NoResultException e) {
                throw new BookstoreIsNotFoundException(e);
            }

            entityManager.getTransaction().commit();

            logger.info(bookstoreEntity == null ? "Bookstore with id " + id + " hasn't been found." : "Bookstore with id " + id + " has been found.");

            entityManager.close();

            return bookstoreEntity;
        } catch (BookstoreIsNotFoundException e) {
            logger.info("Searching for bookstore with id " + id + " has been failed. Reason: dao doesn't contain bookstore with that id.", e);
            throw e;
        } catch (Exception e) {
            logger.error("Searching for bookstore with id " + id + " has been failed.", e);
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteBookstore(BookstoreEntity bookstore) throws DAOException {
        try {
            logger.info("Deleting " + bookstore + " from database...");

            EntityManager entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            logger.debug("Transaction has been opened.");

            entityManager.remove(entityManager.merge(bookstore));

            entityManager.getTransaction().commit();
            logger.debug("Transaction has been commited.");

            logger.info("Deleting " + bookstore + " is successful!");
            entityManager.close();
        } catch (Exception e) {
            logger.error("Deleting " + bookstore + " has been failed.", e);
            throw new DAOException(e);
        }
    }

    @Override
    public void updateBookstore(BookstoreEntity bookstore) throws DAOException {
        try {
            logger.info("Updating " + bookstore + "...");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            logger.debug("Transaction has been opened.");

            entityManager.merge(bookstore);

            entityManager.getTransaction().commit();
            logger.debug("Transaction has been commited.");

            logger.info("Updating " + bookstore + " is successful!");
            entityManager.close();
        } catch (Exception e) {
            logger.error("Updating " + bookstore + " has been failed.");
            throw new DAOException(e);
        }
    }

    @Override
    public List<BookstoreEntity> getAllBookstores() throws DAOException {
        try {
            logger.info("Retrieving bookstores...");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            TypedQuery<BookstoreEntity> query = entityManager.createQuery("SELECT bookstore FROM BookstoreEntity bookstore LEFT JOIN FETCH bookstore.books", BookstoreEntity.class);

            entityManager.getTransaction().begin();
            logger.debug("Transaction has been opened.");

            List<BookstoreEntity> result = query.getResultList();

            entityManager.getTransaction().commit();
            logger.debug("Transaction has been commited.");

            logger.info("Bookstores have been retrieved.");

            entityManager.close();
            return result;
        } catch (Exception e) {
            logger.error("Retrieving bookstores has been failed.", e);
            throw new DAOException(e);
        }
    }


    static BookstoreDAO getInstance() {
        if (dao == null) {
            dao = new BookstoreDatabaseDAO();
        }

        return dao;
    }

    private BookstoreDatabaseDAO() {

    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
