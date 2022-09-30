package com.skytecgames.task.menu;

public class Navigator {

    private Menu currentMenu;

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        System.out.println("Menu: " + currentMenu.getName());
        System.out.println("Choose number from menu");
        currentMenu.getMenuItems().forEach(item -> System.out.println(item.getTitle()));
    }

    public void navigate(Integer index) throws Exception{
        if (currentMenu != null){
            MenuItem menuItem = currentMenu.getMenuItems().get(index);
            menuItem.doAction();
            currentMenu = menuItem.getNextMenu();
        }
    }
}
