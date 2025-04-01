/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentoop;

import java.util.Date;

public class PayPalPayment extends Payment {
    private String email;

    public PayPalPayment(int paymentId, Order order, double amount, Date paymentDate, String email) {
        super(paymentId, order, amount, paymentDate);
        this.email = email;
    }
    
    public PayPalPayment() {
        this(0, null, 0.0, new Date(), ""); 
    }
    
    

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void processPayment() {
        System.out.println("\t\t\t\t\t\t\tProcessing PayPal payment of amount: " + getAmount());
        System.out.println("\t\t\t\t\t\t\tPayPal Email: " + email);
    }
}
