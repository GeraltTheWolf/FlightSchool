package model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class BlogPostObservable  {

    private SimpleIntegerProperty id;
    private SimpleStringProperty title;
    private SimpleStringProperty content;
    private SimpleObjectProperty<LocalDate> lastEditedOn;
    private SimpleStringProperty author;
    private SimpleBooleanProperty enabled;

    public BlogPostObservable() {
        this.id = new SimpleIntegerProperty();
        this.title = new SimpleStringProperty();
        this.content = new SimpleStringProperty();
        this.lastEditedOn = new SimpleObjectProperty();
        this.author = new SimpleStringProperty();
        this.enabled = new SimpleBooleanProperty();
    }

    public void UpdateValues(BlogPost blogPost){
        this.setId(blogPost.getId());
        this.setTitle(blogPost.getTitle());
        this.setContent(blogPost.getContent());
        this.setAuthor(blogPost.getAuthor());
        this.setLastEditedOn(blogPost.getLastEditedOn());
        this.setEnabled(blogPost.isEnabled());
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getContent() {
        return content.get();
    }

    public SimpleStringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public LocalDate getLastEditedOn() {
        return lastEditedOn.get();
    }

    public SimpleObjectProperty<LocalDate> lastEditedOnProperty() {
        return lastEditedOn;
    }

    public void setLastEditedOn(LocalDate lastEditedOn) {
        this.lastEditedOn.set(lastEditedOn);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public boolean isEnabled() {
        return enabled.get();
    }

    public SimpleBooleanProperty enabledProperty() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled.set(enabled);
    }

    public BlogPost getBlogPost() {
        return new BlogPost(getId(),getTitle(),getAuthor(),getLastEditedOn(),getAuthor(),isEnabled());
    }

}
