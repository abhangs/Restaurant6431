/**
 * Created with IntelliJ IDEA.
 * User: bunkmaster
 * Date: 11/10/13
 * Time: 1:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class Table {

    int tableNumber;
    int customerNumber;
    boolean isAvailable;

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Table (int tableNumber , int customerNumber , boolean available)
    {
        this.setTableNumber(tableNumber );
        this.setCustomerNumber(customerNumber);
        this.setAvailable(available);
    }

    public Table (int tableNumber ,boolean available)
    {
        this.setTableNumber(tableNumber );
        this.setCustomerNumber(0);
        this.setAvailable(available);
    }

}

