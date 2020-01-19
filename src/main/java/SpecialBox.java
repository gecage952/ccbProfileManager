import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.geometry.Pos;
import javafx.geometry.Insets;


public class SpecialBox extends HBox {



    private ScrollPane duplicateBox;
    private ScrollPane flaggedBox;
    private VBox duplicatePanel;
    private VBox flaggedPanel;
    private HBox duplicateControl;
    private HBox flaggedControl;
    private Label fLabel;
    private Label dLabel;
    private VBox fPane;
    private VBox dPane;
    private ArrayList<ListingBox> fBoxes;
    private ArrayList<ListingBox> dBoxes;
    private TableView<Listing> dTable;
    private TableView<Listing> fTable;
    private TilePane fControl;
    private TilePane dControl;
    private ProgramController controller;


    public SpecialBox(ProgramController cont) {
        duplicatePanel = new VBox();
        flaggedPanel = new VBox();
        duplicateControl = new HBox();
        flaggedControl = new HBox();
        duplicateBox = new ScrollPane();
        flaggedBox = new ScrollPane();
        dTable = new TableView<Listing>();
        fTable = new TableView<Listing>();
        fControl = new TilePane(1, 1);
        dControl = new TilePane(1, 1);
        Label flagged = new Label("Flagged");
        Label duplicate = new Label("Duplicates");
        this.controller = cont;


        Button deleteButtonD = new Button("Delete Duplicate");
        deleteButtonD.setOnAction((action) -> {
            confirmationBox("delete this duplicate");

        });
        Button clearButtonD = new Button("Clear Duplicates");
        clearButtonD.setOnAction((action) -> {
           confirmationBox("clear duplicates");


        });


        Button deleteButtonF = new Button("Delete Flagged");
        deleteButtonF.setOnAction((action) -> {
            confirmationBox("delete this flagged entry");

        });
        Button clearButtonF = new Button("Clear Flagged");
        clearButtonF.setOnAction((action) -> {
            confirmationBox("clear flagged entries");

        });
        Button explicitButtonF = new Button("Upload Flagged");
        explicitButtonF.setOnAction((action) -> {
           confirmationBox("upload all flagged entries (IGNORE WARNINGS)");

        });

        Button explicitEntryButtonF = new Button("Upload Selected");
        explicitEntryButtonF.setOnAction((action) -> {
            confirmationBox("upload the selected flagged entry (IGNORE WARNINGS)");

        });

        fControl.getChildren().addAll(deleteButtonF, clearButtonF, explicitButtonF, explicitEntryButtonF);
        dControl.getChildren().addAll(deleteButtonD, clearButtonD);






       duplicateControl.getChildren().addAll( dControl);
       flaggedControl.getChildren().addAll(fControl);

        duplicatePanel.getChildren().addAll(duplicate, duplicateControl, duplicateBox);
        flaggedPanel.getChildren().addAll(flagged, flaggedControl, flaggedBox);

        duplicatePanel.setPrefWidth(360);

        flaggedPanel.setPrefWidth(360);

        fLabel = new Label("No Flagged Entries");
        dLabel = new Label("No Duplicate Entries");
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


        TableColumn firstColF = new TableColumn("First Name");
        firstColF.setPrefWidth(150);
        firstColF.setCellValueFactory(
        new PropertyValueFactory<Listing,String>("firstNameCasual")
        );
        TableColumn lastColF = new TableColumn("Last Name");
        lastColF.setPrefWidth(150);
        lastColF.setCellValueFactory(
        new PropertyValueFactory<Listing,String>("lastNameCasual")
        );
        TableColumn streetColF = new TableColumn("Street Address");
        streetColF.setPrefWidth(150);
        streetColF.setCellValueFactory(
        new PropertyValueFactory<Listing,String>("addressCasual")
        );
        TableColumn cityColF = new TableColumn("City");
        cityColF.setPrefWidth(125);
        cityColF.setCellValueFactory(
        new PropertyValueFactory<Listing,String>("city")
        );
        TableColumn stateColF = new TableColumn("State");
        stateColF.setPrefWidth(100);
        stateColF.setCellValueFactory(
        new PropertyValueFactory<Listing,String>("state")
        );
        TableColumn zipColF = new TableColumn("Zip");
        zipColF.setPrefWidth(100);
        zipColF.setCellValueFactory(
        new PropertyValueFactory<Listing,String>("zip")
        );
        TableColumn phoneColF = new TableColumn("Phone");
        phoneColF.setPrefWidth(125);
        phoneColF.setCellValueFactory(
        new PropertyValueFactory<Listing,String>("phoneCasual")
        );
        TableColumn emailColF = new TableColumn("Email");
        emailColF.setPrefWidth(175);
        emailColF.setCellValueFactory(
        new PropertyValueFactory<Listing,String>("emailCasual")
        );

        this.getChildren().addAll(duplicatePanel, flaggedPanel);
        HBox.setHgrow(duplicatePanel, Priority.ALWAYS);
        HBox.setHgrow(flaggedPanel, Priority.ALWAYS);
        fPane = new VBox();
        fPane.getChildren().addAll(fLabel);
        dPane = new VBox();
        dPane.getChildren().addAll(dLabel);

        fTable.getColumns().addAll(firstColF, lastColF, streetColF, cityColF,
            stateColF, zipColF, phoneColF, emailColF);
        dTable.getColumns().addAll(firstCol, lastCol, streetCol, cityCol,
            stateCol, zipCol, phoneCol, emailCol);
        update();

    }


    public void update() {
        fTable.setPlaceholder(fPane);
        dTable.setPlaceholder(dPane);

        fTable.setItems(Database.getObservableFlagged());
        dTable.setItems(Database.getObservableDuplicates());

        duplicateBox.setContent(dTable);
        duplicateBox.setFitToHeight(true);
        flaggedBox.setContent(fTable);
        flaggedBox.setFitToHeight(true);
    }

    public TableView<Listing> getFlaggedTable() {
        return fTable;
    }

    public TableView<Listing> getDuplicateTable() {
        return dTable;
    }



    public void confirmationBox(String task) {
        Stage confirmStage = new Stage();
        confirmStage.setTitle("Confirm");
        Label label = new Label("Are you sure you want to " + task + "?");
        HBox confirmationPanel = new HBox(10);
        HBox.setHgrow(confirmationPanel, Priority.ALWAYS);
        confirmationPanel.setAlignment(Pos.CENTER);
        Button confirmButton = new Button(task.substring(0,1).toUpperCase()
            + task.substring(1));
        Button cancelButton = new Button("Cancel");

        cancelButton.setOnAction((action) -> {
            confirmStage.close();
        });
        confirmationPanel.getChildren().addAll(confirmButton, cancelButton);
        VBox box = new VBox(20);
        box.getChildren().addAll(label, confirmationPanel);

        box.setPrefSize(400, 100);
        VBox.setVgrow(box, Priority.ALWAYS);
        switch (task) {
            case "delete this duplicate":
                confirmButton.setOnAction((action) -> {

                    confirmStage.close();
                    controller.deleteDuplicate(dTable.getSelectionModel().getSelectedItem());


                });

                break;

            case "clear duplicates":
                confirmButton.setOnAction((action) -> {
                    confirmStage.close();

                    Database.clearDuplicates();

                    controller.update();



                });


                break;

            case "delete this flagged entry":

                confirmButton.setOnAction((action) -> {
                    confirmStage.close();
                    controller.deleteFlagged(fTable.getSelectionModel().getSelectedItem());



                });



                break;


                case "clear flagged entries":
                 confirmButton.setOnAction((action) -> {
                    confirmStage.close();

                    Database.clearFlagged();
                    controller.update();
                });

                    break;

                case "upload all flagged entries (IGNORE WARNINGS)":
                 confirmButton.setOnAction((action) -> {
                    confirmStage.close();
                    Database.clearFlagged();
                    controller.uploadFlagged();
                });

                    break;

                case "upload the selected flagged entry (IGNORE WARNINGS)":
                 confirmButton.setOnAction((action) -> {
                    confirmStage.close();
                    Listing current = fTable.getSelectionModel().getSelectedItem();
                    controller.uploadSingle(current, true);
                    controller.deleteFlagged(current);

                 });

                    break;


        }

        Scene confirmScene = new Scene(box);
        confirmStage.setScene(confirmScene);
        confirmStage.sizeToScene();
        confirmStage.initModality(Modality.APPLICATION_MODAL);
        confirmStage.show();

    }








}