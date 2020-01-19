


import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

/**
 * Created by gecag on 6/30/2017.
 * @author Ethan Cage
 * @version 1.2
 *
 * An xml Parser to parse responses from the West Park CCB API
 */

public class CcbParser {

    public static String parseProfile(String source, String desiredValue, String tagName, boolean subNode) {

        String result = "-1";

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(source.getBytes("UTF-8"));
            Document doc = builder.parse(input);
            doc.getDocumentElement().normalize();



            NodeList nList = doc.getElementsByTagName(tagName);


            Node current = nList.item(0);


            if (current.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) current;

                if (subNode) {
                    result = e.getElementsByTagName(desiredValue).item(0).getTextContent();


                } else {

                    result = e.getAttribute(desiredValue);
                }

            }





        } catch(Exception e) {

            return result;
        }




        return result;


    }

    public static List<String> parseFormLoop(String source, String desiredValue, String tagName, boolean subNode, int loop) {

        List<String> result = new ArrayList<String>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(source.getBytes("UTF-8"));
            Document doc = builder.parse(input);
            doc.getDocumentElement().normalize();



            NodeList nList = doc.getElementsByTagName(tagName);

            for (int i = 0; i < loop; i++){
                Node current = nList.item(i);

                if(current != null) {
                    if (current.getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) current;

                        if (subNode) {
                            if (e.getElementsByTagName("status").item(0).getTextContent().equals("Available")) {
                                result.add(e.getElementsByTagName(desiredValue).item(0).getTextContent());
                            }


                        } else {

                            result.add(e.getAttribute(desiredValue));
                        }

                    }
                }
            }





        } catch(Exception e) {
            e.printStackTrace();

            return result;
        }




        return result;


    }

    private static int parseReponseCount(String source) {
        int result = 0;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(source.getBytes("UTF-8"));
            Document doc = builder.parse(input);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("individuals");
            Node current = nList.item(0);
            if (current != null) {
                if (current.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) current;

                    result = Integer.parseInt( e.getAttribute("count"));

                }

            }





        } catch(Exception e) {
            e.printStackTrace();
            return result;
        }
        System.out.println(result);
        return result;

    }

    public static List<Listing> parseSearch(String source) {

        List<Listing> result = new ArrayList<Listing>();
        int loop = parseReponseCount(source);

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(source.getBytes("UTF-8"));
            Document doc = builder.parse(input);
            doc.getDocumentElement().normalize();



            NodeList nList = doc.getElementsByTagName("individual");


            for (int i = 0; i < loop; i++){
                Node current = nList.item(i);

                if(current != null) {
                    if (current.getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) current;


                            Listing currentListing = new Listing();


                            currentListing.setFirst(e.getElementsByTagName("first_name").item(0).getTextContent());
                            currentListing.setLast(e.getElementsByTagName("last_name").item(0).getTextContent());
                            currentListing.setEmail(e.getElementsByTagName("email").item(0).getTextContent());




                            result.add(currentListing);





                    }
                }
            }
            nList = doc.getElementsByTagName("address");
            for (int i = 0; i < loop; i++){
                Node current = nList.item(i);

                if(current != null) {
                    if (current.getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) current;


                        Listing currentListing = result.get(i);

                        currentListing.setAddress(e.getElementsByTagName("street_address").item(0).getTextContent());


                    }
                }
            }
            nList = doc.getElementsByTagName("phones");
            for (int i = 0; i < loop; i++){
                Node current = nList.item(i);

                if(current != null) {
                    if (current.getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) current;


                        Listing currentListing = result.get(i);
                        String phone = e.getElementsByTagName("phone").item(0).getTextContent();
                        try {
                            String area = phone.substring(1,4);
                            String firstPart = phone.substring(6, 9);
                            String lastPart = phone.substring(10, 14);
                            phone = area + firstPart + lastPart;

                        } catch(Exception exception) {
                            phone = "";
                        }



                        currentListing.setPhone(phone);


                    }
                }
            }





        } catch(Exception e) {
            e.printStackTrace();

            return result;
        }




        return result;


    }

}
