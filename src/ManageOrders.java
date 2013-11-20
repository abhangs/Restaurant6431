import com.sun.jmx.remote.internal.ArrayQueue;

/**
 * Created with IntelliJ IDEA.
 * User: bunkmaster
 * Date: 11/10/13
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManageOrders {
    //This class represents a common buffer to add and remover orders from a queue, orders to be added by customers and orders to be removed by cooks
    ArrayQueue<Order> orderArrayQueue;

    public ArrayQueue<Order> getOrderArrayQueue() {
        return orderArrayQueue;
    }

    public void setOrderArrayQueue(ArrayQueue<Order> orderArrayQueue) {
        this.orderArrayQueue = orderArrayQueue;
    }

    public ManageOrders(int numberOfCustomers) {
        orderArrayQueue = new ArrayQueue<Order>(numberOfCustomers);
    }


    //Order to be put in by customers
    public synchronized void putOrder(Order order) {
        this.orderArrayQueue.add(order);
    }

//    //Order to be removed by cook after processing the order
//    public synchronized void removeOrder()
//    {
//       this.orderArrayQueue.remove(0);
//    }

    //Get an order for processing
    public synchronized Order getOrder(int i) {
        return this.orderArrayQueue.remove(i);
    }


}
