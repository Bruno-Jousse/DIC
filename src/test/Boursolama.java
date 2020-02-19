package test;

public class Boursolama extends Bank {

    private ClientsManagement clientsManagement;

    private String devise;

    public Boursolama(){
        super();
        clientsManagement = new ClientsManagement();
        devise = "Efficace et pas chère";
        setName("Boursolama");
    }

    public Boursolama( String devise, AccountManagement accMan, int postalCode, ClientsManagement clients){
        super("Boursolama", accMan, postalCode);
        this.devise = devise;
        this.clientsManagement = clients;
    }

    public ClientsManagement getClientsManagement() {
        return clientsManagement;
    }

    public void setClientsManagement(ClientsManagement clientsManagement) {
        this.clientsManagement = clientsManagement;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public String toString(){
        return "Boursolama: { name = " + getName() +", " + getAccountManagement().toString() + ", " + getClientsManagement().toString() + " }";
    }
}
