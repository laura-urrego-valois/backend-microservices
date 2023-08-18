package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogController {

    @Autowired
    private IMovieClient movieClient;

    @GetMapping("/catalog/{genre}")
    public ResponseEntity<List<Movie>> getCatalogByGenre(@PathVariable String genre) {

        return movieClient.getMoviesByGenre(genre);
    }

    @PostMapping("/api/v1/movies/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return movieClient.saveMovie(movie);
    }

}
