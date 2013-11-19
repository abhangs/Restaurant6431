import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: bunkmaster
 * Date: 11/9/13
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class DinerComparator implements Comparator <Diner> {
    @Override
    public int compare(Diner o1, Diner o2) {
        if(o1.getArrivalTime() < o2.getArrivalTime() )
            return -1;
        else if(o2.getArrivalTime() <o1.getArrivalTime() )
            return 1;
        else
            return 0;


    }
}
