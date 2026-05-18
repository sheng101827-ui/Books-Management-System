package com.book.service;

import com.book.dao.LendDao;
import com.book.domain.Lend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LendService {

    private LendDao lendDao;

    @Autowired
    public void setLendDao(LendDao lendDao) {
        this.lendDao = lendDao;
    }

    public boolean bookLend(long bookId, int readerId) {
        if (lendDao.bookLendTwo(bookId) <= 0) {
            return false;
        }
        try {
            return lendDao.bookLendOne(bookId, readerId) > 0;
        } catch (RuntimeException e) {
            lendDao.rollbackBookLend(bookId);
            return false;
        }
    }

    public boolean bookReturn(long bookId) {
        return lendDao.bookReturnOne(bookId) > 0 && lendDao.bookReturnTwo(bookId) > 0;
    }

    public ArrayList<Lend> lendList() {
        return lendDao.lendList();
    }

    public ArrayList<Lend> myLendList(int readerId) {
        return lendDao.myLendList(readerId);
    }
}
