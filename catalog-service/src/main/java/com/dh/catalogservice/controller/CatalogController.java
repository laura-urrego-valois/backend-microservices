package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.queue.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class CatalogController {

    private static java.util.logging.Logger log = Logger.getLogger(CatalogController.class.getName());

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
        log.info("Solicitando catálogo por género: " + genre);
        return movieClient.getMoviesByGenre(genre);
    }

    @PostMapping("/catalog/movie/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        log.info("Guardando película: " + movie);
        listener.receiveMovie(movie);
        return movieClient.saveMovie(movie);
    }

    /*---------------------------------------------------------------------------------*/

    @GetMapping("/catalog/serie/{genre}")
    public ResponseEntity<List<Serie>> getSeriesByGenre(@PathVariable String genre) {
        log.info("Solicitando serie por género: " + genre);
        return serieClient.getSerieByGenre(genre);
    }

    @PostMapping("/catalog/serie/save")
    public ResponseEntity<Serie> saveSerie(@RequestBody Serie serie) {
        log.info("Guardando serie: " + serie);
        listener.receiveSerie(serie);
        return serieClient.saveSerie(serie);
    }

}
