package sample;

import data.RestBlogPostManager;
import data.RestUserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ActionButtonTableCell;
import model.BlogPost;
import model.BlogPostManager;
import model.UserManager;

import java.awt.*;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.TextField;
import java.time.LocalDate;

public class Controller {

    @FXML
    private TextField tfTitle;

    @FXML
    private ComboBox<String> cmbAuthors;

    @FXML
    private TextArea taContent;

    @FXML
    private CheckBox cbEnabled;

    @FXML
    private DatePicker dpLastEditedOn;

    @FXML
    private TableView tblBlogPosts;

    @FXML
    private TableColumn tblClmTitle;

    @FXML
    private TableColumn tblClmDelete;
    @FXML
    private TableColumn<BlogPost, String> tblClmContent;

    @FXML
    private TableColumn<BlogPost, String> tblClmAuthor;

    @FXML
    private TableColumn<BlogPost, LocalDate> tblClmLastEditedOn;

    @FXML
    private TableColumn<BlogPost, String> tblClmEnabled;

    @FXML
    private Button btnSave;

    private BlogPostManager blogPostManager;
    private UserManager userManager;

    private ObservableList<String> authorsObservableList;
    private ObservableList<BlogPost> blogPostsObservableList;


    @FXML
    public void initialize() {

        this.blogPostManager = new RestBlogPostManager();
        this.userManager = new RestUserManager();

        tblClmTitle.setCellValueFactory(new PropertyValueFactory<BlogPost, String>("title"));
        tblClmContent.setCellValueFactory(new PropertyValueFactory<BlogPost, String>("content"));
       // tblClmAuthor.setCellValueFactory(new PropertyValueFactory<BlogPost, String>("author"));
        tblClmDelete.setCellFactory(ActionButtonTableCell.<BlogPost>forTableColumn("Delete", (BlogPost p) -> {
            if (blogPostManager.Delete(p.getId())) {
                tblBlogPosts.getItems().remove(p);
            }
            return p;
        }));

//        authorsObservableList = FXCollections.observableArrayList(this.userManager.GetAll());
//        cmbAuthors.setItems(authorsObservableList);
        blogPostsObservableList = FXCollections.observableArrayList(this.blogPostManager.GetAll());
        tblBlogPosts.setItems(blogPostsObservableList);
    }

    @FXML
    public void DeleteBlogPost(ActiveEvent event) {

    }


    @FXML
    public void SaveBlogPost(ActiveEvent event) {
        BlogPost blogPost = new BlogPost();

        blogPost.setTitle(tfTitle.getText());
        blogPost.setContent(taContent.getText());
        blogPost.setAuthor(cmbAuthors.getValue());
        blogPost.setEnabled(cbEnabled.isSelected());
        blogPost.setLastEditedOn(dpLastEditedOn.getValue());

        blogPostsObservableList.add(blogPostManager.Create(blogPost));

    }

    @FXML
    public void UpdateBlogPost(ActiveEvent event) {

        BlogPost blogPost = (BlogPost) tblBlogPosts.getSelectionModel().getSelectedItem();
        blogPost.setTitle(tfTitle.getText());
        blogPost.setContent(taContent.getText());
        blogPost.setAuthor(cmbAuthors.getValue());
        blogPost.setEnabled(cbEnabled.isSelected());
        blogPost.setLastEditedOn(dpLastEditedOn.getValue());

        blogPostManager.Update(blogPost);
    }
}
