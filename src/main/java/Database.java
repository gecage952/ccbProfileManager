

import java.util.List;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;




public class Database {


    private static List<Listing> entries = new ArrayList();

    private static List<Listing> duplicateEntries = new ArrayList();
    private static List<Listing> flaggedEntries = new ArrayList();




    public static boolean addEntry(Listing listing) {
        try {
            entries.add(listing);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteEntry(int index) {

       try {
            entries.remove(index);
            return true;
        } catch(Exception e) {
            return false;
        }



    }
    public static void setEntries(List<Listing> list){
        entries = list;
    }

    public static void clearEntries() {
        entries.clear();
    }

    public static List<Listing> getEntries() {
        return entries;
    }

    public static ObservableList<Listing> getObservableEntries() {
        ObservableList<Listing> oEntries = FXCollections.observableArrayList(entries);
        return oEntries;
    }

    public static boolean addDuplicate(Listing listing) {
        try {


            duplicateEntries.add(listing);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteDuplicate(int index) {

       try {
            duplicateEntries.remove(index);
            return true;
        } catch(Exception e) {
            return false;
        }



    }

    public static void clearDuplicates() {
        duplicateEntries.clear();
    }

    public static List<Listing> getDuplicates() {
        return duplicateEntries;

    }

    public static ObservableList<Listing> getObservableDuplicates() {
        ObservableList<Listing> oDuplicates = FXCollections.observableArrayList(duplicateEntries);
        return oDuplicates;
    }

    public static void setDuplicates(List<Listing> list) {
        duplicateEntries = list;
    }


    public static boolean addFlagged(Listing listing) {
        try {
            flaggedEntries.add(listing);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteFlagged(int index) {

       try {
            flaggedEntries.remove(index);
            return true;
        } catch(Exception e) {
            return false;
        }



    }

    public static void clearFlagged() {
        flaggedEntries.clear();
    }

    public static List<Listing> getFlagged() {
        return flaggedEntries;
    }

    public static ObservableList<Listing> getObservableFlagged() {
        ObservableList<Listing> oFlagged = FXCollections.observableArrayList(flaggedEntries);
        return oFlagged;
    }

    public static void setFlagged(List<Listing> list) {
        flaggedEntries = list;
    }


    public static void update() {
        setFlagged(ProfileManager.getFlagged());
        setDuplicates(ProfileManager.getDuplicate());
    }
}