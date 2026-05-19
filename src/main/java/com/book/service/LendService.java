package com.book.service;

import com.book.dao.LendDao;
import com.book.domain.Lend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class LendService {
    private static final long THIRTY_DAYS_MILLISECONDS = 30L * 24 * 60 * 60 * 1000;

    private LendDao lendDao;

    @Autowired
    public void setLendDao(LendDao lendDao) {
        this.lendDao = lendDao;
    }

    public boolean bookReturn(long bookId) {
        return lendDao.bookReturnOne(bookId) > 0 && lendDao.bookReturnTwo(bookId) > 0;
    }

    public boolean bookLend(long bookId, int readerId) {
        return lendDao.bookLendOne(bookId, readerId) > 0 && lendDao.bookLendTwo(bookId) > 0;
    }

    public boolean renewBook(long sernum) {
        Lend lend = lendDao.getLendBySernum(sernum);
        if (lend == null) {
            return false;
        }

        Date backDate = lend.getBackDate();
        if (backDate == null) {
            return false;
        }

        Date newBackDate = new Date(backDate.getTime());
        newBackDate.setTime(newBackDate.getTime() + THIRTY_DAYS_MILLISECONDS);
        return lendDao.renewLend(sernum, newBackDate) > 0;
    }

    public ArrayList<Lend> lendList() {
        return lendDao.lendList();
    }

    public ArrayList<Lend> myLendList(int readerId) {
        return lendDao.myLendList(readerId);
    }
}
