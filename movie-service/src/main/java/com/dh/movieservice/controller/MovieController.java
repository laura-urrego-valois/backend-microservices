package com.dh.movieservice.controller;

import com.dh.movieservice.model.Movie;
import com.dh.movieservice.queue.MovieSender;
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

    private static java.util.logging.Logger log = Logger.getLogger(MovieController.class.getName());

    private final MovieService movieService;
    private final MovieSender sender;

    public MovieController(MovieService movieService, MovieSender sender) {
        this.movieService = movieService;
        this.sender = sender;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
        log.info("Solicitando pelicula por género: " + genre);
        return ResponseEntity.ok().body(movieService.findByGenre(genre));
    }

    @GetMapping("/findAll")
    public List<Movie> findAll(@RequestParam(defaultValue = "true") Boolean throwError) {
        return movieService.findAll(throwError);
    }

    @PostMapping("/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        log.info("Guardando pelicula: " + movie);
        sender.sendMsg(movie);
        return ResponseEntity.ok().body(movieService.save(movie));
    }
}
