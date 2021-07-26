package ucf.assignments;

public class InventoryItem {
//attributes
    String name;
    String serialNum;
    double value;


    //constructor that initializes the attributes
    public InventoryItem(String name, String serialNum, double value) {
        this.name = name;
        this.serialNum = serialNum;
        this.value = value;
    }

    //sets the name
    public void setName(String name) {
        this.name = name;
    }

    //sets the serial number
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    //sets the value
    public void setValue(double value) {
        this.value = value;
    }


    //returns the name
    public String getName() {
        return name;
    }

    //returns the serial number
    public String getSerialNum() {
        return serialNum;
    }

    //returns the value
    public double getValue() {
        return value;
    }

    @Override
    public String toString(){
        return getSerialNum() + "\t" + getName() + "\t$" + getValue();
    }


}
