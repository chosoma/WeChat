package com.thingtek.gil.controller;


import com.thingtek.gil.entity.NetBean;
import com.thingtek.gil.service.GILService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/sf6")
public class GILController {

    @Resource
    private GILService service;

    @RequestMapping("/findAllGateway.action")
    public List<NetBean> find(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("gil controller");
        return service.find();
    }

}
