import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: bunkmaster
 * Date: 11/11/13
 * Time: 1:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class MachineSingleton {
    private boolean isMachine1Available;
    private boolean isMachine2Available;
    private boolean isMachine3Available;

    //create locks for individual machines
    private static final Object machineLock1 = new Object();
    private static final Object machineLock2 = new Object();
    private static final Object machineLock3 = new Object();


    //machine wait time must be called by each cook thread to wait on particular machine function.
    private long machine1WaitTime;
    private long machine2WaitTime;
    private long machine3WaitTime;

    public Object getMachineLock3() {
        return machineLock3;
    }

    public Object getMachineLock1() {
        return machineLock1;
    }

    public boolean isMachine1Available() {
        return isMachine1Available;
    }

    public void setMachine1Available(boolean machine1Available) {
        isMachine1Available = machine1Available;
    }

    public boolean isMachine2Available() {
        return isMachine2Available;
    }

    public void setMachine2Available(boolean machine2Available) {
        isMachine2Available = machine2Available;
    }

    public boolean isMachine3Available() {
        return isMachine3Available;
    }

    public void setMachine3Available(boolean machine3Available) {
        isMachine3Available = machine3Available;
    }

    public long getMachine1WaitTime() {
        return machine1WaitTime;
    }

    public void setMachine1WaitTime(long machine1WaitTime) {
        this.machine1WaitTime = machine1WaitTime;
    }

    public long getMachine2WaitTime() {
        return machine2WaitTime;
    }

    public void setMachine2WaitTime(long machine2WaitTime) {
        this.machine2WaitTime = machine2WaitTime;
    }

    public long getMachine3WaitTime() {
        return machine3WaitTime;
    }

    public void setMachine3WaitTime(long machine3WaitTime) {
        this.machine3WaitTime = machine3WaitTime;
    }


    //Constructors
//    public MachineSingleton(long machine1Time, long machine2Time, long machine3Time)
//    {
//       this.setMachine1WaitTime(machine1Time) ;
//       this.setMachine2WaitTime(machine2Time);
//       this.setMachine3WaitTime(machine3Time);
//    }
//
//    public MachineSingleton(long machine1Time, long machine2Time, long machine3Time, Object machineLock1, Object machineLock2, Object machineLock3)
//    {
//        this.setMachine1WaitTime(machine1Time) ;
//        this.setMachine2WaitTime(machine2Time);
//        this.setMachine3WaitTime(machine3Time);
//        this.setMachineLock1(machineLock1);
//        this.setMachineLock2(machineLock2);
//        this.setMachineLock3(machineLock3);
//
//    }

    /* singleton method instantiation and declaration*/

    private static final MachineSingleton INSTANCE = new MachineSingleton();

    private MachineSingleton() {

        setMachine1Available(true);
        setMachine2Available(true);
        setMachine3Available(true);

        if (INSTANCE != null) {
            throw new IllegalStateException("Machine already instantiated");
        }
    }

    public static MachineSingleton getInstance() {
        return INSTANCE;
    }


    public void getMachine1(Cook c, Order o) {
        try {
            synchronized (Cook.class) {
                this.setMachine1Available(false);
                System.out.println("Cook " + c.getCookNumber() + " got machine 1 " + " " + TimeManager.getCurrentTime());
                Cook.class.wait(this.machine1WaitTime);
                o.processingOrder("burger");
                this.setMachine1Available(true);
              //  System.out.println("Cook " + c.getCookNumber() + " released machine 1 " + " " + TimeManager.getCurrentTime());

            }
        } catch (Exception e) {


        }

    }

    public void getMachine2(Cook c, Order o) {

        try {
            synchronized (Cook.class) {
                this.setMachine2Available(false);
                System.out.println("Cook " + c.getCookNumber() + " got machine 2 " + " " +TimeManager.getCurrentTime());
                Cook.class.wait(this.getMachine2WaitTime());
                o.processingOrder("fries");
                this.setMachine2Available(true);
               // System.out.println("Cook " + c.getCookNumber() + " released machine 2 " + " " + TimeManager.getCurrentTime());
            }
        } catch (Exception e) {

        }

    }

    public void getMachine3(Cook c, Order o) {
        try {
            synchronized (Cook.class) {
                this.setMachine3Available(false);
                System.out.println("Cook " + c.getCookNumber() + " got machine 3 " + " " + TimeManager.getCurrentTime());
                Cook.class.wait(this.machine3WaitTime);
                o.processingOrder("coke");
                this.setMachine3Available(true);
               // System.out.println("Cook " + c.getCookNumber() + " released machine 3 " + " " + TimeManager.getCurrentTime());
            }
        } catch (Exception e) {


        }
    }

    public synchronized void releaseMachine1(Cook c) {
        this.setMachine1Available(true);
        System.out.println(c.getCookNumber() + "released machine 1" + " " + new Date().getTime());
    }

    public synchronized void releaseMachine2(Cook c) {
        this.setMachine2Available(true);
        System.out.println(c.getCookNumber() + "released machine 2" + " " + new Date().getTime());
    }

    public synchronized void releaseMachine3(Cook c) {
        this.setMachine3Available(true);
        System.out.println(c.getCookNumber() + "released machine 3" + " " + new Date().getTime());
    }
}
