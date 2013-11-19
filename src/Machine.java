import javax.print.attribute.standard.DateTimeAtProcessing;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: bunkmaster
 * Date: 11/11/13
 * Time: 1:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class Machine
{
    private boolean isMachine1Available;
    private boolean isMachine2Available;
    private boolean isMachine3Available;


    //machine wait time must be called by each cook thread to wait on particular machine function.
    private long machine1WaitTime;
    private long machine2WaitTime;
    private long machine3WaitTime;

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

    public Machine(long machine1Time,long machine2Time,long machine3Time)
    {
       this.setMachine1WaitTime(machine1Time) ;
       this.setMachine2WaitTime(machine2Time);
       this.setMachine3WaitTime(machine3Time);
    }


    public synchronized void getMachine1(Cook c)
    {
        try
        {
            this.setMachine1Available(false);
            System.out.println(c.getCookNumber()+ "got machine 1" + " " + new Date().getTime() );
            c.wait(this.machine1WaitTime);
        }

        catch (Exception e)
        {

        }

    }

    public synchronized void getMachine2(Cook c){

        try
        {
            this.setMachine2Available(false);
            c.wait(this.getMachine2WaitTime());
            System.out.println(c.getCookNumber() + "got machine 2" + " " + new Date().getTime() );
        }
        catch (Exception e)
        {

        }

    }

    public synchronized void getMachine3(Cook c){


        try
        {
            this.setMachine3Available(false);
            c.wait(this.machine3WaitTime);
            System.out.println(c.getCookNumber() + "got machine 3" + " " + new Date().getTime() );
        }
        catch (Exception e)
        {


        }
    }

    public synchronized void releaseMachine1(Cook c)
    {
         this.setMachine1Available(true);
        System.out.println(c.getCookNumber() + "released machine 1" + " " + new Date().getTime() );
    }

    public synchronized void releaseMachine2(Cook c)
    {
        this.setMachine2Available(true );
        System.out.println(c.getCookNumber() + "released machine 2" + " " + new Date().getTime() );
    }

    public synchronized void releaseMachine3(Cook c)
    {
        this.setMachine3Available(true);
        System.out.println(c.getCookNumber() + "released machine 3" + " " + new Date().getTime() );
    }
}
