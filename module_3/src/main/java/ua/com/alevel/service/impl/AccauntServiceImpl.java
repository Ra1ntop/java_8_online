package ua.com.alevel.service.impl;

import ua.com.alevel.dao.AccauntDao;
import ua.com.alevel.dao.impl.AccauntDaoImpl;
import ua.com.alevel.entity.Account;
import ua.com.alevel.service.AccauntService;

import java.util.Collection;

public class AccauntServiceImpl implements AccauntService {
    private final AccauntDao accauntDao = new AccauntDaoImpl();

    @Override
    public void create(Account account) {
        accauntDao.create(account);

    }

    @Override
    public void update(Account account) {
         accauntDao.update(account);
    }

    @Override
    public void delete(Account account) {
        accauntDao.delete(account);
    }

    @Override
    public Account findById(Long id) {
        return accauntDao.findById(id);
    }

    @Override
    public Collection<Account> findAll() {
        return accauntDao.findAll();
    }
}
