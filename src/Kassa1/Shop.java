package Kassa1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Shop {
    private static Shop instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private String name;
    private ArrayList<CashDesk> cashDesks;

    private Shop(String name) {
        this.name = name;
        cashDesks = new ArrayList<>();
    }

    public static Shop getInstance(String name) {
        lock.lock();
        try {
            if (instance == null) {
                instance = new Shop(name);
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    public void addCashDesk(CashDesk cashDesk) {
        cashDesks.add(cashDesk);
    }

    public String getName() {
        return name;
    }

    public List<CashDesk> getCashDesks() {
        return Collections.unmodifiableList(cashDesks);
    }
}
