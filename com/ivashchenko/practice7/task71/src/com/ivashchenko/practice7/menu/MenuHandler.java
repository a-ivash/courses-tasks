package com.ivashchenko.practice7.menu;

import com.ivashchenko.practice7.db.dao.implementations.DepartmentDaoImpl;
import com.ivashchenko.practice7.db.dao.implementations.EmployeeDaoImpl;
import com.ivashchenko.practice7.db.dao.implementations.TaskDaoImpl;
import com.ivashchenko.practice7.db.dao.interfaces.DepartmentDao;
import com.ivashchenko.practice7.db.dao.interfaces.EmployeeDao;
import com.ivashchenko.practice7.db.dao.interfaces.TaskDao;
import com.ivashchenko.practice7.db.ConnectionHandler;
import com.ivashchenko.practice7.db.ConnectionWrapper;
import com.ivashchenko.practice7.db.exceptions.RecordNotFoundException;
import com.ivashchenko.practice7.entity.AbstractEntity;
import com.ivashchenko.practice7.entity.Department;
import com.ivashchenko.practice7.entity.Employee;
import com.ivashchenko.practice7.entity.Task;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;

public class MenuHandler {
    private TaskDao taskDao;
    private DepartmentDao departmentDao;
    private EmployeeDao employeeDao;


    public MenuHandler() throws SQLException {
        try {
            ConnectionWrapper wrapper = ConnectionHandler.getInstance().getConnection();
            taskDao = new TaskDaoImpl(wrapper);
            departmentDao = new DepartmentDaoImpl(wrapper);
            employeeDao = new EmployeeDaoImpl(wrapper);
        } catch (SQLException e) {
            System.out.println("Error connecting to database.");
        }
    }

    public void handleMenu() {
        MenuItems choice = null;
        do {
            MenuItems.printMenu();
            try {
                int userIndex = Reader.askForInt("Your choice: ");
                choice = MenuItems.getItemForIndex(userIndex);
                handleUserChoice(choice);
            } catch (InputMismatchException e) {
                System.out.println("<Repeat your input>");
            }
        } while (choice != MenuItems.EXIT);
    }


    private void handleUserChoice(MenuItems userChoice) {
        switch (userChoice) {
            case GET_ALL_TASKS:
                getAllTasks();
                break;
            case GET_ALL_EMPLOYEES:
                getAllEmployees();
                break;
            case GET_DEPARTMENTS_EMPLOYEES:
                getDepartmentsEmployees();
                break;
            case ADD_TASK_TO_EMPLOYEE:
                addTaskToEmployee();
                break;
            case GET_EMPLOYEES_TASKS:
                getEmployeesTasks();
                break;
            case DELETE_EMPLOYEE:
                deleteEmployee();
                break;
            case EXIT: break;
        }
    }

    private void printEntityList(List<? extends AbstractEntity> entities) {
        if (entities.isEmpty()) {
            System.out.println("<No records exist.>");
            return;
        }
        System.out.println("\n####################################");
        for(AbstractEntity entity: entities) {
            System.out.println(entity.toString());
        }
        System.out.println("####################################\n");
    }

    private void getAllTasks() {
        try {
            List<Task> tasks = taskDao.findAll();
            printEntityList(tasks);
        } catch (SQLException e) {
            System.out.println("Error retrieving tasks from database: " + e);
        }
    }

    private void getAllEmployees() {
        try {
            List<Employee> employees = employeeDao.findAll();
            printEntityList(employees);
        } catch (SQLException e) {
            System.out.println("Error retrieving employees from database: " + e);
        }
    }

    private void getDepartmentsEmployees() {
        try {
            List<Department> departments = departmentDao.findAll();
            printEntityList(departments);
            if (departments.isEmpty()) {
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving departments employees: " + e);
            return;
        }

        int departmentId = Reader.askForInt("Enter department ID: ");
        try {
            Department department = departmentDao.findById(departmentId);
            List<Employee> employees = employeeDao.getEmployeesFromDepartment(department);
            printEntityList(employees);
        } catch (RecordNotFoundException e) {
            System.out.println("No department with specified ID was found.");
        } catch (SQLException e) {
            System.out.println("Error retrieving departments employees:" + e);
        }
    }


    private void addTaskToEmployee() {
        try {
            List<Employee> employees = employeeDao.findAll();
            printEntityList(employees);

            if (employees.isEmpty()) {
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error during adding task to database: " + e);
            return;
        }

        int employeeId = Reader.askForInt("Enter employee ID: ");
        try {
            Employee employee = employeeDao.findById(employeeId);
            String taskDescription = Reader.askForString("Enter task description: ");
            taskDao.createTask(taskDescription, employee);
        } catch (RecordNotFoundException e) {
            System.out.println("No employee with specified ID was found.");
        } catch (SQLException e) {
            System.out.println("Error during adding task to database: " + e);
        }

    }


    private void getEmployeesTasks() {
        try {
            List<Employee> employees = employeeDao.findAll();
            printEntityList(employees);

            if (employees.isEmpty()) {
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving employees tasks: " + e);
            return;
        }

        int employeeId = Reader.askForInt("Enter employee ID: ");
        try {
            Employee employee = employeeDao.findById(employeeId);
            List<Task> tasks = taskDao.getEmployeesTasks(employee);
            printEntityList(tasks);
        } catch (RecordNotFoundException e) {
            System.out.println("No employee with specified ID was found.");
        } catch (SQLException e) {
            System.out.println("Error retrieving employees tasks: " + e);
        }

    }


    private void deleteEmployee() {
        try {
            List<Employee> employees = employeeDao.findAll();
            printEntityList(employees);

            if (employees.isEmpty()) {
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting employee: " + e);
            return;
        }

        int employeeId = Reader.askForInt("Enter ID of employee to delete: ");
        try {
            Employee employee = employeeDao.findById(employeeId);
            employeeDao.delete(employee);
        } catch (RecordNotFoundException e) {
            System.out.println("No employee with specified ID was found.");
        } catch (SQLException e) {
            System.out.println("Error while deleting employee: " + e);
        }
    }

}
