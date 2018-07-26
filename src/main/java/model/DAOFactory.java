package model;

public abstract class DAOFactory {
    public enum DAOType {
        DATABASE
    }

    public static BookstoreDAO getBookstoreDAO(DAOType type) {
        if (type == DAOType.DATABASE)
            return BookstoreDatabaseDAO.getInstance();

        return null;
    }
}
