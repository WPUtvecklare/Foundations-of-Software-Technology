package ab224qr_assign1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvoiceTest {
    private Invoice sut;
    private String partNumber;
    private String description;
    private int quantity;
    private double price;

    @BeforeEach
    void setUp() {
        this.partNumber = "1ASC";
        this.description = "Lorem ipsum dolor";
        this.quantity = 2;
        this.price = 3.55;
        this.sut = new Invoice(this.partNumber, this.description, this.quantity, this.price);
    }

    @Test
    void getPartNumber() {
        Assertions.assertEquals(this.partNumber, sut.getPartNumber());
    }

    @Test
    void getDescription() {
        Assertions.assertEquals(this.description, sut.getDescription());
    }

    @Test
    void getQuantity() {
        Assertions.assertEquals(this.quantity, sut.getQuantity());
    }

    @Test
    void getPrice() {
        Assertions.assertEquals(this.price, sut.getPrice());
    }

    @Test
    void setPartNumber() {
        String newPartNumber = "019CD";
        sut.setPartNumber(newPartNumber);
        Assertions.assertEquals(newPartNumber, sut.getPartNumber());
    }

    @Test
    void setDescription() {
        String newDescription = "Dolor lorem ipsum";
        sut.setDescription(newDescription);
        Assertions.assertEquals(newDescription, sut.getDescription());
    }

    @Test
    void setQuantity() {
        int newQuantity = 4;
        sut.setQuantity(newQuantity);
        Assertions.assertEquals(newQuantity, sut.getQuantity());
    }

    @Test
    void shouldSetQuantityToZero() {
        int newQuantity = -2;
        sut.setQuantity(newQuantity);
        Assertions.assertEquals(0, sut.getQuantity());
    }

    @Test
    void setPrice() {
        double newPrice = 7.15;
        sut.setPrice(newPrice);
        Assertions.assertEquals(newPrice, sut.getPrice());
    }

    @Test
    void shouldSetPriceToZero() {
        int newPrice = -2;
        sut.setPrice(newPrice);
        Assertions.assertEquals(0.0, sut.getPrice());
    }

    @Test
    void getInvoiceAmount() {
        double expectedTotal = this.price * this.quantity;
        Assertions.assertEquals(expectedTotal, sut.getInvoiceAmount());
    }

    @Test
    void shouldReturnZeroAsInvoiceAmountWhenPriceIsZero() {
        double newPrice = 0.0;
        sut.setPrice(newPrice);
        Assertions.assertEquals(newPrice, sut.getInvoiceAmount());
    }

    @Test
    void shouldReturnZeroAsInvoiceAmountWhenQuantityIsZero() {
        int newQuantity = 0;
        sut.setQuantity(newQuantity);
        Assertions.assertEquals(0.0, sut.getInvoiceAmount());
    }
}