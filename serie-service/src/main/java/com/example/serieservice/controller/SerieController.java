package com.example.serieservice.controller;

import com.example.serieservice.model.Serie;
import com.example.serieservice.queue.SerieSender;
import com.example.serieservice.service.SerieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author vaninagodoy
 */

@RestController
@RequestMapping("/api/v1/series")
public class SerieController {

    private final SerieService serieService;
    private final SerieSender sender;

    public SerieController(SerieService serieService, SerieSender sender) {
        this.serieService = serieService;
        this.sender = sender;
    }

    @GetMapping
    public List<Serie> getAll() {
        return serieService.getAll();
    }

    @GetMapping("/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre) {
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
