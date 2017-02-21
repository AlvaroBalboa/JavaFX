package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    @FXML
    ListView contactlist;
    @FXML
    TextField nameEnter;
    @FXML
    TextField phoneNumberEnter;
    @FXML
    TextField emailEnter;

    ObservableList<Contact> contactsList = FXCollections.observableArrayList();


//    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//    alert.setTitle("Information Dialog");
//    alert.setHeaderText("Look, an Information Dialog");
//    alert.setContentText("I have a great message for you!");
//
//    alert.showAndWait();


    public void addContact() throws IOException{
//        if (nameEnter.getText().isEmpty()) {
//            contactsList.add(new Contact(,,));
//            //contactsList.add(new Contact("Entered", "invalid", "name"));
//        }else if (phoneNumberEnter.getText().isEmpty()) {
//            contactsList.add(new Contact("","",""));
//            //contactsList.add(new Contact("Entered", "invalid", "phone number"));
//
//        }else if (emailEnter.getText().isEmpty() || !emailEnter.getText().endsWith(".com")) {
//            contactsList.add(new Contact("","",""));
//            //contactsList.add(new Contact("Entered", "invalid", "email address"));
//
//        }
//        for (Contact listing : contactsList) {
//            if (listing.getFullName().startsWith("Entered")) {
//                contactsList.remove(listing);
//            }
//        }
        if (!nameEnter.getText().isEmpty()&&!phoneNumberEnter.getText().isEmpty()&&!emailEnter.getText().isEmpty()) {

            contactsList.add(new Contact(nameEnter.getText(), phoneNumberEnter.getText(), emailEnter.getText()));
            nameEnter.setText("");
            phoneNumberEnter.setText("");
            emailEnter.setText("");

            saveContacts();
        }
    }
    public void saveContacts()throws IOException{
        JsonSerializer serializer = new JsonSerializer();
        String jsonFormmatedPerson = serializer.include("*").serialize(contactsList);

        File file = new File("Contacts List.json");
        FileWriter fwriter = new FileWriter(file);
        fwriter.write(jsonFormmatedPerson);
        fwriter.close();
    }

    public void loadContacts()throws IOException{
        File f = new File("Contacts List.json");
        Scanner scanner = new Scanner(f);
        scanner.useDelimiter("\\Z");
        String contents = scanner.next();

        JsonParser parser = new JsonParser();
        Contact adder = parser.parse(contents,Contact.class);
        contactsList.add(adder);
    }

    public void removeContact()throws IOException {
        Contact item = (Contact) contactlist.getSelectionModel().getSelectedItem();
        contactsList.remove(item);
        saveContacts();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            loadContacts();
        } catch(Exception e) {
            contactlist.setItems(contactsList);
        }
    }
}