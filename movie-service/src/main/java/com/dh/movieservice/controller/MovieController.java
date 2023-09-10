package com.dh.movieservice.controller;

import com.dh.movieservice.model.Movie;
import com.dh.movieservice.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author vaninagodoy
 */

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;
    private static java.util.logging.Logger log = Logger.getLogger(MovieController.class.getName());

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
        log.info("Solicitando películas por género: " + genre);
        return ResponseEntity.ok().body(movieService.findByGenre(genre));
    }

    @GetMapping("/findAll")
    public List<Movie> findAll(@RequestParam(defaultValue = "true") Boolean throwError) {
        return movieService.findAll(throwError);
    }

    @PostMapping("/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        log.info("Guardando película: " + movie.getName());
        return ResponseEntity.ok().body(movieService.save(movie));
    }
}
