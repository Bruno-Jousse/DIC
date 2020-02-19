package test;

import DIC.Container;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestContainer {

    @Test
    public final void injectUsingFieldsTest(){
        Container container = new Container();

        System.out.println("--- Inject using fields ---");

        System.out.println("--- Test AccountManagement ---");

        container.register("AccountManagement.amount", 1000.0);

        AccountManagement accManTest = (AccountManagement) container.getInstanceInjectFields(AccountManagement.class);
        AccountManagement accManDefault = new AccountManagement(1000);

        System.out.println("Actual:\t\t" + accManTest.toString() + "\nExpected:\t" + accManDefault.toString());

        assertEquals(accManDefault.toString(), accManTest.toString(), "Account management generated must be equals to the expected one.");

        System.out.println("--- Test CreditAgricool ---");

        AccountManagement accMan = new AccountManagement();
        accMan.setAmount(2000);

        container.register("Bank.postalCode", 63000);
        container.register("Bank.accountManagement", accMan);
        container.register("Bank.name", "Crédit Agricool");

        CreditAgricool cATest = (CreditAgricool) container.getInstanceInjectFields(CreditAgricool.class);
        CreditAgricool cA = new CreditAgricool(accMan, 63000);

        System.out.println("Actual:\t\t" + cATest.toString() + "\nExpected:\t" + cA.toString());

        assertEquals(cA.toString(), cATest.toString(), "CreditAgricool generated must be equals to the expected one.");

        System.out.println("--- Test Bank ---");

        container.register("test.Bank", "test.CreditAgricool");

        Bank b = (Bank)  container.getInstanceInjectFields(Bank.class);

        System.out.println("Actual:\t\t" + ((CreditAgricool) b).toString() + "\nExpected:\t" + cA.toString());

        assertEquals(cA.toString(), ((CreditAgricool)b).toString(), "Bank generated casted to CreditAgricool must be equals to the expected CreditAgricool.");
    }

    @Test
    public final void injectUsingSettersTest(){
        Container container = new Container();

        System.out.println("--- Inject using setters ---");

        System.out.println("--- Test AccountManagement ---");

        container.register("AccountManagement.amount", 1000.0);

        AccountManagement accManTest = (AccountManagement) container.getInstanceInjectSetters(AccountManagement.class);
        AccountManagement accManDefault = new AccountManagement(1000);

        assertEquals(accManDefault.toString(), accManTest.toString(), "Account management generated must be equals to the expected one.");

        System.out.println("Actual:\t\t" + accManTest.toString() + "\nExpected:\t" + accManDefault.toString());

        System.out.println("--- Test CreditAgricool ---");

        AccountManagement accMan = new AccountManagement();
        accMan.setAmount(2000);

        container.register("Bank.postalCode", 63000);
        container.register("Bank.accountManagement", accMan);
        container.register("Bank.name", "Crédit Agricool");

        CreditAgricool cATest = (CreditAgricool) container.getInstanceInjectSetters(CreditAgricool.class);
        CreditAgricool cA = new CreditAgricool(accMan, 63000);

        System.out.println("Actual:\t\t" + cATest.toString() + "\nExpected:\t" + cA.toString());

        assertEquals(cA.toString(), cATest.toString(), "CreditAgricool generated must be equals to the expected one.");

        System.out.println("--- Test Bank ---");

        container.register("test.Bank", "test.CreditAgricool");

        Bank b = (Bank)  container.getInstanceInjectSetters(Bank.class);

        System.out.println("Actual:\t\t" + ((CreditAgricool) b).toString() + "\nExpected:\t" + cA.toString());

        assertEquals(cA.toString(), ((CreditAgricool)b).toString(), "Bank generated casted to CreditAgricool must be equals to the expected CreditAgricool.");
    }

    @Test
    public final void injectUsingConstructorTest() {
        Container container = new Container();

        System.out.println("--- Inject using constructor ---");

        System.out.println("--- Test AccountManagement ---");

        container.register("AccountManagement.amount", 1000.0);

        AccountManagement accManTest = (AccountManagement) container.getInstanceInjectConstructor(AccountManagement.class);
        AccountManagement accManDefault = new AccountManagement(1000);

        assertEquals(accManDefault.toString(), accManTest.toString(), "Account management generated must be equals to the expected one.");

        System.out.println("Actual:\t\t" + accManTest.toString() + "\nExpected:\t" + accManDefault.toString());

        System.out.println("--- Test CreditAgricool ---");

        AccountManagement accMan = new AccountManagement();
        accMan.setAmount(2000);

        //Annotations for constructor only
        container.register("CreditAgricool.accountManagement", accMan);
        container.register("CreditAgricool.postalCode", 63000);

        CreditAgricool cATest = (CreditAgricool) container.getInstanceInjectConstructor(CreditAgricool.class);
        CreditAgricool cA = new CreditAgricool(accMan, 63000);

        System.out.println("Actual:\t\t" + cATest.toString() + "\nExpected:\t" + cA.toString());

        assertEquals(cA.toString(), cATest.toString(), "CreditAgricool generated must be equals to the expected one.");

        System.out.println("--- Test Bank ---");

        container.register("test.Bank", "test.CreditAgricool");

        Bank b = (Bank)  container.getInstanceInjectConstructor(Bank.class);

        System.out.println("Actual:\t\t" + ((CreditAgricool) b).toString() + "\nExpected:\t" + cA.toString());

        assertEquals(cA.toString(), ((CreditAgricool)b).toString(), "Bank generated casted to CreditAgricool must be equals to the expected CreditAgricool.");
    }

    @Test
    public final void noAnnotationsUsingFieldsTest(){
        Container container = new Container();

        System.out.println("--- No annotations specified using fields ---");

        Boursolama bBTest= (Boursolama) container.getInstanceInjectFields(Boursolama.class);
        Boursolama bB = new Boursolama();
        bB.setName("");

        System.out.println("Actual:\t\t" + bBTest.toString() + "\nExpected:\t" + bB.toString());

        assertEquals(bB.toString(), bBTest.toString(), "Boursolama generated must be equals to the expected one.");
    }

    @Test
    public final void noAnnotationsSpecifiedUsingSettersTest() {
        Container container = new Container();

        System.out.println("--- No annotations specified using setters ---");

        Boursolama bBTest= (Boursolama) container.getInstanceInjectSetters(Boursolama.class);
        Boursolama bB = new Boursolama();
        bB.setName("");

        System.out.println("Actual:\t\t" + bBTest.toString() + "\nExpected:\t" + bB.toString());

        assertEquals(bB.toString(), bBTest.toString(), "Boursolama generated must be equals to the expected one.");
    }

    @Test
    public final void noAnnotationsSpecifiedUsingConstructor(){
        Container container = new Container();

        System.out.println("--- No annotations specified using constructor ---");

        Boursolama bBTest= (Boursolama) container.getInstanceInjectConstructor(Boursolama.class);
        Boursolama bB = new Boursolama();

        System.out.println("Actual:\t\t" + bBTest.toString() + "\nExpected:\t" + bB.toString());

        assertEquals(bB.toString(), bBTest.toString(), "Boursolama generated must be equals to the expected one.");
    }


    @Test
    public final void noRegisterUsingFieldsTest(){
        Container container = new Container();

        System.out.println("--- No registration specified using fields ---");

        CreditAgricool bCTest= (CreditAgricool) container.getInstanceInjectFields(CreditAgricool.class);
        CreditAgricool bC = new CreditAgricool();
        bC.setName("");

        System.out.println("Actual:\t\t" + bCTest.toString() + "\nExpected:\t" + bC.toString());

        assertEquals(bC.toString(), bCTest.toString(), "Boursolama generated must be equals to the expected one.");
    }

    @Test
    public final void noRegisterSpecifiedUsingSettersTest() {
        Container container = new Container();

        System.out.println("--- No registration specified using setters ---");

        CreditAgricool bCTest= (CreditAgricool) container.getInstanceInjectSetters(CreditAgricool.class);
        CreditAgricool bC = new CreditAgricool();
        bC.setName("");

        System.out.println("Actual:\t\t" + bCTest.toString() + "\nExpected:\t" + bC.toString());

        assertEquals(bC.toString(), bCTest.toString(), "Boursolama generated must be equals to the expected one.");
    }

    @Test
    public final void noRegisterSpecifiedUsingConstructor(){
        Container container = new Container();

        System.out.println("--- No registration specified using constructor ---");

        CreditAgricool bCTest = (CreditAgricool) container.getInstanceInjectConstructor(CreditAgricool.class);
        CreditAgricool bC = new CreditAgricool();

        System.out.println("Actual:\t\t" + bCTest.toString() + "\nExpected:\t" + bC.toString());

        assertEquals(bC.toString(), bCTest.toString(), "Boursolama generated must be equals to the expected one.");
    }

}
