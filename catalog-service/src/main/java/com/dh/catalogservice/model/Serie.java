package com.dh.catalogservice.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Data
@Document
@RequiredArgsConstructor
public class Serie {
    @MongoId
    private String id;

    private String name;

    private String genre;
}
