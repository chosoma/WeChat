package com.thingtek.base.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseController {


    protected final void logException(Exception e) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.error(e.toString());
    }


}
