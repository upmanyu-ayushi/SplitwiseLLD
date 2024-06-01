package org.bao.services;

import org.bao.models.*;

import java.util.*;

public class GroupService {

    private ExpenseService expenseService;
    private UserService userService;
    Map<String, Group> groupIdToGroup;

    public GroupService(ExpenseService expenseService, UserService userService) {
        groupIdToGroup = new HashMap<>();
        this.expenseService = expenseService;
        this.userService = userService;
    }

    public List<GroupGraphNode> getGroupBalance(Group group) {
        List<Expense> groupExpenses = getGroupExpenses(group);
        Expense sumExpense = new Expense();
        for(Expense expense : groupExpenses) {
            expense.getUserIdToBalance().forEach((userid, balance) -> {
                if (!sumExpense.getUserIdToBalance().containsKey(userid)) {
                    sumExpense.getUserIdToBalance().put(userid,  new Balance(0, balance.getCurrency()));
                }
                Balance sumBalance = sumExpense.getUserIdToBalance().get(userid);
                sumBalance.setAmount(sumBalance.getAmount() + balance.getAmount());
            });
        }

        PriorityQueue<Pair<User, Integer>> negativeIntMinHeap = new PriorityQueue<>((a, b) -> a.getSecond() - b.getSecond());
        PriorityQueue<Pair<User, Integer>> positiveIntMaxHeap = new PriorityQueue<>((a, b) -> b.getSecond() - a.getSecond());

        sumExpense.getUserIdToBalance().forEach((user, balance) -> {
            if (balance.getAmount() < 0) {
                negativeIntMinHeap.add(new Pair<>(userService.getUserById(user), balance.getAmount()));
            } else {
                positiveIntMaxHeap.add(new Pair<>(userService.getUserById(user), balance.getAmount()));
            }
        });
        List<GroupGraphNode> output = new ArrayList<>();
        while(!negativeIntMinHeap.isEmpty() && !positiveIntMaxHeap.isEmpty()) {
            Pair<User, Integer> lenderPair = positiveIntMaxHeap.poll();
            Pair<User, Integer> borrowerPair = negativeIntMinHeap.poll();

            GroupGraphNode graphNode = new GroupGraphNode();
            graphNode.setBorrower(borrowerPair.getFirst());
            graphNode.setLender(lenderPair.getFirst());

            int lenderBalance = lenderPair.getSecond();
            int borrowerBalance = borrowerPair.getSecond();

            int transactionAmout = Math.min(lenderBalance, Math.abs(borrowerBalance));
            graphNode.setAmount(transactionAmout);
            output.add(graphNode);

            int remainder = lenderBalance + borrowerBalance;

            if (remainder > 0) {
                positiveIntMaxHeap.add(new Pair<>(lenderPair.getFirst(), remainder));
            } else if (remainder < 0) {
                negativeIntMinHeap.add(new Pair<>(borrowerPair.getFirst(), remainder));
            }
        }
        return output;
    }

    public List<Expense> getGroupExpenses(Group group) {
        return expenseService.getExpensesForGroup(group.getId());
    }

    public void createGroup(Group group) {
        groupIdToGroup.put(group.getId(), group);
    }




}
