package ua.com.alevel.controller;


import ua.com.alevel.entity.*;
import ua.com.alevel.service.*;
import ua.com.alevel.service.impl.*;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.util.CsvUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

public class MainController {
    private final UserService userService = new UserServiceImpl();
    private final AccauntService accountService = new AccauntServiceImpl();
    private final OperationService operationService = new OperationServiceImpl();
    private final CategoriesService categoryService = new CategoriesServiceImpl();
    private final HistoryService historyService = new HistoryServiceImpl();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public void start() throws IOException {
        setDefaultCategories();
        mainList();
        String choice = "";
        while ((choice = bufferedReader.readLine()) != null) {
            mainChoice(choice);
            mainList();
        }
    }
    private void mainList(){
        System.out.println("-----------------------------------------");
        System.out.println("--------------|Main Choice|--------------");
        System.out.println("-----------------------------------------");
        System.out.println("Input 1 if u want to interact with user/account");
        System.out.println("Input 2 if u want to do some operation");
        System.out.println("Input 3 if u want to print operations on csv file");
        System.out.println("Input 4 if u want exit");
        System.out.println("-----------------------------------------");
    }
    private void editorList() {
        System.out.println("-----------------------------------------");
        System.out.println("-------------|Editor Choice|-------------");
        System.out.println("-----------------------------------------");
        System.out.println("Input 1 if u want to create user");
        System.out.println("Input 2 if u want to create account");
        System.out.println("Input 3 if u want to find users and accounts");
        System.out.println("Input 4 if u want to update user");
        System.out.println("Input 5 if u want to delete user");
        System.out.println("Input 6 if u want to delete account");
        System.out.println("Input 7 if u want to back to main menu");
        System.out.println("-----------------------------------------");
    }
    private void mainChoice(String choice) throws IOException {
        switch (choice){
            case "1":
                editorList();
                String choiceEditor;
                while ((choiceEditor = bufferedReader.readLine()) != null) {
                    editorChoice(choiceEditor);
                    editorList();
                }
                break;
            case "2":
                doOperationInWallets();
                break;
            case "3":
                printOperationInCSVFile();

        }
    }
    private void editorChoice(String choiceEditor) throws IOException {
        switch (choiceEditor) {
            case "1" -> {
                userService.create(setUser(new User()));
                System.out.println("User has been created");
            }
            case "2" -> {
                createAccount();
            }
            case "3" -> {
                Collection<User> users = userService.findAll();
                if (CollectionUtils.isNotEmpty(users)) {
                    printUsers(users);
                } else {
                    System.out.println("No user found");
                }
            }
            case "4" -> {
                updateUser();
            }
            case "5" -> {
                deleteUser();
            }
            case "6" -> {
                deleteAccaunt();
            }
            case "7" -> {
                System.out.println("Closing...");
                System.exit(-1);
            }
    }}
    private void printUsers(Collection<User> users) {
        int i = 1;
        for (User user : users) {
            System.out.println("-----------------------------------------");
            System.out.println("-------------|USER "+ i++ + " |----------");
            System.out.println("-----------------------------------------");
            System.out.println("User: " + "name= "+ user.getName()+ " id = " + user.getId());
            Set<Account> accounts = user.getAccountSet();
            if (CollectionUtils.isNotEmpty(accounts)) {
                System.out.println("Accounts:");
                for (Account account : accounts) {
                    System.out.println("id = " + account.getId());
                    System.out.println("sum = " + account.getSum());
                }
            } else {
                System.out.println("User don't have accounts");
            }
            System.out.println();
        }
    }
    private void setDefaultCategories(){
        if (categoryService.findById(1L) == null && categoryService.findById(2L) == null) {
            Categories category1 = new Categories();
            Categories category2 = new Categories();
            category1.setName("Spending |-|");
            category2.setName("Income |+|");
            categoryService.create(category1);
            categoryService.create(category2);
        }
    }
    private void createAccount() throws IOException {
        Collection<User> users = userService.findAll();
        if (CollectionUtils.isNotEmpty(users)) {
            printUsers(users);
            System.out.println("Enter user id");
            User user = userService.findById(Long.valueOf(bufferedReader.readLine()));
            System.out.println();
            if (user != null) {
                Account account = new Account();
                System.out.println("Enter starter number of money");
                account.setSum(Long.valueOf(bufferedReader.readLine()));
                account.setUser(user);
                user.getAccountSet().add(account);
                accountService.create(account);
                userService.update(user);
                System.out.println("User with name: " + user.getName() + " was created accaunt with id: " + account.getId());
            } else {
                System.out.println("User not found");
            }
        } else {
            System.out.println("User not found");
        }
    }
    private void updateUser() throws IOException {
        Collection<User> users = userService.findAll();
        if (CollectionUtils.isNotEmpty(users)) {
            printUsers(users);
            System.out.println("Enter user id ");
            User user = userService.findById(Long.valueOf(bufferedReader.readLine()));
            System.out.println();
            if (user != null) {
                userService.update(setUser(user));
                System.out.println("User was updated");
            } else {
                System.out.println("User not found");
            }
        } else {
            System.out.println("User not found");

        }
    }
    private void deleteUser() throws IOException{
        Collection<User> users = userService.findAll();
        if (CollectionUtils.isNotEmpty(users)) {
            printUsers(users);
            System.out.println("Enter user id");
            User user = userService.findById(Long.valueOf(bufferedReader.readLine()));
            System.out.println();
            if (user != null) {
                userService.delete(user);
                System.out.println("User was deleted");
            } else {
                System.out.println("User not found");
            }
        } else {
            System.out.println("User not found");

        }
    }
    private void deleteAccaunt() throws IOException{
        Collection<User> users = userService.findAll();
        if (CollectionUtils.isNotEmpty(users)) {
            printUsers(users);
            System.out.println("Enter account id");
            Account account = accountService.findById(Long.valueOf(bufferedReader.readLine()));
            System.out.println();
            if (account != null) {
                User user = account.getUser();
                user.getAccountSet().remove(account);
                userService.update(user);
                accountService.delete(account);
                System.out.println("Account has been deleted");
            } else {
                System.out.println("Account not found");
            }
        } else {
            System.out.println("No user found");
        }
    }

    private void doOperationInWallets() throws IOException {
        Collection<User> users = userService.findAll();
        if (CollectionUtils.isNotEmpty(users)) {
            printUsers(users);
            System.out.println("Enter sender account id");
            Account accountWitchWillBeSend = accountService.findById(Long.valueOf(bufferedReader.readLine()));
            if (accountWitchWillBeSend != null) {
                System.out.println("Enter receiver account id");
                Account accountWitchWillBeRecive = accountService.findById(Long.valueOf(bufferedReader.readLine()));
                if (accountWitchWillBeRecive != null) {
                    System.out.println("Enter the sum to sent");
                    long sum = Long.parseLong(bufferedReader.readLine());
                    if (sum < accountWitchWillBeSend.getSum()) {
                        Operation operation = new Operation();
                        History history1 = new History();
                        History history2 = new History();
                        accountWitchWillBeSend.setSum(accountWitchWillBeSend.getSum() - sum);
                        accountWitchWillBeRecive.setSum(accountWitchWillBeRecive.getSum() + sum);
                        operation.setDateTime(String.valueOf(LocalDateTime.now()));
                        operation.setAccount1(accountWitchWillBeSend);
                        operation.setAccount2(accountWitchWillBeRecive);
                        operation.setSum(sum);
                        operationService.create(operation);
                        history1.setAccount(accountWitchWillBeSend);
                        history1.setOperation(operation);
                        history1.setCategory(categoryService.findById(1L));
                        history2.setAccount(accountWitchWillBeRecive);
                        history2.setOperation(operation);
                        history2.setCategory(categoryService.findById(2L));
                        historyService.create(history1);
                        historyService.create(history2);
                        accountService.update(accountWitchWillBeSend);
                        accountService.update(accountWitchWillBeRecive);
                        System.out.println("done");
                    } else {
                        System.out.println("Not enough sum on account");
                    }
                } else {
                    System.out.println("Account not found");
                }
            } else {
                System.out.println("Account not found");
            }
        } else {
            System.out.println("No user found");
        }
    }

    private void printOperationInCSVFile() throws IOException {
        Collection<User> users = userService.findAll();
        if (CollectionUtils.isNotEmpty(users)) {
            printUsers(users);
            System.out.println("Enter account id");
            Long id = Long.valueOf(bufferedReader.readLine());
            if (accountService.findById(id) != null) {
                CsvUtil.getHistory(id);
                System.out.println();
                System.out.println("An account extract issued");
            } else {
                System.out.println("Account not found");
            }
        } else {
            System.out.println("No user found");
        }
    }
    private User setUser(User user){
        try {
            System.out.println("Enter User name");
            user.setName(bufferedReader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;

    }
}

