package ucf.assignments;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Comparator.comparing;

public class Inventory {

    ArrayList<InventoryItem> itemsList;

    //constructor initializing the attributes
    public Inventory() {
        itemsList = new ArrayList<InventoryItem>();
    }

    //returns the list
    public ArrayList<InventoryItem> getItems () {
        return itemsList;
    }

    //adds items to the list
    public void addItem(InventoryItem item) {
        itemsList.add(item);
    }


    public void removeItem(String serialNumber) {

        //search for the item and once it is found remove it from the arraylist
        for(int i = 0; i < itemsList.size(); i++)
            if(itemsList.get(i).getSerialNum().equals(serialNumber))
                itemsList.remove(i);
    }

    public void editName(String serialNumber, String newName) {
        //searches for the item by serial number since it is the only attribute that is completely unique and once it is found calls the set name function
        for(int i = 0; i < itemsList.size(); i++)
            if(itemsList.get(i).getSerialNum().equals(serialNumber))
                itemsList.get(i).setName(newName);
    }

    public void editSerialNum(String serialNum, String newSerialNum) {
        //loops through and finds the right item and calls the set serial number function
        for(int i = 0; i < itemsList.size(); i++)
            if(itemsList.get(i).getSerialNum().equals(serialNum))
                itemsList.get(i).setSerialNum(newSerialNum);
    }

    public void editValue(String serialNumber, double newValue) {
        //loops through and finds the correct serial number and then calls the set value function
        for(int i = 0; i < itemsList.size(); i++)
            if(itemsList.get(i).getSerialNum().equals(serialNumber))
                itemsList.get(i).setValue(newValue);
    }

    public void sortName(){
        //sorts by the name
        itemsList.sort(comparing(InventoryItem::getName));
    }

    public void sortSerialNum(){
        //sorts by the serial number
        itemsList.sort(comparing(InventoryItem::getSerialNum));
    }

    public void sortValue(){
        //sorts by the value
        itemsList.sort(comparing(InventoryItem::getValue));
    }

    public ArrayList<InventoryItem> searchName(String nameSearched) {
        ArrayList<InventoryItem> list = new ArrayList<InventoryItem>();

        //loops through list for all names that match searched name and adds them
        for(int i = 0; i < itemsList.size(); i++)
            if(itemsList.get(i).getName().equals(nameSearched))
                list.add(itemsList.get(i));

        return list;
    }

    public InventoryItem searchSerialNum(String serialNumSearched) {

        //loops through looping fo rthe unique serial number and returns it
        for(int i = 0; i < itemsList.size(); i++)
            if(itemsList.get(i).getSerialNum().equals(serialNumSearched))
                return itemsList.get(i);
        //returns null if its not found
        return null;
    }

    public String exportTSV(String fileLocation, String fileName) {

        //returns successful if it was export and failure if not
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileLocation + "/" + fileName + ".txt"));
            for(int i = 0; i < itemsList.size(); i++)
                bw.write(itemsList.get(i).toString() + "\n");
            bw.close();
            return "Successful";
        } catch (IOException e){
            return "Failure";
        }
    }

    public String exportHTML(String fileLocation, String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileLocation + "/" + fileName + ".html"));

            //sets up html file and opens tags
            bw.write("<html>\n");
            bw.write("<head>" + "\n \"" + fileName + "\"\n</head><br>\n");
            bw.write("<body>\n");

            //loops through and writes to the file
            for(int i = 0; i < itemsList.size(); i++)
                bw.write(itemsList.get(i).toString() + "<br>\n");

            //closes the tags and the writer
            bw.write("</body>\n");
            bw.write("</html>\n");
            bw.close();
            return "Successful";
        } catch (IOException e){
            return "Failure";
        }
    }

    public String importInventory(String fileLocation) {
        //looks for the file and imports it from the location
        return "";
    }
}
