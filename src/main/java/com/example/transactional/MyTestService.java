package com.example.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class MyTestService {
    @Autowired
    private DocumentDataDao documentDataDao;

    @Transactional(rollbackFor = Exception.class)
    public void test() {
        DocumentData data = new DocumentData();
        data.setData(UUID.randomUUID().toString());
        documentDataDao.create(data);
        throw new IllegalArgumentException("Test rollback");
    }
}
