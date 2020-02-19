package test;

import java.util.HashMap;
import java.util.Map;

public class ClientsManagement {
    private Map<String, String> clients;

    public ClientsManagement(){
        clients = new HashMap<>();
    }

    public Map<String, String> getClients() {
        return clients;
    }

    public void setClients(Map<String, String> clients) {
        this.clients = clients;
    }

    public String toString(){
        final String[] ret = {"ClientsManagement: { "};
        clients.forEach( (k, v) -> {
            ret[0] += k + " -> " + v + ", ";
        });
        ret[0] += " }";

        return ret[0];
    }
}


