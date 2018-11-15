package Kassa1;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class CashDesk {
    private ReentrantLock lock = new ReentrantLock();
    private LinkedList<Client> clients;
    private int number;
    private int timeOfService;

    public CashDesk(int number, int timeOfService) {
        clients = new LinkedList<>();
        this.number = number;
        this.timeOfService = timeOfService;

    }

    public void serveClient(Client client)  {
        System.out.println("Покупатель "+client.getClientName() + " рассчитался на кассе № "+getNumber());

        try {
            client.sleep(timeOfService * client.getItemsInOrder());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getClients() {
        return Collections.unmodifiableList(clients);
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public int getNumber() {
        return number;
    }

    public ReentrantLock getLock() {
        return lock;
    }
}


