package com.dh.catalogservice.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Genre {
    private List<Movie> movies;
    private List<Serie> series;

}
