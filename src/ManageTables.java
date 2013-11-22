import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: bunkmaster
 * Date: 11/10/13
 * Time: 1:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class ManageTables {

    public ArrayList<Table> getTableArrayList() {
        return tableArrayList;
    }

    public void setTableArrayList(ArrayList<Table> tableArrayList) {
        this.tableArrayList = tableArrayList;
    }

    ArrayList<Table> tableArrayList;

    public ManageTables(ArrayList<Table> numOfTables) {
        this.setTableArrayList(numOfTables);
    }


    public synchronized void setCustomer(Diner diner) {
        try {
            for (Table t : tableArrayList) {
                if (t.isAvailable() == true) {
                    t.setAvailable(false);
                    t.setCustomerNumber(diner.getDinerNumber());
                    diner.gotTable(t);
                    return;
                }
            }

//            if (diner.getTable() == null) {
//               // diner.wait();
//                System.out.println(diner.getDinerNumber() + " is waiting for a table");
//            }


        } catch (Exception e) {

        }
    }

    //implement code when diner is done eating
    public synchronized void doneEating(Diner diner) {
        try {
            for (Table t : tableArrayList) {
                if (t.getTableNumber() == diner.getTable().getTableNumber())
                    t.setAvailable(true);

                System.out.println("Diner " + diner.getDinerNumber() + " left table " + diner.getTable().getTableNumber()
                + " at " + TimeManager.getCurrentTime());

               // diner.notify();
                break;
            }
        } catch (Exception e) {

        }
    }




}
