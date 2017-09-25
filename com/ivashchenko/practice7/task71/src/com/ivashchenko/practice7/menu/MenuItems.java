package com.ivashchenko.practice7.menu;

public enum MenuItems {
    GET_ALL_EMPLOYEES("Get all employees."),
    GET_ALL_TASKS("Get all tasks."),
    GET_DEPARTMENTS_EMPLOYEES("Get employees from department."),
    ADD_TASK_TO_EMPLOYEE("Add new task to employee."),
    GET_EMPLOYEES_TASKS("Get tasks for employee."),
    DELETE_EMPLOYEE("Delete employee."),
    EXIT("Exit");

    private String description;
    private MenuItems(String description) {
        this.description = description;
    }

    public static MenuItems getItemForIndex(int index) {
        if(index <= 0 || index > MenuItems.values().length) {
            return MenuItems.EXIT;
        }
        return MenuItems.values()[index-1];
    }

    public static void printMenu() {
        for(MenuItems item: MenuItems.values()) {
            System.out.println((item.ordinal() + 1) + ". " + item);
        }
    }

    @Override
    public String toString() {
        return this.description;
    }


}
