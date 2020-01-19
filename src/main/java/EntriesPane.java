import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import java.util.ArrayList;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;



public class EntriesPane extends VBox {

    private VBox pane;

    private Label label;
    private ControlPanel controlPanel;
    private ScrollPane mainPane;
    private TableView<Listing> table;




    public EntriesPane(ProgramController cont) {
        label = new Label("No Entries");



        mainPane = new ScrollPane();
        table = new TableView<Listing>();
        controlPanel = new ControlPanel(cont, this);
        mainPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

        TableColumn firstCol = new TableColumn("First Name");
        firstCol.setPrefWidth(150);
        firstCol.setCellValueFactory(
        new PropertyValueFactory<Listing,String>("firstNameCasual")
        );
        TableColumn lastCol = new TableColumn("Last Name");
        lastCol.setPrefWidth(150);
        lastCol.setCellValueFactory(
        new PropertyValueFactory<Listing,String>("lastNameCasual")
        );
        TableColumn streetCol = new TableColumn("Street Address");
        streetCol.setPrefWidth(150);
        streetCol.setCellValueFactory(
        new PropertyValueFactory<Listing,String>("addressCasual")
        );
        TableColumn cityCol = new TableColumn("City");
        cityCol.setPrefWidth(125);
        cityCol.setCellValueFactory(
        new PropertyValueFactory<Listing,String>("city")
        );
        TableColumn stateCol = new TableColumn("State");
        stateCol.setPrefWidth(100);
        stateCol.setCellValueFactory(
        new PropertyValueFactory<Listing,String>("state")
        );
        TableColumn zipCol = new TableColumn("Zip");
        zipCol.setPrefWidth(100);
        zipCol.setCellValueFactory(
        new PropertyValueFactory<Listing,String>("zip")
        );
        TableColumn phoneCol = new TableColumn("Phone");
        phoneCol.setPrefWidth(125);
        phoneCol.setCellValueFactory(
        new PropertyValueFactory<Listing,String>("phoneCasual")
        );
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setPrefWidth(175);
        emailCol.setCellValueFactory(
        new PropertyValueFactory<Listing,String>("emailCasual")
        );

        table.getColumns().addAll(firstCol, lastCol, streetCol, cityCol,
            stateCol, zipCol, phoneCol, emailCol);







        this.getChildren().addAll(controlPanel);
        this.getChildren().addAll(mainPane);

        VBox.setVgrow(mainPane, Priority.ALWAYS);

        update();
        pane.setPrefHeight(300);

    }

    public void update() {

        pane = new VBox();
        pane.getChildren().addAll(label);


        table.setPlaceholder(pane);
        table.setPrefSize(2000, 720);
        table.setItems(Database.getObservableEntries());
        mainPane.setContent(table);
        mainPane.setFitToHeight(true);





    }

    public TableView<Listing> getTable() {
        return table;
    }







}