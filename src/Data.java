import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: bunkmaster
 * Date: 11/9/13
 * Time: 9:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Data {


    ArrayList<Cook> cookArrayList;
    ArrayList<Diner> dinerArrayList;
    ArrayList<Table> tableArrayList;
    int numberOfDiners;
    int numberOfTables;
    int numberOfCooks;


    public ArrayList<Cook> getCookArrayList() {
        return cookArrayList;
    }

    public void setCookArrayList(ArrayList<Cook> cookArrayList) {
        this.cookArrayList = cookArrayList;
    }

    public ArrayList<Diner> getDinerArrayList() {
        return dinerArrayList;
    }

    public void setDinerArrayList(ArrayList<Diner> dinerArrayList) {
        this.dinerArrayList = dinerArrayList;
    }

    public ArrayList<Table> getTableArrayList() {
        return tableArrayList;
    }

    public void setTableArrayList(ArrayList<Table> tableArrayList) {
        this.tableArrayList = tableArrayList;
    }

    public int getNumberOfDiners() {
        return numberOfDiners;
    }

    public void setNumberOfDiners(int numberOfDiners) {
        this.numberOfDiners = numberOfDiners;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    public int getNumberOfCooks() {
        return numberOfCooks;
    }

    public void setNumberOfCooks(int numberOfCooks) {
        this.numberOfCooks = numberOfCooks;
    }
}
