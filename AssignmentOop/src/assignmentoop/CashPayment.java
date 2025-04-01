/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentoop;


import java.util.Date;


class CashPayment extends Payment {
    private String cashReceivedBy;

    public CashPayment(int paymentId, Order order, double amount, Date paymentDate, String cashReceivedBy) {
        super(paymentId, order, amount, paymentDate);
        this.cashReceivedBy = cashReceivedBy;
    }
    
    public CashPayment() {
        super();
        this.cashReceivedBy = "Unknown";
    }

    public String getCashReceivedBy() {
        return cashReceivedBy;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing cash payment of amount: RM " + getAmount() + " received by " + cashReceivedBy);
    }
}
