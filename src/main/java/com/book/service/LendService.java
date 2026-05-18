package com.book.service;

import com.book.dao.LendDao;
import com.book.dao.BookDao;
import com.book.domain.Lend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LendService {
    private LendDao lendDao;
    private BookDao bookDao;

    @Autowired
    public void setLendDao(LendDao lendDao) {
        this.lendDao = lendDao;
    }

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public boolean bookReturn(long bookId){
        return lendDao.bookReturnOne(bookId)>0 && lendDao.bookReturnTwo(bookId)>0;
    }

    public boolean lendBook(long bookId, int readerId){
        int state = bookDao.getBookState(bookId);
        if (state != 1) {
            return false;
        }
        if (lendDao.bookLendOne(bookId, readerId) <= 0) {
            return false;
        }
        try {
            lendDao.bookLendTwo(bookId);
        } catch (Exception e) {
        }
        return true;
    }

    public ArrayList<Lend> lendList(){
        return lendDao.lendList();
    }
    public ArrayList<Lend> myLendList(int readerId){
        return lendDao.myLendList(readerId);
    }

}