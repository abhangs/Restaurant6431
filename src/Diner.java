import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: bunkmaster
 * Date: 11/9/13
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Diner extends Thread {

    private  int customerNumber;
    private int arrivalTime;
    private Order order;
    private Table table;
    public static  ManageTables manageTables;
    public static ManageOrders manageOrders;
    private long dinerWaitTime;
    private boolean running;





    //Properties

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }


    public long getDinerWaitTime() {
        return dinerWaitTime;
    }

    public void setDinerWaitTime(long dinerWaitTime) {
        this.dinerWaitTime = dinerWaitTime;
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




    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Diner(int customerNum, int arrivalTime, Order order)
    {
        this.customerNumber = customerNum ;
        this.arrivalTime = arrivalTime ;
        this.order = order;
    }

    public Diner( int arrivalTime, Order order)
    {
        this.customerNumber = 0 ;
        this.arrivalTime = arrivalTime ;
        this.order = order;

    }



    //invoke this function when customer gets table and pass on the order to a common buffer
    public void gotTable(Table table)
    {
        try
        {
            //put the order in the queue, the customerNumber for the order is set in InitiateThreads function
            this.table = table;
            this.manageOrders.putOrder(order);
            System.out.println(this.customerNumber + " has got table " + this.table.getTableNumber() +
            " and set the order: burgers:  " +order.burgers + " fries: " + order.fries + " coke: " + order.coke );
        }
        catch (Exception e)
        {
           System.out.println("Error in setting the order");
        }

    }

    //this function is to be invoked by the cook when order of the customer is complete
    public void orderComplete()
    {
        try
        {
            this.wait(getDinerWaitTime());
            manageTables.doneEating(this);
            running = false;
           // this.join();
        }
        catch (Exception e)
        {

        }

    }

    @Override
    public synchronized void start() {
        super.start();
        System.out.println("Diner Thread: " + this.customerNumber + " has started");

    }


    @Override
    public void run()
    {
            super.run();
            this.manageTables.setCustomer(this);
    }
}
