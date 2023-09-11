package com.softline.simplerestclient.controller;

import com.softline.simplerestclient.service.AuthorizeService;
import com.softline.simplerestclient.service.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private final AuthorizeService authorizeService;
    private final Metadata metadata;

    @Autowired
    public MainController(AuthorizeService authorizeService, Metadata metadata) {
        this.authorizeService = authorizeService;
        this.metadata = metadata;
    }

    @RequestMapping("/index")
    public String mainPage(){
        authorizeService.getToken();
        return "index";
    }

    @GetMapping("/index/getLogs")
    public String getLogs(){
        metadata.downloadLogs();
        return "redirect:index";
    }
}
