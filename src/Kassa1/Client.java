package Kassa1;

import com.sun.istack.internal.logging.Logger;

import java.util.Random;

public class Client extends Thread {
    private final static Logger LOG = Logger.getLogger(Client.class);
    private Shop shop;
    private CashDesk cashDesk;
    private String name;
    private int itemsInOrder;
    String[] goods = {"хлеб", "молоко", "шоколад", "колбаса", "творог"};


    public String getGoods() {
        Random random = new Random();
        int i = random.nextInt(5);
       return goods[i];
    }

    public Client(Shop shop, int itemsInOrder, String name) {
        this.shop = shop;
        this.itemsInOrder = itemsInOrder;
        this.name = name;
    }

    public String getClientName() {
        return name;
    }

    public int getItemsInOrder() {
        return itemsInOrder;
    }

    @Override
    public void run()  {
        System.out.println("Покупатель " + name + " сделал покупки в магазине " + shop.getName() +": " + getGoods());
        this.cashDesk = chooseCashDesk();
        System.out.println("Покупатель " + getClientName() + " выбрал кассу № "+ cashDesk.getNumber());
        cashDesk.addClient(this);
        while (true) {
            if (cashDesk.getLock().tryLock()) {
                try {
                    cashDesk.serveClient(this);
                } finally {
                    cashDesk.getLock().unlock();
                    break;
                }
            } else {
                if (canChooseAnotherCashDesk()) {
                    cashDesk.removeClient(this);
                }
            }
        }
        cashDesk.removeClient(this);
        System.out.println("Покупатель " + getClientName() + " покинул магазин");
    }

    private CashDesk chooseCashDesk(){
        CashDesk result = shop.getCashDesks().get(0);
        for (CashDesk cashDesk : shop.getCashDesks()) {
            if(cashDesk.getClients().size() < result.getClients().size()) {
                result = cashDesk;
            }
        }
        return result;
    }

    private boolean canChooseAnotherCashDesk() {
        CashDesk result = chooseCashDesk();
        if(result.getClients().size() + 1 < cashDesk.getClients().size()) {
            cashDesk = result;
            cashDesk.addClient(this);
            System.out.println("Покупатель " + getClientName() + " перешел к кассе №" + cashDesk.getNumber());
            return true;
        }
        return false;
    }
}















