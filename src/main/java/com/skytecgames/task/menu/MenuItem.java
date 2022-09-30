package com.skytecgames.task.menu;


import com.skytecgames.task.menu.action.IAction;

public class MenuItem {

    private String title;
    private IAction action;
    private Menu nextMenu;

    public MenuItem(String title, IAction action, Menu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
    }

    public void doAction() throws Exception{
        action.execute();
    }

    public String getTitle() {return title;}

    public Menu getNextMenu() {return nextMenu;}
}
