/**
 * Created with IntelliJ IDEA.
 * User: bunkmaster
 * Date: 11/9/13
 * Time: 2:37 PM
 * To change this template use File | Settings | File Templates.
 */


public class Cook extends Thread {

    public  int cookNumber;
    private boolean isCookAvailable;
    public static  ManageOrders manageOrders;
    private ManageTables manageTables;
    private Order order;
    private Machine machine;

    //create locks for individual machines
    private static Object machineLock1;
    private static  Object machineLock2;
    private static Object machineLock3;

    public Object getMachineLock3() {
        return machineLock3;
    }

    public void setMachineLock3(Object machineLock3) {
        this.machineLock3 = machineLock3;
    }

    public Object getMachineLock2() {
        return machineLock2;
    }

    public void setMachineLock2(Object machineLock2) {
        this.machineLock2 = machineLock2;
    }

    public Object getMachineLock1() {
        return machineLock1;
    }

    public void setMachineLock1(Object machineLock1) {
        this.machineLock1 = machineLock1;
    }




    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
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

    public Cook(int i)
    {
       this.setCookNumber(i);
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run()
    {
        super.run();
        while(true)
        {
            try
            {
                //check if any order is present in the queue
                if(manageOrders.getOrderArrayQueue().size()>0)
                {
                    order = manageOrders.getOrder(0) ;
                    System.out.println("Cook " + this.getCookNumber() + " got order for diner: " + order.getCustomerNumber() );
                    processOrder(order);
                }
            }
            catch (Exception e)
            {
               System.out.println("Error in getting order for cook: " + this.getCookNumber() );
            }

        }
    }

    //process the orders in this function
    private void processOrder(Order order)
    {

        try
        {
            this.setCookAvailable(false);
            order.setCookNumber(this.getCookNumber());
            while(!order.isOrderDone())
            {
                if(!order.isBurgersDone())
                {
                    synchronized( machineLock1)
                    {
                    if(machine.isMachine1Available() ==true )
                    {
                    //    synchronized(getMachineLock1())
                        {
                            machine.getMachine1(this);
                            order.processingOrder("burger");
                          //  wait(machine.getMachine1WaitTime());
                            machine.releaseMachine1(this) ;
                        }
                    }
                   }
                }

                if(order.isFries() ==true )
                {
                    synchronized (machineLock2)
                    {
                    if(!order.isFriesDone())
                    {

                      {
                        if(machine.isMachine2Available() == true)
                        {
                            machine.getMachine2(this);
                            order.processingOrder("fries");
                            machine.releaseMachine2(this) ;


                      }
                    }
                }

               }
                }

                if(order.isCoke())
                {
                {
                    synchronized(machineLock1 )
                    {
                    if(!order.isCokeDone())
                    {

                        {
                            if(machine.isMachine3Available() ==true )
                            {
                                machine.getMachine3(this);
                                order.processingOrder("coke");
                                //this.wait(machine.getMachine3WaitTime());
                                machine.releaseMachine3(this);

                            }
                        }
                    }
                }
            }

            if(order.isOrderDone())
            {
              //need to do processing once order is finished
            }

           }
        }
            } catch (Exception e)
        {
             System.out.println("\nError processing diner order");
        }

    }


}
