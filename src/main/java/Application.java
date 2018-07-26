import model.DAOFactory;
import service.BookstoreService;

public class Application {
    private static BookstoreService service = new BookstoreService(DAOFactory.DAOType.DATABASE);

    public static void main(String[] args) {

    }
}