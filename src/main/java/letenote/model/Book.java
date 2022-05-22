package letenote.model;

public class Book {
    private String id;
    private String title;
    private String author;
    private String description;
    private Long createdAt;
    private Long updatedAt;

    public Book() {
    }

    public Book(String id, String title, String author, String description, Long createdAt, Long updatedAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static Book.BookBuilder builder(){
        return new Book.BookBuilder();
    }

    public static class BookBuilder{
        private String id;
        private String title;
        private String author;
        private String description;
        private Long createdAt;
        private Long updatedAt;

        BookBuilder(){}
        public Book.BookBuilder id(final String id){
            this.id = id;
            return this;
        }
        public Book.BookBuilder title(final String title){
            this.title = title;
            return this;
        }
        public Book.BookBuilder author(final String author){
            this.author = author;
            return this;
        }
        public Book.BookBuilder description(final String description){
            this.description = description;
            return this;
        }
        public Book.BookBuilder createdAt(final Long createdAt){
            this.createdAt = createdAt;
            return this;
        }
        public Book.BookBuilder updatedAt(final Long updatedAt){
            this.updatedAt = updatedAt;
            return this;
        }

        public Book build(){
            return new Book(
                    this.id,
                    this.title,
                    this.author,
                    this.description,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }
}
