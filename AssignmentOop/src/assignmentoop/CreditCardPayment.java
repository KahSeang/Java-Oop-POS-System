/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentoop;


import java.util.Date;

public class CreditCardPayment extends Payment {
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(int paymentId, Order order, double amount, Date paymentDate, String cardNumber, String cardHolderName) {
        super(paymentId, order, amount, paymentDate);
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }
    
    public CreditCardPayment() {
        this(0, null, 0.0, new Date(), "", ""); 
    }

    public String getCardNumber() {
        return cardNumber;
    }

  
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }


    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing credit card payment of amount: " + getAmount());
        System.out.println("\t\t\t\t\t\t\tCard Number: " + cardNumber);
        System.out.println("\t\t\t\t\t\t\tCard Holder Name: " + cardHolderName);
    }
}
