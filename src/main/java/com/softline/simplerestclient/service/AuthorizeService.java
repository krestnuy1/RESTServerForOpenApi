package com.softline.simplerestclient.service;

import org.springframework.stereotype.Component;

@Component
public interface AuthorizeService {
    public String getToken();
}
