package org.bao;

import org.bao.models.Expense;
import org.bao.models.Group;
import org.bao.models.User;
import org.bao.services.ExpenseService;
import org.bao.services.GroupService;
import org.bao.services.UserService;
import org.bao.utils.ExpenseUtils;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserService();
        GroupService groupService = new GroupService();
        ExpenseService expenseService = new ExpenseService();

        //Create User
        User user1 = new User("1", "user1");
        User user2 = new User("2", "user1");
        User user3 = new User("3", "user1");

        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);

        //Create Group
        Group group1 = new Group("1", "group 1");

        groupService.createGroup(group1);


        //Create Expense
        String input1 = "";
        Expense expense1 = ExpenseUtils.getExpenseFromUserInput(input1);
        expenseService.createExpense(expense1);




    }
}