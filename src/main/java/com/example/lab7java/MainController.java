package com.example.lab7java;

import com.example.lab7java.BankManager.Bank;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainController {
    @FXML
    public TextField tfCash;
    public TextField tfThreads;

    public Label lthreads;
    public Label lcash;
    public Button startButton;
    int threads;
    int cashAmount;
    Bank bank;
    @FXML
    FlowPane fpThreadControl;

    @FXML
    public Button startAllButton;
    public Button stopAllButton;

    List<ThreadControl> threadList = new ArrayList<>();
    private ExecutorService executorService;


    public void setAmountAndThreads(){
        cashAmount = Integer.parseInt(tfCash.getText());
        threads = Integer.parseInt(tfThreads.getText());

    }
    public void stopAll(ActionEvent e){
        for(ThreadControl th: threadList){
            th.stopThread();

        }
        executorService.shutdownNow();

        System.out.println("\nStopping tasks\n");
    }

    public void startAll(ActionEvent e){
        executorService = Executors.newFixedThreadPool(threads);
        for(ThreadControl th: threadList){
            executorService.execute(th::startTransactions);

        }
        /*for(ThreadControl th: threadList){
            try {
                th.client.join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

        }*/

    }
    public void start(ActionEvent e){
        setAmountAndThreads();
        bank = new Bank(cashAmount);
        System.out.println(bank);
        tfCash.setVisible(false);
        tfThreads.setVisible(false);
        lcash.setVisible(false);
        lthreads.setVisible(false);
        startButton.setVisible(false);
        fpThreadControl.setPrefHeight(620);
        fpThreadControl.setVisible(true);
        startAllButton.setVisible(true);
        stopAllButton.setVisible(true);

        for (int i = 1; i <= threads; i++) {
            ThreadControl threadControl = new ThreadControl("Thread " + i, bank);
            System.out.println("Adding ThreadControl: " + threadControl);
            threadList.add(threadControl);
            fpThreadControl.getChildren().add(threadControl);
        }


    }
}