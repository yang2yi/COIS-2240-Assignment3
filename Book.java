public class Book {
    private int id;
    private String title;
    private boolean isAvailable = true;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void checkedOut() {
        this.isAvailable = false;
    }

    public void returned() {
        this.isAvailable = true;
    }

    public boolean isValidId(int id) {
        return id >= 100 && id <= 999;
    }

    public String toString() {
        return "Book{id=" + id + ", title='" + title + "'" + " avilability=" + isAvailable + "}";
    }
}
