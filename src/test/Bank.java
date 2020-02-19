package test;

import DIC.InjectMe;

@InjectMe
public class Bank {

    @InjectMe(key="Bank.name")
    private String name;
    @InjectMe(key="Bank.accountManagement")
    private AccountManagement accountManagement;
    @InjectMe(key="Bank.postalCode")
    private int postalCode;


    public Bank(){
        this.name = "Not defined";
        this.accountManagement = new AccountManagement();
    }

    //Plusieurs clés
    @InjectMe
    public Bank(@InjectMe(key="Bank.name")String name, @InjectMe(key="Bank.accountManangement") AccountManagement accountManagement, @InjectMe(key="Bank.postalCode") int postalCode){
        this.name = name;
        this.accountManagement = accountManagement;
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    @InjectMe(key="Bank.name")
    public void setName(String name) {
        this.name = name;
    }

    public AccountManagement getAccountManagement() {
        return accountManagement;
    }

    @InjectMe(key="Bank.accountManagement")
    public void setAccountManagement(AccountManagement accountManagement) {
        this.accountManagement = accountManagement;
    }

    @InjectMe(key="Bank.postalCode")
    public void setPostalCode(int pC) {
        this.postalCode = pC;
    }

    public double getAmount(){
        return accountManagement.getAmount();
    }

    public void setAmount(double amount){
        accountManagement.setAmount(amount);
    }

    public String toString(){
        return "Bank: { name = " + name +", " + accountManagement.toString() + ", " + postalCode + " }";
    }

}

