package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "books")
public class BookEntity {
    private int id, printYear;
    private String title, description, author, isbn;
    private boolean isRedAlready;
    private BookstoreEntity bookstoreEntity;

    public BookEntity(int printYear, String title, String description, String author, String isbn, boolean isRedAlready, BookstoreEntity bookstoreEntity) {
        this.printYear = printYear;
        this.title = title;
        this.description = description;
        this.author = author;
        this.isbn = isbn;
        this.isRedAlready = isRedAlready;
        this.bookstoreEntity = bookstoreEntity;
    }

    public BookEntity() {

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
    @Column(name = "printed_year", nullable = false, updatable = false)
    public int getPrintYear() {
        return printYear;
    }

    public void setPrintYear(int printYear) {
        this.printYear = printYear;
    }

    @Basic
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "author", nullable = false)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "is_red_already", nullable = false)
    public boolean isRedAlready() {
        return isRedAlready;
    }

    public void setRedAlready(boolean redAlready) {
        isRedAlready = redAlready;
    }

    @ManyToOne
    @JoinColumn(name = "store_id")
    public BookstoreEntity getBookstoreEntity() {
        return bookstoreEntity;
    }

    public void setBookstoreEntity(BookstoreEntity bookstoreEntity) {
        this.bookstoreEntity = bookstoreEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return id == that.id &&
                printYear == that.printYear &&
                isRedAlready == that.isRedAlready &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(author, that.author) &&
                Objects.equals(isbn, that.isbn) &&
                Objects.equals(bookstoreEntity, that.bookstoreEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, printYear, title, description, author, isbn, isRedAlready, bookstoreEntity);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", printYear=" + printYear +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", isRedAlready=" + isRedAlready +
                ", bookstoreEntity=" + bookstoreEntity +
                '}';
    }
}
