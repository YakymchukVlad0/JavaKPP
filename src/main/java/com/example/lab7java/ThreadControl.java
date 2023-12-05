package com.example.lab7java;

import com.example.lab7java.BankManager.Bank;
import com.example.lab7java.BankManager.Client;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.time.LocalTime;
import java.util.Random;

public class ThreadControl extends FlowPane {
    private String threadName;
    private Label nameLabel;
    private Label priorityLabel;
    private Label stateLabel;
    private Button stopButton;
    private Button withdrawButton;
    private Button addButton;
    private Button runButton;

    public Client client;

    public ThreadControl(String threadName, Bank bank) {
        this.threadName = threadName;
        this.client = new Client(threadName, bank);
        Random rand =new Random();
        int prior = rand.nextInt(10)+1;
        this.client.setPriority(prior);


        nameLabel = new Label("Name: " + threadName+"  ");
        priorityLabel = new Label("Priority: " + this.client.getPriority()+" ");
        stateLabel = new Label("State: " +this.client.getState()+" ");
        runButton= new Button("Run");
        stopButton = new Button("Stop");
        withdrawButton = new Button("Withdraw");
        addButton = new Button("Add");


        getChildren().addAll(nameLabel, priorityLabel, stateLabel,runButton, stopButton, withdrawButton, addButton);

        int amount = rand.nextInt(100)+20;
        int wAmount = rand.nextInt(60)+10;
        stopButton.setOnAction(event -> stopThread());
        withdrawButton.setOnAction(event -> withdrawMoney(wAmount));
        addButton.setOnAction(event -> addMoney(amount));
        runButton.setOnAction(event -> startTransactions());
    }

    public void stopThread() {

        client.interrupt();
        stateLabel.setText("State: " +this.client.getState()+" ");
        System.out.println("Time of stopping "+ this.client.getName()+" : "+ LocalTime.now());
    }

    private void withdrawMoney(int amount) {

        client.withdrawCash(amount);
    }

    private void addMoney(int amount) {

        client.depositCash(amount);

    }

    public void startTransactions(){
        client.start();
        stateLabel.setText("State: " +this.client.getState()+" ");
        System.out.println("Time of starting "+ this.client.getName()+" : "+ LocalTime.now());

    }
}
