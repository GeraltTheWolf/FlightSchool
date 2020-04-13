package sample;

import data.RestBlogPostManager;
import data.RestUserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;


import java.time.LocalDate;

public class Controller {

    // FORM
    @FXML
    private Label labelId;
    @FXML
    private TextField textTitle;
    @FXML
    private TextArea textContent;
    private ObservableList<String> authorsObservableList;
    @FXML
    private ComboBox<String> comboBoxAuthors;
    @FXML
    private CheckBox checkBoxEnabled;
    @FXML
    private DatePicker datePickerLastEditedOn;

    // TABLE
    private ObservableList<BlogPost> blogPostsObservableList;
    @FXML
    private TableView tableBlogPosts;
    @FXML
    private TableColumn<BlogPost, Integer> columnId;
    @FXML
    private TableColumn<BlogPost, Boolean> columnEnabled;
    @FXML
    private TableColumn<BlogPost, String> columnTitle;
    @FXML
    private TableColumn<BlogPost, String> columnContent;
    @FXML
    private TableColumn<BlogPost, String> columnAuthor;
    @FXML
    private TableColumn<BlogPost, LocalDate> columnLastEditedOn;
    @FXML
    private TableColumn columnDelete;

    // FORM BUTTONS
    @FXML
    private Button btnClearForm;
    @FXML
    private Button btnSaveChanges;

    private BlogPostManager blogPostManager;
    private UserManager userManager;
    private BlogPostObservable observableBlogPost;

    @FXML
    public void initialize() {
        InitializeManagers();
        InitializeForm();
        InitializeTable();
        SetupListeners();
    }

    private void InitializeManagers() {
        this.blogPostManager = new RestBlogPostManager();
        this.userManager = new RestUserManager();
    }

    private void InitializeTable() {

        columnId.setCellValueFactory(new PropertyValueFactory<BlogPost, Integer>("id"));
        columnEnabled.setCellValueFactory(new PropertyValueFactory<BlogPost, Boolean>("enabled"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<BlogPost, String>("title"));
        columnContent.setCellValueFactory(new PropertyValueFactory<BlogPost, String>("content"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<BlogPost, String>("author"));

        columnDelete.setCellFactory(ActionButtonTableCell.<BlogPost>forTableColumn("Delete", (BlogPost p) -> {
            if (blogPostManager.Delete(p.getId())) {
                tableBlogPosts.getItems().remove(p);
            }
            return p;
        }));
    }

    private void InitializeForm() {
        authorsObservableList = FXCollections.observableArrayList(this.userManager.GetAll());
        comboBoxAuthors.setItems(authorsObservableList);
        observableBlogPost = new BlogPostObservable();
        blogPostsObservableList = FXCollections.observableArrayList(this.blogPostManager.GetAll());
        tableBlogPosts.setItems(blogPostsObservableList);

        labelId.textProperty().bind(observableBlogPost.idProperty().asString());
        textTitle.textProperty().bindBidirectional(observableBlogPost.titleProperty());
        textContent.textProperty().bindBidirectional(observableBlogPost.contentProperty());
        comboBoxAuthors.valueProperty().bindBidirectional(observableBlogPost.authorProperty());
        checkBoxEnabled.selectedProperty().bindBidirectional(observableBlogPost.enabledProperty());
        datePickerLastEditedOn.valueProperty().bindBidirectional(observableBlogPost.lastEditedOnProperty());
    }

    private void SetupListeners() {
        tableBlogPosts.getSelectionModel().selectedItemProperty().addListener((observableValue, oldSelection, newSelection) -> {
            if (newSelection != null) {
                observableBlogPost.UpdateValues((BlogPost) newSelection);
            }
        });
    }

    @FXML
    public void Clear() {
        observableBlogPost.UpdateValues(new BlogPost());
        tableBlogPosts.getSelectionModel().clearSelection();
    }

    @FXML
    public void Save() {
        if (observableBlogPost.getId() > 0) {
            blogPostManager.Update(observableBlogPost.getBlogPost());
        } else {
            blogPostManager.Create(observableBlogPost.getBlogPost());
        }
        blogPostsObservableList = FXCollections.observableArrayList(this.blogPostManager.GetAll());
        tableBlogPosts.refresh();
    }
}
