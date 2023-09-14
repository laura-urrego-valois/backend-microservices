package com.example.serieservice.controller;

import com.example.serieservice.model.Serie;
import com.example.serieservice.queue.SerieSender;
import com.example.serieservice.service.SerieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author vaninagodoy
 */

@RestController
@RequestMapping("/api/v1/series")
public class SerieController {

    private static java.util.logging.Logger log = Logger.getLogger(SerieController.class.getName());

    private final SerieService serieService;
    private final SerieSender sender;

    public SerieController(SerieService serieService, SerieSender sender) {
        this.serieService = serieService;
        this.sender = sender;
    }

    @GetMapping
    public List<Serie> getAll() {
        log.info("Solicitando todas las series");
        return serieService.getAll();
    }

    @GetMapping("/findAll")
    public List<Serie> findAll(@RequestParam(defaultValue = "true") Boolean throwError) {
        return serieService.findAll(throwError);
    }

    @GetMapping("/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre) {
        log.info("Solicitando series por género: " + genre);
        return serieService.getSeriesBygGenre(genre);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Serie serie) {
        sender.sendMsg(serie);
        serieService.create(serie);
        return serie.getId();
    }
}
