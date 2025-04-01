/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentoop;

import java.util.Date;


class EWalletPayment extends Payment {
    private String eWalletProvider;
    private String transactionId;

    public EWalletPayment(int paymentId, Order order, double amount, Date paymentDate, String eWalletProvider, String transactionId) {
        super(paymentId, order, amount, paymentDate);
        this.eWalletProvider = eWalletProvider;
        this.transactionId = transactionId;
    }
    
    public EWalletPayment() {
        super();
        this.eWalletProvider = "Unknown";
        this.transactionId = "N/A";
    }

    public String getEWalletProvider() {
        return eWalletProvider;
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing e-wallet payment of amount: RM " + getAmount() + " via " + eWalletProvider + " with transaction ID " + transactionId);
    }
}
