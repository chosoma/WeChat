package com.thingtek.gil.controller;


import com.thingtek.base.controller.BaseController;
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
public class GILController extends BaseController{

    @Resource
    private GILService service;


}
