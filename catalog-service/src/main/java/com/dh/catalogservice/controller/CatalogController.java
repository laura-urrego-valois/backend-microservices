package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.queue.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogController {

    @Autowired
    private IMovieClient movieClient;
    @Autowired
    private ISerieClient serieClient;

    private final Listener listener;

    public CatalogController(Listener listener) {
        this.listener = listener;
    }

    @GetMapping("/catalog/movie/{genre}")
    public ResponseEntity<List<Movie>> getCatalogByGenre(@PathVariable String genre) {

        return movieClient.getMoviesByGenre(genre);
    }

    @PostMapping("/catalog/movie/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        listener.receiveMovie(movie);
        return movieClient.saveMovie(movie);
    }

    /*---------------------------------------------------------------------------------*/

    @GetMapping("/catalog/serie/{genre}")
    public ResponseEntity<List<Serie>> getSeriesByGenre(@PathVariable String genre) {

        return serieClient.getSerieByGenre(genre);
    }

    @PostMapping("/catalog/serie/save")
    public ResponseEntity<Serie> saveSerie(@RequestBody Serie serie) {
        listener.receiveSerie(serie);
        return serieClient.saveSerie(serie);
    }

}
