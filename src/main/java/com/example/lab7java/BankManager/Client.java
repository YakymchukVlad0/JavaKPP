package com.example.lab7java.BankManager;



   public class Client extends Thread {
        private String name;
        private Bank bank;

        public Client(String name, Bank bank) {
            this.name = name;
            this.setName(name);
            this.bank = bank;
        }

        public String getClientName() {
            return name;
        }

        public void withdrawCash(int amount) {
            bank.withdrawCash(amount, this);
        }

        public void depositCash(int amount) {
            bank.depositCash(amount, this);
        }

        @Override
        public void run() {

            while (!Thread.interrupted()) {
                withdrawCash(50);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                    System.out.println(getName() + " was interrupted. Exiting...");
                    return;
                }
                depositCash(100);
            }
        }
    }

