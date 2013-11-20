/**
 * Created with IntelliJ IDEA.
 * User: bunkmaster
 * Date: 11/9/13
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Order {

    int customerNumber;
    int cookNumber;

    int burgers;
    int fries;
    int coke;

    boolean burgersDone;
    boolean friesDone;
    boolean cokeDone;

    boolean isFriesOrdered;
    boolean isCokeOrdered;

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getCookNumber() {
        return cookNumber;
    }

    public void setCookNumber(int cookNumber) {
        this.cookNumber = cookNumber;
    }

    public int getBurgers() {
        return burgers;
    }

    public void setBurgers(int burgers) {
        this.burgers = burgers;
    }

    public int getFries() {
        return fries;
    }

    public void setFries(int fries) {
        this.fries = fries;
    }

    public int getCoke() {
        return coke;
    }

    public void setCoke(int coke) {
        this.coke = coke;
    }

    public boolean isBurgersDone() {
        return burgersDone;
    }

    public void setBurgersDone(boolean burgersDone) {
        this.burgersDone = burgersDone;
    }

    public boolean isFriesDone() {
        return friesDone;
    }

    public void setFriesDone(boolean friesDone) {
        this.friesDone = friesDone;
    }

    public boolean isCokeDone() {
        return cokeDone;
    }

    public void setCokeDone(boolean cokeDone) {
        this.cokeDone = cokeDone;
    }

    public Order(int customerNum, int cookNum, int burgers, int fries, int coke)

    {
        this.customerNumber = customerNum;
        this.cookNumber = cookNum;
        this.burgers = burgers;
        this.fries = fries;
        this.coke = coke;
    }

    public Order(int customerNum, int burgers, int fries, int coke)

    {
        this.customerNumber = customerNum;
        this.cookNumber = 0;
        this.burgers = burgers;
        this.fries = fries;
        this.coke = coke;
    }

    public Order(int burgers, int fries, int coke)

    {
        this.customerNumber = 0;
        this.cookNumber = 0;
        this.burgers = burgers;
        this.fries = fries;
        this.coke = coke;
        if (fries > 0)
            isFriesOrdered = true;
        if (coke > 0)
            isCokeOrdered = true;
    }


    //check if fries are ordered
    public Boolean isFries()

    {
        if (!isFriesOrdered)
            return false;
        else
            return true;
    }

    //check if coke is ordered
    public Boolean isCoke() {
        if (!isCokeOrdered)
            return false;
        else
            return true;
    }

    //check if the processing of order is complete
    public boolean isOrderDone() {
        //need to do this for different combination of orders
        //can implement a stack here to check for orders done
        if (isBurgersDone() == true) {
            if (isFries() == true) {
                if (isCoke() == true) {
                    if (isCokeDone() == true && isFriesDone() == true)
                        return true;

                    else
                        return false;
                }

                if (isFriesDone() == true)
                    return true;
                else return false;

            }

            if (isCoke() == true) {
                if (isCokeDone() == true)
                    return true;
                else
                    return false;
            }

        }

        return false;
    }

    //set values accordingly for processing each item
    public void processingOrder(String itemType) {
        try {
            if (itemType.toLowerCase().equals("burger")) {
                burgers--;
                if (burgers == 0)
                    setBurgersDone(true);
            }
            if (itemType.toLowerCase().equals("fries")) {
                if (isFries() == true) {
                    fries--;
                    if (fries == 0)
                        setFriesDone(true);
                }
            }

            if (itemType.toLowerCase().equals("coke")) {
                if (isCoke() == true) {
                    coke--;
                    if (coke == 0)
                        setCokeDone(true);

                }
            }
        } catch (Exception e) {
            System.out.println("error processing order!");
        }


    }


}
