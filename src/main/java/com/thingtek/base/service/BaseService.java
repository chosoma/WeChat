package com.thingtek.base.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;


@Transactional(isolation = Isolation.DEFAULT, readOnly = false, propagation = Propagation.REQUIRED)
public abstract class BaseService {

    @Resource
    private DataSourceTransactionManager txManager;


    protected final void logException(Exception e) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.error(e.toString());
    }


    /*
           事务
     */
    public void transaction() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);
        txManager.rollback(status);
//        txManager.commit(status);
    }


}
