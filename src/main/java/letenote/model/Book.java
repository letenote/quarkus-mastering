package letenote.model;

public class Book {
    private final String id;
    private final String title;
    private final String description;
    private final String author;
    private final Long createdAt;
    private final Long updatedAt;

    public Book(String id, String title, String description, String author, Long createdAt, Long updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public static Book.BookBuilder builder(){
        return new Book.BookBuilder();
    }

    public static class BookBuilder{
        private String id;
        private String title;
        private String description;
        private String author;
        private Long createdAt;
        private Long updatedAt;

        public BookBuilder() {}
        public Book.BookBuilder id(String id){
            this.id = id;
            return this;
        }
        public Book.BookBuilder title(String title){
            this.title = title;
            return this;
        }
        public Book.BookBuilder description(String description){
            this.description = description;
            return this;
        }
        public Book.BookBuilder author(String author){
            this.author = author;
            return this;
        }
        public Book.BookBuilder createdAt(Long createdAt){
            this.createdAt = createdAt;
            return this;
        }
        public Book.BookBuilder updatedAt(Long updatedAt){
            this.updatedAt = updatedAt;
            return this;
        }
        public Book build(){
            return new Book(
                    this.id,
                    this.title,
                    this.description,
                    this.author,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }
}
