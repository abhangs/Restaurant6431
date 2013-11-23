import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: bunkmaster
 * Date: 11/9/13
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
        */
public class MainClass {

   public static PriorityBlockingQueue<Diner> dinerPriorityQueue;
   public static BlockingQueue<Table> tableBlockingQueue;

    public static void main(String args[])
    {
        try {




            System.out.println("Program started at: " + TimeManager.getCurrentTime());
            Data data = PrepareData(args);
            InitiateThreads(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //function to prepare the data from input file
    public static Data PrepareData(String args[])
    {
        Data data = new Data();
        data.setDinerArrayList(new ArrayList<Diner>());
        data.setTableArrayList(new ArrayList<Table>());
        data.setCookArrayList(new ArrayList<Cook>());


        try
        {
            Scanner fileIn = new Scanner(new File("D:\\data1.txt"));
            data.setNumberOfDiners(Integer.parseInt(fileIn.next()));
            data.setNumberOfTables(Integer.parseInt(fileIn.next()));
            data.setNumberOfCooks(Integer.parseInt(fileIn.next()));

            tableBlockingQueue = new LinkedBlockingQueue<Table>(data.getNumberOfTables());
            dinerPriorityQueue = new PriorityBlockingQueue<Diner>(data.getNumberOfDiners(),new DinerComparator());

            //now process each diner order and arrival time
            String tempString = "";
            String tempStringArray[] = new String[data.getNumberOfDiners()];
            int key; int dinerNumberSeed;
            while (fileIn.hasNextLine())
            {
                tempString+=" "+fileIn.nextLine();
            }

            tempString = tempString.replaceAll("[\\s]+", " ");
            tempString = tempString.trim();
            tempStringArray = tempString.split(" ");
            key=0;  dinerNumberSeed = 0;
            while (key<tempStringArray.length)
            {
                dinerNumberSeed++;
                Diner diner = new Diner();
                Order order = new Order();
                diner.setDinerNumber(dinerNumberSeed);
                diner.setArrivalTime(Integer.parseInt(tempStringArray[key]));
                order.setDinerNumber(diner.getDinerNumber());
                order.setBurgers(Integer.parseInt(tempStringArray[key + 1]));
                order.setFries(Integer.parseInt(tempStringArray[key + 2]));
                order.setCoke(Integer.parseInt(tempStringArray[key + 3]));
                order.setIsCokeOrdered();
                order.setIsFriesOrdered();
                diner.setOrder(order);
                data.getDinerArrayList().add(diner);
                dinerPriorityQueue.add(diner);
                key = key+4;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error processing input file");
        }

        //initially passing dummy data

//        data.setNumberOfCooks(5);
//        data.setNumberOfDiners(10);
//        data.setNumberOfTables(5);



        //set the diners
        /*for (int i = 1; i <= data.getNumberOfDiners(); i++) {
            //Note: Customer number is not set here!!!
            Order o = new Order(1, 1, 1);
            Diner d = new Diner(i + 30, o);
            data.dinerArrayList.add(d);
        }*/

        //set the tables
        for (int i = 1; i <= data.getNumberOfTables(); i++) {
            Table t = new Table(i, true);
            data.tableArrayList.add(t);
            tableBlockingQueue.add(t);
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
            //c.setCookNumber(i);
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

       // i = 1;
        for (Diner d : data.getDinerArrayList()) {
            d.setDinerWaitTime(4000);
           // d.setDinerNumber(i);
           // d.getOrder().setDinerNumber(i);
            d.setManageTables(manageTables);
            d.setManageOrders(manageOrders);
            d.setSeated(false);

           // i++;
        }


        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(data.getNumberOfDiners());

        for(Diner d: data.getDinerArrayList())
            scheduledExecutorService.schedule(d,d.getArrivalTime(), TimeUnit.SECONDS);

        try {
            for (Cook c : data.getCookArrayList())
            {
                c.start();
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


//        for (Diner d : data.getDinerArrayList()) {
//            d.start();
//        }




    }


}

