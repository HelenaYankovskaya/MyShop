package Kassa1;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Shop shop = Shop.getInstance("АЛМИ");
        CashDesk cashDesk1 = new CashDesk(1, 140);
        CashDesk cashDesk2 = new CashDesk(2, 200);
        CashDesk cashDesk3 = new CashDesk(3, 250);

        shop.addCashDesk(cashDesk1);
        shop.addCashDesk(cashDesk2);
        shop.addCashDesk(cashDesk3);

        new Client(shop, 20, "№5").start();
        Random random = new Random();
        for (int i = 1; i < 10; i++) {

            int randNumbOfItems = random.nextInt(10) + 1;
            Client client =  new Client(shop, randNumbOfItems, "№"+i);
            client.start();
        }
    }




}
