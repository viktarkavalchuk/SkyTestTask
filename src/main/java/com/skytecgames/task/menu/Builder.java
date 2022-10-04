package com.skytecgames.task.menu;

import com.skytecgames.task.menu.action.*;

public class Builder {

    private Menu rootMenu;

    public Menu getRootMenu() {
        this.buildMenu();
        return rootMenu;
    }

    private void buildMenu(){
        rootMenu = new Menu("Root menu");
        rootMenu.addMenuItem(new MenuItem("0 - Exit",
                () -> System.out.println(""),
                null));
        rootMenu.addMenuItem(new MenuItem("1 - Sending Gold",
                () -> System.out.println("Go to Sending gold"),
                createSendingMenu()));
        rootMenu.addMenuItem(new MenuItem("2 - Transaction History Menu",
                () -> System.out.println("Go to Transaction History Menu"),
                createTransactionHistoryMenu()));
    }

    private Menu createSendingMenu(){
        Menu sendingMenu = new Menu("Sending Menu");
        sendingMenu.addMenuItem(new MenuItem("0 - Main Menu",
                () -> System.out.println("Go to Main menu"),
                rootMenu));
        sendingMenu.addMenuItem(new MenuItem("1 - Complete Task Action",
                new CompleteTaskAction(),
                sendingMenu));
        sendingMenu.addMenuItem(new MenuItem("2 - Add Gold To Clan",
                new AddGoldToClanAction(),
                sendingMenu));
        sendingMenu.addMenuItem(new MenuItem("3 - Clan Payment",
                new ClanPaymentAction(),
                sendingMenu));
        return sendingMenu;
    }

    private Menu createTransactionHistoryMenu(){
        Menu transactionMenu = new Menu("Transaction History Menu");
        transactionMenu.addMenuItem(new MenuItem("0 - Main Menu",
                () -> System.out.println("Go to Main menu"),
                rootMenu));
        transactionMenu.addMenuItem(new MenuItem("1 - Get All Records",
                new GetTransactionHistoryAction(),
                transactionMenu));
        transactionMenu.addMenuItem(new MenuItem("2 - Get All Records By User",
                new GetTransactionHistoryByUserAction(),
                transactionMenu));
        transactionMenu.addMenuItem(new MenuItem("3 - Get All Records By Clan",
                new GetTransactionHistoryByClanAction(),
                transactionMenu));
        transactionMenu.addMenuItem(new MenuItem("4 - Get All Records By Reason",
                new GetTransactionHistoryByReasonAction(),
                transactionMenu));
        return transactionMenu;
    }
}







