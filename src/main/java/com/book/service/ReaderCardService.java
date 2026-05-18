package com.book.service;

import com.book.dao.ReaderCardDao;
import com.book.domain.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderCardService {
    private ReaderCardDao readerCardDao;

    @Autowired
    public void setReaderCardDao(ReaderCardDao readerCardDao) {
        this.readerCardDao = readerCardDao;
    }
    public boolean addReaderCard(ReaderInfo readerInfo){
        return  readerCardDao.addReaderCard(readerInfo)>0;
    }
    public boolean updatePasswd(int readerId,String passwd){
        return readerCardDao.rePassword(readerId,passwd)>0;
    }
    public boolean updateName(int readerId,String name){
        return readerCardDao.updateName(readerId,name)>0;
    }

    public void lossCard(int readerId){
        com.book.domain.ReaderCard readerCard = readerCardDao.findReaderByReaderId(readerId);
        if (readerCard.getReaderId() == 0) {
            throw new RuntimeException("读者不存在，无法挂失读者卡！");
        }
        readerCardDao.lossCard(readerId);
    }
}
