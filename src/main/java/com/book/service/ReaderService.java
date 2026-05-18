package com.book.service;

import com.book.dao.ReaderCardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderService {

    private ReaderCardDao readerCardDao;

    @Autowired
    public void setReaderCardDao(ReaderCardDao readerCardDao) {
        this.readerCardDao = readerCardDao;
    }

    public void lossCard(int readerId) {
        if (!readerCardDao.hasReaderId(readerId)) {
            throw new RuntimeException("Reader ID not found in database");
        }
        readerCardDao.updateCardState(readerId, 0);
    }
}
