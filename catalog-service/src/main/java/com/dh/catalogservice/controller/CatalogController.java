package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CatalogController {

    @Autowired
    private IMovieClient movieClient;

    @GetMapping("/catalog/{genre}")
    public ResponseEntity<List<Movie>> getCatalogByGenre(@PathVariable String genre) {

        return movieClient.getMoviesByGenre(genre);
    }

}
