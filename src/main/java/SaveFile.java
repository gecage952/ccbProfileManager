import java.util.List;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class SaveFile implements Serializable{

    private transient List<Listing> entries;


    public SaveFile(List<Listing> entries) {
        this.entries = entries;
    }




    public List<Listing> getEntries(){
        return entries;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(entries);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        entries = (List<Listing>) in.readObject();
    }









}