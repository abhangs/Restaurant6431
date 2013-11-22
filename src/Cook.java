import javax.print.attribute.standard.DateTimeAtCompleted;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: bunkmaster
 * Date: 11/9/13
 * Time: 2:37 PM
 * To change this template use File | Settings | File Templates.
 */


public class Cook extends Thread {

    public static ManageOrders manageOrders;
    static MachineSingleton machineSingleton;
    public int cookNumber;
    private boolean isCookAvailable;
    private ManageTables manageTables;
    private Order order;


    public Cook(int i) {
        this.setCookNumber(i);
    }

    public MachineSingleton getMachineSingleton() {
        return machineSingleton;
    }

    public void setMachineSingleton(MachineSingleton machineSingleton) {
        this.machineSingleton = machineSingleton;
    }

    public boolean isCookAvailable() {
        return isCookAvailable;
    }

    public void setCookAvailable(boolean cookAvailable) {
        isCookAvailable = cookAvailable;
    }

    public int getCookNumber() {
        return cookNumber;
    }

    public void setCookNumber(int cookNumber) {
        this.cookNumber = cookNumber;
    }

    public ManageOrders getManageOrders() {
        return manageOrders;
    }

    public void setManageOrders(ManageOrders manageOrders) {
        this.manageOrders = manageOrders;
    }

    public ManageTables getManageTables() {
        return manageTables;
    }

    public void setManageTables(ManageTables manageTables) {
        this.manageTables = manageTables;
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        super.run();
        startProcessing();
    }

    public void startProcessing()
    {
        while (true) {
            try {
                //check if any order is present in the queue
                if (manageOrders.getOrderArrayQueue().size() > 0) {
                    order = manageOrders.getOrder(0);
                    System.out.println("Cook " + this.getCookNumber() + " got order for diner: " + order.getCustomerNumber()
                            + " at: " + TimeManager.getCurrentTime());
                    processOrder(order);
                }
            } catch (Exception e) {
                //System.out.println("Error in getting order for cook: " + this.getCookNumber());
            }

        }
    }


    //process the orders in this function
    public void processOrder(Order order) {

        try {
            //this.setCookAvailable(false);
            order.setCookNumber(this.getCookNumber());
            while (!order.isOrderDone()) {
                if (!order.isBurgersDone()) {
//                    synchronized(machineSingleton.getMachineLock1())
//                    {
//                    if(machineSingleton.isMachine1Available() ==true )
//                    {
//                    //    synchronized(getMachineLock1())
//                        {
//                            machineSingleton.getMachine1(this);
//                            order.processingOrder("burger");
//                          //  wait(machineSingleton.getMachine1WaitTime());
//                            machineSingleton.releaseMachine1(this) ;
//                        }
//                    }


                    synchronized (Cook.class) {
                        if (machineSingleton.isMachine1Available()) {
                            machineSingleton.getMachine1(this, order);
                        }
                    }

                }


                if (order.isFries() == true) {
//                    synchronized (machineSingleton.getMachineLock2())
//                    {
//                    if(!order.isFriesDone())
//                    {
//
//                      {
//                        if(machineSingleton.isMachine2Available() == true)
//                        {
//                            machineSingleton.getMachine2(this);
//                            order.processingOrder("fries");
//                            machineSingleton.releaseMachine2(this) ;
//
//
//                      }
//                    }
                    if (!order.isFriesDone()) {


                        synchronized (Cook.class) {
                            if (machineSingleton.isMachine2Available()) {
                                machineSingleton.getMachine2(this, order);
                            }

                        }
                    }

                }


                if (order.isCoke()) {
                    {
//                    synchronized(machineSingleton.getMachineLock3())
//                    {
//                    if(!order.isCokeDone())
//                    {
//
//                        {
//                            if(machineSingleton.isMachine3Available() ==true )
//                            {
//                                machineSingleton.getMachine3(this);
//                                order.processingOrder("coke");
//                                //this.wait(machineSingleton.getMachine3WaitTime());
//                                machineSingleton.releaseMachine3(this);
//
//                            }
//                        }
//                    }


                        if (!order.isCokeDone()) {
                            synchronized (Cook.class) {
                                if (machineSingleton.isMachine3Available())
                                    machineSingleton.getMachine3(this, order);
                            }
                        }

                    }
                }




            }
        } catch (Exception e) {
            //System.out.println("\nError processing diner order");
        }

        if (order.isOrderDone())
        {
            //need to do processing once order is finished
            order.setSetOrderDone(true);
            System.out.println("Cook "+ this.getCookNumber() + " has processed order of diner: "
                    + order.getCustomerNumber() + " at: " + TimeManager.getCurrentTime() );
        }

    }


}
