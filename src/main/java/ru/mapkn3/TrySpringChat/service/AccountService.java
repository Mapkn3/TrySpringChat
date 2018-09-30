package ru.mapkn3.TrySpringChat.service;

import ru.mapkn3.TrySpringChat.model.Account;

import java.util.List;

public interface AccountService {
    Account getAccount(Long id);

    Account getAccountByName(String name);

    Long addNewAccount(Account account);

    List<Account> getAllAccounts();

    Account updateAccount(Account account);

    void deleteAccount(Account account);
}
