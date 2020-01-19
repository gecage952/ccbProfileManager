
/* TODO:
    Coding:
        _/Search for mutiple string queriesin the ccb database, and return results
        _/check results for duplicates
        _/if duplicate add to duplicate list
        _/if name is same, but contact info is different, add to flagged list
        _/if no results add queries (in the form of a listing object) to ccb
        Create the program controller

    UI:
        create the whole ui :)


*/

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;


public class Launcher extends Application {
    private Stage primaryStage;
    private ProgramController controller;
    private MainWindow window;


    public void start(Stage ps) {
        primaryStage = ps;
        primaryStage.setTitle("West Park Connect Profile Adder");
        controller = new ProgramController(this);
        window = new MainWindow(this, controller, ps);

        Scene mainScene = new Scene(window);
        KeyCombination saveComb = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        KeyCombination loadComb = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
        mainScene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

            public void handle(KeyEvent event) {
                if(saveComb.match(event)) {
                    window.saveEntries();

                } else if (loadComb.match(event)) {
                    window.loadEntries();
                }

            }
        });
        primaryStage.setScene(mainScene);
        primaryStage.sizeToScene();
        primaryStage.show();


    }


    public void update() {
        Database.update();
        window.update();

    }


    public static void main(String[] args) {
        launch(args);

    }





}