package com.example.lab7java.BankManager;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private int totalCash;
    private final Lock lock = new ReentrantLock();

    public Bank(int initialCash) {
        this.totalCash = initialCash;
    }

    public void withdrawCash(int amount, Client client) {
        lock.lock();
        try {
            if (totalCash >= amount) {
                totalCash -= amount;
                System.out.println(client.getClientName() + " withdrew $" + amount + ". Remaining cash: $" + totalCash);
            } else {
                System.out.println(client.getClientName() + " failed to withdraw $" + amount + " (Insufficient funds).");
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString(){

        return "Bank has this amount:" +totalCash;
    }
    public void depositCash(int amount, Client client) {
        System.out.println("Number of active threads: " + Thread.activeCount());
        lock.lock();
        try {
            totalCash += amount;
            System.out.println(client.getClientName() + " deposited $" + amount + ". Remaining cash: $" + totalCash);
        } finally {
            lock.unlock();
        }
    }
}
