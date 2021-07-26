package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void addItem() {
        Inventory testInventory = new Inventory();
        InventoryItem testItem = new InventoryItem("Test", "1234567890", 100);
        testInventory.addItem(testItem);
        assertEquals(testInventory.itemsList.size(), 1);
    }

    @Test
    void removeItem() {
        Inventory testInventory = new Inventory();
        InventoryItem testItem = new InventoryItem("Test", "1234567890", 100);
        testInventory.addItem(testItem);
        testInventory.removeItem("1234567890");
        assertEquals(testInventory.itemsList.size(), 0);
    }

    @Test
    void editName() {
        Inventory testInventory = new Inventory();
        InventoryItem test = new InventoryItem("Test", "1234567890", 100);
        testInventory.addItem(test);
        testInventory.editName("1234567890", "Test2");
        assertEquals("Test2", testInventory.itemsList.get(0).getName());
    }

    @Test
    void editSerialNum() {
        Inventory testInventory = new Inventory();
        InventoryItem testItem = new InventoryItem("Test", "1234567890", 100);
        testInventory.addItem(testItem);
        testInventory.editSerialNum("1234567890", "1234567899");
        assertEquals("1234567899", testInventory.itemsList.get(0).getSerialNum());
    }

    @Test
    void editValue() {
        Inventory testInventory = new Inventory();
        InventoryItem testItem = new InventoryItem("Test", "1234567890", 100);
        testInventory.addItem(testItem);
        testInventory.editValue("1234567890", 200);
        assertEquals(200, testInventory.itemsList.get(0).getValue());
    }

    @Test
    void sortName() {
        Inventory testInventory = new Inventory();
        InventoryItem test = new InventoryItem("Test", "1234567890", 100);
        InventoryItem test2 = new InventoryItem("Test2", "1234567899", 200);
        testInventory.addItem(test2);
        testInventory.addItem(test);
        testInventory.sortName();
        assertEquals("Test", testInventory.itemsList.get(0).getName());

    }

    @Test
    void sortSerialNum() {
        Inventory testInventory = new Inventory();
        InventoryItem test = new InventoryItem("Test", "1234567890", 100);
        InventoryItem test2 = new InventoryItem("Test2", "1234567899", 200);
        testInventory.addItem(test2);
        testInventory.addItem(test);
        testInventory.sortSerialNum();
        assertEquals("1234567890", testInventory.itemsList.get(0).getSerialNum());
    }

    @Test
    void sortValue() {
        Inventory testInventory = new Inventory();
        InventoryItem test = new InventoryItem("Test", "1234567890", 100);
        InventoryItem test2 = new InventoryItem("Test2", "1234567899", 200);
        testInventory.addItem(test2);
        testInventory.addItem(test);
        testInventory.sortValue();
        assertEquals(100, testInventory.itemsList.get(0).getValue());
    }


    @Test
    void searchName() {
        Inventory testInventory = new Inventory();
        InventoryItem test = new InventoryItem("Test", "1234567890", 100);
        InventoryItem test2 = new InventoryItem("Test2", "1234567899", 200);
        testInventory.addItem(test2);
        testInventory.addItem(test);
        ArrayList<InventoryItem> temp = testInventory.searchName("Test");
        assertEquals("Test", temp.get(0).getName());
    }

    @Test
    void searchSerialNum() {
        Inventory testInventory = new Inventory();
        InventoryItem test = new InventoryItem("Test", "1234567890", 100);
        InventoryItem test2 = new InventoryItem("Test2", "1234567899", 200);
        testInventory.addItem(test2);
        testInventory.addItem(test);
        InventoryItem temp = testInventory.searchSerialNum("1234567890");
        assertEquals("Test", temp.getName());
    }

    @Test
    void exportTSV() {
        Inventory testInventory = new Inventory();
        InventoryItem test = new InventoryItem("TestItem", "1234567890", 100);
        InventoryItem test2 = new InventoryItem("TestItem2", "1234567899", 200);
        testInventory.addItem(test);
        testInventory.addItem(test2);
        String status = testInventory.exportTSV("src/test/resources", "test");
        assertEquals("Successful", status);
    }

    @Test
    void exportHTML() {
        Inventory testInventory = new Inventory();
        InventoryItem test = new InventoryItem("Test", "1234567890", 100);
        InventoryItem test2 = new InventoryItem("Test2", "1234567899", 200);
        testInventory.addItem(test);
        testInventory.addItem(test2);
        String status = testInventory.exportHTML("src/test/resources", "test");
        assertEquals("Successful", status);
    }
}