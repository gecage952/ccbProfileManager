
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class Listing implements Serializable{


    private transient SimpleStringProperty firstName;
    private transient SimpleStringProperty lastName;
    private transient SimpleStringProperty address;
    private transient SimpleStringProperty phone;
    private transient SimpleStringProperty email;
    private transient SimpleStringProperty zip;
    private transient SimpleStringProperty state;
    private transient SimpleStringProperty city;


    private transient SimpleStringProperty firstNameCasual;
    private transient SimpleStringProperty lastNameCasual;
    private transient SimpleStringProperty addressCasual;
    private transient SimpleStringProperty phoneCasual;
    private transient SimpleStringProperty emailCasual;

    private transient SimpleStringProperty searchAddress;


    private String firstNameString;
    private String lastNameString;
    private String addressString;
    private String phoneString;
    private String emailString;
    private String zipString;
    private String stateString;
    private String cityString;


    private String firstNameCasualString;
    private String lastNameCasualString;
    private String addressCasualString;
    private String phoneCasualString;
    private String emailCasualString;

    private String searchAddressString;


    public Listing(String first, String last, String address, String zip, String phone,
        String email, String state, String city) {
        this.firstName = new SimpleStringProperty("&first_name=" + first.trim());
        this.lastName = new SimpleStringProperty("&last_name=" + last.trim());
        this.address = new SimpleStringProperty(address.trim());
        this.searchAddress = new SimpleStringProperty("&street_address="+address.trim());

        try {
            String areaCode = phone.substring(0,3);
            String phonePartOne = phone.substring(3,6);
            String phoneLastPart = phone.substring(6);
            this.phone = new SimpleStringProperty("&phone=" + areaCode + "-" + phonePartOne + "-" + phoneLastPart);
            this.phoneCasual = new SimpleStringProperty(areaCode + "-" + phonePartOne + "-" + phoneLastPart);
        } catch (Exception e) {
            this.phone = new SimpleStringProperty("");
            this.phoneCasual = new SimpleStringProperty(phone.trim());
        }

        this.email = new SimpleStringProperty(email.trim());
        this.zip = new SimpleStringProperty(zip.trim());
        this.state = new SimpleStringProperty(state.trim());
        this.city = new SimpleStringProperty(city.trim());

        this.firstNameCasual = new SimpleStringProperty(first.trim());
        this.lastNameCasual = new SimpleStringProperty(last.trim());
        this.addressCasual = new SimpleStringProperty(address.trim());

        this.emailCasual = new SimpleStringProperty(email.trim());
         firstNameString = "&first_name=" + first.trim();
         lastNameString = "&last_name=" + last.trim();
         addressString = address.trim();

         emailString = email.trim();
         zipString = zip.trim();
         stateString = state.trim();
         cityString = city.trim();
         try {
            String areaCode = phone.substring(0,3);
            String phonePartOne = phone.substring(3,6);
            String phoneLastPart = phone.substring(6);
            phoneString = "&phone=" + areaCode + "-" + phonePartOne + "-" + phoneLastPart;
            phoneCasualString = areaCode + "-" + phonePartOne + "-" + phoneLastPart;
        } catch (Exception e) {
            phoneString = "";
            phoneCasualString = phone.trim();
        }


         firstNameCasualString = first.trim();
         lastNameCasualString = last.trim();
         addressCasualString = address.trim();
         phoneCasualString = phone.trim();
         emailCasualString = email.trim();

         searchAddressString = "&street_address="+ address.trim();


    }
    public Listing() {
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.phoneCasual = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.zip = new SimpleStringProperty();
        this.state = new SimpleStringProperty();
        this.city = new SimpleStringProperty();

        this.firstNameCasual = new SimpleStringProperty();
        this.lastNameCasual = new SimpleStringProperty();
        this.addressCasual = new SimpleStringProperty();

        this.emailCasual = new SimpleStringProperty();

    }


    public String[] getCasuals() {
        String[] casuals = new String[] {firstNameCasual.get(), lastNameCasual.get(), addressCasual.get(),
            phoneCasual.get(), emailCasual.get()};
        return casuals;
    }


    public String getFirst() {
        return firstNameString.replaceAll(" ", "+");
    }

    public String getFirstNameCasual() {
        return firstNameCasualString;
    }

    public void setFirst(String newfirst) {
        firstNameCasualString = newfirst.trim();
         firstNameString ="&first_name=" +  newfirst.trim();
        firstName = new SimpleStringProperty(firstNameString);
        firstNameCasual = new SimpleStringProperty(firstNameCasualString);



    }

    public String getLast() {
        return lastNameString.replaceAll(" ", "+");
    }
     public String getLastNameCasual() {
        return lastNameCasualString;
    }


    public void setLast(String newlast) {
        lastNameCasualString = newlast.trim();
        lastNameString = "&last_name=" + newlast.trim();
        lastName = new SimpleStringProperty(lastNameString);

        lastNameCasual = new SimpleStringProperty(lastNameCasualString);


    }

    public String getAddress() {
        return addressString;
    }
    public String getFormattedAddress(){
        String formatted = addressString.replaceAll(" ", "+");
        return formatted;

    }
    public String getSearchAddress() {
        String formatted = searchAddressString.replaceAll(" ", "+");
        return formatted;


    }

     public String getAddressCasual() {
        return addressCasualString;
    }


    public void setAddress(String newaddress) {
        addressString = newaddress.trim();
        addressCasualString = newaddress.trim();
        address = new SimpleStringProperty(addressString);
        addressCasual = new SimpleStringProperty(addressString);
        searchAddress = new SimpleStringProperty("&street_address="+ addressString);
        searchAddressString = "&street_address="+ addressString;


    }
    public void setZip(String newzip) {
        zipString = newzip.trim();
        zip = new SimpleStringProperty(zipString);
    }

    public String getZip() {
        return zipString;
    }


    public void setCity(String newcity) {
        cityString = newcity.trim();
        city = new SimpleStringProperty(cityString);
    }

    public String getCity() {
        return cityString;
    }
    public void setState(String newstate) {
        stateString = newstate.trim();
        state = new SimpleStringProperty(stateString);
    }

    public String getState() {
        return stateString;
    }


    public String getPhone() {
        return phoneString;
    }


    public String getPhoneCasual() {
        return phoneCasualString;
    }

    public void setPhone(String newphone) {

        try {

            String areaCode = newphone.substring(0,3);
            String phonePartOne = newphone.substring(3,6);
            String phoneLastPart = newphone.substring(6);
            phoneString = "&phone=" + areaCode + "-" + phonePartOne + "-" + phoneLastPart;
            phone = new SimpleStringProperty(phoneString);
            phoneCasualString = areaCode + "-" + phonePartOne + "-" + phoneLastPart;
            phoneCasual = new SimpleStringProperty(phoneCasualString);

        } catch (Exception e) {
            phone= new SimpleStringProperty("&phone=");
            phoneString = "&phone=";
            phoneCasualString = newphone.trim();
            phoneCasual = new SimpleStringProperty(phoneCasualString);


        }
    }

    public String getEmail() {
        return emailString;
    }

    public String getFormattedEmail() {
        String formatted ="&email=" + emailCasualString;

        return formatted.replaceAll("@", "%40");
    }
     public String getEmailCasual() {
        return emailCasualString;
    }


    public void setEmail(String newemail) {
        emailString = "&email=" + newemail.trim();
        emailCasualString = newemail.trim();
        email = new SimpleStringProperty(emailString);
        emailCasual = new SimpleStringProperty(emailCasualString);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {return true;}
        if (!(other instanceof Listing)) {return false;}
        Listing that = (Listing) other;
        int hits = 0;

        if (this.getLastNameCasual().equals(that.getLastNameCasual())) {hits++;}
        if (this.getFirstNameCasual().equals(that.getFirstNameCasual())) {
            hits++;
            if (this.getEmailCasual().equals(that.getEmailCasual())) {hits++;}
            if (this.getPhoneCasual().equals(that.getPhoneCasual())) {hits++;}
            if (this.getAddressCasual().equals(that.getAddressCasual())) {hits++;}

        }


        if (hits > 3) {return true;}




        return false;


    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }


    public void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(getFirstNameCasual());
        out.writeObject(getLastNameCasual());
        out.writeObject(getAddressCasual());
        out.writeObject(getZip());
        out.writeObject(getCity());
        out.writeObject(getState());
        out.writeObject(getPhoneCasual());
        out.writeObject(getEmailCasual());

    }

    public void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        setFirst((String) in.readObject());
        setLast((String) in.readObject());
        setAddress((String) in.readObject());
        setZip((String) in.readObject());
        setCity((String) in.readObject());
        setState((String) in.readObject());
        setPhone((String) in.readObject());
        setEmail((String) in.readObject());
    }






}