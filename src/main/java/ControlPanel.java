import javafx.scene.layout.TilePane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.scene.layout.Priority;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.util.List;




public class ControlPanel extends TilePane {

    private Button addButton;
    private Button editButton;
    private Button deleteButton;
    private Button verifyButton;
    private Button uploadButton;
    private Button uploadSingle;
    private Button explicitUpload;
    private Button explicitSingle;
    private Button clearButton;
    private ProgramController controller;
    private EntriesPane entries;
    //private Button printButton;


    public ControlPanel(ProgramController cont, EntriesPane entries) {
        this.controller = cont;
        this.entries = entries;
        this.setPrefHeight(65);

        addButton = new Button("Add Listing");

        editButton = new Button("Edit Listing");
        deleteButton = new Button("Delete Listing");
        verifyButton = new Button("Verify Listings");
        uploadButton = new Button("Upload Listings");
        uploadSingle = new Button("Upload Selected Listing");
        explicitUpload = new Button("Explicit Upload");
        explicitSingle = new Button("Explicitly Upload Selected");
        clearButton = new Button("Clear Entries");

        //printButton = new Button("Debug");

        addButton.setOnAction((action) -> {
            modifyListingBox("Add");
        });

        editButton.setOnAction((action) -> {
            modifyListingBox("Edit");
        });

        deleteButton.setOnAction((action) -> {
            confirmationBox("delete");
        });

        verifyButton.setOnAction((action) -> {
            confirmationBox("verify");
        });

        uploadButton.setOnAction((action) -> {
            confirmationBox("upload");
        });

        uploadSingle.setOnAction((action) -> {
            confirmationBox("upload this listing");
        });

        explicitUpload.setOnAction((action) -> {
            confirmationBox("upload all listings without checking them (INGORE WARNINGS");
        });

        explicitSingle.setOnAction((action) -> {
            confirmationBox("upload this listing without checking it (INGORE WARNINGS");
        });
        clearButton.setOnAction((action) -> {
            confirmationBox("clear all");
        });

        /*printButton.setOnAction((action) -> {
            List<Listing> l = Database.getDuplicates();
            List<Listing> f = Database.getFlagged();
            System.out.println(l.isEmpty() + " Duplicates");
            System.out.println(f.isEmpty() + " Flagged");
            if (!l.isEmpty()) {
                System.out.println(l.get(0).getFirstNameCasual());
            }
            if (!f.isEmpty()) {
                System.out.println(f.get(0).getFirstNameCasual());
            }
        });*/


        this.getChildren().addAll(addButton, editButton, deleteButton,
         verifyButton, uploadButton, uploadSingle, explicitUpload, explicitSingle, clearButton);

    }

    public void modifyListingBox(String task) {

        Stage listingStage = new Stage();



        VBox modBox = new VBox(20);
        Button confirmButton = new Button(task);
        Button cancelButton = new Button("Cancel");



        cancelButton.setOnAction((action) -> {
            listingStage.close();
        });

        HBox confirmationPanel = new HBox(10);
        confirmationPanel.getChildren().addAll(confirmButton, cancelButton);
        confirmationPanel.setAlignment(Pos.CENTER);

        HBox nameFields = new HBox(5);
        HBox addressFields = new HBox(5);
        HBox contactFields = new HBox(5);

        VBox firstBox = new VBox(1);
        Label firstName = new Label("First Name");
        TextField firstNameField = new TextField();
        firstBox.getChildren().addAll(firstName, firstNameField);

        VBox lastBox = new VBox(1);
        Label lastName = new Label("Last Name");
        TextField lastNameField = new TextField();
        lastBox.getChildren().addAll(lastName, lastNameField);

        nameFields.getChildren().addAll(firstBox, lastBox);

        VBox streetBox = new VBox(1);
        Label street = new Label("Street Address");
        TextField streetField = new TextField();
        streetBox.getChildren().addAll(street, streetField);

        VBox cityBox = new VBox(1);
        Label city = new Label("City");
        TextField cityField = new TextField();
        cityBox.getChildren().addAll(city, cityField);

        VBox stateBox = new VBox(1);
        Label state = new Label("State (2 Letters)");
        TextField stateField = new TextField();
        stateBox.getChildren().addAll(state, stateField);

        VBox zipBox = new VBox(1);
        Label zip = new Label("Zip Code");
        TextField zipField = new TextField();
        zipBox.getChildren().addAll(zip, zipField);

        addressFields.getChildren().addAll(streetBox, cityBox, stateBox, zipBox);

        VBox phoneBox = new VBox(1);
        Label phone = new Label("Phone (Include Area Code)");
        TextField phoneField = new TextField();
        phoneBox.getChildren().addAll(phone, phoneField);

        VBox emailBox = new VBox(1);
        Label email = new Label("Email");
        TextField emailField = new TextField();
        emailBox.getChildren().addAll(email, emailField);

        contactFields.getChildren().addAll(phoneBox, emailBox);

        modBox.getChildren().addAll(nameFields, addressFields, contactFields,
            confirmationPanel);

        modBox.setPadding(new Insets(20));
        Scene listingScene = new Scene(modBox);

        if (task.equals("Edit")) {
            Listing selected = entries.getTable().getSelectionModel().getSelectedItem();
            firstNameField.setText(selected.getFirstNameCasual());
            lastNameField.setText(selected.getLastNameCasual());
            streetField.setText(selected.getAddressCasual());
            zipField.setText(selected.getZip());
            phoneField.setText(selected.getPhoneCasual());
            emailField.setText(selected.getEmailCasual());
            stateField.setText(selected.getState());
            cityField.setText(selected.getCity());
            confirmButton.setOnAction((action) -> {


                controller.addListing(firstNameField.getText(), lastNameField.getText(),
                    streetField.getText(), zipField.getText(), phoneField.getText(),
                    emailField.getText(), stateField.getText(), cityField.getText());


                controller.deleteListing(selected);
                entries.getTable().refresh();

                listingStage.close();

            });


        } else {

            confirmButton.setOnAction((action) -> {
                controller.addListing(firstNameField.getText(), lastNameField.getText(),
                    streetField.getText(), zipField.getText(), phoneField.getText(),
                    emailField.getText(), stateField.getText(), cityField.getText());
                listingStage.close();
            });
        }

        listingStage.setTitle(task);
        listingStage.setScene(listingScene);
        listingStage.sizeToScene();
        listingStage.initModality(Modality.APPLICATION_MODAL);
        listingStage.show();




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

        box.setPrefSize(250, 100);
        VBox.setVgrow(box, Priority.ALWAYS);
        switch (task) {
            case "delete":
                confirmButton.setOnAction((action) -> {

                    confirmStage.close();
                    controller.deleteListing(entries.getTable().getSelectionModel().getSelectedItem());


                });

                break;

            case "verify":
                confirmButton.setOnAction((action) -> {
                    confirmStage.close();
                    Database.clearFlagged();
                    Database.clearDuplicates();
                    controller.verifyListings();




                });


                break;

            case "upload":

                confirmButton.setOnAction((action) -> {
                    confirmStage.close();
                    Database.clearDuplicates();
                    Database.clearFlagged();
                    controller.uploadListings();


                });



                break;

            case "upload this listing":

                confirmButton.setOnAction((action) -> {
                    confirmStage.close();

                    controller.uploadSingle(entries.getTable().getSelectionModel().getSelectedItem(), false);




                });



                break;

            case "upload all listings without checking them (INGORE WARNINGS":

                confirmButton.setOnAction((action) -> {
                    confirmStage.close();
                    Database.clearDuplicates();
                    Database.clearFlagged();
                    controller.uploadListingsExplicit();



                });



                break;
            case "upload this listing without checking it (INGORE WARNINGS":

                confirmButton.setOnAction((action) -> {
                    confirmStage.close();
                    controller.uploadSingle(entries.getTable().getSelectionModel().getSelectedItem(), true);



                });



                break;
            case "clear all":

                confirmButton.setOnAction((action) -> {
                    confirmStage.close();
                    Database.clearEntries();
                    Database.clearDuplicates();
                    Database.clearFlagged();
                    entries.getTable().refresh();

                    controller.update();


                });



                break;


        }

        Scene confirmScene = new Scene(box);
        confirmStage.setScene(confirmScene);
        confirmStage.sizeToScene();
        confirmStage.initModality(Modality.APPLICATION_MODAL);
        confirmStage.show();

    }

    public void progressBox() {

    }




}