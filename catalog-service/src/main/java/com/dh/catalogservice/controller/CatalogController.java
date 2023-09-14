package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.queue.Listener;
import com.dh.catalogservice.service.CatalogService;
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

    @Autowired
    private CatalogService catalogService;

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
        return movieClient.saveMovie(movie);
    }

    @PostMapping("/record/movie")
    public ResponseEntity<Movie> guardarMovie(@RequestBody Movie movie) {
        log.info("Guardando película: " + movie);
        listener.receiveMovie(movie);
        return ResponseEntity.noContent().build();
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
        return serieClient.saveSerie(serie);
    }

    @PostMapping("/record/serie")
    public ResponseEntity<Serie> guardarSerie(@RequestBody Serie serie) {
        log.info("Guardando serie: " + serie);
        listener.receiveSerie(serie);
        return ResponseEntity.noContent().build();
    }

    /*---------------------------------------------------------------------------------*/

    @GetMapping("/catalog/{genre}")
    public ResponseEntity<Genre> getMoviesAndSeriesByGenre(@PathVariable String genre) {
        log.info("Solicitando desde el catálogo películas y series por género: " + genre);

        Genre genreInfo = catalogService.getMoviesAndSeries(genre);

        return new ResponseEntity<>(genreInfo, HttpStatus.OK);

    }

}
