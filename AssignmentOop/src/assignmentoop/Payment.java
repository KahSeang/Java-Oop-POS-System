/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentoop;


import java.util.Date;

class Payment {
        final String ANSI_GREEN = "\u001B[32m";
    private int paymentId;
    private Order order;
    private double amount;
    private Date paymentDate;

    public Payment(int paymentId, Order order, double amount, Date paymentDate) {
        this.paymentId = paymentId;
        this.order = order;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }
    
   public Payment() {
        this(0, null, 0.0, new Date()); 
    }


    public int getPaymentId() {
        return paymentId;
    }

    public Order getOrder() {
        return order;
    }

    public double getAmount() {
        return amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void processPayment() {
        System.out.println(ANSI_GREEN + "\t\t\t\t\t\tProcessing payment of amount RM: " + amount);
    }
}
