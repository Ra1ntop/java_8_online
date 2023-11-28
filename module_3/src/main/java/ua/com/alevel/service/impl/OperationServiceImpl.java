package ua.com.alevel.service.impl;

import ua.com.alevel.dao.OperationDao;
import ua.com.alevel.dao.impl.OperationDaoImpl;
import ua.com.alevel.entity.Operation;
import ua.com.alevel.service.OperationService;

public class OperationServiceImpl implements OperationService {

    private final OperationDao operationDao = new OperationDaoImpl();
    @Override
    public void create(Operation operation) {
        operationDao.create(operation);
    }
}
