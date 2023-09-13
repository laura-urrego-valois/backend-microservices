package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.queue.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    /*---------------------------------------------------------------------------------*/

    @GetMapping("/catalog/{genre}")
    public ResponseEntity<Map<String, List<?>>> getMoviesAndSeriesByGenre(@PathVariable String genre) {
        log.info("Solicitando películas y series por género: " + genre);

        ResponseEntity<List<Movie>> moviesResponse = movieClient.getMoviesByGenre(genre);
        ResponseEntity<List<Serie>> seriesResponse = serieClient.getSerieByGenre(genre);

        Map<String, List<?>> catalog = new HashMap<>();

        if (moviesResponse.getStatusCode().is2xxSuccessful()) {
            catalog.put("movies", moviesResponse.getBody());
        } else {
            catalog.put("movies", Collections.emptyList());
        }

        if (seriesResponse.getStatusCode().is2xxSuccessful()) {
            catalog.put("series", seriesResponse.getBody());
        } else {
            catalog.put("series", Collections.emptyList());
        }

        return ResponseEntity.ok(catalog);
    }


}
