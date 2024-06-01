package org.bao.services;

import org.bao.models.Expense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpenseService {
    List<Expense> expenses;

    public ExpenseService() {
        expenses = new ArrayList<>();
    }

    public void createExpense(Expense expense) {
        expenses.add(expense);

    }

    public void deleteExpense(Expense expense) {


    }

    public void editExpense(Expense newExpense) {

    }

    public List<Expense> getExpensesForGroup(String groupId) {
        return expenses.stream().filter(e -> e.getGroupId().equals(groupId)).collect(Collectors.toList());
    }




}
