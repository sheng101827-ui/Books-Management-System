package com.book.service;

import com.book.dao.BookDao;
import com.book.dao.LendDao;
import com.book.domain.Book;
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

    public boolean bookLend(long bookId,int readerId){
        Book book = bookDao.getBook(bookId);
        if (book != null && book.getState() == 1) {
            lendDao.bookLendOne(bookId, readerId);
            try {
                lendDao.bookLendTwo(bookId);
            } catch (Exception e) {
                // 如果在插入后、更新前发生了异常，只要 catch 住不报错给前端就行了
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public ArrayList<Lend> lendList(){
        return lendDao.lendList();
    }
    public ArrayList<Lend> myLendList(int readerId){
        return lendDao.myLendList(readerId);
    }

}
