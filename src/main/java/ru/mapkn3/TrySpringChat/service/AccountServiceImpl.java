package ru.mapkn3.TrySpringChat.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mapkn3.TrySpringChat.dao.PrettyEntityDao;
import ru.mapkn3.TrySpringChat.model.Account;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
public class AccountServiceImpl implements AccountService {
    private final static Logger logger = Logger.getLogger(AccountServiceImpl.class);

    @Autowired
    private PrettyEntityDao accountDao;

    @Override
    @Transactional(readOnly = true)
    public Account getAccount(Long id) {
        logger.debug("Getting account with id = " + id);
        return accountDao.findById(Account.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccountByName(String name) {
        List<Account> accounts = accountDao.getAll(Account.class);
        Optional<Account> result = accounts.stream().filter(account -> account.getNickname().equals(name)).findFirst();
        return result.orElse(null);
    }

    @Override
    public Long addNewAccount(Account account) {
        Long id = (Long) accountDao.save(account);
        logger.debug("Id of new Account: " + id);
        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> getAllAccounts() {
        List<Account> accounts = accountDao.getAll(Account.class);
        logger.debug("Get " + accounts.size() + " accounts:");
        for (Account account : accounts) {
            logger.debug(account.toString());
        }
        return accounts;
    }

    @Override
    public Account updateAccount(Account account) {
        Account oldAccount = accountDao.findById(Account.class, account.primaryKey());
        Account newAccount = accountDao.update(account);
        logger.debug("Old account: " + oldAccount.toString());
        logger.debug("New account: " + newAccount.toString());
        return newAccount;
    }

    @Override
    public void deleteAccount(Account account) {
        logger.debug("Delete account: " + account.toString());
        accountDao.delete(account);
    }
}
