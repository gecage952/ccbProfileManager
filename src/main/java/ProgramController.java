import java.util.List;
import java.util.ArrayList;

public class ProgramController {

    private Launcher launcher;


    public ProgramController(Launcher launch) {

        this.launcher = launch;

    }


        public void addListing(String first, String last, String address,
            String zip, String phone, String email, String state, String city) {

            Listing newListing = new Listing(first, last, address, zip, phone,
                email, state, city);


            boolean response = Database.addEntry(newListing);
            if (!response) {
                System.out.println("ERROR");
                launchError("There was an error while adding the entry.");
            } else {
                update();
            }



        }



        public void deleteListing(Listing listing) {
            int ind = 0;
            for (Listing entry: Database.getEntries()) {
                if (listing.equals(entry)) {
                    Database.deleteEntry(ind);
                    break;
                }
                ind++;
            }
            update();

        }
        public void deleteDuplicate(Listing listing) {
            int ind = 0;
            for (Listing entry: Database.getDuplicates()) {
                if (listing.equals(entry)) {
                    Database.deleteDuplicate(ind);
                    break;
                }
                ind++;
            }
            update();

        }
        public void deleteFlagged(Listing listing) {
            int ind = 0;
            for (Listing entry: Database.getFlagged()) {
                if (listing.equals(entry)) {
                    Database.deleteFlagged(ind);
                    break;
                }
                ind++;
            }
            update();

        }

        public void verifyListings() {

            List<Listing> entries = Database.getEntries();

            ProfileManager.createProfiles(entries, false);
            update();
        }

        public void uploadListings() {
            List<Listing> entries = Database.getEntries();
            ProfileManager.createProfiles(entries, true);
            Database.clearEntries();
            update();
        }
        public void uploadListingsExplicit() {
            List<Listing> entries = Database.getEntries();
            ProfileManager.createProfilesExplicit(entries);
            Database.clearEntries();
            update();
        }

        public void uploadFlagged() {
            List<Listing> entries = Database.getFlagged();
            ProfileManager.createProfilesExplicit(entries);
            Database.clearFlagged();
            update();
        }
        public void uploadSingle(Listing listing, boolean explicit) {
            List<Listing> singleList = new ArrayList<Listing>();
            singleList.add(listing);
            if (explicit) {
                ProfileManager.createProfilesExplicit(singleList);
            }  else {
                ProfileManager.createProfiles(singleList, true);
            }
            update();


        }

        private void launchError(String message) {

        }


        public void update() {
            launcher.update();
        }


}



























