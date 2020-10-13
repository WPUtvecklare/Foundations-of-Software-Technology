/*
 * Date: 2020-09-08
 * File Name: Invoice.java
 * Author: Adam Bergman
 */

package ab224qr_assign1;

/**
 * Class Description: Represents an invoice for an item sold
 * @version 1.0
 * @author Adam Bergman
 */
public class Invoice {
    private String partNumber;
    private String description;
    private int quantity;
    private double price;

    /**
     * Constructor
     * @param partNumber part number
     * @param description description
     * @param quantity quantity
     * @param price price
     */
    public Invoice (String partNumber, String description, int quantity, double price) {
        this.partNumber = partNumber;
        this.description = description;
        this.setQuantity(quantity);
        this.setPrice(price);
    }

    /**
     * Get part number
     * @return the part number
     */
    public String getPartNumber () {
        return partNumber;
    }

    /**
     * Get description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get quantity
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Get price
     * @return the price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set part number
     * @param partNumber new part number
     */
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * Set description
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set quantity, set to 0 if smaller than 0
     * @param quantity new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity > 0 ? quantity : 0;
    }

    /**
     * Set price, set to 0 if smaller than 0
     * @param price new price
     */
    public void setPrice(double price) {
        this.price = price > 0.0 ? price : 0.0;
    }

    /**
     * Get invoice amount
     * @return the sum of price multiplied with quantity
     */
    public double getInvoiceAmount() {
        return price * quantity;
    }
}
