package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryListController implements Initializable {

    @FXML
    private TableView<InventoryItem> inventoryTable;
    @FXML
    private TableColumn<InventoryItem, String> serialNumberColumn;
    @FXML
    private TableColumn<InventoryItem, String> nameColumn;
    @FXML
    private TableColumn<InventoryItem, Double> valueColumn;
    @FXML
    private Button addItemBut;
    @FXML
    private Button removeBut;
    @FXML
    private Button editItemBut;
    @FXML
    private Button sortNameBut;
    @FXML
    private Button sortSerialNumBut;
    @FXML
    private Button sortValueBut;
    @FXML
    private Button searchNameBut;
    @FXML
    private Button searchSerialNumBut;
    @FXML
    private Button viewAllBut;
    @FXML
    private Button exportTxtBut;
    @FXML
    private Button exportHtmlBut;
    @FXML
    private Button importBut;
    @FXML
    private TextField newSerialNum;
    @FXML
    private TextField newValue;
    @FXML
    private TextField newName;
    @FXML
    private TextField editSerialNum;
    @FXML
    private TextField editName;
    @FXML
    private TextField editValue;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtLocation;
    @FXML
    private TextField htmlName;
    @FXML
    private TextField htmlLocation;
    @FXML
    private TextField importLocation;
    @FXML
    private TextField nameSearch;
    @FXML
    private TextField serialNumSearch;


    //creates the inventory for the tableView
    Inventory listInventory =  new Inventory();
    ObservableList<InventoryItem> listItems = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        //Set each of our columns to display the correct attribute of each InventoryItem Object in the TableView.
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, Double>("value"));
    }

    @FXML
    void addItemButClicked(Event event) {

        //check if the fields are filled
        if((newName.getText().length() < 2 || newName.getText().length() > 256) || (newSerialNum.getText().length() != 10) || (newValue.getText().isEmpty())) {
            newName.setText("");
            newSerialNum.setText("");
            newValue.setText("");
            return;
        }

        //sets new values
        String name = newName.getText();
        String serialNumber = newSerialNum.getText();
        double value = Double.parseDouble(newValue.getText());

        //checks for repeated serial numbers and does not allow anything to happen if it is repeatred
        boolean repeated = false;
        for(int i = 0; i < listInventory.itemsList.size(); i++) {
            if (serialNumber.equals(listInventory.itemsList.get(i).getSerialNum()))
                repeated = true;
        }
        if(repeated) {
            newName.setText("");
            newSerialNum.setText("");
            newValue.setText("");
            return;
        }


        //creates a new item and adds it with the new information
        InventoryItem temp = new InventoryItem(name, serialNumber, value);

        listInventory.addItem(temp);

        listItems.add(temp);
        inventoryTable.setItems(listItems);
        newName.setText("");
        newSerialNum.setText("");
        newValue.setText("");
    }

    @FXML
    void removeButClicked(Event event) {

        //gets the item to remove and calls the remove function then updates the table
        InventoryItem itemRemove = inventoryTable.getSelectionModel().getSelectedItem();
        String SNRemove = inventoryTable.getSelectionModel().getSelectedItem().getSerialNum();

        listInventory.removeItem(SNRemove);
        listItems.remove(itemRemove);

        inventoryTable.setItems(listItems);
    }

    @FXML
    void editItemButClicked(Event event) {
        String editItem = inventoryTable.getSelectionModel().getSelectedItem().getSerialNum();


        //checks for filled fields
        if((editName.getText().length() < 2 || editName.getText().length() > 256) || (editSerialNum.getText().length() != 10) || (editValue.getText().isEmpty())) {
            editName.setText("");
            editSerialNum.setText("");
            editValue.setText("");
            return;
        }


        //calls the edit attributes and gets the text
        String newName = editName.getText();
        String newSN = editSerialNum.getText();
        double newValue = Double.parseDouble(editValue.getText());



        //compares the serial numbers and finds the serial number thats repeated
        boolean repeated = false;
        for(int i = 0; i < listInventory.itemsList.size(); i++)
            if(newSN.equals(listInventory.itemsList.get(i).getSerialNum()))
                repeated = true;
        if(repeated) {
            editName.setText("");
            editSerialNum.setText("");
            editValue.setText("");
            return;
        }

        //calls the edit functions
        listInventory.editName(editItem, newName);
        listInventory.editValue(editItem, newValue);
        listInventory.editSerialNum(editItem, newSN);

        listItems.clear();

        //loops through and adds the items to the tableview
        for(int i = 0 ; i < listInventory.itemsList.size(); i++)
            listItems.add(listInventory.itemsList.get(i));

        editName.setText("");
        editSerialNum.setText("");
        editValue.setText("");
    }

    @FXML
    void exportTxtButClicked(Event event) {

        //checks for empty fields
        if(txtLocation.getText().isEmpty() || txtName.getText().isEmpty())
            return;


        //gets the text and exports it
        String location = txtLocation.getText();
        String name = txtName.getText();
        listInventory.exportTSV(location, name);

        txtLocation.setText("");
        txtName.setText("");
    }

    @FXML
    void exportHtmlButClicked(Event event) {
        //checks for empty fields
        if(htmlLocation.getText().isEmpty() || htmlName.getText().isEmpty())
            return;

        //gets text from fields and sets it
        String location = htmlLocation.getText();
        String name = htmlName.getText();
        listInventory.exportHTML(location, name);

        htmlLocation.setText("");
        htmlName.setText("");
    }

    @FXML
    void sortNameButClicked(Event event) {

        //sorts and updates list
        listInventory.sortName();
        listItems.clear();
        for(int i = 0 ; i < listInventory.itemsList.size(); i++)
            listItems.add(listInventory.itemsList.get(i));
    }

    @FXML
    void sortSerialNumButClicked(Event event) {

        //sorts and updates list
        listInventory.sortSerialNum();
        listItems.clear();
        for(int i = 0 ; i < listInventory.itemsList.size(); i++)
            listItems.add(listInventory.itemsList.get(i));
    }

    @FXML
    void sortValueButClicked(Event event) {

        //sorts and updates view
        listInventory.sortValue();
        listItems.clear();
        for(int i = 0 ; i < listInventory.itemsList.size(); i++)
            listItems.add(listInventory.itemsList.get(i));
    }

    @FXML
    void searchNameButClicked(Event event) {

        //saves name and searches for it then updates table
        String name = nameSearch.getText();
        if(name.length() > 2 && name.length() < 256) {
            if(listInventory.searchName(name).size() > 0) {
                listItems.clear();
                for(int i = 0; i < listInventory.searchName(name).size(); i++)
                    listItems.add(listInventory.searchName(name).get(i));
            }
        }

        nameSearch.setText("");
    }

    @FXML
    void searchSerialNumButClicked(Event event) {
        //saves the text checks for the sn and updates the list
        String sn = serialNumSearch.getText();
        if(sn.length() != 10) {
            if (listInventory.searchSerialNum(sn) != null) {
                listItems.clear();
                listItems.add(listInventory.searchSerialNum(sn));
            }
        }

        serialNumSearch.setText("");
    }

    @FXML
    void viewAllButClicked(Event event) {
        //sets the table view to all
        listItems.clear();
        for(int i = 0 ; i < listInventory.itemsList.size(); i++)
            listItems.add(listInventory.itemsList.get(i));
    }

    @FXML
    void importButClicked(Event event) {

    }
}
