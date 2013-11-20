import java.util.ArrayList;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: bunkmaster
 * Date: 11/9/13
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainClass {

    public static void main(String args[]) {
        Data data = PrepareData();
        InitiateThreads(data);
    }

    //function to prepare the data from input file
    public static Data PrepareData() {

        //initially passing dummy data
        Data data = new Data();
        data.setNumberOfCooks(5);
        data.setNumberOfDiners(10);
        data.setNumberOfTables(5);
        data.setDinerArrayList(new ArrayList<Diner>());
        data.setTableArrayList(new ArrayList<Table>());
        data.setCookArrayList(new ArrayList<Cook>());


        //set the diners
        for (int i = 1; i <= data.getNumberOfDiners(); i++) {
            //Note: Customer number is not set here!!!
            Order o = new Order(1, 1, 1);
            Diner d = new Diner(i + 30, o);
            data.dinerArrayList.add(d);
        }

        //set the tables
        for (int i = 1; i <= data.getNumberOfTables(); i++) {
            Table t = new Table(i, true);
            data.tableArrayList.add(t);
        }

        //set the cooks
        for (int i = 1; i <= data.getNumberOfCooks(); i++) {
            Cook c = new Cook(i);
            data.cookArrayList.add(c);
        }

        return data;
    }

    //function to initiate all the threads
    public static void InitiateThreads(Data data) {
        int i = 1;
        //Set the tables and set the order common buffer queue based on the number of diners
        ManageTables manageTables = new ManageTables(data.getTableArrayList());
        ManageOrders manageOrders = new ManageOrders(data.getNumberOfDiners());

        //create the lock objects for cook class , same lock object on different threads
//        Object machineLock1 = new Object();
//        Object machineLock2 = new Object();
//        Object machineLock3 = new Object();
//
//
//        //the machineSingleton wait time for each machineSingleton in long format.
//        MachineSingleton machineSingleton = new MachineSingleton(3000,2000,1000,machineLock1,machineLock2,machineLock3);
//        machineSingleton.setMachine1Available(true);
//        machineSingleton.setMachine2Available(true);
//        machineSingleton.setMachine3Available(true);


        //set the machineSingleton object for each cook

        for (Cook c : data.getCookArrayList()) {
            c.setCookNumber(i);
            c.setMachineSingleton(MachineSingleton.getInstance());
            c.getMachineSingleton().setMachine1WaitTime(3000);
            c.getMachineSingleton().setMachine2WaitTime(2000);
            c.getMachineSingleton().setMachine3WaitTime(1000);
            c.setManageTables(manageTables);
            c.setManageOrders(manageOrders);
            i++;
        }

//        for(Cook c: data.getCookArrayList() )
//        {
//            c.start();
//        }

        //first sort the diners according to input time
        //Note: Set the customer number here!!!


        Collections.sort(data.getDinerArrayList(), new DinerComparator());

        i = 1;
        for (Diner d : data.getDinerArrayList()) {
            d.setDinerWaitTime(30000);
            d.setCustomerNumber(i);
            d.getOrder().setCustomerNumber(i);
            d.setManageTables(manageTables);
            d.setManageOrders(manageOrders);
            i++;
        }

        for (Diner d : data.getDinerArrayList()) {
            d.run();
        }


        for (Cook c : data.getCookArrayList()) {
            c.start();
        }


    }


}

