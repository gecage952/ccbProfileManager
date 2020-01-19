import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;


public class ListingBox extends HBox {

    private Listing listing;

    private Text firstName;
    private Text lastName;
    private Text address;
    private Text phone;
    private Text email;
    private Text zip;
    private Text state;
    private Text city;

    private String[] fields;



    //UPDATE: This is used for duplicates and flagged entries, as those don't
    //need to be edited and update based on the lists stored in the Database class
    public ListingBox(Listing entry) {


        this.listing = entry;
        this.setSpacing(20);
        fields = listing.getCasuals();
        firstName = new Text(fields[0]);
        lastName = new Text(fields[1]);
        address = new Text(fields[2]);
        phone = new Text(fields[3]);
        email = new Text(fields[4]);

        zip = new Text(listing.getZip());
        state = new Text(listing.getState());
        city = new Text(listing.getCity());

        this.getChildren().addAll(firstName, lastName, address, city,
        state, zip, phone, email);




    }
}