/**
 * Created with IntelliJ IDEA.
 * User: bunkmaster
 * Date: 11/9/13
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Diner implements Runnable{

    private int dinerNumber;
    private int arrivalTime;
    private Order order;
    private Table table;
    public static ManageTables manageTables;
    public static ManageOrders manageOrders;
    private long dinerWaitTime;
    boolean isSeated;



   public boolean isSeated() {
        return isSeated;
    }

    public void setSeated(boolean seated) {
        isSeated = seated;
    }

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


    public int getDinerNumber() {
        return dinerNumber;
    }

    public void setDinerNumber(int dinerNumber) {
        this.dinerNumber = dinerNumber;
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

    public Diner(int customerNum, int arrivalTime, Order order) {
        this.dinerNumber = customerNum;
        this.arrivalTime = arrivalTime;
        this.order = order;
    }

    public Diner(int arrivalTime, Order order) {
        this.dinerNumber = 0;
        this.arrivalTime = arrivalTime;
        this.order = order;

    }

    public Diner()
    {}


    //invoke this function when customer gets table and pass on the order to a common buffer
    public void gotTable(Table table) {
        try {
            //put the order in the queue, the dinerNumber for the order is set in InitiateThreads function
            this.table = table;
            System.out.println(this.dinerNumber + " has got table " + this.table.getTableNumber() +
                    " and set the order: burgers:  " + order.burgers + " fries: " + order.fries + " coke: " + order.coke
                    + " at: " + TimeManager.getCurrentTime());
            this.manageOrders.putOrder(order);
            this.setSeated(true);

        } catch (Exception e) {
            System.out.println("Error in setting the order");
        }

    }

    //this function is to be invoked by the cook when order of the customer is complete
    public void orderComplete() {
        try
        {
            synchronized (Diner.class)
            {
            wait(getDinerWaitTime());
            manageTables.doneEating(this);
            System.out.println(this.getDinerNumber() + " has done eating and left table");
            //running = false;
            //this.join();
            }
        } catch (Exception e)
        {

        }

    }

    //@Override
//    public synchronized void start() {
//        //super.start();
//        System.out.println("Diner Thread: " + this.dinerNumber + " has started at " + TimeManager.getCurrentTime());
//
//    }


    //@Override
    public void run()
    {
        //super.run();
        try
        {
            System.out.println("Diner Thread: " + this.dinerNumber + " has started at " + TimeManager.getCurrentTime());

//            while (!this.isSeated())
//            {
//                this.getManageTables().setCustomer(this);
//            }
//
//            synchronized (Diner.class)
//            {
//            this.gotTable(this.getTable());
//            }

            while (true)
            {
                  if(MainClass.dinerPriorityQueue.peek().equals(this))
                  {
                       this.setTable(MainClass.tableBlockingQueue.take());
                       break;
                  }

            }

            this.gotTable(this.table);
            MainClass.dinerPriorityQueue.remove();

            while (!order.isSetOrderDone())
            {

            }

            synchronized (Diner.class)
            {
            System.out.println("Cook "+ this.order.getCookNumber() + " has processed order of diner: "
                    + order.getDinerNumber() + " at: " + TimeManager.getCurrentTime() );
            System.out.println("Diner " + this.getDinerNumber() + " started eating at: "+ TimeManager.getCurrentTime());

            }
            Thread.sleep(4000);
//
//            synchronized (this)
//            {
//                wait(4000);
//            }
//
            System.out.println("Diner " + this.dinerNumber+ " left table " + this.table.getTableNumber()
                    + " at " + TimeManager.getCurrentTime());


            MainClass.tableBlockingQueue.offer(this.getTable());

             //this.getManageTables().doneEating(this);

        }
        catch (Exception e)
        {

        }


    }
}
