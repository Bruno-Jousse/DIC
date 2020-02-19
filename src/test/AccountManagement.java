package test;

import DIC.InjectMe;

public class AccountManagement {

    @InjectMe(key="AccountManagement.amount")
    private double amount;

    public AccountManagement() {
        amount= 0.0;
    }

    @InjectMe
    public AccountManagement(@InjectMe(key="AccountManagement.amount") double a){
        amount= a;
    }

    void addAmount(double val){
        amount += val;
    }

    void subAmount(double val){
        amount -= val;
    }

    public double getAmount() {
        return amount;
    }

    @InjectMe(key="AccountManagement.amount")
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String toString(){
        return "Account management: { amount = "+ amount + " }";
    }
}
