

import java.util.List;
import java.util.ArrayList;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.OutputStream;

import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.DatatypeConverter;





public class ProfileManager {




    private static List<Listing> flagged = new ArrayList<Listing>();
    private static List<Listing> duplicate = new ArrayList<Listing>();
    private static final String SEARCHURL =
    "https://westpark.ccbchurch.com/api.php?srv=individual_search";
    //search queries can be appended directly to the url


    private static final String PROFILEURL =
    "https://westpark.ccbchurch.com/api.php?srv=create_individual";
    //individual parameters must be sent by POST
    //

    private static final String USERPASS = "";



    public static void createProfiles(List<Listing> entries, boolean add) {

        for (Listing entry: entries) {
            System.out.println(entry + " createprofiles");
            System.out.println(entry.getFirstNameCasual());
            if(check(entry)) {

                if (add) {

                    upload(entry);
                }

            }

        }



    }
    public static void createProfilesExplicit(List<Listing> entries) {

        for (Listing entry: entries) {
                    upload(entry);
        }
    }







    public static List<Listing> getFlagged() {
        return flagged;
    }

    public static List<Listing> getDuplicate() {
        return duplicate;
    }

    public static void store() {
        Database.setDuplicates(duplicate);
        Database.setFlagged(flagged);
    }

    private static boolean check(Listing entry) {
        StringBuilder response = new StringBuilder();
        System.out.println(entry + " checking");

        try {

            URL url = new URL(SEARCHURL + entry.getFirst() + entry.getLast()
                + entry.getFormattedEmail() + entry.getSearchAddress());
            HttpsURLConnection connector =(HttpsURLConnection) url.openConnection();


            String basicAuth = "Basic " +
            javax.xml.bind.DatatypeConverter.printBase64Binary(USERPASS.getBytes("UTF-8"));

            connector.setRequestProperty("Authorization", basicAuth);
            connector.setRequestProperty("Accept-Charset", "UTF-8");
            connector.setRequestProperty("Content-Type", "application/x-www-"
                + "form-urlencoded;charset=UTF-8");
            connector.setReadTimeout(10000);




            try {
                    BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(connector.getInputStream()));
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line).append("\n");

                    }
                    bufferedReader.close();

                } finally {
                    connector.disconnect();;


                }



        } catch(Exception e) {
            e.printStackTrace();
        }
        //System.out.println(response);

        List<Listing> searchResults = CcbParser.parseSearch(response.toString());



        boolean passed = true;

        for (Listing current: searchResults) {
            System.out.println(current.getFirstNameCasual());
            System.out.println(entry.getFirstNameCasual() + " " + searchResults.size());

            if (current.equals(entry)) {
                duplicate.add(entry);
                return false;
            } else if (current.getLast().equals(entry.getLast())) {
                if (current.getFirst().equals(entry.getFirst())) {
                    flagged.add(entry);
                    passed = false;


                }
            }
        }
        System.out.println(passed);
        return passed;




    }

    private static void upload(Listing entry) {
        try {
            URL url = new URL(PROFILEURL);

            HttpsURLConnection connector =
            (HttpsURLConnection) url.openConnection();

            connector.setDoOutput(true);
            connector.setRequestMethod("POST");




            String basicAuth = "Basic " +
            javax.xml.bind.DatatypeConverter.printBase64Binary(USERPASS.getBytes("UTF-8"));



            connector.setRequestProperty ("Authorization", basicAuth);
            connector.setRequestProperty("Accept-Charset", "UTF-8");
            connector.setRequestProperty("Content-Type", "application/x-www-"
                + "form-urlencoded;charset=UTF-8");

            String info =
                String.format("first_name=%s&last_name=%s"
                    + "&home_street_address=%s"
                    + "&home_city=%s"
                    + "&home_state=%s"
                    + "&home_zip=%s"
                    + "&email=%s"
                    + "&mobile_phone=%s",

                    URLEncoder.encode(entry.getFirstNameCasual(), "UTF-8"),
                    URLEncoder.encode(entry.getLastNameCasual(), "UTF-8"),
                    URLEncoder.encode(entry.getFormattedAddress(), "UTF-8"),
                    URLEncoder.encode(entry.getCity(), "UTF-8"),
                    URLEncoder.encode(entry.getState(), "UTF-8"),
                    URLEncoder.encode(entry.getZip(), "UTF-8"),
                    URLEncoder.encode(entry.getFormattedEmail(), "UTF-8"),
                    URLEncoder.encode(entry.getPhoneCasual(), "UTF-8")
                    );
                try {
                    OutputStream output = connector.getOutputStream();
                    output.write(info.getBytes("UTF-8"));

                } catch(Exception ex) {
                    connector.disconnect();
                    ex.printStackTrace();

                }
                StringBuilder stringBuilder = new StringBuilder();

                try {
                    BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(connector.getInputStream()));
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();

                } finally {
                    connector.disconnect();
                    System.out.println(stringBuilder.toString());


                }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }






}