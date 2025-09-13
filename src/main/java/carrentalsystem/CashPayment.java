/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrentalsystem;

/**
 *
 * @author VIRTUOSO
 */
public class CashPayment implements Payment {
    private double amountPaid;
    
    
    public CashPayment()
    {
        
    }
     public CashPayment(double amountPaid) {
        this.amountPaid = amountPaid;
    }
    
    
    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }
    
     @Override
     public boolean processPayment(double amount) {
        if (amountPaid >= amount) {
            double change = amountPaid - amount;
            System.out.println("Cash payment successful: $" + amount);
            if (change > 0) {
                System.out.println("Change returned: $" + change);
            
            }
            return true;
        } else {
            System.out.println("Insufficient cash provided. Payment failed.");
            return false;
        }
    }
    
}
