package com.skytecgames.task.menu;

import java.util.Scanner;

public class MenuController {

    private Builder builder;
    private Navigator navigator;

    public MenuController(){
        this.builder = new Builder();
        this.navigator = new Navigator();
    }

    public void run() throws Exception{
        navigator.setCurrentMenu(builder.getRootMenu());
        navigator.printMenu();
        Integer index;
        Scanner scanner = new Scanner(System.in);
        while (true){
            try {
                if (scanner.hasNextInt()) {
                    index = scanner.nextInt();
                    if (index.equals(-1)) {
                        System.out.println("Exit");

                        return;
                    }
                    navigator.navigate(index);
                    navigator.printMenu();
                }
            }
            catch (Exception e){
                System.out.println("Неверный тип данных");
            }
        }
    }
}
