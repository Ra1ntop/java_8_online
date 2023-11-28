package ua.com.alevel.service.impl;

import ua.com.alevel.dao.HistoryDao;
import ua.com.alevel.dao.impl.HistoryDaoImpl;
import ua.com.alevel.entity.History;
import ua.com.alevel.service.HistoryService;

public class HistoryServiceImpl implements HistoryService {
    private final HistoryDao historyDao = new HistoryDaoImpl();

    @Override
    public void create(History history) {
        historyDao.create(history);
    }
}
