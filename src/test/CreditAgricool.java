package test;

import DIC.InjectMe;

public class CreditAgricool extends Bank{

    public CreditAgricool(){
        super();
        setName("Crédit Agricool");
    }

    @InjectMe
    public CreditAgricool(@InjectMe(key="CreditAgricool.accountManagement") AccountManagement accountManagement, @InjectMe(key="CreditAgricool.postalCode") int postalCode) {
        super("Crédit Agricool", accountManagement, postalCode);
    }
}
