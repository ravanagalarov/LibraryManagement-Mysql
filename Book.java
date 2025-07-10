package MiniProjects.LibraryManagement;

public class Book {
    int id;
    String title;
    String author;
    int publishYear;
    String genre;

    public Book(int id, String title, String author, int publishYear, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.genre = genre;
    }

    public Book(String title, String author, int publishYear, String genre) {
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
