import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.geometry.Pos;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;


import javafx.scene.layout.BorderPane;

public class MainWindow extends BorderPane {
    private EntriesPane entryPane;
    private SpecialBox specials;
    private Stage primary;

    public MainWindow(Launcher launcher, ProgramController control, Stage ps) {

        MenuBar menuBar = new MenuBar();
        this.primary = ps;
        Menu menuFile = new Menu("File");
        MenuItem close = new MenuItem("Close");
        MenuItem saveItem = new MenuItem("Save (CTRL + S)");
        MenuItem loadItem = new MenuItem("Open (CTRL + O)");
        close.setOnAction((action) -> {
            Platform.exit();


        });
        saveItem.setOnAction((action) ->{
            saveEntries();
        });

        loadItem.setOnAction((action) -> {
            loadEntries();
        });

        menuFile.getItems().addAll(saveItem, loadItem, close);

        /*Menu menuEdit = new Menu("Edit");
        MenuItem preferences = new MenuItem("Preferences");
        menuEdit.getItems().add(preferences); */


        Menu menuHelp = new Menu("Help");
        MenuItem about = new MenuItem("About");
        about.setOnAction((action) -> {
            Stage aboutStage = new Stage();
            HBox aboutBox = new HBox();
            aboutBox.setPrefSize(200, 100);
            Label aboutLabel = new Label("Programmed by Gregory Cage.\n2017\nv 1.1");
            aboutBox.getChildren().addAll(aboutLabel);
            Scene aboutScene = new Scene(aboutBox);
            aboutStage.setScene(aboutScene);
            aboutStage.setScene(aboutScene);
            aboutStage.sizeToScene();
            aboutStage.initModality(Modality.APPLICATION_MODAL);
            aboutStage.show();

        });
        menuHelp.getItems().add(about);

        menuBar.getMenus().addAll(menuFile, menuHelp);


        entryPane = new EntriesPane(control);
        specials = new SpecialBox(control);
        specials.setPrefHeight(300);


        this.setBottom(specials);
        this.setTop(menuBar);
        this.setCenter(entryPane);

        this.setPrefSize(1080, 720);

    }

    public void update() {
        entryPane.update();
        specials.update();

    }

    public void saveEntries() {
        FileChooser browser = new FileChooser();
        File saveLocation = new File(browser.showSaveDialog(primary).toString() + ".ccb");
        saveLocation.getParentFile().mkdirs();
        SaveFile saveFile = new SaveFile(Database.getEntries());
        try {
                if (!saveLocation.exists()) {
                    boolean made = saveLocation.createNewFile();
                }

                FileOutputStream saveStream = new FileOutputStream(saveLocation, false);
                ObjectOutputStream saver = new ObjectOutputStream(saveStream);
                saver.writeObject(saveFile);

                saver.close();


            } catch (Exception e) {
                e.printStackTrace();


            }



    }

    public void loadEntries() {
        FileChooser browser = new FileChooser();
        File loadLocation = browser.showOpenDialog(primary);

        try {
            FileInputStream input = new FileInputStream(loadLocation);
            ObjectInputStream loadStream = new ObjectInputStream(input);
            SaveFile savedEntries = (SaveFile) loadStream.readObject();
            loadStream.close();
            Database.setEntries(savedEntries.getEntries());
            entryPane.getTable().refresh();
            update();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }







}