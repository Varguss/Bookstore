package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "book_stores")
public class BookstoreEntity implements Serializable {
    private int id;
    private String name, owner;
    private List<BookEntity> books = new ArrayList<>();

    public BookstoreEntity(String name, String owner, List<BookEntity> books) {
        this.name = name;
        this.owner = owner;
        this.books = books;
    }

    public BookstoreEntity() {

    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = BookEntity.class, mappedBy = "bookstoreEntity", fetch = FetchType.LAZY)
    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    public void addBook(BookEntity bookEntity) {
        books.add(bookEntity);
    }

    public void removeBook(int bookId) {
        books.removeIf((book) -> book.getId() == bookId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookstoreEntity that = (BookstoreEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(books, that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, books);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(50 + 30 * books.size());

        builder.append("Bookstore #").append(id).append(": \"").append(name).append("\", owner - ").append(owner).append("\n");
        builder.append("Books:\n");
        books.forEach((book) -> builder.append(book).append("\n"));

        return builder.toString();
    }
}
