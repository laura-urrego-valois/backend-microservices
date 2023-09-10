package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class CatalogController {

    private static java.util.logging.Logger log = Logger.getLogger(CatalogController.class.getName());

    @Autowired
    private IMovieClient movieClient;

    @GetMapping("/catalog/{genre}")
    public ResponseEntity<List<Movie>> getCatalogByGenre(@PathVariable String genre) {
        log.info("Solicitando catálogo por género: " + genre);
        return movieClient.getMoviesByGenre(genre);
    }

    @PostMapping("/catalog/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        log.info("Guardando película: " + movie);
        return movieClient.saveMovie(movie);
    }

}
