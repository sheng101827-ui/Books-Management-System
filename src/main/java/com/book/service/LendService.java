package com.book.service;

import com.book.dao.LendDao;
import com.book.dao.ReaderCardDao;
import com.book.domain.Lend;
import com.book.domain.ReaderCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LendService {
    private LendDao lendDao;
    private ReaderCardDao readerCardDao;

    @Autowired
    public void setLendDao(LendDao lendDao) {
        this.lendDao = lendDao;
    }

    @Autowired
    public void setReaderCardDao(ReaderCardDao readerCardDao) {
        this.readerCardDao = readerCardDao;
    }

    public boolean bookReturn(long bookId){
        return lendDao.bookReturnOne(bookId)>0 && lendDao.bookReturnTwo(bookId)>0;
    }

    public boolean bookLend(long bookId,int readerId){
        ReaderCard readerCard = readerCardDao.findReaderByReaderId(readerId);
        if (readerCard.getCardState() == 0) {
            return lendDao.bookLendLostCard(readerId) > 0;
        }
        return lendDao.bookLendOne(bookId,readerId)>0 && lendDao.bookLendTwo(bookId)>0;
    }

    public ArrayList<Lend> lendList(){
        return lendDao.lendList();
    }
    public ArrayList<Lend> myLendList(int readerId){
        return lendDao.myLendList(readerId);
    }

}
