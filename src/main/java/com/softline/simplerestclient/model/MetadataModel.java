package com.softline.simplerestclient.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MetadataModel {
    private String URL;
    private String name;
    private String title;

    public MetadataModel(String URL, String name, String title) {
        this.URL = URL;
        this.name = name;
        this.title = title;
    }


}
